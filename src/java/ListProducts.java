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


public class ListProducts extends HttpServlet {

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
               PreparedStatement query=con.prepareStatement("SELECT * FROM ((producto JOIN subcategoria ON producto.id_subcategoria = subcategoria.id) JOIN categoria ON producto.id_categoria = categoria.id) JOIN marca ON producto.id_marca = marca.id ORDER BY producto.id ASC");
               LinkedList<Producto> productos = new LinkedList<Producto>();
               ResultSet result=query.executeQuery();
                while(result.next()){
                     productos.add(new Producto(result.getInt("producto.id"),new Categoria(result.getInt("producto.id_categoria"),result.getString("categoria.nombre")),new Categoria(result.getInt("producto.id_subcategoria"),result.getString("subcategoria.nombre")),result.getString("producto.nombre"),result.getString("marca.nombre"),result.getString("producto.descripcion"),result.getString("producto.imagen"),result.getInt("producto.cantidad"),result.getString("categoria.nombre"),result.getString("subcategoria.nombre")));
                }
               query.close();
              url="/cont.jsp"; 
                aux.disconnect();
                request.setAttribute("verProductosAlm",1);
                request.setAttribute("listaProductosAlm", productos);
            }catch(SQLException e){
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
