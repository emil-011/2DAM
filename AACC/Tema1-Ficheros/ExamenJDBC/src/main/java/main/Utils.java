package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.Conexion;
import model.Pelicula;

public class Utils {

	// Create Tabla
	private static final String CREATE_TABLE_PELICULAS = "CREATE TABLE IF NOT EXISTS peliculas_cortas ("
			+ "film_id INT PRIMARY KEY, " + "title VARCHAR(128), " + "description TEXT, " + "release_year YEAR, "
			+ "language_id TINYINT UNSIGNED, " + "length SMALLINT, " + "rating ENUM('G','PG','PG-13','R','NC-17'), "
			+ "FOREIGN KEY (language_id) REFERENCES language (language_id) " + "ON DELETE RESTRICT "
			+ "ON UPDATE CASCADE" + ");";

	// Insert si la longitud es menor que 100
	private static final String INSERT_CONDICIONAL = "INSERT INTO peliculas_cortas (film_id, title, description, "
			+ "release_year, language_id, length, rating) "
			+ "SELECT film_id, title, description, release_year, language_id, length, rating "
			+ "FROM film WHERE length < 100;";

	// Insert para el csv
	private static final String INSERT_NORMAL = "INSERT INTO peliculas_cortas (film_id, title, description, release_year, language_id, "
			+ "length, rating) VALUES (?, ?, ?, ?, ?, ?, ?)";

	// Lotes del batch (editable)
	private static final int LOTE = 10;

	// Nombre por defecto
	private static String nombreArchivo;

	private static final String SELECT_LANGUAGE = "SELECT p.film_id, p.title, p.description, p.release_year, p.language_id, p.length, p.rating "
			+ "FROM peliculas_cortas p " + "INNER JOIN language l ON p.language_id = l.language_id "
			+ "WHERE l.name = ?";

	// Método que crea las tablas y realiza la inserción
	public static void crearTablaPeliculas() {
		// Conectamos con la base de datos
		try (Connection conn = Conexion.conectar()) {
			// Preparamos la sentencia
			PreparedStatement pst = conn.prepareStatement(CREATE_TABLE_PELICULAS);
			// Ejecutamos
			pst.executeUpdate();
			System.out.println("Tablas 'peliculas_cortas' creadas con éxito.");
			// Insertamos películas
			insertPeliculas();
			System.out.println("Inserciones realizadas con éxito.");
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Método que inserta las películas
	public static void insertPeliculas() {
		// Conectamos con la base de datos
		try (Connection conn = Conexion.conectar()) {
			// Preparamos la sentencia
			PreparedStatement pst = conn.prepareStatement(INSERT_CONDICIONAL);
			// Ejecutamos
			pst.executeUpdate();
			System.out.println("Inserciones de películas realizadas con éxito.");
		} catch (SQLException e) {
			System.err.println("Error al insertar datos en la tabla: " + e.getMessage());
		}
	}

	/**
	 * Metodo que carga las películas desde  un csv
	 */
	public static void cargarPeliculas() {
		try (BufferedReader lector = new BufferedReader(new FileReader("resources/pelis.csv"))) {
			// Pasamos la primera línea
			lector.readLine();
			String linea;
			try (Connection conn = Conexion.conectar()) {
				PreparedStatement pst = conn.prepareStatement(INSERT_NORMAL);
				int contador = 0;

				while ((linea = lector.readLine()) != null) {
					String[] campos = linea.split(";");
					if (campos.length == 7) {
						pst.setInt(1, Integer.parseInt(campos[0]));
						pst.setString(2, campos[1]);
						pst.setString(3, campos[2]);
						pst.setInt(4, Integer.parseInt(campos[3]));
						pst.setInt(5, Integer.parseInt(campos[4]));
						pst.setInt(6, Integer.parseInt(campos[5]));
						pst.setString(7, campos[6]);
						pst.addBatch();

						if (contador % LOTE == 0) {
							pst.executeBatch();
							pst.clearBatch();
						}
						contador++;
					}
				}
				// Ejecutamos cualquier batch restante
				pst.executeBatch();
				pst.clearBatch();
				System.out.println("Películas cargadas desde el CSV con éxito.");
			} catch (SQLException e) {
				System.err.println("Error al insertar datos desde el CSV: " + e.getMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recibe un nombre de idioma (language) como parámetro (por ejemplo "English",
	 * o "French"), y un booleano que indique texto sí o no
	 * 
	 * @param idioma Idioma de la película
	 * @param texto  Si es .csv o .dat
	 */
	public static void peliculasToFichero(String idioma, boolean texto) {
		if (texto) {
			nombreArchivo = "peliculas_lang_" + idioma.toLowerCase() + ".csv";
		} else {
			nombreArchivo = "peliculas_lang_" + idioma.toLowerCase() + ".dat";
		}

		// Conectamos con la base de datos
		try (Connection conn = Conexion.conectar(); PreparedStatement pst = conn.prepareStatement(SELECT_LANGUAGE)) {

			pst.setString(1, idioma);
			ResultSet rs = pst.executeQuery();

			if (texto) {
				try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
					escritor.write("film_id;title;description;release_year;language_id;length;rating");
					escritor.newLine();

					while (rs.next()) {
						// Obtenemos datos de la película
						int filmId = rs.getInt("film_id");
						String title = rs.getString("title");
						String description = rs.getString("description");
						int releaseYear = rs.getInt("release_year");
						int languageId = rs.getInt("language_id");
						int length = rs.getInt("length");
						String rating = rs.getString("rating");

						// Escribimos en el csv
						escritor.write(filmId + ";" + title + ";" + description + ";" + releaseYear + ";" + languageId
								+ ";" + length + ";" + rating);
						escritor.newLine();
					}
				}
			} else {
				try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
					while (rs.next()) {
						// Obtenemos datos de la película
						int filmId = rs.getInt("film_id");
						String title = rs.getString("title");
						String description = rs.getString("description");
						int releaseYear = rs.getInt("release_year");
						int languageId = rs.getInt("language_id");
						int length = rs.getInt("length");
						String rating = rs.getString("rating");

						// Crear el objeto Pelicula
						Pelicula pelicula = new Pelicula(filmId, title, description, releaseYear, languageId, length,
								rating);

						// Escribir el nuevo objeto Pelicula en el archivo .dat
						escritor.writeObject(pelicula);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error en la consulta: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error al escribir el archivo: " + e.getMessage());
		}
	}
	
	/**
	 * Metodo que carga las peliculas de un .dat
	 * @param archivo Archivo a leer
	 */
	public static void cargarPeliculasBinario(String archivo) {
		verificarExtension(archivo , ".dat");
		try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivo))) {
			@SuppressWarnings("unused")
			Object objeto;
			while ((objeto = lector.readObject()) != null) {
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	// Metodo para comprobrar la extension
	private static void verificarExtension(String archivo, String extensionEsperada) {
		if (!archivo.endsWith(extensionEsperada)) {
			throw new IllegalArgumentException("El archivo debe tener la extensión " + extensionEsperada);
		}
	}

}
