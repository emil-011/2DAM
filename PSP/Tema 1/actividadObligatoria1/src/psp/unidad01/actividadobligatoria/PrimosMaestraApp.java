package psp.unidad01.actividadobligatoria;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PrimosMaestraApp {
	private static final int VALOR_MINIMO = 2;
	private static final int VALOR_MAXIMO = 2147483647;
	private static final int PROCESADORES_DISPONIBLES = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args) {
		if (args.length < 2 || args.length > 3) {
			System.err.println("Error: Debes proporcionar al menos dos parámetros <Inicio> <Fin> del rango");
			return;
		}

		int inicio = 0;
		int fin = 0;
		int numNucleos = PROCESADORES_DISPONIBLES;

		try {
			inicio = Integer.parseInt(args[0]);
			fin = Integer.parseInt(args[1]);

			// Validar que los parámetros estén en el rango adecuado
			if (inicio < VALOR_MINIMO || fin < VALOR_MINIMO || inicio > VALOR_MAXIMO || fin > VALOR_MAXIMO) {
				System.err.println("Error: Los parámetros de inicio y fin deben ser valores dentro del rango válido.");
				return;
			}

			// Si hay 3 parámetros, tomamos el valor para el número de procesadores
			if (args.length == 3) {
				numNucleos = Integer.parseInt(args[2]);
				if (numNucleos > PROCESADORES_DISPONIBLES) {
					System.err.println("Error: No puedes utilizar más núcleos de los disponibles en el sistema.");
					return;
				}
			}

		} catch (NumberFormatException e) {
			System.err.println("Error: Los parámetros deben ser números enteros válidos");
			return;
		}

		// Cálculo del rango por proceso
		int rangoPorProceso = (fin - inicio + 1) / numNucleos;
		if (rangoPorProceso == 0) {
			System.err.println("Error: El rango es demasiado pequeño para los núcleos disponibles.");
			return;
		}

		System.out.print("[");

		for (int i = 0; i < numNucleos; i++) {
			int subInicio = inicio + i * rangoPorProceso;
			int subFin = (i == numNucleos - 1) ? fin : subInicio + rangoPorProceso - 1;

			ProcessBuilder proceso = new ProcessBuilder("java", "-cp", "./bin",
					"psp.unidad01.actividadobligatoria.esclava.PrimosEsclavaApp", String.valueOf(subInicio),
					String.valueOf(subFin));

			try {
				Process procesoEsclavo = proceso.start();

				try (BufferedReader reader = new BufferedReader(
						new InputStreamReader(procesoEsclavo.getInputStream()))) {
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.print(line + ", ");
					}
				}

				procesoEsclavo.waitFor();

			} catch (Exception e) {
				System.err.println("Error al ejecutar el proceso esclavo: " + e.getMessage());
			}
		}

		System.out.println("]");
	}
}
