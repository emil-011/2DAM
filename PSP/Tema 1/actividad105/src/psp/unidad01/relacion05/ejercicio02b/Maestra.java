package psp.unidad01.relacion05.ejercicio02b;

public class Maestra {

	public static void main(String[] args) {
		String ruta = "C:\\Users\\Alumnado2DAM\\eclipse-workspace\\actividad105\\Generador.jar";

		// Vamos a la ruta de la clase generador
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", ruta);

		try {
			pb.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Archivo creado con exito");

	}
}
