
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;

public class Compra {
    private int id,estado;
    private String fecha_inicio,fecha_entrega;
    private LinkedList<Producto> productos;
    private Cliente cliente;
    private Usuario usuario;
    
    public Compra(int id,int estado,String fecha_inicio,String fecha_entrega,LinkedList<Producto> productos,Cliente cliente,Usuario usuario){
        this.id=id;
        this.estado=estado;
        this.fecha_inicio=fecha_inicio;
        this.fecha_entrega=fecha_entrega;
        this.productos=productos;
        this.cliente=cliente;
        this.usuario=usuario;
    }
    
    public void setid(int id){
        this.id=id;
    }
    public void setestado(int estado){
        this.estado=estado;
    }
    public void setfecha_inicio(String fecha_inicio){
        this.fecha_inicio=fecha_inicio;
    }
    public void setfecha_entrega(String fecha_entrega){
        this.fecha_entrega=fecha_entrega;
    }
    public void setproductos(LinkedList<Producto> productos){
        this.productos=productos;
    }
    public void setcliente(Cliente cliente){
        this.cliente=cliente;
    }
    public void setusuario(Usuario usuario){
        this.usuario=usuario;
    }
    
    public int getid(){
        return id;
    }
    public int getestado(){
        return estado;
    }
    public String getfecha_inicio(){
        return fecha_inicio;
    }
    public String getfecha_entrega(){
        return fecha_entrega;
    }
    public LinkedList<Producto> getproductos(){
        return productos;
    }
    public Cliente getcliente(){
        return cliente;
    }
    public Usuario getusuario(){
        return usuario;
    }
    
    public Compra registrar(){
        return this;
    }
    
    public static boolean eliminar(int id){
        boolean eliminado=false;
        return eliminado;
    }
    
    public static boolean modificar(int id,Compra commod){
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
