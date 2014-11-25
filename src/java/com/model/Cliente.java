package com.model;


import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

public class Cliente {
    private int id;
    private String nombre,logo;
    
    public Cliente(int id,String nombre,String logo){
        this.id=id;
        this.nombre=nombre;
        this.logo=logo;
    }
    
    public Cliente(int id,String nombre){
        this.id=id;
        this.nombre=nombre;
    }
    
    public void setid(int id){
        this.id=id;
    }
    public void setnombre(String nombre){
        this.nombre=nombre;
    }
    public void setlogo(String logo){
        this.logo=logo;
    }
    
    public int getid(){
        return id;
    }
    public String getnombre(){
        return nombre;
    }
    public String getlogo(){
        return logo;
    }
    
    public Cliente registrar(){
        return this;
    }
    
    public static boolean eliminar(int id){
        boolean eliminado=false;
        return eliminado;
    }
    
    public static boolean modificar(int id, Cliente climod){
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
    
    public HttpServletRequest verUsuarios(Integer ini){
        HttpServletRequest elementos=null;
        return elementos;
    }
    
    public LinkedList<ListaTitulada> verUsuarios(Connection con) throws SQLException{
        
        PreparedStatement query=con.prepareStatement("SELECT id,nombre,apellido,tipo FROM usuario,usuario_ext WHERE usuario.id=usuario_ext.id_usuario AND tipo=3 AND id_cliente= ? ;");
        query.setString(1, this.id+"");

        int size=1;
        ResultSet result=query.executeQuery();
        LinkedList<Usuario> registros=new LinkedList<Usuario>();
        LinkedList<ListaTitulada> listatitu=new LinkedList<ListaTitulada>();
        
        while(result.next()){
            registros.add(new Usuario(result.getInt("id"),result.getString("nombre"),result.getString("apellido"),result.getInt("tipo")));
        }
        
        listatitu.add(new ListaTitulada(this.nombre,this.logo,registros));
        
        return listatitu;
    }
    
    public LinkedList<ListaTitulada> buscarUsuarios(String busca,String limit,Connection con)throws SQLException{
        PreparedStatement query=con.prepareStatement("SELECT id,nombre,apellido,tipo FROM usuario,usuario_ext WHERE usuario.id=usuario_ext.id_usuario AND tipo=3 AND id_cliente= ? AND (usuario.id LIKE ?  OR usuario.nombre LIKE ? OR usuario.apellido LIKE ? );");
        query.setString(1, this.id+"");
        query.setString(2, "%"+busca+"%");
        query.setString(3, "%"+busca+"%");
        query.setString(4, "%"+busca+"%");

        int size=1;
        ResultSet result=query.executeQuery();
        LinkedList<Usuario> registros=new LinkedList<Usuario>();
        LinkedList<ListaTitulada> listatitu=new LinkedList<ListaTitulada>();
        
        while(result.next()){
            registros.add(new Usuario(result.getInt("id"),result.getString("nombre"),result.getString("apellido"),result.getInt("tipo")));
        }
        
        listatitu.add(new ListaTitulada(this.nombre,this.logo,registros));
        
        return listatitu;
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
