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

public class ModificaCatalogo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String notify="",url="";
        String id = request.getParameter("id");

        DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));

        Connection con=aux.connect();

        if(con!=null){

            try{

			PreparedStatement stat = con.prepareStatement("SELECT * FROM catalogo where id='"+id+"';");
			ResultSet res = stat.executeQuery();

			res.next();

			Catalogo catalogo = new Catalogo(res.getInt(1), res.getString(2), res.getString(3));

			res = stat.executeQuery("SELECT * FROM catalogo_prod, producto where id_catalogo='"+id+"' AND id_producto = producto.id");

			LinkedList<Producto> productos = new LinkedList<Producto>();

			while(res.next()){

				Producto prod = new Producto();

				prod.setid(res.getInt("producto.id"));
				prod.setnombre(res.getString("producto.nombre"));
				prod.setdescripcion(res.getString("producto.descripcion"));
				prod.setcantidad(res.getInt("producto.cantidad"));
				prod.setimagen(res.getString("producto.imagen"));
				productos.add(prod);
			}

			res = stat.executeQuery("SELECT * FROM producto WHERE NOT EXISTS(SELECT * FROM catalogo, catalogo_prod WHERE catalogo.id = id_catalogo AND producto.id = id_producto AND catalogo.id='"+id+"');");

			LinkedList<Producto> productos2 = new LinkedList<Producto>();

			while(res.next()){

				Producto prod = new Producto();

				prod.setid(res.getInt("producto.id"));
				prod.setnombre(res.getString("producto.nombre"));
				prod.setdescripcion(res.getString("producto.descripcion"));
				prod.setcantidad(res.getInt("producto.cantidad"));
				prod.setimagen(res.getString("producto.imagen"));
				productos2.add(prod);
			}

			aux.disconnect();
			stat.close();


			request.setAttribute("catalogo", catalogo);
			request.setAttribute("productos", productos);
			request.setAttribute("productos2", productos2);

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
