
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private String[] bdAdm;
    private String bdHost,bdName,ruta;
    private Connection con;
    
    public DBConnection(String[] bdAdm, String bdHost, String bdName){
        
        this.bdAdm=bdAdm;
        this.bdHost=bdHost;
        this.bdName=bdName;
        ruta="jdbc:mysql://"+this.bdHost+"/"+this.bdName;
                
    }
    
    public Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(ruta, bdAdm[0],bdAdm[1]);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
            return con;
    }
    
    public boolean disconnect(){
        try{
            con.close();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
}

