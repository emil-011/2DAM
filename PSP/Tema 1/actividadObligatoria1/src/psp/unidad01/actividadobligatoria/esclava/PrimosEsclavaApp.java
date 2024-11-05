package psp.unidad01.actividadobligatoria.esclava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PrimosEsclavaApp {
	public static void main(String[] args) {
		// Creamos las listas
		List<Integer> listaNumeros = new ArrayList<>();
		List<Integer> listaPrimos = new ArrayList<>();

		// Leemos la entrada
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				// Añadimos los numeros a la lista
				int numero = Integer.parseInt(linea.trim());
				listaNumeros.add(numero);

			}

			// Comprobamos si es primo y lo añadimos a la lista de primos
			for (int numeroLista : listaNumeros) {
				if (esPrimo(numeroLista)) {
					listaPrimos.add(numeroLista);
				}
			}

		} catch (IOException | NumberFormatException e) {
			System.err.println("Error al leer los datos: " + e.getMessage());
			return;
		}

		System.out.println("La longitud de la lista de primos es: " + listaPrimos.size());
		System.out.println("Números primos encontrados:");
		for (int primo : listaPrimos) {
			System.out.println(primo);
		}
		System.out.println("-----------------------------------------");

	}

	// Metodo para averiguar si es primo
	private static boolean esPrimo(int num) {
		if (num <= 1)
			return false;
		if (num <= 3)
			return true;
		// Si es par o multiplo de tres no es primo
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
