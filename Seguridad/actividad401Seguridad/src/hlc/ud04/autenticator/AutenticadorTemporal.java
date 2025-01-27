package hlc.ud04.autenticator;

import hlc.auth.otp.GeneradorHOTP;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Desafio;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;

/**
 * Autenticador que usa un algoritmo TOTP (Time-Based One-Time Password). Se
 * ofrece al usuario un secreto compartido para generar un código TOTP. El
 * usuario debe ingresar el código TOTP generado por su aplicación de
 * autenticación. Tiene 4 usuarios a piñón ya que es una versión de prueba
 * <ul>
 * <li>usuario1 Secreto: SECRETO1</li>
 * <li>usuario2 Secreto: SECRETO2</li>
 * <li>usuario3 Secreto: SECRETO3</li>
 * <li>usuario4 Secreto: SECRETO4</li>
 * </ul>
 */
public class AutenticadorTemporal implements Autenticador {

	// Datos de los usuarios
	private static DatosUsuario[] USUARIOS;

	/**
	 * Construye un autenticador temporal basado en TOTP.
	 */
	public AutenticadorTemporal() {
		iniciaUsuarios();
	}

	@Override
	public Desafio iniciaAutenticacion(String identificador) {
		// Obtiene el secreto del usuario desde el identificador
		String secreto = getSecreto(identificador);
		// Si el usuario no existe, devuelve null
		if (secreto == null) {
			return null;
		}
		// Devuelve un nuevo desafío temporal con el secreto
		return new DesafioTemporal(identificador, secreto);
	}

	@Override
	public Usuario finalizaAutenticacion(Desafio desafio, RespuestaDesafio respuesta) {
		// Obtiene el desafío y la respuesta
		DesafioTemporal desafioTemporal = (DesafioTemporal) desafio;
		RespuestaDesafioTemporal respuestaTemporal = (RespuestaDesafioTemporal) respuesta;

		// Genera el código TOTP esperado usando el secreto
		GeneradorHOTP generador = new GeneradorHOTP();
		String codigoGenerado = generador.genera(desafioTemporal.getSecreto(), 6);

		// Compara el código generado con el código ingresado por el usuario
		if (codigoGenerado.equals(respuestaTemporal.getCodigo())) {
			// Busca al usuario en el array y devuelve un nuevo objeto Usuario con el id
			for (DatosUsuario usuario : USUARIOS) {
				if (usuario.nombre.equals(desafioTemporal.getIdentificador())) {
					return new Usuario(usuario.uid);
				}
			}
		}
		// Si la respuesta no es correcta o el usuario no existe, devuelve null
		return null;
	}

	/**
	 * Obtiene el secreto del usuario a partir de su identificador.
	 * 
	 * @param identificador Identificador del usuario
	 * @return Secreto del usuario (String) o null si no se encuentra
	 */
	private String getSecreto(String identificador) {
		// Para cada usuario
		for (DatosUsuario usuario : USUARIOS) {
			// Si lo encuentra
			if (usuario.nombre.equals(identificador)) {
				// Devuelve el secreto
				return usuario.secreto;
			}
		}
		// No se encontró. Devuelve null
		return null;
	}

	/**
	 * Inicia el array de usuarios con los valores de prueba.
	 */
	private void iniciaUsuarios() {
		USUARIOS = new DatosUsuario[4];
		USUARIOS[0] = new DatosUsuario("usuario1", "6a542e818715b34b", 1);
		USUARIOS[1] = new DatosUsuario("usuario2", "e20800dba48808a0", 2);
		USUARIOS[2] = new DatosUsuario("usuario3", "c689d2c62e9b9709", 3);
		USUARIOS[3] = new DatosUsuario("usuario4", "6c6517ad350003bc", 4);
	}

	/**
	 * Clase interna para almacenar los datos de los usuarios.
	 */
	private static class DatosUsuario {
		String nombre;
		String secreto;
		long uid;

		DatosUsuario(String nombre, String secreto, long uid) {
			this.nombre = nombre;
			this.secreto = secreto;
			this.uid = uid;
		}
	}
}