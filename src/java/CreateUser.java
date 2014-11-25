import com.model.UsuarioCliente;
import com.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String name=request.getParameter("name").trim().replaceAll("<", " ").replaceAll(">", " ");
        String apellido=request.getParameter("apellido").trim().replaceAll("<", " ").replaceAll(">", " ");
        String username=request.getParameter("username").trim().replaceAll("<", " ").replaceAll(">", " ");
        String password=request.getParameter("password").trim().replaceAll("<", " ").replaceAll(">", " ");
        String cpassword=request.getParameter("cpassword").trim().replaceAll("<", " ").replaceAll(">", " ");
        String notify="";
        String url="";
        
        Usuario actual=(Usuario)request.getSession().getAttribute("user");
            switch(actual.gettipo()){
                case 0:
                    url="/ListUsers";
                break;
                case 4:
                    url="/ListExtUsers";
                break;
                    
            }
        
        DBConnection aux=new DBConnection(getServletContext().getInitParameter("bdAdm").split("/"),getServletContext().getInitParameter("bdHost"),getServletContext().getInitParameter("bdNom"));
        
        Connection con=aux.connect();
        
        boolean seguir=true;
        
        if(name.isEmpty()){
            seguir=false;
            notify="Nombre Vacío ";
        }
        if(apellido.isEmpty()){
            seguir=false;
            notify="Apellido Vacío ";
        }
        
        if(username.isEmpty()){
            seguir=false;
            notify="Username Vacío ";
        }else{
            try{
                System.out.println("Ingresa: " + username);
                PreparedStatement statement=con.prepareStatement("SELECT * FROM usuario WHERE username=SHA2( ? ,256) ;");
                statement.setString(1,username);
                
                ResultSet result=statement.executeQuery();
                if(result.next()){
                    seguir=false;
                    notify="Username Ocupado ";
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        
        if(password.isEmpty()){
            seguir=false;
            notify="Contraseña Vacía ";
        }else{
            if(!password.equals(cpassword)){
                seguir=false;
                notify="Contraseñas Diferentes ";
            }
        }
        
        if(seguir){
            con=aux.connect();
            try{
                PreparedStatement query=con.prepareStatement("INSERT INTO usuario(nombre,apellido,username,password,tipo) VALUES ( ? , ? ,SHA2( ? , 256),SHA2( ? , 256), ? );",Statement.RETURN_GENERATED_KEYS);
                query.setString(1, name);
                query.setString(2, apellido);
                query.setString(3, username);
                query.setString(4, password);
                HttpSession session=request.getSession();
                Usuario usr=(Usuario)session.getAttribute("user");
                int insert;
                ResultSet keys;
                int id_cliente;
                if(usr.gettipo()==0){
                    String tipo=request.getParameter("tipo");
                    query.setString(5, tipo);
                    query.executeUpdate();
                    keys = query.getGeneratedKeys();    
                    keys.next();  
                    insert = keys.getInt(1);
                    System.out.println(insert);
                    if(tipo.equals("3") || tipo.equals("4")){
                        String id_cli=request.getParameter("cliente");
                        
                        query=con.prepareStatement("INSERT INTO usuario_ext VALUES ( ? , ? );");
                        query.setString(1, insert+"");
                        query.setString(2, id_cli);

                        query.executeUpdate();
                    }
                }else{
                    query.setString(5, "3");
                    query.executeUpdate();
                    keys = query.getGeneratedKeys();    
                    keys.next();  
                    insert = keys.getInt(1);
                    UsuarioCliente temp=(UsuarioCliente)usr;
                    id_cliente=temp.getid_cliente().getid();
                    query=con.prepareStatement("INSERT INTO usuario_ext VALUES ( ? , ? );");
                    query.setString(1, insert+"");
                    query.setString(2, id_cliente+"");
                    
                    query.executeUpdate();
                }
                 
                notify=("Usuario Creado Exitosamente");
            }catch(SQLException e){
                e.printStackTrace();
            }
                    
            aux.disconnect();
            
            
            RequestDispatcher disp = getServletContext().getRequestDispatcher(url);
            if(disp!=null){
                disp.forward(request, response);
            }
        }else{
            RequestDispatcher disp = getServletContext().getRequestDispatcher(url);
            if(disp!=null){
                disp.forward(request, response);
            }
        }
        
    }

}
