package actividad106Procesos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;

public class Esclavo {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			String inputText;
			Random random = new Random();

			// Leer entrada del maestro y procesar hasta que no haya más datos
			while ((inputText = br.readLine()) != null && !inputText.isEmpty()) {
				// Elegir una operación aleatoria: mayusculizar, minusculizar o capitalizar
				int operation = random.nextInt(3); // 0, 1 o 2

				String outputText = "";
				switch (operation) {
				case 0:
					outputText = inputText.toUpperCase();
					break;
				case 1:
					outputText = inputText.toLowerCase();
					break;
				case 2:
					outputText = capitalize(inputText);
					break;
				}

				// Devolver el texto procesado al maestro
				System.out.println(outputText);
				// Asegurarse de que se envíe inmediatamente
				System.out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Método para capitalizar el texto
	private static String capitalize(String text) {
		String[] words = text.toLowerCase().split("\\s+");
		StringBuilder capitalizedText = new StringBuilder();
		for (String word : words) {
			if (!word.isEmpty()) {
				capitalizedText.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
			}
		}
		return capitalizedText.toString().trim();
	}
}
