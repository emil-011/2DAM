package main;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Base64;
import javax.crypto.Cipher;

public class CifraRSAApp {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Introduce dos parámetros: <TextoACifrar> <AliasClavePublicaAmigo>");
			return;
		}

		String textoACifrar = args[0];
		String aliasAmigo = args[1];

		String keyStorePath = "almacenClaves.jks";
		char[] keyStorePassword = "12345678".toCharArray();

		FileInputStream keyStoreFile = null;

		try {
			// Cargamos el KeyStore
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStoreFile = new FileInputStream(keyStorePath);
			keyStore.load(keyStoreFile, keyStorePassword);

			// Obtenemos el certificado y clave pública de tu amigo
			Certificate certificate = keyStore.getCertificate(aliasAmigo);
			if (certificate == null) {
				System.err.println("Alias no encontrado");
				return;
			}

			// Conseguimos la clave publica
			PublicKey publicKey = certificate.getPublicKey();

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);

			// Ciframos el mensaje
			byte[] mensajeCifrado = cipher.doFinal(textoACifrar.getBytes());

			// Convertimos el mensaje cifrado a Base64
			String mensajeCifradoBase64 = Base64.getEncoder().encodeToString(mensajeCifrado);

			// Guardamos el mensaje cifrado en un archivo
			try (BufferedWriter writer = new BufferedWriter(new FileWriter("mensajeCifrado.txt", false))) {
				writer.write(mensajeCifradoBase64);
			}

			System.out.println("Mensaje cifrado y guardado en mensajeCifrado.txt");

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
