package psp.unidad02.ejercicio01.main;

import psp.unidad02.ejercicio01.utils.CalculaPrimos;

public class CalculaPrimosApp {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Error: Debes proporcionar el rango como parámetros.");
			System.out.println("Uso: java App <inicio> <fin>");
			return;
		}

		int inicio = Integer.parseInt(args[0]);
		int fin = Integer.parseInt(args[1]);

		// Crear instancia de CalculaPrimos
		CalculaPrimos calculaPrimos = new CalculaPrimos(inicio, fin);

		// Iniciar el hilo
		calculaPrimos.start();

		try {
			// Esperar a que el hilo termine
			calculaPrimos.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
