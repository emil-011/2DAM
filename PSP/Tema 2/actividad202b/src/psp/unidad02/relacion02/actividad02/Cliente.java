package psp.unidad02.relacion02.actividad02;

public class Cliente extends Thread {
	// Tiempos minimos y maximos habiendo o no comido
	private static final long MIN_PASEO_SIN_COMER = 10000;
	private static final long MAX_PASEO_SIN_COMER = 15000;
	private static final long MIN_PASEO_COMER = 20000;
	private static final long MAX_PASEO_COMER = 30000;

	private static final int PIZZAS_A_COMER = 5;

	// Atributos

	// Nombre del Cliente
	private String nombre;
	// Bandeja
	Bandeja bandeja;
	// Pizzas restantes
	private int pizzasRestantes;

	/**
	 * Constructor de Cliente
	 * 
	 * @param nombre  Nombre del cliente
	 * @param bandeja Bandeja donde estan las pizzas
	 */
	public Cliente(String nombre, Bandeja bandeja) {
		this.nombre = nombre;
		this.bandeja = bandeja;
		// Cada cliente nuevo empieza con 5 pizzas
		this.pizzasRestantes = PIZZAS_A_COMER;
	}

	@Override
	public void run() {
		informar("Empezando...");
		// Mientas no se hayan comido 5 pizzas
		while (pizzasRestantes > 0) {
			informar("Intenda comprar pizza");
			// Si hay pizzas en la bandeja
			if (bandeja.cogerPizza()) {
				// Restamos una pizza 
				pizzasRestantes--;
				informar("Pizza recogida, quedan " + pizzasRestantes + " por recoger");
				// Si no es la ultima
				if (pizzasRestantes > 0) {
					// Se da un paseo largo
					informar("Va a dar un paseo largo");
					darPaseoLargo();
				}
			} else {
				// Damos un paseo corto si no hay pizzas
				informar("No hay pizzas en la bandeja");
				informar("Va a dar un paseo corto");
				darPaseoCorto();
			}
		}
	}

	private void darPaseoLargo() {
		long tiempo = (long) (Math.random() * (MAX_PASEO_COMER - MIN_PASEO_COMER + 1) + MIN_PASEO_COMER);

		try {
			sleep(tiempo);
		} catch (InterruptedException e) {
		}

	}

	private void darPaseoCorto() {
		long tiempo = (long) (Math.random() * (MAX_PASEO_SIN_COMER - MIN_PASEO_SIN_COMER + 1) + MIN_PASEO_SIN_COMER);

		try {
			sleep(tiempo);
		} catch (InterruptedException e) {
		}

	}

	private void informar(String mensaje) {
		System.out.println("Cliente " + this.nombre + ": " + mensaje);
		System.out.println();
	}
}
