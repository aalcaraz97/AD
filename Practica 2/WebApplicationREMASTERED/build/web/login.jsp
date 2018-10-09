<%-- 
    Document   : index
    Created on : 25-sep-2018, 17:46:32
    Author     : adri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <link rel="stylesheet" href="css/styles.css">
    <body>
        <h1>Benvinguts a la pagina </h1>
        <h3>Introduiu les dades per entrar</h3>
        <br>
        <form name="form1" action="/WebApplicationREMASTERED/login" method="post">
            <div>Username</div>
            <input type ="text" name="user" placeholder="Enter username" required>
            
            <div>Password</div>
            <input type ="password" name ="pass" placeholder ="Enter password" required>
            
            <input type ="submit" value="Login">
        </form>
        <br><br>
        <a href="crearUser.jsp">¿No tens un usuari? Registra't!</a>
    </body>
</html>