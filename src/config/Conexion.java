       
package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    
    Connection con = null;
    
    public Connection getConnection(){
       
        try {
            con =DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Maximo26092017");
           
        } catch (Exception e) {
            
            System.out.println("e");           
        }
        return con;
    }
}
