import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author adri
 */
@WebServlet(name = "error", urlPatterns = {"/error"})
public class error extends HttpServlet {

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
        
        if(request.getParameter("tipus").equals("loginfail")) { //error en el login: username i/o password son incorrectes
            
            out.println("<html> <body>"
                    + "<link rel='stylesheet' href='css/styles.css'>"
                    + "<h2>Error: L'usuari o contrasenya introduits son incorrectes</h2>"
                    + "<br>"
                    + "<a href='login.jsp'>Tornar a login</a>"
                    + "<br>"
                    + "</body></html>");
        }
        
        else if(request.getParameter("tipus").equals("tornamenu")) { //error general
            
            out.println("<html> <body>"
                    + "<link rel='stylesheet' href='css/styles.css'>"
                    + "<h2>Hi ha hagut un error!</h2>"
                    + "<br>"
                    + "<a href='menu.jsp'>Tornar al menu</a>"
                    + "<br>"
                    + "</body></html>");
        }
        
        else if(request.getParameter("tipus").equals("Notrobat")) { //la cerca de imatges no obte cap resultat
            
            out.println("<html> <body>"
                    + "<link rel='stylesheet' href='css/styles.css'>"
                    + "<h2>La teva cerca no ha obtingut ningun resultat</h2>"
                    + "<br>"
                    + "<a href='buscarImagen.jsp'>Torna a fer una cerca</a>"
                    + "<br>"
                    + "<a href='menu.jsp'>Tornar al menu</a>"
                    + "<br>"
                    + "</body></html>");
        }
        else if(request.getParameter("tipus").equals("userexists")) { //l'usuari ja existeix a la DB
            
            out.println("<html> <body>"
                    + "<link rel='stylesheet' href='css/styles.css'>"
                    + "<h2>L'usuari introduit ja existeix. Siusplau, introdueix un altre username</h2>"
                    + "<br>"
                    + "<a href='crearUser.jsp'>Tornar a crear usuari</a>"
                    + "<br>"
                    + "</body></html>");
        }
        
        else if(request.getParameter("tipus").equals("imageexists")) { //la imatge que es vol registrar ja existeix
            
            out.println("<html> <body>"
                    + "<link rel='stylesheet' href='css/styles.css'>"
                    + "<h2>La imatge ja esta registrada. Siusplau, introdueix un altra imatge</h2>"
                    + "<br>"
                    + "<a href='registrarImagen.jsp'>Tornar a registrar imatge</a>"
                    + "<br>"
                    + "</body></html>");
        }
        else if(request.getParameter("tipus").equals("formatmal")) { //el format de la imatge no es JPEG
            
            out.println("<html> <body>"
                    + "<link rel='stylesheet' href='css/styles.css'>"
                    + "<h2>El format de la imatge no es JPEG. Siusplau, introdueix un altra imatge</h2>"
                    + "<br>"
                    + "<a href='registrarImagen.jsp'>Tornar a registrar imatge</a>"
                    + "<br>"
                    + "</body></html>");
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
