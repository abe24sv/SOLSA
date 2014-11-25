import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CambiaCatalogo extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String notify="",url="";
        String oldnom=request.getParameter("oldnom");
        String id=request.getParameter("idLol");
        String nombre=request.getParameter("nombre").trim();
        String descripcion=request.getParameter("descripcion").trim();

		String nota;

        boolean seguir=true;

        if(nombre.isEmpty()){
            seguir=false;
        }
        if(descripcion.isEmpty()){
            seguir=false;
        }


        if(seguir){
            DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));
            Connection con=con=aux.connect();

			if(con!=null){
	            try{

	                PreparedStatement stat=con.prepareStatement("SELECT nombre FROM catalogo WHERE nombre='"+nombre+"';");

    	            ResultSet res=stat.executeQuery();

    	            if(res.next()&&!nombre.equalsIgnoreCase(oldnom)){
    	                nota="Nombre Ocupado, Sin Modificaciones";
    	            }else{
    	                stat.executeUpdate("UPDATE catalogo SET nombre='"+nombre+"', descripcion='"+descripcion+"' WHERE id='"+id+"';");
    	                nota="Información Modificada";
    	            }

    	            aux.disconnect();
    	            stat.close();

    	            request.setAttribute("nota", nota);

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

        }else{
            nota="Campos Vacíos";
            request.setAttribute("nota", nota);
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/main.jsp");
            if(disp!=null){
                disp.forward(request, response);
            }
        }

    }

}
