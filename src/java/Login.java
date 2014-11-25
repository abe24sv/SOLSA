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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String notify="",url="";
        String username=request.getParameter("username").trim().replaceAll("<", " ").replaceAll(">", " ");
        String password=request.getParameter("password").trim().replaceAll("<", " ").replaceAll(">", " ");
        
        DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));
        
        Connection con=aux.connect();
        
        if(con!=null){
            
            try{
                PreparedStatement query=con.prepareStatement("SELECT id,nombre,apellido,tipo FROM usuario WHERE username=SHA2( ? ,256) AND password=SHA2( ? ,256);");
                query.setString(1, username);
                query.setString(2, password);
                
                ResultSet result=query.executeQuery();
                
                if(result.next()){
                    
                    int tipo=result.getInt("tipo");
                    int id=result.getInt("id");
                    String name=result.getString("nombre");
                    String apellido=result.getString("apellido");
                    Usuario usr=null;
                    
                    
                    HttpSession session=request.getSession(true);
                    
                    switch(tipo){
                        case 0:
                            usr=new UsuarioAdministrador(id,name,apellido,tipo);
                            UsuarioAdministrador.interfaz(request);
                            break;
                        case 1:
                            usr=new UsuarioVentas(id,name,apellido,tipo);
                            UsuarioVentas.interfaz(request);
                            break;
                        case 2:
                            usr=new UsuarioAlmacen(id,name,apellido,tipo);
                            UsuarioAlmacen.interfaz(request);
                            break;
                        case 3:
                        case 4:
                            query=con.prepareStatement("SELECT cliente.* FROM usuario,usuario_ext,cliente WHERE usuario.id=usuario_ext.id_usuario AND usuario_ext.id_cliente=cliente.id AND usuario.id= ? ;");
                            query.setString(1,(id+""));
                            result=query.executeQuery();
                            
                            result.next();
                            Cliente cliente=new Cliente(result.getInt("id"),result.getString("nombre"),result.getString("logo"));
                            
                            if(tipo==3){
                                usr=new UsuarioCapturista(id,name,apellido,tipo,cliente);
                                UsuarioCapturista.interfaz(request);
                            }else{
                                usr=new UsuarioAprobador(id,name,apellido,tipo,cliente);
                                UsuarioAprobador.interfaz(request);
                            }
                            
                            session.setAttribute("cliente", cliente);
                            
                            break;
                    }
                    
                    session.setAttribute("user", usr);
                    url="/main.jsp";
                            
                }else{
                    notify="Información Incorrecta";
                    url="/index.jsp";
                }      
                
                aux.disconnect();
                query.close();  

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
