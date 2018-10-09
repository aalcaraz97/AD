<%-- 
    Document   : buscarImagen
    Created on : 26-sep-2018, 14:57:36
    Author     : adri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Busca una imagen</title>
    </head>
    <link rel="stylesheet" href="css/styles.css">
    <body>
        
        <% if(session.getAttribute("user")==null) response.sendRedirect("login.jsp");  %>
        
        <h1>Busca una imatge ja registrada</h1>
        <br><br>
        <h2>Escull els camps de cerca de la imatge</h2>
        <form method ="post" action="/WebApplicationREMASTERED/buscarImagen" enctype="multipart/form-data">
            <div>Titol</div>
            <input type="text" name="titol" placeholder="Titol de la foto">
            
            <div>Descripcio</div>
            <input type ="text" name="descripcio" placeholder="Descripció breu de la foto">
            
            <div>Paraula clau</div>
            <input type="text" name="paraulesclau" placeholder="Paraules clau de la foto">
            
            <div>Autor</div>
            <input type ="text" name="author" placeholder="Autor de la foto">
            
            <div>Nom del fitxer<div>
            <input type ="text" name="fitxer" placeholder="Nom">        
            
            <input type ="submit" value="Busca!">
                     
        </form>
        
        <br><br>
        
        <a href="menu.jsp"><h4>Tornar al menu!</h4></a>
        
    </body>
</html>