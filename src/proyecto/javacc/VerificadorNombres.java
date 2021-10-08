package proyecto.javacc;
import proyecto.sql.Conexion;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class VerificadorNombres {
	public static String usedb = null;
	
	public static void crearDatabase(String nombre) throws Exception{
		ArrayList<String> bases = new ArrayList<String>();
		
		try {
			Connection con = Conexion.getMainConnection(); 					
			DatabaseMetaData meta = con.getMetaData();
			ResultSet res = meta.getCatalogs();
			while (res.next()) {
               bases.add(res.getString("TABLE_CAT"));
            }
            con.close();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		if(bases.contains(nombre)){
			throw new Exception("Base de datos ya existente");
		}
		
	}
	
	public static void crearTabla(String nombre) throws Exception{
		ArrayList<String> tablas = new ArrayList<String>();
		if (usedb != null){		
			try {
	            Connection con = Conexion.getMainConnection(); 					
	            con.setCatalog(usedb);
	            Statement stmt = con.createStatement();
	            ResultSet res = stmt.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_CATALOG='" + usedb + "'");
	            while (res.next()) {
	            	tablas.add(res.getString("TABLE_NAME"));
	            }
	            res.close();
	            con.close();
	            
	            if(tablas.contains(nombre)){
	    			throw new Exception("Tabla ya existente");
	    		}
	        } catch (SQLException e){
	            JOptionPane.showMessageDialog(null, e);
	        }
		} else {
			throw new Exception("Debe especificarse una base de datos con el comando \"USE nombre_base_datos\"");
		}
		
	}
}
