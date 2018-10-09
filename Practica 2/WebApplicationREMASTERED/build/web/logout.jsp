<%-- 
    Document   : logout
    Created on : 25-sep-2018, 18:02:14
    Author     : adri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <link rel="stylesheet" href="css/styles.css">
    <body>
        <% session.invalidate(); %>
        
        <h1>Sessió finalitzada!</h1>
        <a href="login.jsp"> Tornar a login </a>
        <br><br>
        </body>
</html>