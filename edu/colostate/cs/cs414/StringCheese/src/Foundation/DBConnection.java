package edu.colostate.cs.cs414.StringCheese.src.Foundation;

import edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.User;

import java.sql.*;
import java.util.*;


public class DBConnection {
    static Connection con;

    public static Connection open(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rollerball","root","");
            //here rollerball is database name, root is username and password stringcheese
            con=DriverManager.getConnection(
                    "jdbc:mysql://rollerball.c16s0sml8won.us-east-1.rds.amazonaws.com:3306/rollerball",
                    "root", "stringcheese");
            /*
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from emp");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
            */
        }catch(Exception e){
           // System.out.println(e);
            //System.exit(1);
            e.printStackTrace();
        }
        return con;
    }

    public static boolean close(Connection connection){
        try {
            if (connection != null) {
                connection.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

