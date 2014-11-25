import Interfaz.ListaTitulada;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListExtUsers extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doGet(request,response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
            String notify="",url="";
            String tab="1";
            String limit="0";

            DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));
            Connection con=aux.connect();
            
            
            HttpSession session=request.getSession();
            UsuarioCliente usr=(UsuarioCliente)session.getAttribute("user");
            Cliente cliente=usr.getid_cliente();
            try{
                
                LinkedList<ListaTitulada> listausr=cliente.verUsuarios(con);

                request.setAttribute("listausr", listausr);

            }catch(SQLException e){
                e.printStackTrace();
            }

            aux.disconnect();

            RequestDispatcher disp = getServletContext().getRequestDispatcher("/listusers.jsp");
            if(disp!=null){
                disp.forward(request, response);
            }
   }
    
}
