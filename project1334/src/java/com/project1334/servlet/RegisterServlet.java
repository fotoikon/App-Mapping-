/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project1334.servlet;


import java.sql.*;  

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static jdk.nashorn.internal.runtime.Debug.id;

import org.apache.log4j.Logger;
/**
 *
 * @author fotini
 */
public class RegisterServlet extends HttpServlet {

    //Connection conn=null;
    //PreparedStatement pst=null;
    
    private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(RegisterServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
                // JDBC driver name and database URL
                String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
                String DB_URL = "jdbc:mysql://localhost:3306/userdb";
                //  Database credentials
                String USER = "root";
                String PASS = "123!@#";
            
                Connection conn;
                
                String email = request.getParameter("uemail");
		String password = request.getParameter("upass");
		String name = request.getParameter("uname");
		String password2 = request.getParameter("upass2");
		String errorMsg = null;
		
		if(email == null || email.equals("")){
			errorMsg = "Email ID can't be null or empty.";
		}
		if(password == null || password.equals("") || !password.equals(password2)){
			errorMsg = "Password can't be null or empty or different";
		}
		if(name == null || name.equals("")){
			errorMsg = "Name can't be null or empty.";
		}
		
		
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{
		
                //P conn = SQLConnect.ConnectDB();
		//Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		try {
                        conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    
			ps = conn.prepareStatement("insert into Users(id,name,email,password) values (?,?,?,?)");
			ps.setString(1, null);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, password);
			
			ps.execute();
			
			logger.info("User registered with email="+email);
			
			//forward to login page to login
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=green>Registration successful, please login below.</font>");
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