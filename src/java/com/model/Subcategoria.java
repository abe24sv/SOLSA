package com.model;


import com.model.Categoria;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

public class Subcategoria extends Categoria {
    
    public Subcategoria(int id,String nombre){
        super(id,nombre);
    }
    
    @Override
    public Subcategoria registrar(){
        return this;
    }
    
    public static boolean eliminar(int id){
        boolean eliminado=false;
        return eliminado;
    }
    
    public static boolean modificar(int id,Subcategoria subcatmod){
        boolean eliminado=false;
        return eliminado;
    }
    
    public static HttpServletRequest mostrar(int id){
        HttpServletRequest elementos=null;
        return elementos;
    }
    
    public static HttpServletRequest verTodos(Integer ini){
        HttpServletRequest elementos=null;
        return elementos;
    }
    
    public HttpServletRequest verProductos(Integer ini){
        HttpServletRequest elementos=null;
        return elementos;
    }
    
    public static ResultSet buscar(String busca,Integer ini){
        ResultSet resultado=null;
        return resultado;
    }
    
     public static ResultSet buscarAvanzado(String busca,Integer ini,Boolean... opciones){
        ResultSet resultado=null;
        return resultado;
    }
}
