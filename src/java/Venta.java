
import com.model.Producto;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;

public class Venta extends Compra{
    private Usuario vendedor;
    public Venta(int id,int estado,String fecha_inicio,String fecha_entrega,LinkedList<Producto> productos,Cliente cliente,Usuario usuario,Usuario vendedor){
        super(id,estado,fecha_inicio,fecha_entrega,productos,cliente,usuario);
        this.vendedor=vendedor;
    }
    
    public void setvendedor(Usuario vendedor){
        this.vendedor=vendedor;
    }
    
    public Usuario getvendedor(){
        return vendedor;
    }
    
    public Venta registrar(){
        return this;
    }
    
    public static boolean eliminar(int id){
        boolean eliminado=false;
        return eliminado;
    }
    
    public static boolean modificar(int id,Venta venmod){
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
    
    public static ResultSet buscar(String busca){
        ResultSet resultado=null;
        return resultado;
    }
    
     public static ResultSet buscarAvanzado(String busca,Boolean... opciones){
        ResultSet resultado=null;
        return resultado;
    }
}
