import java.sql.ResultSet;
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
    
    public static HttpServletRequest verTodos(Integer ini){
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
