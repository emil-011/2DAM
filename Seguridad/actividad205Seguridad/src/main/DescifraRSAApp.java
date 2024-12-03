package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Base64;
import javax.crypto.Cipher;

public class DescifraRSAApp {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Introduce un parámetro: <AliasClavePrivada>");
			return;
		}

		String alias = args[0];

		String keyStorePath = "almacenClaves.jks";
		char[] keyStorePassword = "12345678".toCharArray();

		String mensajeCifradoBase64 = "";

		FileInputStream keyStoreFile = null;

		try {
			try (BufferedReader reader = new BufferedReader(new FileReader("mensajeCifrado.txt"))) {
				mensajeCifradoBase64 = reader.readLine();
			}

			// Cargar el KeyStore
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStoreFile = new FileInputStream(keyStorePath);
			keyStore.load(keyStoreFile, keyStorePassword);

			// Obtener la clave privada asociada al alias
			KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias,
					new KeyStore.PasswordProtection(keyStorePassword));

			if (privateKeyEntry == null) {
				System.err.println("Alias no encontrado en el KeyStore");
				return;
			}

			// Obtenemos la clave privada
			PrivateKey privateKey = privateKeyEntry.getPrivateKey();

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			// Decodificamos el mensaje cifrado de Base64
			byte[] mensajeCifrado = Base64.getDecoder().decode(mensajeCifradoBase64);

			// Desciframos el mensaje
			byte[] mensajeDescifrado = cipher.doFinal(mensajeCifrado);

			// Convertimos el mensaje descifrado a texto
			String mensajeClaro = new String(mensajeDescifrado);

			// Mostrar el mensaje descifrado
			System.out.println("Mensaje descifrado: " + mensajeClaro);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			java.util.Arrays.fill(keyStorePassword, ' ');
			if (keyStoreFile != null) {
				try {
					keyStoreFile.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
