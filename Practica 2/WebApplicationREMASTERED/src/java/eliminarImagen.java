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
@WebServlet(name = "eliminarImagen", urlPatterns = {"/eliminarImagen"})
@MultipartConfig
public class eliminarImagen extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
      private final static Logger LOGGER = 
            Logger.getLogger(registrarImagen.class.getCanonicalName());
    private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
}
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection connection = null;
        OutputStream out2 = null;
        InputStream filecontent = null;
        
        HttpSession session = request.getSession();        
        if(session.getAttribute("user")==null) response.sendRedirect("login.jsp");
        try {
            /* TODO output your page here. You may use following sample code. */
            
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\adri\\Documents\\LABAD.db");
            
            //agafem l'id de la imatge passada per parametre
            int id= Integer.parseInt(request.getParameter("id"));
        
            //borrem imatge de la DB
            PreparedStatement statement= connection.prepareStatement("delete from imatges where id_imatge=?");
  
            statement.setInt(1,id);
            
            statement.executeUpdate();
            
                out.println("<html><body><link rel='stylesheet' href='css/styles.css'>"
                                + "<h2>L'imatge s'ha eliminat satisfactoriament</h2> "
                                + "<br>"
                                + "<a href='menu.jsp'>Tornar al menu</a>"
                                + "<br>"
                                + "</body></html>");
        }
        catch(java.lang.IllegalStateException e) {
            System.err.println(e.getMessage());
            response.sendRedirect("login.jsp");
        }
        catch(java.lang.NumberFormatException e) {
            System.err.println(e.getMessage());
            response.sendRedirect("login.jsp");
        }
        catch(SQLException e){
          System.err.println(e.getMessage());
          response.sendRedirect("error?tipus=tornamenu");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
         }

        finally
        {
          try
          {
            if (out2 != null) {
                out2.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (out != null) {
                out.close();
            }
            if(connection != null){
              connection.close();
            }
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
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
