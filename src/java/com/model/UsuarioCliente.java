package com.model;

import Interfaz.ListaTitulada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;

public abstract class UsuarioCliente extends Usuario{
    
    protected Cliente id_cliente;
    
    public UsuarioCliente(int id,String nombre,String apellido,int tipo,Cliente id_cliente){
        super(id,nombre,apellido,tipo);
        this.id_cliente=id_cliente;
    }
    
    public void setid_cliente(Cliente id_cliente){
        this.id_cliente=id_cliente;
    }
    
    public Cliente getid_cliente(){
        return id_cliente;
    }
    
    public static HttpServletRequest mostrar(int id){
        HttpServletRequest elementos=null;
        return elementos;
    }
    
    public static LinkedList<ListaTitulada> verTodos(String limit,Connection con)throws SQLException{
        Integer lim=Integer.parseInt(limit); 
        
        PreparedStatement query=con.prepareStatement("SELECT usuario.*,id_cliente,cliente.nombre AS cliente,logo FROM usuario,usuario_ext,cliente WHERE usuario.id=usuario_ext.id_usuario AND usuario_ext.id_cliente=cliente.id ORDER BY cliente.nombre;");
        //query.setInt(1, lim);
        
        int id=-10;
        int size=1;
        ResultSet result=query.executeQuery();
        LinkedList<Usuario> registros;
        LinkedList<ListaTitulada> listatitu=new LinkedList<ListaTitulada>();
        
        if(result.next()){
            id=result.getInt("id_cliente");
            Cliente cliente = new Cliente(result.getInt("id_cliente"),result.getString("cliente"),result.getString("logo"));
            registros=new LinkedList<Usuario>();
            registros.add(new Usuario(result.getInt("id"),result.getString("nombre"),result.getString("apellido"),result.getInt("tipo")));
            while(result.next()){
                size+=1;
                if(id!=result.getInt("id_cliente")){
                    listatitu.add(new ListaTitulada(cliente.getnombre(),cliente.getlogo(),registros));
                    id=result.getInt("id_cliente");
                    cliente=new Cliente(result.getInt("id_cliente"),result.getString("cliente"),result.getString("logo"));
                    registros=new LinkedList<Usuario>();
                }
                    registros.add(new Usuario(result.getInt("id"),result.getString("nombre"),result.getString("apellido"),result.getInt("tipo")));
            }
            listatitu.add(new ListaTitulada(cliente.getnombre(),cliente.getlogo(),registros));
        }
        
        return listatitu;
    }
    
    public static LinkedList<ListaTitulada> buscar(String busca,String limit,Connection con)throws SQLException{
        Integer lim=Integer.parseInt(limit); 
        
        PreparedStatement query=con.prepareStatement("SELECT usuario.*,id_cliente,cliente.nombre AS cliente,logo FROM usuario,usuario_ext,cliente WHERE usuario.id=usuario_ext.id_usuario AND usuario_ext.id_cliente=cliente.id AND (usuario.id LIKE ?  OR usuario.nombre LIKE ? OR usuario.apellido LIKE ? OR usuario.tipo LIKE ? ) ORDER BY cliente.nombre;");
        query.setString(1, "%"+busca+"%");
        query.setString(2, "%"+busca+"%");
        query.setString(3, "%"+busca+"%");
        query.setString(4, "%"+busca+"%");
        
        int id=-10;
        int size=1;
        ResultSet result=query.executeQuery();
        LinkedList<Usuario> registros;
        LinkedList<ListaTitulada> listatitu=new LinkedList<ListaTitulada>();
        
        if(result.next()){
            id=result.getInt("id_cliente");
            Cliente cliente = new Cliente(result.getInt("id_cliente"),result.getString("cliente"),result.getString("logo"));
            registros=new LinkedList<Usuario>();
            registros.add(new Usuario(result.getInt("id"),result.getString("nombre"),result.getString("apellido"),result.getInt("tipo")));
            while(result.next()){
                size+=1;
                if(id!=result.getInt("id_cliente")){
                    listatitu.add(new ListaTitulada(cliente.getnombre(),cliente.getlogo(),registros));
                    id=result.getInt("id_cliente");
                    cliente=new Cliente(result.getInt("id_cliente"),result.getString("cliente"),result.getString("logo"));
                    registros=new LinkedList<Usuario>();
                }
                    registros.add(new Usuario(result.getInt("id"),result.getString("nombre"),result.getString("apellido"),result.getInt("tipo")));
            }
            listatitu.add(new ListaTitulada(cliente.getnombre(),cliente.getlogo(),registros));
        }
        
        return listatitu;
    }
    
     public static ResultSet buscarAvanzado(String busca,Integer ini,Boolean... opciones){
        ResultSet resultado=null;
        return resultado;
    }
}
