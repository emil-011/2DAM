package jdbc.conexion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Funciones {
	
	// Funci�n para crear las tablas e insertar los datos.
    public static void crearTablaSuperusuarios(String nombreFichero) throws SQLException {
    	Connection con = new Conexion().conectar();

        // String para crear tabla.
        String dropTabSuperusuarios = "drop table if exists Superusuarios;";
        String tabSuperusuarios = "create table Superusuarios (idUser int auto_increment ,nombre varchar(100),apellidos varchar(100), user VARCHAR(6), constraint pk_superusuarios primary key (idUser));";

        try {
            PreparedStatement stat = null;

            // Comprobar y crear tablas.
            stat = con.prepareStatement(dropTabSuperusuarios);
            stat.execute();
            stat = con.prepareStatement(tabSuperusuarios);
            stat.execute();

            System.out.println("Todo bien");
        } catch (Exception e) {
            System.out.println("Ocurrio algun error");
        }
        
        
        
        // Creo el statement object.
    	Statement stmt = con.createStatement();
    	
    	// Set auto-commit a false
    	con.setAutoCommit(false);

        try {
        	// Declaro un ArrayList y llamo a la funci�n para leer el fichero.
        	ArrayList<String> datos = new ArrayList<String>();
        	datos = leerFicheros(nombreFichero);
        	int contaBatch = 0;
        	// Recorro el ArrayList y voy a�adiendo los datos a la base de datos.
        	for (int i = 0; i < datos.size(); i++) {
        		String fila[] = datos.get(i).split(",");
        		
	        		// Inserto los datos del superusuario con preparedStatement.
	            	String SQL = "insert into Superusuarios (nombre, apellidos, user) values (?,?,?)";
	    			PreparedStatement stat = con.prepareStatement(SQL);
	    			stat.setString(1, fila[0]);
	    			stat.setString(2, fila[1]);
	    			stat.setString(3, "2DAM"+fila[0].charAt(0)+fila[1].charAt(1));
	    			stat.execute();
 
            		// Agrego la declaraci�n SQL anterior en el lote.
	    			stat.addBatch();
            		
            		contaBatch++;
            		// Cuanto contaBatch llega a 10 ejecuto las 10 sentencias de cada lote.
            		if (contaBatch == 10) {
            			stmt.executeBatch();
            			stmt.clearBatch();
            			contaBatch = 0;
            		}
            		// Este else if es para que en el caso de que no llegue a 10 pero sea el final del fichero, se ejecute.
            		else if (i == datos.size()-1) {
            			stmt.executeBatch();
            			stmt.clearBatch();
            		}
                	con.commit(); 
        	}
        
        	System.out.println("Todo ok");
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Ocurrio algun error");
        }
        // Cierro conexi�n.
    	con.close();  
        
        
    }
    

    
    // Leer fichero y pasarlo a un ArrayList.
    public static ArrayList<String> leerFicheros(String archivo) throws IOException {
        ArrayList<String> listado = new ArrayList<String>();

        try {
        	// Leo el archivo.
            BufferedReader lector = new BufferedReader(new FileReader(archivo));

            // Leo las l�neas del fichero y los a�ado al arrayList.
            String linea = lector.readLine();
            
            while (linea != null) {
                listado.add(linea);
                linea = lector.readLine();
            }
            
            lector.close();
        }catch (Exception e) {
            System.out.println("El fichero no existe");
        }   
        return listado;
    }
    

}
