package psp.unidad02.relacion02.actividad02;

public class Bandeja {
	private int pizzas;

	public Bandeja() {
		pizzas = 0;
	}

	/**
	 * Añade una pizza a la bandeja
	 * 
	 * @return Número de pizzas que hay en la bandeja en total
	 */
	public synchronized int depositarPizza() {
		pizzas++;

		return pizzas;
	}

	/**
	 * Coge una pizza de la bandeja si hay alguna
	 * 
	 * @return True si hay alguna pizza, false si no
	 */
	public synchronized boolean cogerPizza() {
		// Si hay alguna pizza
		if (pizzas > 0) {
			// Cogemos una
			pizzas--;
			return true;
		} else {
			// No hay ninguna pizza
			return false;
		}
	}

}
