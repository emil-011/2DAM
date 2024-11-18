package validaciones03.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ValidadorCSV {

	private final static String ARCHIVO = "datos03.csv";

	public static void main(String[] args) {

		try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				// Dividir la línea en clave y valor
				String[] parts = line.split(",");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
