package hlc.ud04.practica01.totp;

import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Desafio;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.auth.otp.GeneradorException;
import hlc.auth.otp.GeneradorHOTP;

public class AutenticadorTemporal implements Autenticador {
	private static long systemTime;

	public AutenticadorTemporal() {}


	@Override
	public Desafio iniciaAutenticacion(String usuario) {
		systemTime = System.currentTimeMillis();
		return new DesafioTemporal(usuario);
	}
	

	@Override
	public Usuario finalizaAutenticacion(Desafio desafio, RespuestaDesafio respuesta) {
		DesafioTemporal desafioTemp = (DesafioTemporal)desafio;
		RespuestaDesafioTemporal respuestaTemp = (RespuestaDesafioTemporal)respuesta;
		GeneradorHOTP generador = new GeneradorHOTP();
		
		String userPin = respuestaTemp.getPin();
		try {
			if (userPin.equals(generador.genera(desafioTemp.getSecreto(), systemTime / 30000))) {
				System.out.println("Usuario verificado, iniciando interfaz...");
				return new Usuario(1);
			} else {
				throw new GeneradorException("Error en la verificación de datos" , null);
			}
		} catch (GeneradorException e) {
			System.err.println("Autenticación errónea");
			return null;
		}
	}

	public static long getSystemTime() {
		return systemTime;
	}
	public static void setSystemTime(long time) {
		AutenticadorTemporal.systemTime = time;
	}

}
