
package Base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class MysqlConnect {
    private Connection cnx ; 
    
    private static MysqlConnect instance ; 
    
    private final String USERNAME ="root" ; 
    private final String PASSWORD="" ; 
    private final String URL ="jdbc:mysql://localhost:3306/packandgo" ; 
    
    public MysqlConnect() {
        try { 
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD) ;
            System.out.println("Connecté avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static MysqlConnect getInstance(){
        if(instance == null )
            instance = new MysqlConnect() ; 
        return instance ;
    }

    public Connection getConnection() {
        return cnx;
    }
}
