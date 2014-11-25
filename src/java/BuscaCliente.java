import com.model.Cliente;
import com.model.Producto;
import com.model.Categoria;
import com.model.Usuario;
import com.model.UsuarioAdministrador;
import com.model.UsuarioAlmacen;
import com.model.UsuarioAprobador;
import com.model.UsuarioCapturista;
import com.model.UsuarioVentas;
import com.model.Venta;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BuscaCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String notify="",url="";
        String search = request.getParameter("busca");

        DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));

        Connection con=aux.connect();

        if(con!=null){

            try{

			PreparedStatement stat = con.prepareStatement("SELECT * FROM cliente WHERE nombre LIKE '%"+search+"%' OR id='"+search+"';");
			ResultSet res = stat.executeQuery();

			LinkedList<Cliente> clientes = new LinkedList<Cliente>();

			while(res.next()){

				Cliente cet = new Cliente();

				cet.setid(res.getInt("id"));
				cet.setnombre(res.getString("nombre"));
				cet.setlogo(res.getString("logo"));

				clientes.add(cet);
			}

			aux.disconnect();
            		stat.close();

			request.setAttribute("clientes", clientes);
			url="/mainadmin.jsp";

            }catch(Exception e){
                e.printStackTrace();
            }

        }else{
            notify="Â¡Ups! Tenemos problemas TÃ©cnicos";
            url="/mainadmin.jsp";
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher(url);
        if(disp!=null){
            disp.forward(request, response);
        }

    }

     protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		 try {
			 doGet(request, response);
		 } catch (Exception e){
			 e.printStackTrace();
		 }
	 }

}
