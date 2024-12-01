package psp.unidad01.relacion05.ejercicio01;

import java.io.IOException;

public class Explorer {

	public static void main(String[] args) {
		String origen = "C:\\Windows\\explorer.exe";
		String destino = "C:\\Users\\Alumnado2DAM\\eclipse-workspace\\actividad105";

		ProcessBuilder pb = new ProcessBuilder("xcopy", origen, destino, "/Y");

		try {
			System.out.println("Copiando Fichero...");
			pb.start();
		} catch (IOException e) {
			// Mostrar detalles del error
			System.err.println("Error al copiar el fichero: " + e.getMessage());

		}
	}
}
