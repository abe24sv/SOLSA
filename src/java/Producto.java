
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

public class Producto {
    private int id,cantidad;
    private Categoria id_categoria, id_subcategoria;
    private String nombre, marca, descripcion,imagen,cat,subcat;
    
    public Producto(int id,Categoria id_categoria,Categoria id_subcategoria,String nombre, String marca, String descripcion,String imagen,int cantidad){
        this.id=id;
        this.id_categoria=id_categoria;
        this.id_subcategoria=id_subcategoria;
        this.nombre=nombre;
        this.marca=marca;
        this.descripcion=descripcion;
        this.imagen=imagen;
        this.cantidad=cantidad;
    }
    
    public Producto(int id,Categoria id_categoria,Categoria id_subcategoria,String nombre, String marca, String descripcion,String imagen,int cantidad, String cat, String subcat){
        this.id=id;
        this.id_categoria=id_categoria;
        this.id_subcategoria=id_subcategoria;
        this.nombre=nombre;
        this.marca=marca;
        this.descripcion=descripcion;
        this.imagen=imagen;
        this.cantidad=cantidad;
        this.cat = cat;
        this.subcat = subcat;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getSubcat() {
        return subcat;
    }

    public void setSubcat(String subcat) {
        this.subcat = subcat;
    }
    
    public void setid(int id){
        this.id=id;
    }
    public void setid_categoria(Categoria categoria){
        this.id_categoria=categoria;
    }
    public void setid_subcategoria(Categoria subcategoria){
        this.id_subcategoria=subcategoria;
    }
    public void setnombre(String nombre){
        this.nombre=nombre;
    }
    public void setmarca(String marca){
        this.marca=marca;
    }
    public void setdescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public void setimagen(String imagen){
        this.imagen=imagen;
    }
    public void setcantidad(int cantidad){
        this.cantidad=cantidad;
    }
    
    public int getid(){
        return id;
    }
    public Categoria getid_categoria(){
        return id_categoria;
    }
    public Categoria getid_subcategoria(){
        return id_subcategoria;
    }
    public String getnombre(){
        return nombre;
    }
    public String getmarca(){
        return marca;
    }
    public String getdescripcion(){
        return descripcion;
    }
    public String getimagen(){
        return imagen;
    }
    public int getcantidad(){
        return cantidad;
    }
    
    public Producto registrar(){
        return this;
    }
    
    public static boolean eliminar(int id){
        boolean eliminado=false;
        return eliminado;
    }
    
    public static boolean modificar(int id, Producto promod){
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
    
    public static ResultSet buscar(String busca,Integer ini){
        ResultSet resultado=null;
        return resultado;
    }
    
     public static ResultSet buscarAvanzado(String busca,Integer ini,Boolean... opciones){
        ResultSet resultado=null;
        return resultado;
    }
}
