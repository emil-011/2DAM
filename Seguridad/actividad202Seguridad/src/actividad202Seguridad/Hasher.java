package actividad202Seguridad;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

	public static void main(String[] args) {
		// Verificación de parámetros
		if (args.length < 1 || args.length > 2) {
			System.out.println("Usa: <nombre_fichero> [SHA|MD5]");
			System.exit(1); // Salir si los parámetros son incorrectos
		}

		// Nombre del fichero
		String fileName = args[0];
		// Algoritmo, por defecto SHA-256
		String algorithm = "";

		if (args.length == 2) {
			// Verificar si es SHA o MD5 y ajustar
			if (args[1].equalsIgnoreCase("SHA")) {
				algorithm = "SHA-256"; // Convertir a SHA-256
			} else if (args[1].equalsIgnoreCase("MD5")) {
				algorithm = "MD5"; // Mantener MD5
			} else {
				System.out.println("Algoritmo no válido. Usa SHA o MD5.");
				System.exit(1);
			}

			try {
				// Leemos el contenido del archivo
				byte[] fileContent = Files.readAllBytes(Paths.get(fileName));

				// Obtenemos la instancia del algoritmo de hash
				MessageDigest digest = MessageDigest.getInstance(algorithm);

				// Aplicamos el algoritmo de hash
				byte[] hashBytes = digest.digest(fileContent);

				// Convertimos el resumen a hexadecimal
				StringBuilder hexString = new StringBuilder();
				for (byte b : hashBytes) {
					hexString.append(String.format("%02x", b));
				}
				
				

				// Mostramos el hash
				System.out.println("Hash: " + hexString.toString());

			} catch (IOException e) {
				System.err.println("Error al leer el fichero");
			} catch (NoSuchAlgorithmException e) {
				System.err.println("Algoritmo no disponible");
			}

		}
		}
	}

