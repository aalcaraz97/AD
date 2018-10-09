<%-- 
    Document   : menu
    Created on : 25-sep-2018, 18:01:54
    Author     : adri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
    </head>
    <link rel="stylesheet" href="css/styles.css">
    <body>
        
        <% if(session.getAttribute("user")==null) response.sendRedirect("login.jsp");  %>
        
        <h1>Menu Principal</h1>
        <br>
        <br>
        <br>
        <a href="registrarImagen.jsp"><h2>Registrar una imatge</h2></a>
        <br>
        <br>
        <a href="buscarImagen.jsp"><h2>Buscar una imatge</h2></a>
        <br>
        <br>
        <a href="list.jsp"><h2>Listar imatges</h2></a>
        <br>
        <br>
        <br>
        <br>
        <a href="logout.jsp"><h3>Tancar sessió</h3></a>
    </body>
</html>