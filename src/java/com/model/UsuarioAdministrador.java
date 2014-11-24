package com.model;


import Interfaz.Opcion;
import Interfaz.DivLateral;
import Interfaz.Link;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsuarioAdministrador extends Usuario{
    
     public UsuarioAdministrador(int id,String nombre,String apellido,int tipo){
        super(id,nombre,apellido,tipo);
    }
    public static void interfaz(HttpServletRequest request){
        System.out.println("Administrador Loggeado");
        LinkedList<Opcion> interfazlat=new LinkedList<Opcion>();
        interfazlat.add(new Opcion("Catálogos",new DivLateral(true,"./SearchCatalogue","./searchcataform.jsp",new Link("Administrar","./ListCatalogues"))));
        interfazlat.add(new Opcion("Clientes",new DivLateral(true,"./SearchClient","./searchcliform.jsp",new Link("Administrar","./ListClients"))));
        interfazlat.add(new Opcion("Sistema",new DivLateral(false,new Link("Usuarios","./ListUsers"),new Link("Administrar","./report.jsp"))));
        HttpSession session=request.getSession(true);
        session.setAttribute("interfazlat", interfazlat);
        session.setAttribute("mainsearch", false);
        session.setAttribute("canbuy", false);
    }
}
