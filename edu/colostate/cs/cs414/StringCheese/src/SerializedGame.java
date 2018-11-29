package edu.colostate.cs.cs414.StringCheese.src;

import java.io.*;
import java.sql.*;

public class SerializedGame {

    public void write(Connection con, Game tmp) throws IOException, SQLException {
        try {
            byte[] data = null;
            ByteArrayOutputStream bt = new ByteArrayOutputStream();
            ObjectOutputStream obj = new ObjectOutputStream(bt);
            obj.writeObject(tmp);
            obj.flush();
            obj.close();
            obj.reset();
            bt.close();
            data = bt.toByteArray();
            System.out.println("--------------"+data);

            if (!con.isClosed()) {
                PreparedStatement ptst = con.prepareStatement("SELECT * from game where G_ID=?");// For meet local db instance
                // PreparedStatement ptst = con.prepareStatement("insert into gameSerialized values(?,?)");//for amazon aws
                ptst.setInt(1, tmp.getGameID());
                ResultSet rs = ptst.executeQuery();
                if (!rs.next()) {
                    ptst = con.prepareStatement("insert into game values(?,?)");
                    ptst.setInt(1, tmp.getGameID());
                    ptst.setBytes(2, data);
                    ptst.executeUpdate();
                    System.out.println("+++++inserted++++++");
                } else {
                    ptst = con.prepareStatement("update game SET G_object=? where G_ID=?");
                    ptst.setBytes(1,data);
                    ptst.setInt(2, tmp.getGameID());

                    System.out.println("+++++update+++++");
                }
                rs.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public  Game read(Connection con, int id) {

        Game meet = null;
        try {
            if (!con.isClosed()) {
                PreparedStatement ptst = con.prepareStatement("select G_object from game where G_id=?");//For meet LOcal db Instance
              //  PreparedStatement ptst = con.prepareStatement("select game_object from gameSerialized where game_id=?");//for aws Web Service
                ptst.setInt(1, id);
                ResultSet rs = ptst.executeQuery();
                rs.next();
                byte[] t = rs.getBytes(1);
                System.out.println(t);
                if (t != null)
                {
                        ByteArrayInputStream targetStream = new ByteArrayInputStream(t);
                        ObjectInputStream in = new ObjectInputStream(targetStream);
                        meet = (Game) in.readObject();
                        // in.reset();
                        in.close();

                        targetStream.close();

                }
                rs.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return meet;
    }

    public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException {

        Class.forName("com.mysql.cj.jdbc.Driver");
      /* Connection  con=DriverManager.getConnection(
                "jdbc:mysql://rollerball.c16s0sml8won.us-east-1.rds.amazonaws.com:3306/rollerball",
                "root", "stringcheese");*/
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phpmyadmin", "root", "");
        Game m = new Game(1, "chris", "Meet", "1-nov", null, null);
       // System.out.println(m.getHost());
       /* Game chris = new Game(2, "chris", "chris", "2-nov", null, null);
        System.out.println(chris.getHost());
        Game Tim= new Game(3, "tim", "meet", "3-nov", null, null);
        System.out.println(Tim.getHost());
        Game sirius= new Game(4, "sirius", "meet", "4-nov", null, null);
        System.out.println(sirius.getHost());*/
        SerializedGame s=new SerializedGame();
        s.write(con,m);
      //  s.write(con,chris);
       // s.write(con,Tim);
        //s.write(con,sirius);

      Game md=s.read(con,m.getGameID());
    //    Game c=s.read(con,chris.getGameID());
      //  Game t=s.read(con,Tim.getGameID());
        con.close();
        /*
        System.out.println(" Deseralized Object  for meet ");
        System.out.println("Game id : " + md.getGameID());
        System.out.println("Game host : " + md.getHost());
        System.out.println("Game invitee : " + md.getInvitee());
        System.out.println("Game Start time : " + md.getStartTime());
        System.out.println("Game end Time : " + md.getEndTime());
        System.out.println("Game Result : " + md.getResult());
        System.out.println("--------------------------------");
   /*     System.out.println(" Deseralized Object for chris ");
        System.out.println("Game id : " + c.getGameID());
        System.out.println("Game host : " + c.getHost());
        System.out.println("Game invitee : " + c.getInvitee());
        System.out.println("Game Start time : " + c.getStartTime());
        System.out.println("Game end Time : " + c.getEndTime());
        System.out.println("Game Result : " + c.getResult());
        System.out.println("--------------------------------");
        System.out.println(" Deseralized Object ");
        System.out.println("Game id : " + t.getGameID());
        System.out.println("Game host : " + t.getHost());
        System.out.println("Game invitee : " + t.getInvitee());
        System.out.println("Game Start time : " + t.getStartTime());
        System.out.println("Game end Time : " + t.getEndTime());
        System.out.println("Game Result : " + t.getResult());*/







    }
}

          /*  byte[] data=null;
            ByteArrayOutputStream bt = new ByteArrayOutputStream();
            ObjectOutputStream obj = new ObjectOutputStream(bt);
            obj.writeObject(temp);
            obj.flush();
            obj.close();
            bt.close();
            data = bt.toByteArray();
          //  System.out.println(data);
            System.out.println(data.toString());


        /*
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(temp);
        String str=out.toString();


        System.out.println("Serialized Object: " + str);
        System.out.println("Serialized Object: *******\n" + out);

        FileInputStream f1 = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(f1);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phpmyadmin", "root", "");
            if(!con.isClosed())
            {
                PreparedStatement ptst=con.prepareStatement("insert into game values(?,?)");
                ptst.setInt(1,10);
                ptst.setObject(2,data);
              ptst.executeUpdate();
            }
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phpmyadmin", "root", "");
            if(!con.isClosed())
            {
                PreparedStatement ptst=con.prepareStatement("select G_object from game where G_id=10");
                ResultSet rs=ptst.executeQuery();
                rs.next();
                byte [] t=rs.getBytes(1);

                InputStream targetStream = new ByteArrayInputStream(t);
                ObjectInputStream in=new ObjectInputStream(targetStream);
                Game meet=(Game)in.readObject();

                    System.out.println(" Deseralized Object ");
                    System.out.println("Game id : " + meet.getGameID());
                    System.out.println("Game host : " + meet.getHost());
                    System.out.println("Game invitee : " + meet.getInvitee());
                    System.out.println("Game Start time : " + meet.getStartTime());
                    System.out.println("Game end Time : " + meet.getEndTime());
                    System.out.println("Game Result : " + meet.getResult());
                    rs.close();






            }
            con.close();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        /*
        Game meet = null;
        meet = (Game) in.readObject();
        System.out.println(" Deseralized Object ");
        System.out.println("Game id : " + meet.getGameID());
        System.out.println("Game host : " + meet.getHost());
        System.out.println("Game invitee : " + meet.getInvitee());
        System.out.println("Game Start time : " + meet.getStartTime());
        System.out.println("Game end Time : " + meet.getEndTime());
        System.out.println("Game Result : " + meet.getResult());*/





