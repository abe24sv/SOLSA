import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;


@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)

public class CreaCliente extends HttpServlet {

	private String filePath;

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("Content-Disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

	public void init(){
		filePath = getServletContext().getInitParameter("im-dir");
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		init();

		String notify="",url="";
        String nombre=request.getParameter("nombre").trim();

		Part part = request.getPart("logo");
		String fileName=extractFileName(part);

		File fileSaveDir=new File(filePath+fileName);

		part.write(filePath+fileName);

        String lol= "./IMG/" + fileName;
        String nota;

        boolean seguir=true;

        if(nombre.isEmpty()){
            seguir=false;
        }
        if(lol.isEmpty()){
            seguir=false;
        }


        if(seguir){
            DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));
            Connection con=con=aux.connect();

			if(con!=null){
	            try{

	                PreparedStatement stat=con.prepareStatement("SELECT nombre FROM cliente WHERE nombre='"+nombre+"';");

    	            ResultSet res=stat.executeQuery();

    	            if(res.next()){
    	                nota="Nombre Ocupado, Sin Modificaciones";
    	            }else{
    	                stat.executeUpdate("INSERT INTO cliente(nombre, logo) VALUES('"+nombre+"', '"+lol+"');");
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
