package psp.unidad01.relacion103;

public class EchoCommandLineMandatory {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("ERROR. Introduce más de dos parámetros");
		} else {
			for (String argumentos : args) {
				System.out.println(argumentos);
			}

		}

	}

}
