/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project1334.servlet;

import java.sql.*; 

import static com.project1334.servlet.RegisterServlet.logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/**
 *
 * @author fotini
 */
public class StoryServlet extends HttpServlet {


    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        String DB_URL = "jdbc:mysql://localhost:3306/userdb";
        //  Database credentials
        String USER = "root";
        String PASS = "123!@#";
        
        Connection conn;
            
        String place = "1";
        String text = "2";
        String title = request.getParameter("title");
        
        
        String lat = request.getParameter("lat");
        String lon = request.getParameter("lon");
        String errorMsg = null;
        
        
    
        if (lat.equals("")) {
                errorMsg = "PLease select a marker on the map.";
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/story.jsp");
                PrintWriter out= response.getWriter();
                out.println("<font color=red size=\"4\">"+errorMsg+"</font>");
                rd.include(request, response);
        }
        else {
    
                PreparedStatement ps = null;
		
                try {
                        conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    
			ps = conn.prepareStatement("insert into markers(id,title,place,text,lat,lon) values (?,?,?,?,?,?)");
			ps.setString(1, null);
			ps.setString(2, title);
			ps.setString(3, place);
			ps.setString(4, text);
                        ps.setFloat(5, Float.parseFloat(lat));
                        ps.setFloat(6, Float.parseFloat(lon));
			
			ps.execute();
			
			//forward to 
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
			PrintWriter out= response.getWriter();
			rd.include(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement");
			}
		}
		}        
            
            
        }


}
