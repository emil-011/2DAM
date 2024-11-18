package filtroEntradas01.main;

import filtroEntradas01.utils.Valida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class FormularioApp {

	private final static String ARCHIVO = "datos.txt";

	public static void main(String[] args) {
		Map<String, String> mapa = new LinkedHashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				// Dividir la línea en clave y valor
				String[] parts = line.split("=");
				if (parts.length == 2) {
					mapa.put(parts[0].trim(), parts[1].trim()); // Agregar al mapa
				}
			}

			// Validar los datos del mapa
			String nombreApellidos = mapa.get("nombreApellidos");
			String fechaNacimiento = mapa.get("fechaNacimiento");
			String telefonoFijo = mapa.get("telefonoFijo");
			String telefonoMovil = mapa.get("telefonoMovil");
			String numeroHijos = mapa.get("numeroHijos");
			String comunidad = mapa.get("comunidad");
			String localidad = mapa.get("localidad");

			// Validar datos usando la librería de validación
			if (nombreApellidos == null || nombreApellidos.isEmpty()) {
				System.err.println("Error: El nombre y apellidos son obligatorios.");
			} else if (!Valida.validarFecha(fechaNacimiento)) {
				System.err.println("Error: La fecha de nacimiento no tiene un formato válido.");
			} else if (!Valida.validarTelefono(telefonoFijo)) {
				System.err.println("Error: El teléfono fijo no tiene un formato válido.");
			} else if (!Valida.validarTelefono(telefonoMovil)) {
				System.err.println("Error: El teléfono móvil no tiene un formato válido.");
			} else if (!Valida.validarNumeroHijos(numeroHijos)) {
				System.err.println("Error: El número de hijos no es válido.");
			} else if (!Valida.validarComunidad(comunidad)) {
				System.err.println("Error: La comunidad solo puede contener letras.");
			} else if (!Valida.validarLocalidad(localidad)) {
				System.err.println("Error: La localidad solo puede contener letras.");
			} else {
				// Si todo es válido, procesamos los datos
				System.out.println();
				System.out.println("    Datos validados correctamente");
				System.out.println("====================================");

				System.out.printf("%-20s: %s%n%-20s: %s%n%-20s: %s%n%-20s: %s%n%-20s: %s%n%-20s: %s%n%-20s: %s%n",
						"Nombre y Apellidos", nombreApellidos, "Fecha de Nacimiento", fechaNacimiento, "Teléfono Fijo",
						telefonoFijo, "Teléfono Móvil", telefonoMovil, "Número de Hijos", numeroHijos, "Comunidad",
						comunidad, "Localidad", localidad);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
