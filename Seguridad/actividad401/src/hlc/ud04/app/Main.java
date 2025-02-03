package hlc.ud04.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import hlc.ud04.appsec.core.Clientes;
import hlc.ud04.appsec.core.GestorPersistencia;
import hlc.ud04.appsec.interfaz.Interfaz;
import hlc.ud04.appsec.interfaz.consola.InterfazConsola;
import hlc.ud04.appsec.persistencia.GestorPersistenciaSqlite;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;
import hlc.ud04.practica01.seguridad.SistemaSeguridadTemporal;
import hlc.ud04.practica01.totp.AutenticadorTemporal;

public class Main {
	/**
	 * La base de datos está creada en SQLite, almacenada en un archivo local
	 * dentro del proyecto. Tiene la tabla "Usuarios" y "Clientes".
	 */
	private static final String DATABASE = "jdbc:sqlite:base.db";
	
	public static void main(String[] args) {
        
        try (Connection conn = DriverManager.getConnection(DATABASE)) {
		    GestorPersistencia gestor = new GestorPersistenciaSqlite("base.db");
		    Clientes clientes = new Clientes(gestor);
		    
		    // Usamos nuestro sistema de seguridad
		    SistemaSeguridad sistemaSeguridad = new SistemaSeguridadTemporal(new AutenticadorTemporal());
		    
		    // Creamos interfaz de usuario de tipo consola y le pasamos el sistema de seguridad
		    Interfaz interfaz = new InterfazConsola(clientes, sistemaSeguridad);
		    interfaz.run();
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
	}
}
