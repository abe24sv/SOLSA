
import com.model.Producto;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;

public class Orden extends Compra{
    private Usuario trabajador;
    public Orden(int id,int estado,String fecha_inicio,String fecha_entrega,LinkedList<Producto> productos,Cliente cliente,Usuario usuario,Usuario trabajador){
        super(id,estado,fecha_inicio,fecha_entrega,productos,cliente,usuario);
        this.trabajador=trabajador;
    }
    
    public void settrabajador(Usuario trabajador){
        this.trabajador=trabajador;
    }
    
    public Usuario gettrabajador(){
        return trabajador;
    }
    
    public Orden registrar(){
        return this;
    }
    
    public static boolean eliminar(int id){
        boolean eliminado=false;
        return eliminado;
    }
    
    public static boolean modificar(int id,Orden ordmod){
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
