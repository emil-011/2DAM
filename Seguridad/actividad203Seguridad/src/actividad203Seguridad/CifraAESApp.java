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

public class CifraAESApp {

	private static String instanciaFabricaCLave = "PBKDF2WithHmacSHA256";
	private static final String MENSAJE_DEFAULT = "Mensaje de Prueba";
	private static final char[] PASSWORD_DEFAULT = "pass123".toCharArray();
	private static byte[] salt = "prueba de cadena para salir".getBytes();
	private static int iterations = 100;
	private static int keySize = 256;
	private String mensaje;
	private char[] password;

	public CifraAESApp(String instanciaFabricaCLave, byte[] salt, int iterations, int keySize, String mensaje,
			char[] password) {
		super();
		this.instanciaFabricaCLave = instanciaFabricaCLave;
		CifraAESApp.salt = salt;
		CifraAESApp.iterations = iterations;
		CifraAESApp.keySize = keySize;
		this.mensaje = mensaje;
		this.password = password;
	}

	public String getInstanciaFabricaCLave() {
		return instanciaFabricaCLave;
	}

	public static String getMensajeDefault() {
		return MENSAJE_DEFAULT;
	}

	public static char[] getPasswordDefault() {
		return PASSWORD_DEFAULT;
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
		// Indico la PBE a utilizar con la clave, el salt, las iteraciones y el tamaño
		// de clave
		PBEKeySpec especificaClave = new PBEKeySpec(this.getPasswordDefault(), this.getSalt(), this.getIterations(),
				this.getKeySize());
		// Genero una clave secreta con el tipo de fábrica y las especificaciones de la
		// clave
		SecretKey claveSecreta = fabricaClave.generateSecret(especificaClave);
		// Aquí usamos 'claveSecreta' para generar la clave AES
		Key key = new SecretKeySpec(claveSecreta.getEncoded(), "AES");

		return key;
	}

	public String cifrarMensaje()
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {

		Cipher cifrador = Cipher.getInstance("AES");
		cifrador.init(Cipher.ENCRYPT_MODE, this.getClaveCifrado());
		byte[] textoCipher = cifrador.doFinal(mensaje.getBytes());
		
		String textoCifrado = Base64.getEncoder().encodeToString(textoCipher);
		
		return textoCifrado;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

		String mensaje = args.length > 0 ? args[0] : MENSAJE_DEFAULT;
		char[] password = args.length > 1 ? args[1].toCharArray() : PASSWORD_DEFAULT;

		CifraAESApp cifraAES = new CifraAESApp(instanciaFabricaCLave, salt, iterations, keySize, mensaje, password);
		
		System.out.println(cifraAES.cifrarMensaje());
	}
}
