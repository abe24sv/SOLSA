
import com.model.UsuarioCliente;
import com.model.Cliente;
import com.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class User extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doPost(request,response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String notify="",url="";
        String tipo=request.getParameter("tipo");
        
        DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));
        Connection con=aux.connect();
        PreparedStatement query;
        
        Usuario usr;
        
        if(tipo==null){
            HttpSession session=request.getSession(true);
            usr=(Usuario)session.getAttribute("user");
            switch(usr.gettipo()){
                case 3:
                case 4:
                    UsuarioCliente temp=(UsuarioCliente)session.getAttribute("user");
                    request.setAttribute("cliente", temp.getid_cliente());
                    request.setAttribute("user", usr);
                    break;
                case 0:
                case 1:
                case 2:
                    request.setAttribute("user", usr);
                    break;
            }
            if(usr.gettipo()==1||usr.gettipo()==2||usr.gettipo()==3){
                request.setAttribute("disable", "disabled");
            }else{
                request.setAttribute("disable", "");
            }
            
        }else{
            Cliente cliente;
            if(tipo.equals("editar")){
                String id=request.getParameter("id");
                 try{
                    query=con.prepareStatement("SELECT id,nombre,apellido,tipo FROM usuario WHERE id= ? ;");
                    query.setString(1, id);

                    ResultSet result=query.executeQuery();
                    if(result.next()){
                        usr=new Usuario(result.getInt("id"),result.getString("nombre"),result.getString("apellido"),result.getInt("tipo"));
                        if(usr.gettipo()==3||usr.gettipo()==4){
                            query=con.prepareStatement("SELECT cliente.* FROM usuario_ext,cliente WHERE id_cliente=id AND id_usuario= ? ;");
                            query.setString(1, id);
                            result=query.executeQuery();
                            if(result.next()){
                                cliente=new Cliente(result.getInt("id"),result.getString("nombre"),result.getString("logo"));
                                request.setAttribute("user", usr);
                            }
                        }
                        request.setAttribute("user", usr);
                    }
                 }catch(SQLException e){
                     e.printStackTrace();
                 }
            }
        }
        
        try{
            query=con.prepareStatement("SELECT * FROM cliente ORDER BY nombre;");
            LinkedList<Cliente> clientes=new LinkedList<Cliente>();
            
            ResultSet result=query.executeQuery();
            while(result.next()){
                clientes.add(new Cliente(result.getInt("id"),result.getString("nombre"),result.getString("logo")));
            }
            
            request.setAttribute("clientes", clientes);
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        
        aux.disconnect();
        
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/createuser.jsp");
        if(disp!=null){
            disp.forward(request, response);
        }
        
    }


}
