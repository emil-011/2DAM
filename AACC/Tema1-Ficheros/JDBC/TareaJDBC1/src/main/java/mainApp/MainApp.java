package mainApp;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import conexion.Conexion;

public class MainApp {

	public static void main(String[] args) {
			
		
		try(Connection conn = Conexion.conectar()){
			PreparedStatement st = conn.prepareStatement("SELECT * FROM actor");
			
			ResultSet rs = st.executeQuery();
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("actors_tabla.txt"))) {
				String linea;					
						
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String last_name = rs.getString(3);
				Timestamp date = rs.getTimestamp(4);
				
				linea = id + ", " + name + ", " + last_name + ", " + date;
				bw.write(linea);
				bw.newLine();
				
			}
			
			} catch (Exception e) {
			}
		
			
		} catch (Exception e) {
			
		}finally {
			
		}
		

	}

}
