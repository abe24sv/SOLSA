
import com.model.Cliente;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

public class Marca {
    private int id;
    private String nombre;
    
    public Marca(int id,String nombre){
        this.id=id;
        this.nombre=nombre;
    }
    
    public void setid(int id){
        this.id=id;
    }
    public void setnombre(String nombre){
        this.nombre=nombre;
    }
    
    public int getid(){
        return id;
    }
    public String getnombre(){
        return nombre;
    }
    
    public Marca registrar(){
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

}
