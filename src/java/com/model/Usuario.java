package com.model;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

public class Usuario {
    protected int id,tipo;
    protected String nombre,apellido;

    public Usuario(int id,String nombre,String apellido,int tipo){
        this.id=id;
        this.nombre=nombre;
        this.apellido=apellido;
        this.tipo=tipo;
    }
    
    public void setid(int id){
        this.id=id;
    }
    public void setnombre(String nombre){
        this.nombre=nombre;
    }
    public void setapellido(String apellido){
        this.apellido=apellido;
    }
    public void setipo(int tipo){
        this.tipo=tipo;
    }
    
    public int getid(){
        return id;
    }
    public String getnombre(){
        return nombre;
    }
    public String getapellido(){
        return apellido;
    }
    public int gettipo(){
        return tipo;
    }
    
    public static void interfaz(HttpServletRequest request){
    }
    
    public Usuario registrar(){
        return this;
    }
    
    public static boolean eliminar(int id){
        boolean eliminado=false;
        return eliminado;
    }
    
    public static boolean modificar(int id,Usuario usrmod){
        boolean eliminado=false;
        return eliminado;
    }
    
    public static HttpServletRequest mostrar(int id){
        HttpServletRequest elementos=null;
        return elementos;
    }
    
    public static LinkedList<ListaTitulada> verTodos(String limit,Connection con)throws SQLException{
        Integer lim=Integer.parseInt(limit); 
        
        PreparedStatement query=con.prepareStatement("SELECT * FROM usuario WHERE tipo=1 OR tipo=2;");
        //query.setInt(1, lim);
        
        int id=-10;
        int size=1;
        ResultSet result=query.executeQuery();
        LinkedList<Usuario> registros=new LinkedList<Usuario>();
        LinkedList<ListaTitulada> listatitu=new LinkedList<ListaTitulada>();
        
        while(result.next()){
            registros.add(new Usuario(result.getInt("id"),result.getString("nombre"),result.getString("apellido"),result.getInt("tipo")));
        }
        
        listatitu.add(new ListaTitulada("Internos","./IMG/SOLSA.png",registros));
        
        return listatitu;
    }
    
    public static LinkedList<ListaTitulada> buscar(String busca,String limit,Connection con)throws SQLException{
        Integer lim=Integer.parseInt(limit); 
        
        PreparedStatement query=con.prepareStatement("SELECT * FROM usuario WHERE (tipo=1 OR tipo=2) AND (usuario.id LIKE ?  OR usuario.nombre LIKE ? OR usuario.apellido LIKE ? OR usuario.tipo LIKE ? );");
        query.setString(1, "%"+busca+"%");
        query.setString(2, "%"+busca+"%");
        query.setString(3, "%"+busca+"%");
        query.setString(4, "%"+busca+"%");
        
        int id=-10;
        int size=1;
        ResultSet result=query.executeQuery();
        LinkedList<Usuario> registros=new LinkedList<Usuario>();
        LinkedList<ListaTitulada> listatitu=new LinkedList<ListaTitulada>();
        
        while(result.next()){
            registros.add(new Usuario(result.getInt("id"),result.getString("nombre"),result.getString("apellido"),result.getInt("tipo")));
        }
        
        listatitu.add(new ListaTitulada("Internos","./IMG/SOLSA.png",registros));
        
        return listatitu;
    }
    
     public static ResultSet buscarAvanzado(String busca,Integer ini,Boolean... opciones){
        ResultSet resultado=null;
        return resultado;
    }
    
}
