package psp.unidad01.actividadobligatoria;

public class PrimosMaestraApp {
	private static final int VALOR_MINIMO = 2;
	private static final int VALOR_MAXIMO = 2147483647;
	private static final int PROCESADORES_DISPONIBLES = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args) {
		int inicio = Integer.parseInt(args[0]);
		int fin = Integer.parseInt(args[1]);
		int numNucleos = 0;

		if (args.length < 2 || args.length > 3) {
			System.err.println("Error: Debes proporcionar al menos dos parámetros <Inicio> <Fin> del rango");
			return;
		}

		if (inicio < VALOR_MINIMO || inicio < VALOR_MAXIMO || fin < VALOR_MINIMO || fin > VALOR_MAXIMO) {
			System.err.println("Error: Los parámetros deben ser números enteros válidos");
			return;
		}

		try {
			// Si hay 3 parametros utilizamos el tercer parametro para el numero de
			// procesadores si no cogemos los procesadores disponibles
			numNucleos = (args.length == 3) ? Integer.parseInt(args[3]) : PROCESADORES_DISPONIBLES;

			if (numNucleos > Runtime.getRuntime().availableProcessors()) {
				System.err.println("Error: No puedes utilizar más núcleos de los disponibles en el sistema.");
				return;
			}

		} catch (NumberFormatException e) {
			System.err.println("Error: Los parámetros deben ser números enteros válidos");
			return;
		}
		
		
		int rangoPorProceso = (fin - inicio + 1) / numNucleos;
		

	}
}
