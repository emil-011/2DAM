package psp.unidad01.relacion04;

import java.util.InputMismatchException;

public class Ejercicio03 {

	public static void main(String[] args) {
		if (args.length < 1) {
			throw new InputMismatchException("ERROR. Introduce más de dos parámetros");
		} else {
			for (String argumentos : args) {
				System.out.println(argumentos.toUpperCase());

			}

		}

	}

}
