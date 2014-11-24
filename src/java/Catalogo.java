
import com.model.Producto;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;


public class Catalogo {
    public int id;
    public String nombre,descripcion;
    public LinkedList<Producto> productos;
    
    public Catalogo(int id,String nombre,String descripcion,LinkedList<Producto> productos){
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.productos=productos;
    }
    
    public Catalogo(int id,String nombre){
        this.id=id;
        this.nombre=nombre;
    }
    
    public void setid(int id){
        this.id=id;
    }
    public void setnombre(String nombre){
        this.nombre=nombre;
    }
    public void setdescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public void setproductos(LinkedList<Producto> productos){
        this.productos=productos;
    }
    
    public int getid(){
        return id;
    }
    public String getnombre(){
        return nombre;
    }
    public String getdescripcion(){
        return descripcion;
    }
    public LinkedList<Producto> getproductos(){
        return productos;
    }
    
    public Catalogo registrar(){
        return this;
    }
    
    public static boolean eliminar(int id){
        boolean eliminado=false;
        return eliminado;
    }
    
    public static boolean modificar(int id,Catalogo catmod){
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
