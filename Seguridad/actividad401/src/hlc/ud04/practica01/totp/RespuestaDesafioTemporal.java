package hlc.ud04.practica01.totp;

import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;

/**
 * Clase que únicamente está encargada de guardarnos la respuesta del usuario, en este
 * caso, sería el pin
 */
public class RespuestaDesafioTemporal implements RespuestaDesafio {
	private String pin;
	
	/**
	 * Constructor principal, guardamos el pin que ingresó el usuario
	 * @param pin
	 */
	public RespuestaDesafioTemporal(String pin) {
		this.pin = pin;
	}
	
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
}
