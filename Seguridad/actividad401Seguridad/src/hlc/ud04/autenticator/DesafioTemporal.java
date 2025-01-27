package hlc.ud04.autenticator;

import hlc.ud04.appsec.seguridad.autenticacion.Desafio;

/**
 * Implementación de Desafio para el protocolo de autenticación basado en TOTP (Time-Based One-Time Password).
 * Contiene el secreto compartido que se usará para generar el código TOTP.
 */
public class DesafioTemporal implements Desafio {

  // Identificador de usuario
  private String identificador;
  // Secreto compartido para generar el código TOTP
  private String secreto;

  /**
   * Constructor
   * @param identificador Identificador del usuario
   * @param secreto Secreto compartido para generar el código TOTP
   */
  public DesafioTemporal(String identificador, String secreto) {
    this.identificador = identificador;
    this.secreto = secreto;
  }

  /**
   * Obtiene el identificador del usuario
   * @return Identificador del usuario
   */
  public String getIdentificador() {
    return identificador;
  }

  /**
   * Obtiene el secreto compartido
   * @return Secreto compartido para generar el código TOTP
   */
  public String getSecreto() {
    return secreto;
  }
}