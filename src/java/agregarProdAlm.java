
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
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

public class agregarProdAlm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String notify="",url="/index.jsp";
        
        DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));
        
        Connection con=aux.connect();
        //int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        int marca = Integer.parseInt(request.getParameter("marca"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        int subcategoria = Integer.parseInt(request.getParameter("subcategoria"));
        
        if(con!=null){
            
            try{
                //Subir imagen
                String filePath = getServletContext().getInitParameter("im-dir");
                Part part = request.getPart("logo");
		String fileName=extractFileName(part);

		File fileSaveDir=new File(filePath+fileName);

		part.write(filePath+fileName);

                String lol= "./IMG/" + fileName;
                if(fileName.isEmpty()){
                    lol = "";
                }
                int categoria=0;
                PreparedStatement query2= con.prepareStatement("SELECT * FROM subcategoria JOIN categoria ON subcategoria.id_categoria = categoria.id WHERE subcategoria.id = ?");
                query2.setInt(1, subcategoria);
                
                ResultSet result = query2.executeQuery();
                if(result.next()){
                    categoria = result.getInt("categoria.id");
                }
                PreparedStatement query=con.prepareStatement("INSERT INTO producto(nombre, descripcion, cantidad, id_categoria, id_subcategoria, imagen, id_marca) VALUES( ? , ? , ? , ? , ? , ? , ? )");
                query.setString(1, nombre);
                query.setString(2, descripcion);
                query.setInt(3, cantidad);
                query.setInt(4, categoria);
                query.setInt(5, subcategoria);
                query.setString(6, lol);
                query.setInt(7, marca);
                query.executeUpdate();
               url="/ListProducts";  
                
                aux.disconnect();
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
