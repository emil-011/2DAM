package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class FirmaApp {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println(
					"USO: \nFirmaApp <archivo> <password> " + "\narchivo - archivo del que se quiere obtener la firma "
							+ "\npassword - Password para desbloquear la clave privada");
			return;
		}

		String file = args[0];
		String password = args[1];

		String alias = "examen";
		String keyStorePath = "examenclaves.jks";
		char[] keyStorePassword = password.toCharArray();

		try {
			// Leemos el contenido del archivo
			byte[] fileContent = Files.readAllBytes(Paths.get(file));

			// Obtenemos la instancia del algoritmo de hash SHA-256
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// Obtenemos el hash del archivo
			byte[] hashBytes = digest.digest(fileContent);

			// Cargamos el KeyStore
			KeyStore keyStore = KeyStore.getInstance("JKS");
			try (FileInputStream keyStoreFile = new FileInputStream(keyStorePath)) {
				keyStore.load(keyStoreFile, keyStorePassword);
			}

			// Obtenemos la clave privada del keystore
			KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias,
					new KeyStore.PasswordProtection(keyStorePassword));

			// Si no encuentra llave salta error
			if (privateKeyEntry == null) {
				System.err.println("Alias no encontrado en el KeyStore");
				return;
			}

			// Private key
			PrivateKey privateKeyExamen = privateKeyEntry.getPrivateKey();

			// Ciframos el hash con la clave privada utilizando RSA
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKeyExamen);
			byte[] encryptedHash = cipher.doFinal(hashBytes);

			// Pasamos a Base64
			String encryptedHashBase64 = Base64.getEncoder().encodeToString(encryptedHash);
			System.out.println(encryptedHashBase64);

		} catch (IOException e) {
			System.err.println("Error al leer el fichero");
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Algoritmo no disponible");
		} catch (KeyStoreException e) {
			System.err.println("Error al acceder al KeyStore");
		} catch (UnrecoverableEntryException e) {
			System.err.println("No se puede recuperar la entrada de la clave privada");
		} catch (CertificateException e) {
			System.err.println("Error en el certificado del KeyStore");
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			System.err.println("Error en el cifrado del hash");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			System.err.println("Clave privada no válida para el cifrado");
		}
	}
}
