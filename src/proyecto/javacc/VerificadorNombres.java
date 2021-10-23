package proyecto.javacc;
import proyecto.sql.Conexion;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class VerificadorNombres {
	public static String usedb = null;
	
	public static void crearDatabase(String nombre) throws Exception{
		ArrayList<String> bases = new ArrayList<String>();/*Litsa de
                bases de datos que se reciben desde SQLparse*/
		
		try {
			Connection con = Conexion.getMainConnection(); 					
			DatabaseMetaData meta = con.getMetaData(); /*ver tablas o columnas que tengas una base*/
			ResultSet res = meta.getCatalogs(); /*Se traen todos los catalogos que se tienen*/
			while (res.next()) {
               bases.add(res.getString("TABLE_CAT")); //Aqui se gregan las tablas a el arreglo bases.
            }
            con.close();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		if(bases.contains(nombre)){
			throw new Exception("Base de datos ya existente"); /*Si la base ya existe se miestra la Exepction*/
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
                    /*Consulta directamente la base de datos en uso*/
	            while (res.next()) {
	            	tablas.add(res.getString("TABLE_NAME"));/*Se van agregando las tablas*/
	            }
	            res.close();
	            con.close();
	            
	            if(tablas.contains(nombre)){
	    			throw new Exception("Tabla ya existente"); /*La tabla ya eciste en la base*/
	    		}
	        } catch (SQLException e){
	            JOptionPane.showMessageDialog(null, e);
	        }
		} else {
			throw new Exception("Debe especificarse una base de datos con el comando \"USE nombre_base_datos\"");/*No se especifico la tabla*/
		}
		
	}
}
