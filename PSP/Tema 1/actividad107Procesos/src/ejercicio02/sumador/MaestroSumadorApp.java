package ejercicio02.sumador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MaestroSumadorApp {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		if (args.length != 2) {
			System.out.println("Se requieren dos parámetros: <Entero> <Entero>");
			return;
		}

		int num1 = 0;
		int num2 = 0;
		// Pasamos los parametros a enteros
		try {
			num1 = Integer.parseInt(args[0]);
			num2 = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.err.println("Debes de introducir como parámetros números enteros");
			return;
		}

		// Calculamos la diferencia
		int diferencia = Math.abs(num1 - num2);
		int numeroProcesos = 1;

		if (diferencia < 25) {
			numeroProcesos = 1;
		} else if (diferencia <= 100) {
			numeroProcesos = 2;
		} else {
			numeroProcesos = Runtime.getRuntime().availableProcessors();
		}

		int minimo = Math.min(num1, num2);
		int maximo = Math.max(num1, num2);
		int rangoPorProceso = (maximo - minimo + 1) / numeroProcesos;
		int totalSuma = 0;

		@SuppressWarnings("unchecked")
		CompletableFuture<Integer>[] futures = new CompletableFuture[numeroProcesos];

		for (int i = 0; i < numeroProcesos; i++) {
			int inicio = minimo + i * rangoPorProceso;
			int fin = (i == numeroProcesos - 1) ? maximo : inicio + rangoPorProceso - 1;

			// Lanzamos el proceso de forma asíncrona (a la vez) usando supplyAsync
			futures[i] = CompletableFuture.supplyAsync(() -> {
				try {
					return lanzarProceso(inicio, fin);
				} catch (IOException e) {
					System.err.print("Error al lanzar los procesos: ");
					e.printStackTrace();
					// Si da error devolvemos 0
					return 0;
				}
			});
		}

		// Esperamos a que todos los procesos terminen y cogemos los resultados
		for (CompletableFuture<Integer> future : futures) {
			totalSuma += future.get();
		}

		// Imprimimos por pantalla el resultado
		System.out.println("La suma total es: " + totalSuma);
	}

	// Método privado que lanza los procesos y le las salidas de estos
	private static int lanzarProceso(int num1, int num2) throws IOException {
		ProcessBuilder builder = new ProcessBuilder("java", "-cp", "./bin", "sumador.ejercicio02.Sumador",
				String.valueOf(num1), String.valueOf(num2));
		Process process = builder.start();

		int suma = 0;

		// Leemos la salida del proceso
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("La suma de")) {
					suma += Integer.parseInt(line.split(": ")[1]);
				}
			}
		} catch (IOException e) {
			System.err.println("Error al leer la salida del proceso: " + e.getMessage());
		}

		return suma;
	}
}
