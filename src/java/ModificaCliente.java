import com.model.Cliente;
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

public class ModificaCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String notify="",url="";
        String id = request.getParameter("id");

        DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));

        Connection con=aux.connect();

        if(con!=null){

            try{

			PreparedStatement stat = con.prepareStatement("SELECT * FROM cliente where id='"+id+"';");
			ResultSet res = stat.executeQuery();

			res.next();

			Cliente cliente = new Cliente(res.getInt(1), res.getString(2), res.getString(3));

			res = stat.executeQuery("SELECT * FROM cliente_cat, catalogo where id_cliente='"+id+"' AND id_catalogo = catalogo.id");

			LinkedList<Catalogo> cats = new LinkedList<Catalogo>();

			while(res.next()){

				Catalogo cat = new Catalogo();

				cat.setid(res.getInt("catalogo.id"));
				cat.setnombre(res.getString("catalogo.nombre"));
				cat.setdescripcion(res.getString("catalogo.descripcion"));
				cats.add(cat);
			}

			res = stat.executeQuery("SELECT * FROM catalogo WHERE NOT EXISTS(SELECT * FROM cliente, cliente_cat WHERE cliente.id = id_cliente AND catalogo.id = id_catalogo AND cliente.id='"+id+"');");

			LinkedList<Catalogo> cats2 = new LinkedList<Catalogo>();

			while(res.next()){

				Catalogo cat = new Catalogo();

				cat.setid(res.getInt("catalogo.id"));
				cat.setnombre(res.getString("catalogo.nombre"));
				cat.setdescripcion(res.getString("catalogo.descripcion"));
				cats2.add(cat);
			}

			aux.disconnect();
			stat.close();


			request.setAttribute("cliente", cliente);
			request.setAttribute("cats", cats);
			request.setAttribute("cats2", cats2);

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
