package hlc.ud04.autenticator;

import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;

/**
 * Respuesta del usuario al desafío TOTP (Time-Based One-Time Password).
 * Contiene el código TOTP ingresado por el usuario.
 */
public class RespuestaDesafioTemporal implements RespuestaDesafio {

  // Código TOTP ingresado por el usuario
  private String codigo;

  /**
   * Constructor
   * @param codigo Código TOTP ingresado por el usuario
   */
  public RespuestaDesafioTemporal(String codigo) {
    this.codigo = codigo;
  }

  /**
   * Obtiene el código TOTP
   * @return Código TOTP ingresado por el usuario
   */
  public String getCodigo() {
    return codigo;
  }
}