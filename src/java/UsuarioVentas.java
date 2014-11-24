
import Interfaz.DivLateral;
import Interfaz.Link;
import Interfaz.Opcion;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsuarioVentas extends Usuario{

     public UsuarioVentas(int id,String nombre,String apellido,int tipo){
        super(id,nombre,apellido,tipo);
    }
     
    public static void interfaz(HttpServletRequest request){
        System.out.println("Vendedor Loggeado");
        LinkedList<Opcion> interfazlat=new LinkedList<Opcion>();
        interfazlat.add(new Opcion("Ventas",new DivLateral(false,new Link("Por Fecha","./ListSalDate"),new Link("Por Cliente","./ListSalCli"),new Link("Por Producto","./ListSalProd"))));
        interfazlat.add(new Opcion("Historial",new DivLateral(false,new Link("Aprobaciones","./ListSalApro"),new Link("Cancelaciones","./ListSalCancel"))));
        HttpSession session=request.getSession(true);
        session.setAttribute("interfazlat", interfazlat);
        session.setAttribute("mainsearch", true);
        session.setAttribute("searchurl", "./searchSales");
        session.setAttribute("searchtip", "Buscar Ventas");
        session.setAttribute("advsearchurl", "./advsearchSales");
        session.setAttribute("canbuy", false);
    }
    
}
