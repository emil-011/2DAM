package psp.unidad02.relacion02.actividad02;

import psp.common.parameters.Parameters;
import psp.common.parameters.ParametersException;

public class PizzeriaLuigiParameters implements Parameters {

	// Valores por defecto
	private static final int PIZZEROS_POR_DEFECTO = 2;
	private static final int CLIENTES_POR_DEFECTO = 5;

	private int clientes;
	private int pizzeros;

	@Override
	public boolean proccessArguments(String[] arguments) {
		// Si el primer parametro no se pasa
		// Ponemos el que va por defecto
		if (arguments.length < 1) {
			pizzeros = PIZZEROS_POR_DEFECTO;
		} else {
			try {
				// Pasamos a entero
				pizzeros = Integer.parseInt(arguments[0]);
				// Si es menor que 1
				if (pizzeros < 1) {
					return false;
				}
			} catch (NumberFormatException e) {
				// Si no se puede pasar a entero
				return false;
			}

		}
		// Si el segundo parametro no se pasa
		// Ponemos el que va por defecto
		if (arguments.length < 1) {
			clientes = CLIENTES_POR_DEFECTO;
		} else {
			try {
				// Pasamos a entero
				clientes = Integer.parseInt(arguments[1]);
				// Si es menor que 1
				if (clientes < 1) {
					return false;
				}
			} catch (NumberFormatException e) {
				// Si no se puede pasar a entero
				return false;
			}

		}
		return true;
	}

	@Override
	public int getIntParameter(int position) {
		if (position == 0) {
			return pizzeros;
		}
		if (position == 1) {
			return clientes;
		}
		throw new ParametersException("Invalid position");
	}

	@Override
	public long getLongParameter(int position) {
		throw new ParametersException("Invalid position");
	}

	@Override
	public double getDoubleParameter(int position) {
		throw new ParametersException("Invalid position");
	}

	@Override
	public String getStringParameter(int position) {
		throw new ParametersException("Invalid position");
	}

}
