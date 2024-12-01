package psp.unidad01.relacion103;

public class EchoCommandLine {

	public static void main(String[] args) {
		// Verifica si hay argumentos
		if (args.length == 0) {
			System.out.println("No se han proporcionado argumentos.");
		} else {
			System.out.println("Argumentos recibidos:");
			// Imprimimos cada argumento
			for (String argumento : args) {
				System.out.println(argumento);
			}
		}
	}
}
