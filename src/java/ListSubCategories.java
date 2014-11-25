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


public class ListSubCategories extends HttpServlet {

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
               PreparedStatement query=con.prepareStatement("SELECT * FROM subcategoria JOIN categoria ON subcategoria.id_categoria = categoria.id ORDER BY subcategoria.id ASC");
               LinkedList<Subcategoria> subcategorias = new LinkedList<Subcategoria>();
               ResultSet result=query.executeQuery();
                while(result.next()){
                     subcategorias.add(new Subcategoria(result.getInt("subcategoria.id"),result.getString("subcategoria.nombre"),result.getInt("categoria.id"),result.getString("categoria.nombre")));
                }
               query.close();
               url="/cont.jsp"; 
                PreparedStatement query2=con.prepareStatement("SELECT * FROM categoria");
               LinkedList<Categoria> categorias = new LinkedList<Categoria>();
               result=query2.executeQuery();
                while(result.next()){
                     categorias.add(new Categoria(result.getInt("categoria.id"),result.getString("categoria.nombre")));
                }
               query2.close();
                aux.disconnect();
                request.setAttribute("verSubCategorias",1);
                request.setAttribute("listaSubCategorias", subcategorias);
                request.setAttribute("categorias", categorias);
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
