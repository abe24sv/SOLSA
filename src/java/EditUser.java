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

public class EditUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String name=request.getParameter("name").trim().replaceAll("<", " ").replaceAll(">", " ");
        String apellido=request.getParameter("apellido").trim().replaceAll("<", " ").replaceAll(">", " ");
        String username=request.getParameter("username").trim().replaceAll("<", " ").replaceAll(">", " ");
        String password=request.getParameter("password").trim().replaceAll("<", " ").replaceAll(">", " ");
        String cpassword=request.getParameter("cpassword").trim().replaceAll("<", " ").replaceAll(">", " ");
        String id=request.getParameter("id");
        String url="";
        String notify="";
        
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
        
        Connection con=con=aux.connect();
        
        Usuario usuario=null;
        String pastusername=null;
        String pastpassword=null;
        
        try{
        PreparedStatement state=con.prepareStatement("SELECT * FROM usuario WHERE id= ? ;");
        state.setString(1,id);
        
        ResultSet res=state.executeQuery();
        res.next();
        usuario=new Usuario(res.getInt("id"),res.getString("nombre"),res.getString("apellido"),res.getInt("tipo"));
        pastusername=res.getString("username");
        pastpassword=res.getString("password");
        
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        
        boolean seguir=true;
        
        if(name.isEmpty()){
            name=usuario.getnombre();
        }
        
        if(apellido.isEmpty()){
            apellido=usuario.getapellido();
        }
        
        if(username.isEmpty()){
           username=pastusername;
        }else{
            try{
                PreparedStatement statement=con.prepareStatement("SELECT SHA2( ? , 256);");
                statement.setString(1,username);
                ResultSet result=statement.executeQuery();
                result.next();
                username=result.getString(1);
                
                if(!username.equals(pastusername)){
                    statement=con.prepareStatement("SELECT * FROM usuario WHERE username= ? ;");
                    statement.setString(1,username);

                    result=statement.executeQuery();
                    if(result.next()){
                        seguir=false;
                        notify="Username Ocupado ";
                    }
                }

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        
        if(password.isEmpty()){
            password=pastpassword;
        }else{
            if(!password.equals(cpassword)){
                seguir=false;
                notify="Contraseñas Diferentes ";
            }else{
                try{
                    PreparedStatement statement=con.prepareStatement("SELECT SHA2( ? , 256);");
                    statement.setString(1,password);
                    ResultSet result=statement.executeQuery();
                    result.next();
                    password=result.getString(1);

                }catch(SQLException e){
                    e.printStackTrace();
                }
                
            }
        }
        
        if(seguir){
            con=aux.connect();
            try{
                PreparedStatement query=con.prepareStatement("UPDATE usuario SET nombre = ? , apellido= ? ,username= ? , password= ? WHERE id= ? ;",Statement.RETURN_GENERATED_KEYS);
                query.setString(1, name);
                query.setString(2, apellido);
                query.setString(3, username);
                query.setString(4, password);
                query.setString(5, id);
                
                System.out.println(name + " " + apellido + " " + username + " " + name + " " + password);

                query.executeUpdate();
                 
                notify=("Información Editada Exitosamente");
                HttpSession session=request.getSession();
                Usuario us=(Usuario)session.getAttribute("user");
                if(id.equals(us.getid()+"")){
                    notify=("Los Cambios se Aplicarán en su Siguiente Inicio de Sesión");
                    System.out.println(notify);
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
                    
            aux.disconnect();
            
            RequestDispatcher disp = getServletContext().getRequestDispatcher(url);
            if(disp!=null){
                disp.forward(request, response);
            }
        }
        
    }

}

