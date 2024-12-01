package actividad106Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Maestro {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			// Crear proceso esclavo
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", "./bin", "actividad106Procesos.Esclavo");
			Process procesoEsclavo = pb.start();

			// Flujo de salida del maestro al esclavo
			OutputStream output = procesoEsclavo.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);

			// Flujo de entrada del esclavo al maestro
			BufferedReader reader = new BufferedReader(new InputStreamReader(procesoEsclavo.getInputStream()));

			String linea;

			while (true) {
				System.out.print("Escribe algo (déjalo vacío para terminar):");
				linea = sc.nextLine();

				// Si el usuario deja la línea vacía, terminamos el ciclo
				if (linea.isEmpty()) {
					break;
				}

				// Enviar texto al esclavo
				writer.println(linea);

				// Leer la respuesta del esclavo
				String respuesta = reader.readLine();
				System.out.println("Respuesta del esclavo: " + respuesta);
			}

			// Cerrar flujos
			writer.close();
			reader.close();
			procesoEsclavo.destroy();

		} catch (IOException e) {
			e.printStackTrace();
		}

		sc.close();
	}
}
