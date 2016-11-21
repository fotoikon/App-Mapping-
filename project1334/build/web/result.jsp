<%-- 
    Document   : result
    Created on : 12 ??? 2016, 3:13:18 ??
    Author     : fotini
--%>

<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <body>
        <h1 align="center">Map-ping Welcome</h1>
        
        <%

            out.print("hello "+session.getAttribute("user"));
                     
        %>
        
        <%
            int sessions=(Integer)request.getAttribute("all_sessions");
            out.println(" The number of sessions are "+sessions+" ");
        %>
    </body>
</html>