package hlc.ud03.examen.datos;

public class BloqueDatosApp {
	
	private static final String DATOS = "./datos_previos/datos.txt";
	// private static final String DATOS_ERROR = "./datos_previos/conErrores/datos.txt";

	public static void main(String[] args) {
		BloqueDatos bloque = new BloqueDatosEnFichero(DATOS);
		
		System.out.println(bloque.contieneDato("referencia"));
		System.out.println(bloque.getDato("referencia"));
		
	}
}