<%-- 
    Document   : registrarImagen
    Created on : 26-sep-2018, 14:56:48
    Author     : adri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar imagen</title>
    </head>
    <link rel="stylesheet" href="css/styles.css">
    <body>
        <% if(session.getAttribute("user")==null) response.sendRedirect("login.jsp");  %>
        
        <h1>Registrar una imatge</h1> <br>
        
        <form method ="post" action="/WebApplicationREMASTERED/registrarImagen" enctype="multipart/form-data">
            <div>Titol</div>
            <input type="text" name="titol" placeholder="Titol de la foto" required>
            
            <div>Descripcio</div>
            <input type ="text" name="descripcio" placeholder="Descripció breu de la foto" required>
            
            <div>Paraules clau</div>
            <input type="text" name="paraulesclau" placeholder="Paraules clau de la foto" required>
            
            <div>Autor</div>
            <input type ="text" name="author" placeholder="Autor de la foto" required>
            
            <div>Data creacio</div>
            <input type="date" name="creationdate" required>
            
            <div>Fitxer</div>
            <input type="file" name="imatge" required>
            <input type="submit" name="Submit" value="Upload">
            
                     
        </form>
        <br><br>
        
        <a href="menu.jsp"><h4>Tornar al menu!</h4></a>
        <br>
    </body>
</html>