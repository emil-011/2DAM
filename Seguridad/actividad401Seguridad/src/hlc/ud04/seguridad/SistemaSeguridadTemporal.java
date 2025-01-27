package hlc.ud04.seguridad;

import java.util.Scanner;

import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;
import hlc.ud04.autenticator.DesafioTemporal;
import hlc.ud04.autenticator.RespuestaDesafioTemporal;

/**
 * Sistema de seguridad temporal basado en TOTP (Time-Based One-Time Password).
 * Utiliza un Autenticador TOTP para generar y validar códigos de un solo uso basados en el tiempo.
 * El control de acceso empleado es simple. El usuario con ID 1 tiene acceso total,
 * el de ID 2 sólo lectura y el de ID 3 sólo escritura. Cualquier otro ID no tiene acceso.
 */
public class SistemaSeguridadTemporal implements SistemaSeguridad {

  // Autenticador
  private Autenticador autenticador;
  // Control de acceso
  private ControlAcceso controlAcceso;
  
  /**
   * Constructor
   * @param autenticador Autenticador a usar
   * @param controlAcceso Control de acceso a usar
   */
  public SistemaSeguridadTemporal(Autenticador autenticador, ControlAcceso controlAcceso) {
    this.autenticador = autenticador;
    this.controlAcceso = controlAcceso;
  }
  
  @Override
  public Usuario autentica() {
    
    // Scanner para interactuar con el usuario
    Scanner sc = new Scanner(System.in);
    
    // Solicitamos el nombre de usuario
    System.out.print("Introduzca el identificador del usuario: ");
    String usuario = sc.nextLine();
    
    // Creamos un nuevo desafío temporal usando el autenticador y proporcionando el ID
    DesafioTemporal desafio = (DesafioTemporal) autenticador.iniciaAutenticacion(usuario);
    
    // Muestra el desafío generado al usuario (en este caso, el secreto TOTP)
    System.out.println("Secreto TOTP: " + desafio.getSecreto());
    System.out.print("Introduzca el código: ");
    String respuesta = sc.nextLine();
    
    // Termina la autenticación enviando el resultado al autenticador
    // Devuelve el resultado de la autenticación
    return autenticador.finalizaAutenticacion(desafio, new RespuestaDesafioTemporal(respuesta));
    
  }

  @Override
  public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {
    return controlAcceso.estaPermitido(usuario, operacion, recurso);
  }

}