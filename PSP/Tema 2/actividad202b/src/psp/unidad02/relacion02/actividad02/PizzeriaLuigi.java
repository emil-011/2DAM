package psp.unidad02.relacion02.actividad02;

import java.util.ArrayList;
import java.util.List;

import psp.common.parameters.Parameters;

public class PizzeriaLuigi {
	// Parametros
	Parameters parameters;
	// Bandeja
	Bandeja bandeja;
	// Lista de clientes
	List<Cliente> listaClientes;
	// Lista de pizzeros
	List<Pizzero> listaPizzeros;

	public static void main(String[] args) {
		PizzeriaLuigi pizzeria = new PizzeriaLuigi();
		pizzeria.run(args);
	}

	private void run(String[] args) {
		parameters = new PizzeriaLuigiParameters();

		// Si los parametros estan correctos
		if (parameters.proccessArguments(args)) {
			bandeja = new Bandeja();
			
			creaPizzeros();
			creaClientes();
			
			simular();

		} else {
			// Si los parametros no son correctos
			// Ponemos mensaje de error
			error();
		}

	}

	private void creaPizzeros() {
		listaPizzeros = new ArrayList<>();

		for (int i = 0; i < parameters.getIntParameter(0); i++) {
			Pizzero pizzero = new Pizzero("Pizzero" + i, bandeja);
			listaPizzeros.add(pizzero);
		}

	}

	private void creaClientes() {
		listaClientes = new ArrayList<>();

		for (int i = 0; i < parameters.getIntParameter(0); i++) {
			Cliente cliente = new Cliente("Cliente" + i, bandeja);
			listaClientes.add(cliente);
		}
	}

	private void simular() {
		// Iniciamos cada cliente(hilo)
		for (Cliente cliente : listaClientes) {
			cliente.start();
		}
		// Iniciamos cada pizzero(hilo)
		for (Pizzero pizzero : listaPizzeros) {
			pizzero.start();
		}

		// Esperamos a que terminen los clientes
		for (Cliente cliente : listaClientes) {
			try {
				cliente.join();
			} catch (InterruptedException e) {
			}
		}

		// Paramos a todos los trabajadores
		for (Pizzero pizzero : listaPizzeros) {
			pizzero.terminar();
		}

	}
	
	private void error() {
		System.err.println("Uso: java PizzeriaLuigi <numPizzeros> <numClientes>");
	    System.err.println("	Pizzeros. Valor por defecto = 2. Debe ser igual o mayor a 1");
	    System.err.println("	Clientes. Valor por defecto = 5. Debe ser igual o mayor a 1");
	}
}
