package psp.unidad01.actividadobligatoria.esclava;

import java.util.ArrayList;
import java.util.List;

public class PrimosEsclavaApp {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Error: Debes proporcionar el rango <Inicio> <Fin> para procesar los números.");
			return;
		}

		int inicio = Integer.parseInt(args[0]);
		int fin = Integer.parseInt(args[1]);

		// Creamos la lista para almacenar los primos encontrados
		List<Integer> listaPrimos = new ArrayList<>();

		// Comprobamos si cada número en el rango es primo
		for (int numero = inicio; numero <= fin; numero++) {
			if (esPrimo(numero)) {
				listaPrimos.add(numero);
			}
		}

		// Imprimimos los números primos encontrados
		for (int primo : listaPrimos) {
			System.out.println(primo);
		}
	}

	// Metodo para averiguar si es primo
	private static boolean esPrimo(int num) {

		if (num <= 1)
			return false;
		if (num == 2 || num == 3)
			return true;
		// Si es par o multiplo de 3 devolvemos false
		// esto hace mucho mas eficaz el método
		if (num % 2 == 0 || num % 3 == 0)
			return false;

		for (int i = 5; i <= Math.sqrt(num); i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
