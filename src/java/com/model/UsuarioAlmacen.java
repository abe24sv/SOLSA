package com.model;


import Interfaz.DivLateral;
import Interfaz.Link;
import Interfaz.Opcion;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsuarioAlmacen extends Usuario{

    public UsuarioAlmacen(int id,String nombre,String apellido,int tipo){
        super(id,nombre,apellido,tipo);
    }
     
    public static void interfaz(HttpServletRequest request){
        System.out.println("Trabjador Almacen Loggeado");
        LinkedList<Opcion> interfazlat=new LinkedList<Opcion>();
        interfazlat.add(new Opcion("Ordenes de Envío",new DivLateral(false,new Link("General","./ListOrdGen"),new Link("Por Fecha","./ListOrdDate"),new Link("Por Cliente","./ListOrdCli"),new Link("Por Producto","./ListOrdProd"))));
        interfazlat.add(new Opcion("Productos",new DivLateral(true,"./searchProduct","./searchproform.jsp",new Link("Administrar","./ListProducts"))));
        interfazlat.add(new Opcion("Categorías",new DivLateral(true,"./searchCategory","./searchcatform.jsp",new Link("Administrar","./ListCategories"),new Link("Subcategorias","./ListSubCategories"))));
        HttpSession session=request.getSession(true);
        session.setAttribute("interfazlat", interfazlat);
        session.setAttribute("mainsearch", true);
        session.setAttribute("searchurl", "./searchOrdBus");
        session.setAttribute("searchtip", "Buscar Órdenes de Envío");
        session.setAttribute("advsearchurl", "./advsearchOrdBus");
        session.setAttribute("canbuy", false);
    }
    
}