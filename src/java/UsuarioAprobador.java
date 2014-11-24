
import Interfaz.DivLateral;
import Interfaz.Link;
import Interfaz.Opcion;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsuarioAprobador extends UsuarioCliente{
    
    public UsuarioAprobador(int id,String nombre,String apellido,int tipo,Cliente id_cliente){
        super(id,nombre,apellido,tipo,id_cliente);
    }
    
    public static void interfaz(HttpServletRequest request){
        System.out.println("Aprobador Loggeado");
        LinkedList<Opcion> interfazlat=new LinkedList<Opcion>();
        interfazlat.add(new Opcion("Productos",new DivLateral(false,new Link("Por Catálogo","./ShowCataProd"),new Link("Por Categoría","./ShowCatProd"),new Link("Por Marca","./ShowBrandProd"))));
        interfazlat.add(new Opcion("Compras",new DivLateral(false,new Link("Historial","./ListSalMade"),new Link("Acuses de Recibo","./ListToReceive"),new Link("Administrar Peticiones","./ListToApprove"))));
        interfazlat.add(new Opcion("Capturistas",new DivLateral(true,"./SearchExtUsers","./searchextuform.jsp",new Link("Administrar","./ListExtUsers"))));
        HttpSession session=request.getSession(true);
        session.setAttribute("interfazlat", interfazlat);
        session.setAttribute("mainsearch", true);
        session.setAttribute("searchurl", "./searchProd");
        session.setAttribute("searchtip", "Buscar Productos");
        session.setAttribute("advsearchurl", "./advsearchProd");
        session.setAttribute("canbuy", true);
    }
}
