<%-- 
    Document   : mapstory
    Created on : Feb 2, 2017, 5:44:01 PM
    Author     : fotini
--%>
<%@page import="com.project1334.util.User"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page import="java.io.*,java.util.*,java.sql.*,javax.servlet.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>

<% Class.forName("com.mysql.jdbc.Driver"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> MAP </title>
    </head>
    <body>
        
        <% 
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/userdb", "root", "%fo2355");
            
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            ps = con.prepareStatement("select * from markers");
            rs = ps.executeQuery();
            
        List<String> RestaurantNames = new ArrayList<String>();
        List<String> foodOptions = new ArrayList<String>();
        List<String> descriptions = new ArrayList<String>();
        List<String> ratings = new ArrayList<String>();
        List<String> latArray = new ArrayList<String>();
        List<String> lngArray = new ArrayList<String>();
        %>
        
        
    </body>
</html>
