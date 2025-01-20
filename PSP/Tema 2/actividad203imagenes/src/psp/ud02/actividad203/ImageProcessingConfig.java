package psp.ud02.actividad203;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

public class ImageProcessingConfig {
	
	

	protected Properties loadConfig() {
		Properties properties = new Properties();
		File configFile = new File("config.properties");
		try {
			if (configFile.exists()) {
				// Read the file if exists
				properties.load(Files.newInputStream(configFile.toPath()));
			} else {
				// Create the file if dont exists
				System.out.println("Creating default config.properties...");
				properties.setProperty("inputfolder", ".");
				properties.setProperty("outputfolder", "output");
				properties.setProperty("maxwidth", "100");
				properties.setProperty("maxheight", "100");
				properties.store(Files.newOutputStream(configFile.toPath()), "Default configuration");
			}
		} catch (IOException e) {
			System.err.println("Could not load or create config.properties.");
			System.err.println("Default Configuration.");
			// Usar valores por defecto directamente
			properties.setProperty("inputfolder", "input");
			properties.setProperty("outputfolder", "output");
			properties.setProperty("maxwidth", "100");
			properties.setProperty("maxheight", "100");
		}
		return properties;
	}

}
