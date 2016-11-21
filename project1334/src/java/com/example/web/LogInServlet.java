package com.example.web;

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

/**
 *
 * @author fotini
 */
public class LogInServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        SessionListener numSession=new SessionListener();
        
        PrintWriter out = response.getWriter();
        String uname = request.getParameter("uname");
        String upass = request.getParameter("upass");
        
        Uthldap auth=new Uthldap(uname,upass);
        
        List info=new ArrayList();
        
        if(auth.auth()){
            int num = numSession.getAllSession();
            request.setAttribute("all_sessions",num );

            HttpSession session =request.getSession();
            session.setAttribute("user",uname);
            
            info.add(auth.getName());
            info.add(auth.getBirthYear());
            info.add(auth.getMail());
            
            //request.setAttribute("info",info);
            
            RequestDispatcher view = request.getRequestDispatcher("result.jsp");
            view.forward(request,response);
        }
        
        
        
        
        //if username and password are wrong
        else {
            out.println("wrong username or password");
        }
        /*try  {
            if(uname.equalsIgnoreCase("admin") && upass.equalsIgnoreCase("admin")){
                HttpSession session =request.getSession();
                session.setAttribute("user",uname);
                RequestDispatcher view = request.getRequestDispatcher("result.jsp");
                view.forward(request,response);
               
            }else{
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.forward(request,response);
            }
        } finally{
            out.close();
        }*/
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
