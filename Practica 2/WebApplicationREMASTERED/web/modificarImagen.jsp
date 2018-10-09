<%-- 
    Document   : buscarImagen
    Created on : 26-sep-2018, 14:57:36
    Author     : adri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.sql.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Busca una imagen</title>
    </head>
    <link rel="stylesheet" href="css/styles.css">
    <body>
        
        <% if(session.getAttribute("user")==null) response.sendRedirect("login.jsp");  %>
        
        <h1>Modifica la imatge</h1>
        <br>
        <h2>Escull els camps que vols modificar de la imatge</h2>
        
        <%
            Connection connection = null;
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\adri\\Documents\\LABAD.db");
                int id = Integer.parseInt(request.getParameter("id"));               
                String t,d,p,a;
                String sql = "select titol_imatge, descripcio, paraula_clau, autor "
                        + "from imatges where id_imatge = "; //agafem els camps de la imatge per a mostrar els valors al formulari
                sql = sql + id;
 
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                
                if(rs.next()){ //si trobem els camps al select, els mostrem al formulari
                t=rs.getString("titol_imatge");
                d=rs.getString("descripcio");
                p=rs.getString("paraula_clau");
                a=rs.getString("autor");
               %>
 
        <form method ="post" action="/WebApplicationREMASTERED/modificarImagen?id=<%=id%>" enctype="multipart/form-data">
            <div>Titol</div>
            <input type="text" name="titol" placeholder="Titol de la foto" value="<%=t%>">
            
            <div>Descripcio</div>
            <input type ="text" name="descripcio" placeholder="Descripció breu de la foto" value="<%=d%>">
            
            <div>Paraula clau</div>
            <input type="text" name="paraulesclau" placeholder="Paraules clau de la foto" value="<%=p%>">
            
            <div>Autor</div>
            <input type ="text" name="author" placeholder="Autor de la foto" value="<%=a%>" required>

            <input type ="submit" value="Modifica!">
                     
        </form>
        
        <br><br>
        
        <a href="menu.jsp"><h4>Tornar al menu!</h4></a>
        <%
           } 

}
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }   
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }
        %>
    </body>
</html>