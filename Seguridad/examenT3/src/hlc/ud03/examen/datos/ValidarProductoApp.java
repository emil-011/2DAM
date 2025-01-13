package hlc.ud03.examen.datos;

public class ValidarProductoApp {

	private static final String ERROR = "Error en el campo ";

	public static void main(String[] args) {
		validaConErrores();
		validaSinErrores();
	}

	/**
	 * Valida el fichero sin errores
	 */
	private static void validaSinErrores() {
		String rutaFichero = ("./datos_previos/datos.txt");

		try {
			BloqueDatosEnFichero datos = new BloqueDatosEnFichero(rutaFichero);

			validarCampoObligatorio(datos.getDato("referencia"), "referencia");
			validarReferencia(datos.getDato("referencia"));
			validarCampoObligatorio(datos.getDato("nombre"), "nombre");
			validarNombre(datos.getDato("nombre"));
			validarCampoObligatorio(datos.getDato("precio"), "precio");
			validarPrecio(datos.getDato("precio"));
			validarCampoObligatorio(datos.getDato("marca"), "marca");
			validarMarca(datos.getDato("marca"));
			validarCampoObligatorio(datos.getDato("puntuacion"), "puntuacion");
			validarPuntuacion(datos.getDato("puntuacion"));
			validarCampoObligatorio(datos.getDato("fecha"), "fecha_inicio_venta");
			validarFechaInicioVenta(datos.getDato("fecha"));
			validarCampoObligatorio(datos.getDato("dominio"), "dominio");
			validarDominio(datos.getDato("dominio"));
			validarCampoObligatorio(datos.getDato("url"), "url");
			validarURL(datos.getDato("url"));
			validarCampoObligatorio(datos.getDato("correo"), "correo_pedidos");
			validarCorreoPedidos(datos.getDato("correo"));
		} catch (BloqueDatosException e) {
			System.err.println("Error procesando el archivo " + rutaFichero + ": " + e.getMessage());
		}

	}

	/**
	 * Válida el fichero con errores
	 */
	private static void validaConErrores() {
		for (int indice = 1; indice <= 33; indice++) {
			String rutaFichero = String.format("./datos_previos/conErrores/datos%03d.txt", indice);
			System.out.println("datos" + String.format("%03d", indice) + ".txt");

			try {
				BloqueDatosEnFichero datos = new BloqueDatosEnFichero(rutaFichero);

				validarCampoObligatorio(datos.getDato("referencia"), "referencia");
				validarReferencia(datos.getDato("referencia"));
				validarCampoObligatorio(datos.getDato("nombre"), "nombre");
				validarNombre(datos.getDato("nombre"));
				validarCampoObligatorio(datos.getDato("precio"), "precio");
				validarPrecio(datos.getDato("precio"));
				validarCampoObligatorio(datos.getDato("marca"), "marca");
				validarMarca(datos.getDato("marca"));
				validarCampoObligatorio(datos.getDato("puntuacion"), "puntuacion");
				validarPuntuacion(datos.getDato("puntuacion"));
				validarCampoObligatorio(datos.getDato("fecha"), "fecha_inicio_venta");
				validarFechaInicioVenta(datos.getDato("fecha"));
				validarCampoObligatorio(datos.getDato("dominio"), "dominio");
				validarDominio(datos.getDato("dominio"));
				validarCampoObligatorio(datos.getDato("url"), "url");
				validarURL(datos.getDato("url"));
				validarCampoObligatorio(datos.getDato("correo"), "correo_pedidos");
				validarCorreoPedidos(datos.getDato("correo"));
			} catch (BloqueDatosException e) {
				System.err.println("Error procesando el archivo " + rutaFichero + ": " + e.getMessage());
			}
		}
	}

	/**
	 * Imprime los errores
	 * @param campo Campo a imprimir
	 * @param mensaje Mensaje
	 */
	private static void imprimirError(String campo, String mensaje) {
		System.out.println(ERROR + campo + ": " + mensaje);
	}

	/**
	 * 
	 * @param valor
	 * @param campo
	 */
	private static void validarCampoObligatorio(String valor, String campo) {
		if (valor == null || valor.isBlank()) {
			imprimirError(campo, "El campo es obligatorio");
		}
	}

	/**
	 * Valida el producto
	 * @param referencia Referencia del producto
	 */
	public static void validarReferencia(String referencia) {
		if (referencia == null || referencia.isBlank())
			return;

		if (!referencia.matches("\\d{13}")) {
			imprimirError("referencia", "Debe tener exactamente 13 dígitos");
			return;
		}

		int prefijo = Integer.parseInt(referencia.substring(0, 2));
		if (prefijo < 34 || prefijo > 67) {
			imprimirError("referencia", "Los dos primeros dígitos deben estar entre 34 y 67");
		}
	}

	/**
	 * Valida el nombre
	 * @param nombre Nombre del producto
	 */
	public static void validarNombre(String nombre) {
		if (nombre == null || nombre.isBlank())
			return;

		if (nombre.length() > 200) {
			imprimirError("nombre", "El campo excede la longitud máxima (200)");
		}
	}

	/**
	 * Valida el precio
	 * @param precio Precio del producto
	 */
	public static void validarPrecio(String precio) {
		if (precio == null || precio.isBlank())
			return;

		if (!precio.matches("\\d+\\.\\d{2}")) {
			imprimirError("precio", "El valor debe ser un número real con dos decimales");
			return;
		}
		if (Double.parseDouble(precio) <= 0) {
			imprimirError("precio", "El valor no puede ser negativo o cero");
		}
	}

	/**
	 * Valida la marca
	 * @param marca Marca del producto
	 */
	public static void validarMarca(String marca) {
		if (marca == null || marca.isBlank())
			return;

		if (marca.length() > 100) {
			imprimirError("marca", "El campo debe tener hasta 100 caracteres");
		}
	}

	/**
	 * Valida la puntuación
	 * @param puntuacion Puntuacion del producto
	 */
	public static void validarPuntuacion(String puntuacion) {
		if (puntuacion == null || puntuacion.isBlank())
			return;

		if (!puntuacion.matches("[0-5]\\.\\d")) {
			imprimirError("puntuacion", "El valor está fuera del rango indicado (0.0 - 5.0)");
		}
	}

	/**
	 * Valida la fecha
	 * @param fecha Fecha del produco
	 * @return False si el formato no es dd/mm/aaaa
	 */
	public static boolean validarFechaInicioVenta(String fecha) {
	    if (fecha == null || fecha.isBlank()) {
	        return true;
	    }

	    if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
	        System.err.println("Error en el campo fecha_inicio_venta: La fecha debe estar en formato dd/mm/aaaa.");
	        return false;
	    }
	    return true;
	}


	/**
	 * Valida el dominio
	 * @param dominio Dominio de la empresa
	 */
	public static void validarDominio(String dominio) {
		if (dominio == null || dominio.isBlank())
			return;

		String dominioRegex = "^(?:[a-zA-Z](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,63}$";
		if (!dominio.matches(dominioRegex)) {
			imprimirError("dominio", "El formato del campo no es correcto");
		}
	}

	/**
	 * Valida la URL
	 * @param url Url de la web
	 */
	public static void validarURL(String url) {
		if (url == null || url.isBlank())
			return;

		String urlRegex = "^(https?|ftp)://[\\w.-]+(?:\\.[\\w\\.-]+)+[/\\w\\.-]*$";
		if (!url.matches(urlRegex)) {
			imprimirError("url", "El valor no es una URL válida");
		}
	}

	/**
	 * Valida el correo
	 * @param correo Correo de la empresa
	 * @return False si el correo no tiene un formato valido
	 */
	public static boolean validarCorreoPedidos(String correo) {
		if (correo == null || correo.isBlank())
			return true;

		String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
		if (!correo.matches(emailRegex)) {
			imprimirError("correo_pedidos", "El valor no es una dirección de correo electrónico válida");
		return false;
		}
		
		return true;
	}

	/**
	 * Valida los puntos fuertes
	 * @param puntosFuertes Puntos fuertes del producto
	 * @return False si no son los puntos fuertes del array
	 */
	public static boolean validarPuntosFuertes(String puntosFuertes) {
		String[] opcionesValidas = { "Precio", "Tecnología", "Durabilidad", "Disponibilidad", "Marca", "Sostenible",
				"Calidad", "Tamaño" };

		if (puntosFuertes == null || puntosFuertes.isBlank()) {
			System.err.println("Error en el campo puntos fuertes: El campo es obligatorio.");
			return false;
		}

		String[] puntosArray = puntosFuertes.split(",");
		if (puntosArray.length < 1 || puntosArray.length > 5) {
			System.err.println("Error en el campo puntos fuertes: Debe especificar de 1 a 5 puntos fuertes.");
			return false;
		}

		for (String punto : puntosArray) {
			boolean valido = false;
			for (String opcion : opcionesValidas) {
				if (punto.trim().equals(opcion)) {
					valido = true;
					break;
				}
			}
			if (!valido) {
				System.err.println("Error en el campo puntos fuertes: Valor no permitido (" + punto.trim() + ").");
				return false;
			}
		}
		return true;
	}

	/**
	 * Valida los puntos debiles del producto
	 * @param puntosDebiles Puntos debiles del producto
	 * @return False si no son los puntos debiles del array
	 */
	public static boolean validarPuntosDebiles(String puntosDebiles) {
		String[] opcionesValidas = { "Precio", "Tecnología", "Durabilidad", "Disponibilidad", "Marca", "Sostenible",
				"Calidad", "Tamaño" };

		if (puntosDebiles == null || puntosDebiles.isBlank()) {
			System.err.println("Error en el campo puntos débiles: El campo es obligatorio.");
			return false;
		}

		String[] puntosArray = puntosDebiles.split(",");
		if (puntosArray.length < 1 || puntosArray.length > 5) {
			System.err.println("Error en el campo puntos débiles: Debe especificar de 1 a 5 puntos débiles.");
			return false;
		}

		for (String punto : puntosArray) {
			boolean valido = false;
			for (String opcion : opcionesValidas) {
				if (punto.trim().equals(opcion)) {
					valido = true;
					break;
				}
			}
			if (!valido) {
				System.err.println("Error en el campo puntos débiles: Valor no permitido (" + punto.trim() + ").");
				return false;
			}
		}
		return true;
	}

}
