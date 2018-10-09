
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.lang.Object; 
import javax.servlet.http.Part;
import java.util.Enumeration;
import javax.servlet.ServletContext;
/**
 *
 * @author adri
 */
@WebServlet(name = "buscarImagen", urlPatterns = {"/buscarImagen"})
@MultipartConfig
public class buscarImagen extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        Connection connection = null;
        try {
            /* TODO output your page here. You may use following sample code. */
                       
            Class.forName("org.sqlite.JDBC");
            
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\adri\\Documents\\LABAD.db");
            
            String peticion="select * from imatges where 1=1";
            
            //agafem els parametres del formulari 
            String t = request.getParameter("titol");
            String d = request.getParameter("descripcio");
            String p = request.getParameter("paraulesclau");
            String a = request.getParameter("author");
            String n = request.getParameter("fitxer");
           
           //omplim la consulta sql depenent de quins camps hagi omplert l'usuari al formulari
            boolean b1,b2,b3,b4,b5;
            b1 = b2 = b3 = b4 = b5 = false;
            int cont = 0;         
            if(!t.isEmpty()){
                peticion += " and titol_imatge = ?";
                b1 = true; 
            }
            if(!d.isEmpty()){
                peticion += " and descripcio = ?";
                b2 = true; 
                }
            if(!p.isEmpty()){
                peticion += " and paraula_clau = ?";
                b3 = true; 
                    }
            if(!a.isEmpty()){
                peticion += " and autor = ?";
                b4 = true; 
                        }
            if(!n.isEmpty()){
                 peticion += " and nom = ?";
                 b5 = true; 
                            }
            PreparedStatement statement = connection.prepareStatement(peticion);
            
           
           if(b1){
              ++cont;
               statement.setString(cont,t); 
           }
           if(b2){
               ++cont;
                statement.setString(cont,d);
           }
           if(b3){
              ++cont;
               statement.setString(cont,p); 
           }
           if(b4){
               ++cont;
                statement.setString(cont,a);
           }
           if(b5){
              ++cont;
               statement.setString(cont,n); 
           }
          
            ResultSet rs = statement.executeQuery();
            
           if(rs.isAfterLast()) response.sendRedirect("error?tipus=Notrobat"); //si no troba cap imatge, redirecciona error
           else{
                out.println("<html><body>"
                        + "<link rel='stylesheet' href='css/styles.css'>"
                        + "<h1 style='text-align:center;'> LA MEVA CERCA </h1>"
                            + "<table style='width:100%;text-align:center;'>"
                            + "<tr>"
                            + "<th>Nom imatge</th>"
                            + "<th>Visualitzar imatge</th>"
                            + "<th>Modificar</th>"
                            + "<th>Eliminar imatge</th>"
                            + "</tr>");

                while(rs.next()){ //mostra imatges trobades
                    String aut = rs.getString("autor");
                    int id = rs.getInt("id_imatge");
                    HttpSession session = request.getSession();
                    String ola = (String)session.getAttribute("user");                 
                    if(ola.equals(aut)) {
                        out.println("<html><body>"
                        + "<tr>"
                        + "<td style='text-align:center;'>" + rs.getString("nom") + "</td>"
                        + "<td style='text-align:center;'> <a href='.\\images\\" + rs.getString("nom") + "'>Veure imatge</a> </td>"
                        + "<td style='text-align:center;'> <a href='.\\modificarImagen.jsp?id=" + id + "'>Modificar imatge</a> </td>"
                        + "<td style='text-align:center;'> <a href='.\\eliminarImagen?id=" + id + "'>Eliminar imatge</a> </td>");
                        } 
                    else {
                        out.println("<html><body>"
                        + "<tr>"
                        + "<td style='text-align:center;'>" + rs.getString("nom") + "</td>"
                        + "<td style='text-align:center;'> <a href='.\\images\\" + rs.getString("nom") + "'>Veure imatge</a> </td>"
                        + "<td style='text-align:center;'> No ets el propietari </td>"
                        + "<td style='text-align:center;'> No ets el propietari </td>");
                    }
                }
            }
           
         out.println("<html> <body>"
                 + "</table>"
                 + "<br><br>"
                 + "<a href='menu.jsp'><h4>Tornar a menu!</h4></a>"
                 + "</body> </html>");
         
                 }
        catch(java.lang.NullPointerException e){
        System.err.println(e.getMessage());
            response.sendRedirect("login.jsp");
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
          out.println("hola");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            out.println("hola");
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
            // connection close failed
            System.err.println(e.getMessage());
            out.println("hola");
          }
        }
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