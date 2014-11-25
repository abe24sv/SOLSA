
import com.model.UsuarioCliente;
import com.model.Usuario;
import Interfaz.ListaTitulada;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class searchUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String notify="",url="";
        String busca=request.getParameter("busca").trim().replaceAll("<", " ").replaceAll(">", " ");
        String tab="1";
        String limit="0";
        
        switch(busca){
            case "Ventas":
                busca="1";
                break;
            case "Almacen":
                busca="2";
                break;
            case "Capturista":
                busca="3";
                break;
            case "Aprobador":
                busca="4";
                break;
        }

        DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));
        Connection con=aux.connect();

        try{

            LinkedList<ListaTitulada> listausr=UsuarioCliente.buscar(busca,limit,con); 
            listausr.addFirst(Usuario.buscar(busca, limit, con).getFirst());

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
