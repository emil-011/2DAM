package psp.ud02.actividad203;

import java.util.Properties;

public class ImageProcessingService {

	public static void main(String[] args) {

		        ImageProcessingConfig config = new ImageProcessingConfig();
		        Properties properties = config.loadConfig(); // Llama al método para crear o leer el archivo
		        
		        // Imprime los valores cargados para verificar
		        System.out.println("Input folder: " + properties.getProperty("inputfolder"));
		        System.out.println("Output folder: " + properties.getProperty("outputfolder"));
		        System.out.println("Max Width: " + properties.getProperty("maxwidth"));
		        System.out.println("Max Height: " + properties.getProperty("maxheight"));
		    }

}
