package main;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;

public class CifraRSAApp {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Introduce dos parámetros: <Texto> <Alias>");
			return;
		}

		String textoACifrar = args[0];
		String alias = args[1];
		
		String keyStorePath = "almacenClaves.jks";
		char[] keyStorePassword = "12345678".toCharArray();
		
		FileInputStream keyStoreFile = null;

		try {
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStoreFile = new FileInputStream(keyStorePath);
			keyStore.load(keyStoreFile, keyStorePassword);
			
			Certificate certificate
			
		} catch (Exception e) {
			
		}
		
		

	
		
	}

}
