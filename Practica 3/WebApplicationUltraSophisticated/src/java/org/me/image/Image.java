package org.me.image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleix
 */
public class Image {
    private int idImag;
    private String titulo; 
    private String fecha;
    private String paraulaclau; 
    private String author; 
    
    
    public Image(int idImag, String titulo, String fecha, String paraulaclau, String author){ 
        this.idImag = idImag;
        this.titulo = titulo; 
        this.fecha = fecha; 
        this.paraulaclau = paraulaclau; 
        this.author = author; 
    }
    
    public Image() {}
    
    public int GetIdImag(){
        return this.idImag;
    }
    public void SetIdImag(int idImag){
        this.idImag = idImag; 
    }
    
    public String GetTitulo(){
        return this.titulo; 
    }
    public void SetTitulo(String titulo){
        this.titulo = titulo; 
    }
    public String GetFecha(){
        return this.fecha; 
    }
    public void SetFecha(String fecha){
        this.fecha = fecha; 
    }
    public String GetParaulaClau(){
        return this.paraulaclau; 
    }
    public void SetParaulaClau(String paraulaclau){
        this.paraulaclau = paraulaclau; 
    }
    public String GetAuthor(){
        return this.author; 
    }
    public void SetAutor(String author){
        this.author = author;
    }
}
