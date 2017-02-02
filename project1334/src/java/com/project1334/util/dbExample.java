package com.project1334.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;

/**
 *
 * @author fotini
 */
public class dbExample {
    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
   
   
    public static void main(String[] args) {
   
    Connection conn = null;
    Statement stmt = null;
    
    try{
        
        Class.forName("com.mysql.jdbc.Driver");

        // Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        // Execute a query
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT id, name, email, password FROM users";
        ResultSet rs = stmt.executeQuery(sql);

        // Extract data from result set
        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");
            String age = rs.getString("name");
            String first = rs.getString("email");
            //String last = rs.getString("last");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", Name: " + age);
            System.out.print(", email: " + first);
            //System.out.println(", Last: " + last);
        }
      
        // Clean-up environment
        rs.close();
        stmt.close();
        conn.close();
    }catch(SQLException se){
        //Handle errors for JDBC
        se.printStackTrace();
    }catch(Exception e){
        //Handle errors for Class.forName
        e.printStackTrace();
    }finally{
        //finally block used to close resources
        try{
            if(stmt!=null)
            stmt.close();
        }catch(SQLException se2){
        }// nothing we can do
        try{
            if(conn!=null)
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    
    System.out.println("Goodbye!");
    
    }

}
