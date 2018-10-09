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
        <title>Llistar imatges</title>
    </head>
    <link rel="stylesheet" href="css/styles.css">
    <body>
        
        <% if(session.getAttribute("user")==null) response.sendRedirect("login.jsp");  %>
        
     <h1>Llistat d'imatges</h1> 
        <%
            Connection connection = null;
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\adri\\Documents\\LABAD.db");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select nom, autor, id_imatge from imatges"); //necessitem id, nom i autor de les imatges a la DB
                %>
                            <table style="width:60%">
                            <tr>
                            <th>Nom imatge</th>
                            <th>Visualitzar imatge</th>
                            <th>Modificar</th>
                            <th>Eliminar imatge</th>
                            </tr>
                <%            
                while(rs.next()){
                    String aut = rs.getString("autor");
                    int id = rs.getInt("id_imatge");
                    session = request.getSession();
                    String ola = (String)session.getAttribute("user");  
                    
                    if(ola.equals(aut)) { //comprovacio si user es autor foto
                        out.println("<html><body>"
                        + "<tr>"
                        + "<td style='text-align:center;'>" + rs.getString("nom") + "</td>"
                        + "<td style='text-align:center;'> <a href='.\\images\\" + rs.getString("nom") + "'>Veure imatge</a> </td>"
                        + "<td style='text-align:center;'> <a href='.\\modificarImagen.jsp?id=" + id + "'>Modificar imatge</a> </td>"
                        + "<td style='text-align:center;'> <a href='.\\eliminarImagen?id=" + id + "'>Eliminar imatge</a> </td>");
                        } 
                    else { //si no es l'autor, no pot eliminar ni modificar foto
                        out.println("<html><body>"
                        + "<tr>"
                        + "<td style='text-align:center;'>" + rs.getString("nom") + "</td>"
                        + "<td style='text-align:center;'> <a href='.\\images\\" + rs.getString("nom") + "'>Veure imatge</a> </td>"
                        + "<td style='text-align:center;'> No ets el propietari </td>"
                        + "<td style='text-align:center;'> No ets el propietari </td>");
                    }
                } 
            }
    catch(java.lang.NullPointerException e){
        System.err.println(e.getMessage());
            response.sendRedirect("login.jsp");
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
            
            </table>
            <br><br>
            <a href='menu.jsp'><h4>Tornar a menu!</h4></a>      
        </body>
</html>
           
