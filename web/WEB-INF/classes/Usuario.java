import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

public abstract class Usuario {
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
    
    public static ResultSet buscar(String busca,Integer ini){
        ResultSet resultado=null;
        return resultado;
    }
    
     public static ResultSet buscarAvanzado(String busca,Integer ini,Boolean... opciones){
        ResultSet resultado=null;
        return resultado;
    }
    
}
