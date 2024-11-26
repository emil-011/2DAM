package psp.unidad02.ejercicio01.utils;

import java.util.ArrayList;
import java.util.List;

public class CalculaPrimos extends Thread {
	private int inicio;
	private int fin;
	private List<Integer> primos;

	// Constructor para inicializar el rango
	public CalculaPrimos(int inicio, int fin) {
		this.inicio = inicio;
		this.fin = fin;
		this.primos = new ArrayList<>();
	}

	// Método que verifica si un número es primo
	private boolean esPrimo(int numero) {
		if (numero <= 1)
			return false;
		for (int i = 2; i <= Math.sqrt(numero); i++) {
			if (numero % i == 0)
				return false;
		}
		return true;
	}

	// Método run que se ejecutará en el hilo
	@Override
	public void run() {
		for (int i = inicio; i <= fin; i++) {
			if (esPrimo(i)) {
				primos.add(i);
			}
		}
		System.out.println("Números primos en el rango " + inicio + "-" + fin + ": " + primos);
	}
}
