package com.project1334.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.uthldap.Uthldap;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import com.project1334.util.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author fotini
 */
public class LogInServlet extends HttpServlet {
    
    //Connection conn=null;
    //PreparedStatement pst=null;
    
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(LogInServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                // JDBC driver name and database URL
                String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
                String DB_URL = "jdbc:mysql://localhost:3306/userdb";
                //  Database credentials
                String USER = "root";
                String PASS = "123!@#";
        
                Connection conn;
		
                String name = request.getParameter("uname");
		String password = request.getParameter("upass");
		String errorMsg = null;
                
		if(name == null || name.equals("")){
			errorMsg ="User Name can't be null or empty";
		
                }
		if(password == null || password.equals("")){
			errorMsg = "Password can't be null or empty";
		}
		
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{
		
                //P conn = SQLConnect.ConnectDB();    
                    
		//Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                
		
                PreparedStatement ps = null;
		ResultSet rs = null;
                
		try {
                        conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    
			ps = conn.prepareStatement("select id, name, email, password from Users where name=? and password=? limit 1");
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs != null){
				rs.next();
				User user = new User(rs.getString("name"), rs.getString("email"), rs.getInt("id"));
				logger.info("User found with details="+user);
				HttpSession session = request.getSession();
				session.setAttribute("User", user);
				response.sendRedirect("story.jsp");; //home.jsp h test.jsp(template)
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
				PrintWriter out= response.getWriter();
				logger.error("User not found with name="+name);
				out.println("<font color=red>No user found with given email id, please register first.</font>");
				rd.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement or ResultSet");;
			}
			
		}
		}
	}


}