package edu.colostate.cs.cs414.StringCheese.src;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;



public class User {

    private String name, email;
    private static Statement stmt;
    private static Connection conn;

    public User(String nickname){
        this.name = nickname;
    }
    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<User> listRegisteredUsers(){
        ArrayList<User> users= new ArrayList<>();
        try{
            conn = DBConnection.open();
            stmt = conn.createStatement();
            String query = "SELECT name, email FROM user WHERE name!='"+name+"' AND is_active=1";
            ResultSet rs = stmt.executeQuery(query);
            String name,email;
            while(rs.next()){
                users.add(new User(rs.getString("name"),rs.getString("email")));
            }
            DBConnection.close(conn);
        }catch(SQLException se){
            se.printStackTrace();
            System.exit(1);
        }
        return users;
    }
    public ArrayList<Game> listActiveGames(){
        // what do you want to display to user when displaying games
        // Pair<OpponentName, StartTime>
        ArrayList<Game> games = new ArrayList<>();
        int gameID, invitee, host;
        Game game = null;
        String startTime, endTime, result;
        SerializedGame sg = new SerializedGame();
        ResultSet rs = queryDatabase("SELECT * FROM game WHERE (host='"+name+"' OR invitee='"+name+"') AND (result is NULL AND invitee is NOT NULL)");
        ArrayList<Integer> gameids = new ArrayList<>();
        if(rs != null){
            try {
                while (rs.next()) {
                    gameids.add(rs.getInt("game_id"));
                }
                rs.close();
                for(int i: gameids){
                    System.out.println("Game Id: " +i);
                    game = sg.read(new DBConnection(),i);
                    games.add(game);
                }
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return games;
    }
    public boolean deactivate(){
        int numRecordsAffected =0;
        try {
            conn = DBConnection.open();
            stmt = conn.createStatement();
            String query = "UPDATE user SET is_active=0 WHERE name='"+name+"'";
            numRecordsAffected = stmt.executeUpdate(query);
        }catch(SQLException se){
            se.printStackTrace();
           // System.exit(1);
        }
        return numRecordsAffected==1;
    }
    public static boolean login(String name, String password){
        return authenticate(name,password);
    }
    //fixme remove null values after testing
    private static boolean authenticate(String name, String password){
        if(name ==null || password==null || name.length()<5 || password.length()<5 ){
            System.out.println("Check username and password and try again.");
            return false;
        }
        try {
            conn = DBConnection.open();
            stmt = conn.createStatement();
            String query = "SELECT password, salt FROM user WHERE name='" + name+"'";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                byte[] hashedPass = rs.getBytes("password");
                byte[] salt = rs.getBytes("salt");
                //fixme should never be null, .sql should have constraint not null, only for testing
                if(hashedPass==null || salt==null){return true;}
                byte[] encryptedAttemptedPassword = getEncryptedPassword(password, salt);
                boolean isEqual = Arrays.equals(encryptedAttemptedPassword, hashedPass);
                DBConnection.close(conn);
                return isEqual;
            }
            else{
                //System.out.println("No Results Found");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean registerUser(String name, String email, String password){
        //email syntax check
        EmailValidator emailValidator = new EmailValidator();
        boolean isValidEmail = emailValidator.validateEmail(email);
        //validate name and password length
        if(isValidEmail && name.length()>=5 && password.length()>=5) {
            try {
                conn = DBConnection.open();
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user (name, email, password, salt ) VALUES (?,?,?,?)");
                byte[] salt = generateSalt();
                byte[] hashedPassword = getEncryptedPassword(password, salt);
                String lowCaseName = name.toLowerCase();
                pstmt.setString(1,lowCaseName);
                pstmt.setString(2,email);
                ByteArrayInputStream bInput = new ByteArrayInputStream(hashedPassword);
                pstmt.setBinaryStream(3, bInput);
                bInput.close();
                ByteArrayInputStream bInput2 = new ByteArrayInputStream(salt);
                pstmt.setBinaryStream(4, bInput2);
                bInput2.close();

                try {
                    int numRecordsInserted = pstmt.executeUpdate();
                    //System.out.println(numRecordsInserted);
                    DBConnection.close(conn);
                    //validate database has new user
                    return numRecordsInserted==1;
                }catch(SQLIntegrityConstraintViolationException e){
                    System.out.println("This name or email is already registered, try again");
                    return false;
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }else{
            System.out.println("Something went wrong");
            System.out.println("Check email is valid. Nickname and Password length must be at least 5 characters");
        }
        return false;
    }
    //takes a SQL statement, queries DB and returns resultset or null
    private ResultSet queryDatabase(String query){
        ResultSet rs = null;
        try{
            conn = DBConnection.open();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            //DBConnection.close(conn);
        }catch(SQLException se){
            se.printStackTrace();
           // System.exit(1);
        }
        return rs;
    }
    //validate database has new user
    //FIXME: has bugs BUT NOT SURE IF WE EVEN NEED THIS METHOD
    private static boolean isInDatabase(String name) {
        try {
            conn = DBConnection.open();
            stmt = conn.createStatement();
            String query = "SELECT COUNT(*) FROM (SELECT * FROM user WHERE name='"+name.toLowerCase()+"') AS T";
            ResultSet rs = stmt.executeQuery(query);
            int size = rs.getInt(1);
            DBConnection.close(conn);
            return size==1;
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }
    private static byte[] getEncryptedPassword(String password, byte[] salt) {
        // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
        // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
        String algorithm = "PBKDF2WithHmacSHA1";
        // SHA-1 generates 160 bit hashes, so that's what makes sense here
        int derivedKeyLength = 160;
        // The NIST recommends at least 1,000 iterations:
        // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
        int iterations = 20000;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
        SecretKeyFactory f = null;
        try {
            f = SecretKeyFactory.getInstance(algorithm);
            if (f==null) {System.exit(1);}
            return f.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return new byte[0];
    }
    private static byte[] generateSalt()  {
        // VERY important to use SecureRandom instead of just Random
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return salt;
    }

    public static void main(String args[]) throws IOException, SQLException {
        User user = new User("chris");
        User user1 = new User("john");

       /*
        // Game game = new Game(1,"chris","john","now",null,"UNFINISHED");
        Game game1 = new Game(2,"john","chris","now",null,"UNFINISHED");
      //  Game game2 = new Game(1,"chris","john","now",null,"UNFINISHED");
        SerializedGame sg = new SerializedGame();
        DBConnection dconn= new DBConnection();
        sg.write(dconn,game1);


        user.listActiveGames();
        */
       // user1.listRegisteredUsers();
       System.out.println(user.listRegisteredUsers());
       ArrayList<User> users = user1.listRegisteredUsers();
       for(User u:users){
           System.out.println(u.getName());
       }
       //user.deactivate();
    }
}