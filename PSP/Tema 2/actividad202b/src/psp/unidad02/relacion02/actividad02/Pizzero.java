package psp.unidad02.relacion02.actividad02;

public class Pizzero extends Thread {

	// Tiempo mínimo y máximo que se tira el cocinero haciendo una pizza
	private static final long MIN_COOKING = 5000;
	private static final long MAX_COOKING = 10000;

	private String nombre;
	Bandeja bandeja;

	private boolean terminado;
	
	/**
	 * Construtor
	 * 
	 * @param nombre  Nombre del pizzero
	 * @param bandeja Bandeja donde se guardan las pizzas
	 */
	public Pizzero(String nombre, Bandeja bandeja) {
		this.nombre = nombre;
		this.bandeja = bandeja;
		this.terminado = false;
	}

	/**
	 * Hace que un trabajador termine de trabajar
	 */
	public void terminar() {
		this.terminado = true;
	}

	@Override
	public void run() {
		informar("Empezando...");
		// Mientras no haya termnido
		while (!terminado) {
			informar("Haciendo una nueva pizza");
			// Esperamos a que se haga la pizza
			hacerPizza();
			// Añadimos la pizza a la bandeja
			int contadorPizzas = bandeja.depositarPizza();
			informar("Pizza terminada. Pizzas disponibles: " + contadorPizzas);
		}
	}

	private void hacerPizza() {
		// Calculamos lo que va a tardar en "hacer" la pizza
		long tiempo = (long) (Math.random() * (MAX_COOKING - MIN_COOKING + 1) + MIN_COOKING);

		try {
			sleep(tiempo);
		} catch (InterruptedException e) {
		}

	}

	private void informar(String mensaje) {
	 System.out.println("Pizzero " + nombre + ": " + mensaje);
	 System.out.println();
	}
	


}
