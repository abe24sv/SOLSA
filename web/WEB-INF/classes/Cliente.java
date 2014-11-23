
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
    
    public static ResultSet buscar(String busca,Integer ini){
        ResultSet resultado=null;
        return resultado;
    }
    
     public static ResultSet buscarAvanzado(String busca,Integer ini,Boolean... opciones){
        ResultSet resultado=null;
        return resultado;
    }
    
}
