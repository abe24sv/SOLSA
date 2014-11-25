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

public class BuscaCatalogo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String notify="",url="";
        String search = request.getParameter("busca");

        DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));

        Connection con=aux.connect();

        if(con!=null){

            try{

			PreparedStatement stat = con.prepareStatement("SELECT * FROM catalogo WHERE nombre LIKE '%"+search+"%' OR id='"+search+"';");
			ResultSet res = stat.executeQuery();

			LinkedList<Catalogo> catalogos = new LinkedList<Catalogo>();

			while(res.next()){

				Catalogo cet = new Catalogo();

				cet.setid(res.getInt("id"));
				cet.setnombre(res.getString("nombre"));
				cet.setdescripcion(res.getString("descripcion"));

				catalogos.add(cet);
			}

			aux.disconnect();
            stat.close();

			request.setAttribute("catalogos", catalogos);
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

