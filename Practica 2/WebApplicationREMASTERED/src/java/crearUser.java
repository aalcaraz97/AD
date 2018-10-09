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

/**
 *
 * @author adri
 */
@WebServlet(name = "crearUser", urlPatterns = {"/crearUser"})
@MultipartConfig
public class crearUser extends HttpServlet {

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
            
            String u = request.getParameter("user");
            String p = request.getParameter("pass");
            
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\adri\\Documents\\LABAD.db");
            
            //comprovem si el username ja existeix a la DB
            PreparedStatement statement = connection.prepareStatement("select * from usuaris where id_usuario = ?");
            statement.setString(1,u);
            
            ResultSet rs = statement.executeQuery();
            
            if(rs.next()) response.sendRedirect("error?tipus=userexists"); //si existeix el username redirecciona a error i no crea usuari
            
            else { //si no existeix username, s'afegeix el nou usuari a la DB
                
                PreparedStatement statement2 = connection.prepareStatement("insert into usuaris values(?,?)");
                statement2.setString(1,u);
                statement2.setString(2,p);
                
                statement2.executeUpdate();
                
                out.println("<html> <body> "
                    + "<link rel='stylesheet' href='css/styles.css'>"
                    + "<h2>L'usuari s'ha creat satisfactoriament!</h2> "
                    + "<br>"
                    + "<h3>Per entrar al sistema, fes login amb el teu nou user i password</h3> "
                    + "<a href='login.jsp'>Login</a>"
                    + "<br>"
                    + "</body></html>");
            }
            
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
