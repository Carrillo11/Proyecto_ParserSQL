/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.sql;
import java.sql.*;
/**
 *
 * @author renec
 */
public class Conexion {
    
    public static Connection getMainConnection() {
		Connection con = null;
		
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		String userName = "sa";
              //String userName = "DESKTOP-8HJ2MO3\\chris";
                String password = "123456";
                //String url = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;";
                String url = "jdbc:sqlserver://localhost;"+"user="+userName+";password="+password+";";
                con = DriverManager.getConnection(url);
                //con = DriverManager.getConnection(url);
                System.out.println(con);
        } catch (ClassNotFoundException ex) {
                System.out.println("Driver no encontrado"); 
        } catch (SQLException ex) {
        	System.out.println("Error Conexión SQL"); 
        }
        return con;
    }
    
}
