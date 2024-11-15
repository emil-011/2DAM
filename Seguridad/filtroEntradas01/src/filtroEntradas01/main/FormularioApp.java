package filtroEntradas01.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FormularioApp {

	private final static String ARCHIVO = "datos.txt";

	public static void main(String[] args) {
		Map<String, String> mapa = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				String key = line.
				mapa.put(, line)
				
			}
		} catch (IOException e) {

		}

		
	}

}
