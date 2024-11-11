package psp.unidad01.actividadobligatoria;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimosMaestraApp {
	// Establecemos los valores máximos y mínimos
	// Y obtenemos el número de procesadores disponibles
	private static final int VALOR_MINIMO = 2;
	private static final int VALOR_MAXIMO = 2147483647;
	private static final int PROCESADORES_DISPONIBLES = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args) {
		// Lanzamos error si el número de parámetros es erróneo
		if (args.length < 2 || args.length > 3) {
			System.err.println("Insuficiento número de parámetros\nUSO: \n"
					+ "java -jar maestra.jar valor1 valor2 valor3\n"
					+ "valor1 -> (obligatorio) primer extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
					+ "valor2 -> (obligatorio)segundo extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
					+ "valor3 -> (opcional) número de procesos a lanzar. Su equipo tiene " + PROCESADORES_DISPONIBLES + " núcleos\n");
			return;
		}

		int inicio = 0;
		int fin = 0;
		int numNucleos = PROCESADORES_DISPONIBLES;

		try {
			inicio = Integer.parseInt(args[0]);
			fin = Integer.parseInt(args[1]);

			// Lanzamos error si algún valor no es válido
			if (inicio < VALOR_MINIMO || fin < VALOR_MINIMO || inicio > VALOR_MAXIMO || fin > VALOR_MAXIMO) {
				System.err.println("Enteros no válidos, valores válidos entre 2 y 2.147.483.647\nUSO:\n"
						+ "java -jar maestra.jar valor1 valor2 valor3\n"
						+ "valor1 -> (obligatorio) primer extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
						+ "valor2 -> (obligatorio)segundo extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
						+ "valor3 -> (opcional) número de procesos a lanzar. Su equipo tiene " + numNucleos
						+ " núcleos");
				return;
			}

			// Lanzamos error si el número de procesos especificado excede los núcleos
			// disponibles
			if (args.length == 3) {
				numNucleos = Integer.parseInt(args[2]);
				if (numNucleos > PROCESADORES_DISPONIBLES) {
					System.err.println("Se solicitan más procesos que núcleos disponibles (" + PROCESADORES_DISPONIBLES
							+ ")\nUSO:\n" + "java -jar maestra.jar valor1 valor2 valor3\n"
							+ "valor1 -> (obligatorio) primer extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
							+ "valor2 -> (obligatorio)segundo extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
							+ "valor3 -> (opcional) número de procesos a lanzar. Su equipo tiene "
							+ PROCESADORES_DISPONIBLES + " núcleos");
					return;
				}
			}

			// Lanzamos error si el número de procesos no es un entero
		} catch (NumberFormatException e) {
			System.err.println("El valor del número de procesos no es un entero\nUSO:\n"
					+ "java -jar maestra.jar valor1 valor2 valor3\n"
					+ "valor1 -> (obligatorio) primer extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
					+ "valor2 -> (obligatorio)segundo extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
					+ "valor3 -> (opcional) número de procesos a lanzar. Su equipo tiene " + PROCESADORES_DISPONIBLES + " núcleos");
			return;
		}

		// Calculamos el rango que cada proceso analizará
		int rangoPorProceso = (fin - inicio + 1) / numNucleos;
		// Lanzamos error si se solicitan más procesos que números a analizar
		if (rangoPorProceso == 0) {
			System.err.println("Se solicitan más procesos que números se quieren analizar\nUSO:\n"
					+ "java -jar maestra.jar valor1 valor2 valor3\n"
					+ "valor1 -> (obligatorio) primer extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
					+ "valor2 -> (obligatorio)segundo extremo del rango de valores a analizar (entre 2 y 2.147.483.647)\n"
					+ "valor3 -> (opcional) número de procesos a lanzar. Su equipo tiene 4 núcleos");
			return;
		}

		// Inicializamos variables
		long tiempoInicioPrograma = System.currentTimeMillis();
		int totalPrimos = 0;
		int totalAnalizados = 0;
		// Lista donde vamos a almacenar los primos encontrados
		List<Integer> primosGlobales = new ArrayList<>();

		// Lanzamos los procesos esclavos para calcular números primos en diferentes
		for (int i = 0; i < numNucleos; i++) {
			int subInicio = inicio + i * rangoPorProceso;
			int subFin = (i == numNucleos - 1) ? fin : subInicio + rangoPorProceso - 1;

			// Proceso esclavo
			ProcessBuilder proceso = new ProcessBuilder("java", "-jar",
					"PrimosEsclavaApp.jar", String.valueOf(subInicio),
					String.valueOf(subFin));

			try {
				long tiempoInicioProceso = System.currentTimeMillis();
				Process procesoEsclavo = proceso.start();

				// Contadores
				int primosEncontrados = 0;
				int numerosAnalizados = subFin - subInicio + 1;

				// Leemos los números primos que el proceso esclavo imprime en su salida
				// estándar
				try (BufferedReader reader = new BufferedReader(
						new InputStreamReader(procesoEsclavo.getInputStream()))) {
					String line;
					while ((line = reader.readLine()) != null) {
						// Lo metemos en la lista de primos
						primosGlobales.add(Integer.parseInt(line));
						primosEncontrados++;
					}
				}

				// Esperamos a que el proceso esclavo termine
				procesoEsclavo.waitFor();
				long tiempoFinProceso = System.currentTimeMillis();
				// Calculamos el tiempo del proceso
				long tiempoProceso = tiempoFinProceso - tiempoInicioProceso;
				// Imprimimos la información de cada proceso
				System.out.printf(
						"Proceso P%d tiempo empleado: %d ms se han encontrado: %d números primos entre los %d analizados%n",
						i, tiempoProceso, primosEncontrados, numerosAnalizados);

				// Actualizamos los totales de primos encontrados y números analizados
				totalPrimos += primosEncontrados;
				totalAnalizados += numerosAnalizados;

			} catch (Exception e) {
				System.err.println("Error al ejecutar el proceso esclavo: " + e.getMessage());
			}
		}

		// Calculamos el tiempo total del programa
		long tiempoFinPrograma = System.currentTimeMillis();
		long tiempoTotal = tiempoFinPrograma - tiempoInicioPrograma;

		System.out.printf("Tiempo total empleado en el programa: %d ms se han encontrado %d números primos entre los %d analizados%n",
				tiempoTotal, totalPrimos, totalAnalizados);

		// Ordenamos y mostramos todos los números primos encontrados
		Collections.sort(primosGlobales);
		// Imprimos por pantalla todos los primos encontrados
		System.out.println(primosGlobales);
	}
}
