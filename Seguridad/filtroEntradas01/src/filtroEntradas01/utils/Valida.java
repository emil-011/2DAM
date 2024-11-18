package filtroEntradas01.utils;

import java.util.regex.Pattern;

public class Valida {

	// Validar correo electrónico
	public static boolean validarEmail(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		return Pattern.matches(regex, email);
	}

	// Validar fecha en formato dd/MM/yyyy
	public static boolean validarFecha(String fecha) {
		String regex = "^\\d{2}/\\d{2}/\\d{4}$";
		return Pattern.matches(regex, fecha);
	}

	// Validar número de teléfono (con 9 dígitos)
	public static boolean validarTelefono(String telefono) {
		String regex = "^\\d{9}$";
		return Pattern.matches(regex, telefono);
	}

	// Validar si un número de hijos es un entero positivo
	public static boolean validarNumeroHijos(String numeroHijos) {
		try {
			int hijos = Integer.parseInt(numeroHijos);
			return hijos >= 0; // Número de hijos no puede ser negativo
		} catch (NumberFormatException e) {
			return false; // Si no es un número válido
		}
	}

	// Validar si una comunidad autónoma no está vacía y no contiene números
	public static boolean validarComunidad(String comunidad) {
		// Asegurarse de que no esté vacía y no contenga números
		return comunidad != null && !comunidad.trim().isEmpty() && comunidad.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜ ]+");
	}

	// Validar si una localidad no está vacía y no contiene números
	public static boolean validarLocalidad(String localidad) {
		// Asegurarse de que no esté vacía y no contenga números
		return localidad != null && !localidad.trim().isEmpty() && localidad.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜ ]+");
	}

}
