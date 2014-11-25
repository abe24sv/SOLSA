
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


public class editarSubCategoria extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nom");
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        if(con!=null){
            
            try{
                request.setAttribute("id", id);
                request.setAttribute("nombre", nombre);
                request.setAttribute("categoria", categoria);
                request.setAttribute("editarSubCategoria", 1);
               url="/cont.jsp";  
                                PreparedStatement query2=con.prepareStatement("SELECT * FROM categoria");
               LinkedList<Categoria> categorias = new LinkedList<Categoria>();
               ResultSet result=query2.executeQuery();
                while(result.next()){
                     categorias.add(new Categoria(result.getInt("categoria.id"),result.getString("categoria.nombre")));
                }
               query2.close();
                aux.disconnect();
                request.setAttribute("categorias", categorias);
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
