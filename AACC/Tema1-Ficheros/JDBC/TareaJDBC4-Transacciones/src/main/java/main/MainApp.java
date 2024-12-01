package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import utils.Asignatura;
import utils.Profesor;

public class MainApp {

	// Inserts SQL
	private static final String INSERT_PROFESOR = "INSERT INTO profesor (NIF, Nombre, Especialidad, Telefono) VALUES (?, ?, ?, ?)";
	private static final String INSERT_ASIGNATURA = "INSERT INTO asignatura (CodAsignatura, Nombre) VALUES (?, ?)";

	public static void main(String[] args) throws Exception {
		// Creamos las listas de profesores y de asignaturas
		List<Profesor> listaProfesores = new ArrayList<>();
		List<Asignatura> listaAsignaturas = new ArrayList<>();

		// Conectamos con la base de datos
		try (Connection conn = Conexion.conectar()) {
			// Leemos el archivo CSV
			try (BufferedReader br = new BufferedReader(new FileReader("resources/Fichero.csv"))) {
				String linea;
				// Mientras la linea no sea nula
				while ((linea = br.readLine()) != null) {
					// Si empieza por P = Profesor
					if (linea.startsWith("P")) {
						String[] campos = linea.split(";");
						String nif = campos[1];
						String nombre = campos[2];
						String especialidad = campos[3];
						String telefono = campos[4];
						// Creamos profesor
						Profesor profesor = new Profesor(nif, nombre, especialidad, telefono);
						// Añadimos el profesor a la lista
						listaProfesores.add(profesor);
						
						// Si empieza por A = Asignatura
					} else if (linea.startsWith("A")) {
						String[] campos = linea.split(";");
						String codAsignatura = campos[1];
						String nombre = campos[2];
						// Creamos la clase asginatura
						Asignatura asignatura = new Asignatura(codAsignatura, nombre);
						// Añadimos la asignatura a la lista
						listaAsignaturas.add(asignatura);
					}
				}
			} catch (Exception e) {
				System.err.println("ERROR al leer el archivo: " + e.getMessage());
			}

			
			try {
				// Quitamos el autocommit
				conn.setAutoCommit(false);

				// Insertamos los profesores
				PreparedStatement stm = conn.prepareStatement(INSERT_PROFESOR, PreparedStatement.RETURN_GENERATED_KEYS);
				for (Profesor profesor : listaProfesores) {
					stm.setString(1, profesor.getNif());
					stm.setString(2, profesor.getNombre());
					stm.setString(3, profesor.getEspecialidad());
					stm.setString(4, profesor.getTelefono());
					stm.executeUpdate();
				}

				// Insertamos las asignaturas
				PreparedStatement stm2 = conn.prepareStatement(INSERT_ASIGNATURA, PreparedStatement.RETURN_GENERATED_KEYS);
				for (Asignatura asignatura : listaAsignaturas) {
					stm2.setString(1, asignatura.getCodAsignatura());
					stm2.setString(2, asignatura.getNombre());
					stm2.executeUpdate();
				}

				// Confirmamos transacción
				conn.commit();

			} catch (Exception e) {
				// Si da error hacemos rollback para
				// volver al estado anterior
				conn.rollback();
				System.err.println("ERROR durante la inserción en la base de datos: " + e.getMessage());
			}

		} catch (Exception e) {
			System.err.println("ERROR al conectar con la base de datos: " + e.getMessage());
		}
	}
}
