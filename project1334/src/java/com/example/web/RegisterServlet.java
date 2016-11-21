/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.web;

import java.sql.*;  

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fotini
 */
public class RegisterServlet extends HttpServlet {

   
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        

        response.setContentType("text/html");  
        PrintWriter out = response.getWriter(); 
        
        String n=request.getParameter("userName");  
        String p=request.getParameter("userPass");  
        String e=request.getParameter("userEmail");  
        String c=request.getParameter("userCity"); 
        
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/1334db","","");  
  
            PreparedStatement ps=con.prepareStatement(  
            "insert into registeruser values(?,?,?,?)");  
  
            ps.setString(1,n);  
            ps.setString(2,p);  
            ps.setString(3,e);  
            ps.setString(4,c);  
          
            int i=ps.executeUpdate();  
            
            if(i>0)  
                out.print("You are successfully registered...");  
                
          
        }catch (Exception e2) {
            System.out.println(e2);  
        }
        
    out.close();  
    }  
        
}