
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BorraUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String id=request.getParameter("id");
        String tipo=request.getParameter("tipo");
        String url="";
        
        DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));
        Connection con=aux.connect();
        PreparedStatement update; 
        
        try{
        if(tipo.equals("3") || tipo.equals("4")){
            update=con.prepareStatement("DELETE FROM usuario_ext WHERE id_usuario= ? ;");
            update.setString(1, id);
            update.executeUpdate();
            
            update=con.prepareStatement("SELECT id FROM compra WHERE id_usuario= ? ;");
            update.setString(1, id);
            
            ResultSet result=update.executeQuery();
            LinkedList<Integer> aborrar=new LinkedList<Integer>();
            while(result.next()){
                aborrar.add(result.getInt(tipo));
            }
            
            Iterator<Integer> iterador=aborrar.iterator();
            while(iterador.hasNext()){
                update=con.prepareStatement("DELETE FROM compra_prod WHERE id_compra= ? ;");
                update.setString(1,iterador.next()+"");
                update.executeUpdate();
            }
            
            update=con.prepareStatement("DELETE FROM compra WHERE id_usuario= ? ;");
            update.setString(1, id);
            update.executeUpdate();
        }
        if(tipo.equals("1")){
            update=con.prepareStatement("DELETE FROM venta WHERE id_usuario= ? ;");
            update.setString(1, id);
            update.executeUpdate();
        }
        if(tipo.equals("1")){
            update=con.prepareStatement("DELETE FROM orden WHERE id_usuario= ? ;");
            update.setString(1, id);
            update.executeUpdate();
        }
        
        update=con.prepareStatement("DELETE FROM usuario WHERE id= ? ;");
        update.setString(1, id);
        update.executeUpdate();        
        
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        aux.disconnect();
        
        Usuario actual=(Usuario)request.getSession().getAttribute("user");
            switch(actual.gettipo()){
                case 0:
                    url="/ListUsers";
                break;
                case 4:
                    url="/ListExtUsers";
                break;
                    
            }
        RequestDispatcher disp = getServletContext().getRequestDispatcher(url);
        if(disp!=null){
            disp.forward(request, response);
        }
    }
}
