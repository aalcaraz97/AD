<%-- 
    Document   : crearUser
    Created on : 04-oct-2018, 17:56:07
    Author     : adri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creacio usuari</title>
    </head>
    <link rel="stylesheet" href="css/styles.css">
    <body>
        <h2>Crear un usuari</h2>
        <h3>Introdueix el teu username i password per poder crear el teu usuari</h3>
        <br>
        
        <form name="form2" action="/WebApplicationREMASTERED/crearUser" method="post">
            <div>Username</div>
            <input type ="text" name="user" placeholder="Enter your new username" required>
            
            <div>Password</div>
            <input type ="password" name ="pass" placeholder ="Enter your new password" required>
            
            <input type ="submit" value="Crea usuari!">
        </form>
        <br>
        <br>
        <a href="login.jsp">Back</a>
    </body>
</html>
