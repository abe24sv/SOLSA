
import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class agProdAlm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String notify="",url="/index.jsp";
        
        DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));
        
        Connection con=aux.connect();
        if(con!=null){
            
            try{
               url="/cont.jsp";  
               PreparedStatement query=con.prepareStatement("SELECT * FROM marca");
               LinkedList<Marca> marcas = new LinkedList<Marca>();
               ResultSet result=query.executeQuery();
                while(result.next()){
                     marcas.add(new Marca(result.getInt("marca.id"),result.getString("marca.nombre")));
                }
               query.close();
               PreparedStatement query2=con.prepareStatement("SELECT * FROM subcategoria");
               LinkedList<Subcategoria> subcategorias = new LinkedList<Subcategoria>();
               result=query2.executeQuery();
                while(result.next()){
                     subcategorias.add(new Subcategoria(result.getInt("subcategoria.id"),result.getString("subcategoria.nombre")));
                }
               query2.close();
               request.setAttribute("marcas", marcas);
               request.setAttribute("subcategorias", subcategorias);
               request.setAttribute("agregarProductosAlm", 1);
                aux.disconnect();
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else{
            notify="¡Ups! Tenemos problemas Técnicos";
            url="/index.jsp";
        }
        
        RequestDispatcher disp = getServletContext().getRequestDispatcher(url);
        if(disp!=null){
            disp.forward(request, response);
        }
    }

}
