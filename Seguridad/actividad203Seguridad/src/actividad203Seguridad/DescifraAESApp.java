package actividad203Seguridad;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class DescifraAESApp {

	private static String instanciaFabricaCLave = "PBKDF2WithHmacSHA256";
	private static final String MENSAJE_DEFAULT = "Mensaje de Prueba";
	private static final char[] PASSWORD_DEFAULT = "pass123".toCharArray();
	private static byte[] salt = "prueba de cadena para salir".getBytes();
	private static int iterations = 100;
	private static int keySize = 256;
	private String mensaje;
	private char[] password;

	public DescifraAESApp(String instanciaFabricaCLave, byte[] salt, int iterations, int keySize, String mensaje,
			char[] password) {
		this.instanciaFabricaCLave = instanciaFabricaCLave;
		DescifraAESApp.salt = salt;
		DescifraAESApp.iterations = iterations;
		DescifraAESApp.keySize = keySize;
		this.mensaje = mensaje;
		this.password = password;
	}

	public String getInstanciaFabricaCLave() {
		return instanciaFabricaCLave;
	}

	public byte[] getSalt() {
		return salt;
	}

	public int getIterations() {
		return iterations;
	}

	public int getKeySize() {
		return keySize;
	}

	public Key getClaveCifrado() throws NoSuchAlgorithmException, InvalidKeySpecException {
		// Preparamos la clave a utilizar HMAC
		SecretKeyFactory fabricaClave = SecretKeyFactory.getInstance(this.getInstanciaFabricaCLave());
		// Especificamos la PBE a utilizar con la clave, el salt, las iteraciones y el tamaño de clave
		PBEKeySpec especificaClave = new PBEKeySpec(this.password, this.getSalt(), this.getIterations(), this.getKeySize());
		// Genero una clave secreta con el tipo de fábrica y las especificaciones de la clave
		SecretKey claveSecreta = fabricaClave.generateSecret(especificaClave);
		// Generamos la clave AES
		Key key = new SecretKeySpec(claveSecreta.getEncoded(), "AES");

		return key;
	}

	public String descifrarMensaje(String mensajeCifrado)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {

		// Decodificar el mensaje cifrado de Base64 a bytes
		byte[] mensajeCifradoBytes = Base64.getDecoder().decode(mensajeCifrado);
		
		Cipher cifrador = Cipher.getInstance("AES");
		cifrador.init(Cipher.DECRYPT_MODE, this.getClaveCifrado());
		byte[] textoDescifradoBytes = cifrador.doFinal(mensajeCifradoBytes);
		
		// Convertir los bytes descifrados a cadena
		String textoDescifrado = new String(textoDescifradoBytes);
		
		return textoDescifrado;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

		String mensaje = args.length > 0 ? args[0] : MENSAJE_DEFAULT;
		char[] password = args.length > 1 ? args[1].toCharArray() : PASSWORD_DEFAULT;
		
		// Instancias de cifrado y descifrado
		DescifraAESApp descifraAES = new DescifraAESApp(instanciaFabricaCLave, salt, iterations, keySize, mensaje, password);
		CifraAESApp cifraAES = new CifraAESApp(instanciaFabricaCLave, salt, iterations, keySize, mensaje, password);
		
		// Cifrar el mensaje
		String mensajeCifrado = cifraAES.cifrarMensaje();
		System.out.println("Mensaje cifrado: " + mensajeCifrado);
		
		// Descifrar el mensaje
		String mensajeDescifrado = descifraAES.descifrarMensaje(mensajeCifrado);
		System.out.println("Mensaje descifrado: " + mensajeDescifrado);
	}
}
