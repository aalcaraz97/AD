/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author 
 */
@WebService(serviceName = "imageWS")
public class imageWS {

    /**
     * This is a sample web service operation
     * @param imatge
     */
    
    Connection connection = null;
    
    //registrar imatge
    @WebMethod(operationName = "RegisterImage")
    public int RegisterImage (@WebParam(name = "imatge") Image imatge) {
        try {
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\adri\\Documents\\LABAD.db");
                 
        PreparedStatement statement = connection.prepareStatement("insert into imatges values (?,?,?,?,?)");
        
        statement.setInt(1,imatge.GetIdImag());
        statement.setString(2,imatge.GetTitulo());
        statement.setString(3,imatge.GetParaulaClau());
        statement.setString(4,imatge.GetAuthor());
        statement.setString(5,imatge.GetFecha());
        
        int r = statement.executeUpdate();
        
        if(connection != null) connection.close();
        
        return r;       
        }
    catch(SQLException e)
        {
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return 0;
        }
        
    }
    
        //modificar imatge
    @WebMethod(operationName = "ModifyImage")
    public int ModifyImage (@WebParam(name = "imatge") Image imatge) {
        try {
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\adri\\Documents\\LABAD.db");
        PreparedStatement statement = connection.prepareStatement("update imatges set titol_imatge = ?, paraula_clau = ?,"
                                                                + "autor = ?, data_creacio = ? where id_imatge = ?");
        
        
        statement.setString(1,imatge.GetTitulo());
        statement.setString(2,imatge.GetParaulaClau());
        statement.setString(3,imatge.GetAuthor());
        statement.setString(4,imatge.GetFecha());
        statement.setInt(5,imatge.GetIdImag());
        
        int r = statement.executeUpdate();
        
        if(connection != null) connection.close();
        
        return r;       
        }
        catch(SQLException e)
        {
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return 0;
        } 
    }
    
    
        //llistar imatges
    @WebMethod(operationName = "ListImages")
    public List ListImages() {
        try {
            List<Image> llista = new ArrayList<>();
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\adri\\Documents\\LABAD.db");
            PreparedStatement statement = connection.prepareStatement("select * from imatges");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {
                Image i1 = new Image();
                i1.SetIdImag(rs.getInt("id_imatge"));
                i1.SetTitulo(rs.getString("titol_imatge"));
                i1.SetParaulaClau(rs.getString("paraula_clau"));
                i1.SetAutor(rs.getString("autor"));
                i1.SetFecha(rs.getString("data_creacio"));
                
                llista.add(i1);
                
            }
        
        
        if(connection != null) connection.close();
        
        return llista;      
        }
        catch(SQLException e)
        {
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        } 
    }
    
    //buscar per id
    @WebMethod(operationName = "SearchbyId")
    public Image SearchbyId(@WebParam(name = "id_imatge")int id_imatge) {
        try {
           connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\adri\\Documents\\LABAD.db");
            PreparedStatement statement = connection.prepareStatement("select * from imatges where id_imatge = ?");
            statement.setInt(1,id_imatge);
            ResultSet rs = statement.executeQuery();
                Image i1 = new Image();
                i1.SetIdImag(rs.getInt("id_imatge"));
                i1.SetTitulo(rs.getString("titol_imatge"));
                i1.SetParaulaClau(rs.getString("paraula_clau"));
                i1.SetAutor(rs.getString("autor"));
                i1.SetFecha(rs.getString("data_creacio"));

        if(connection != null) connection.close();
        
        return i1;      
        }
        catch(SQLException e)
        {
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        } 
    }
    
    
    //buscar per titol
    @WebMethod(operationName = "SearchByTitle")
        public List SearchbyTitle(@WebParam(name = "titol")String titol) {
        try {
            List<Image> llista = new ArrayList<>();
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\adri\\Documents\\LABAD.db");
            PreparedStatement statement = connection.prepareStatement("select * from imatges where titol_imatge = ?");
            statement.setString(1,titol);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                 Image i1 = new Image();
                i1.SetIdImag(rs.getInt("id_imatge"));
                i1.SetTitulo(rs.getString("titol_imatge"));
                i1.SetParaulaClau(rs.getString("paraula_clau"));
                i1.SetAutor(rs.getString("autor"));
                i1.SetFecha(rs.getString("data_creacio"));
                llista.add(i1);
            }

        if(connection != null) connection.close();
        
        return llista;      
        }
        catch(SQLException e)
        {
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        } 
    }
    
    
    //buscar per paraula clau
    @WebMethod(operationName = "SearchbyKeywords")
        public List SearchbyKeywords(@WebParam(name = "paraulaclau")String paraulaclau) {
        try {
            List<Image> llista = new ArrayList<>();
           connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\adri\\Documents\\LABAD.db");
            PreparedStatement statement = connection.prepareStatement("select * from imatges where paraula_clau = ?");
            statement.setString(1,paraulaclau);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Image i1 = new Image();
                i1.SetIdImag(rs.getInt("id_imatge"));
                i1.SetTitulo(rs.getString("titol_imatge"));
                i1.SetParaulaClau(rs.getString("paraula_clau"));
                i1.SetAutor(rs.getString("autor"));
                i1.SetFecha(rs.getString("data_creacio"));
                llista.add(i1);
            }

        if(connection != null) connection.close();
        
        return llista;      
        }
        catch(SQLException e)
        {
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        } 
    }
    
    //buscar per autor
    @WebMethod(operationName = "SearchbyAuthor")
    public List SearchbyAuthor(@WebParam(name = "autor")String autor) {
        try {
            List<Image> llista = new ArrayList<>();
           connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\adri\\Documents\\LABAD.db");
            PreparedStatement statement = connection.prepareStatement("select * from imatges where autor = ?");
            statement.setString(1,autor);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Image i1 = new Image();
                i1.SetIdImag(rs.getInt("id_imatge"));
                i1.SetTitulo(rs.getString("titol_imatge"));
                i1.SetParaulaClau(rs.getString("paraula_clau"));
                i1.SetAutor(rs.getString("autor"));
                i1.SetFecha(rs.getString("data_creacio"));
                llista.add(i1);
            }
        if(connection != null) connection.close();
        
        return llista;      
        }
        catch(SQLException e)
        {
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        } 
    }
    
    
    //buscar per data
    @WebMethod(operationName = "SearchbyCreaDate")
    public List SearchbyCreaDate(@WebParam(name = "datacreacio")String datacreacio) {
        try {
            List<Image> llista = new ArrayList<>();
           connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\adri\\Documents\\LABAD.db");
            PreparedStatement statement = connection.prepareStatement("select * from imatges where data_creacio = ?");
            statement.setString(1,datacreacio);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Image i1 = new Image();
                i1.SetIdImag(rs.getInt("id_imatge"));
                i1.SetTitulo(rs.getString("titol_imatge"));
                i1.SetParaulaClau(rs.getString("paraula_clau"));
                i1.SetAutor(rs.getString("autor"));
                i1.SetFecha(rs.getString("data_creacio"));
                llista.add(i1);
            }

        if(connection != null) connection.close();
        
        return llista;      
        }
        catch(SQLException e)
        {
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        } 
    }
    
    
    
    
}
