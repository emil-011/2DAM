package psp.unidad01.actividadobligatoria;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrimosMaestraApp {
	private static final int VALOR_MINIMO = 2;
	private static final int VALOR_MAXIMO = 2147483647;
	private static final int PROCESADORES_DISPONIBLES = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args) {
		if (args.length < 2 || args.length > 3) {
			System.err.println("Insuficiento número de parámetros\nUSO: \n"
					+ "java -jar maestra.jar valor1 valor2 valor3\n"
					+ "valor1 -> (obligatorio) primer extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
					+ "valor2 -> (obligatorio)segundo extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
					+ "valor3 -> (opcional) número de procesos a lanzar. Su equipo tiene 4 núcleos\n" );
			return;
		}

		int inicio = 0;
		int fin = 0;
		int numNucleos = PROCESADORES_DISPONIBLES;

		try {
			inicio = Integer.parseInt(args[0]);
			fin = Integer.parseInt(args[1]);

			if (inicio < VALOR_MINIMO || fin < VALOR_MINIMO || inicio > VALOR_MAXIMO || fin > VALOR_MAXIMO) {
				System.err.println("Enteros no válidos, valores válidos entre 2 y 2.147.483.647\nUSO:\n"
						+ "java -jar maestra.jar valor1 valor2 valor3\n"
						+ "valor1 -> (obligatorio) primer extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
						+ "valor2 -> (obligatorio)segundo extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
						+ "valor3 -> (opcional) número de procesos a lanzar. Su equipo tiene " + numNucleos + " núcleos");
				return;
			}

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

		int rangoPorProceso = (fin - inicio + 1) / numNucleos;
		if (rangoPorProceso == 0) {
			System.err.println("Error: El rango es demasiado pequeño para los núcleos disponibles.");
			return;
		}

		long tiempoInicioPrograma = System.currentTimeMillis();
		int totalPrimos = 0;
		int totalAnalizados = 0;
		List<Integer> primosGlobales = new ArrayList();

		for (int i = 0; i < numNucleos; i++) {
			int subInicio = inicio + i * rangoPorProceso;
			int subFin = (i == numNucleos - 1) ? fin : subInicio + rangoPorProceso - 1;

			ProcessBuilder proceso = new ProcessBuilder("java", "-cp", "./bin",
					"psp.unidad01.actividadobligatoria.esclava.PrimosEsclavaApp", String.valueOf(subInicio),
					String.valueOf(subFin));

			try {
				long tiempoInicioProceso = System.currentTimeMillis();
				Process procesoEsclavo = proceso.start();

				int primosEncontrados = 0;
				int numerosAnalizados = subFin - subInicio + 1;

				try (BufferedReader reader = new BufferedReader(
						new InputStreamReader(procesoEsclavo.getInputStream()))) {
					String line;
					while ((line = reader.readLine()) != null) {
						primosGlobales.add(Integer.parseInt(line)); // Add primes to set to avoid duplicates
						primosEncontrados++;
					}
				}

				procesoEsclavo.waitFor();
				long tiempoFinProceso = System.currentTimeMillis();
				long tiempoProceso = tiempoFinProceso - tiempoInicioProceso;

				System.out.printf(
						"Proceso P%d tiempo empleado: %d ms se han encontrado: %d números primos entre los %d analizados%n",
						i, tiempoProceso, primosEncontrados, numerosAnalizados);

				totalPrimos += primosEncontrados;
				totalAnalizados += numerosAnalizados;

			} catch (Exception e) {
				System.err.println("Error al ejecutar el proceso esclavo: " + e.getMessage());
			}
		}

		long tiempoFinPrograma = System.currentTimeMillis();
		long tiempoTotal = tiempoFinPrograma - tiempoInicioPrograma;

		System.out.printf(
				"Tiempo total empleado en el programa: %d ms se han encontrado %d números primos entre los %d analizados%n",
				tiempoTotal, totalPrimos, totalAnalizados);
		
		Collections.sort(primosGlobales);

		// Print all unique prime numbers found at the end, with a newline after every
		// 10 numbers
		System.out.print("[");
		int count = 0;
		for (Integer primo : primosGlobales) {
			System.out.print(primo + ", ");
			count++;
			if (count % 25 == 0) {
				System.out.println();
			}
		}
		System.out.print("]");
	}
}
