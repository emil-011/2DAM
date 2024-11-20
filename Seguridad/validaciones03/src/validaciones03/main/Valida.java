package validaciones03.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Valida {

	protected static boolean validarCampos(String[] campos, int numeroLinea) {
		String titulo = campos[0].trim();
		String nombre = campos[1].trim();
		String apellidos = campos[2].trim();
		String telefono = campos[3].trim();
		String codigoPostal = campos[4].trim();
		String email = campos[5].trim();
		String url = campos[6].trim();
		String username = campos[7].trim();
		String password = campos[8].trim().replaceAll("^\"|\"$", "");
		String fechaRegistro = campos[9].trim();

		boolean valido = true;

		if (!validarTitulo(titulo, numeroLinea))
			valido = false;
		if (!validarNombre(nombre, numeroLinea))
			valido = false;
		if (!validarApellidos(apellidos, numeroLinea))
			valido = false;
		if (!validarTelefono(telefono, numeroLinea))
			valido = false;
		if (!validarCodigoPostal(codigoPostal, numeroLinea))
			valido = false;
		if (!validarEmail(email, numeroLinea))
			valido = false;
		if (!validarURL(url, numeroLinea))
			valido = false;
		if (!validarUsername(username, numeroLinea))
			valido = false;
		if (!validarPassword(password, numeroLinea))
			valido = false;
		if (!validarFechaRegistro(fechaRegistro, numeroLinea))
			valido = false;

		return valido;
	}

	protected static boolean validarTitulo(String titulo, int linea) {
		if (!titulo.matches("Doctor|Señor|Señora")) {
			System.err.printf("Error en la línea %d: Título inválido '%s'%n", linea, titulo);
			return false;
		}
		return true;
	}

	protected static boolean validarNombre(String nombre, int linea) {
		if (nombre.isEmpty() || nombre.length() > 50) {
			System.err.printf("Error en la línea %d: Nombre inválido (vacío o mayor a 50 caracteres)%n", linea);
			return false;
		}
		return true;
	}

	protected static boolean validarApellidos(String apellidos, int linea) {
		if (apellidos.isEmpty() || apellidos.length() > 100) {
			System.err.printf("Error en la línea %d: Apellidos inválidos (vacíos o mayores a 100 caracteres)%n", linea);
			return false;
		}
		return true;
	}

	protected static boolean validarTelefono(String telefono, int linea) {
		if (!telefono.matches("^[6789]\\d{8}$")) {
			System.err.printf("Error en la línea %d: Teléfono inválido '%s'%n", linea, telefono);
			return false;
		}
		return true;
	}

	protected static boolean validarCodigoPostal(String codigoPostal, int linea) {
		if (!codigoPostal.matches("^([1-4][0-9]|5[0-2]|0[1-9])\\d{3}$")) {
			System.err.printf("Error en la línea %d: Código postal inválido '%s'%n", linea, codigoPostal);
			return false;
		}
		return true;
	}

	protected static boolean validarEmail(String email, int linea) {
		if (!Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$").matcher(email).matches()) {
			System.err.printf("Error en la línea %d: Email inválido '%s'%n", linea, email);
			return false;
		}
		return true;
	}

	protected static boolean validarURL(String url, int linea) {
		if (!url.isEmpty() && !url.matches("^(http|https)://[\\w.-]+(?:\\.[\\w.-]+)+[/\\w._%+-]*$")) {
			System.err.printf("Error en la línea %d: URL inválida '%s'%n", linea, url);
			return false;
		}
		return true;
	}

	protected static boolean validarUsername(String username, int linea) {
		if (!username.matches("^[a-zA-Z0-9_-]{1,10}$")) {
			System.err.printf("Error en la línea %d: Username inválido '%s'%n", linea, username);
			return false;
		}
		return true;
	}

	protected static boolean validarPassword(String password, int linea) {
		if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[.,;:/\\*&%$()]).{8,16}$")) {
			System.err.printf("Error en la línea %d: Password inválida '%s'%n", linea, password);
			return false;
		}
		return true;
	}

	protected static boolean validarFechaRegistro(String fechaRegistro, int linea) {
		try {
			LocalDate.parse(fechaRegistro, DateTimeFormatter.ISO_DATE);
		} catch (DateTimeParseException e) {
			System.err.printf("Error en la línea %d: Fecha de registro inválida '%s'%n", linea, fechaRegistro);
			return false;
		}
		return true;
	}

}
