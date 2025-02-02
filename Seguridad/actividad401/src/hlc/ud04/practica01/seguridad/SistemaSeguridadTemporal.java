package hlc.ud04.practica01.seguridad;

import java.util.Scanner;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;
import hlc.ud04.practica01.totp.DesafioTemporal;
import hlc.ud04.practica01.totp.RespuestaDesafioTemporal;

public class SistemaSeguridadTemporal implements SistemaSeguridad {
	private Autenticador autenticador;
	
	public SistemaSeguridadTemporal(Autenticador autenticador) {
	  this.autenticador = autenticador;
	}
	
	/**
	 * Comenzamos con el sistema de autenticación temporal, creamos un desafío y guardamos la respuesta del usuario.
	 * @return Usuario - Si se autentica correctamente, retorna al usuario encontrado
	 */
	@Override
	public Usuario autentica() {
	    // Scanner para interactuar con el usuario
	    Scanner sc = new Scanner(System.in);
	    
	    // Solicita el nombre de usuario
	    System.out.print("Introduzca el identificador del usuario: ");
	    String usuario = sc.nextLine();
	    
	    // Crea un nuevo desafío temporal usando el autenticador y proporcionando el ID
	    DesafioTemporal desafio = (DesafioTemporal)autenticador.iniciaAutenticacion(usuario);
	    
	    // Solicita la respuesta
	    System.out.print("Introduzca el PIN actual del usuario (6 cifras): ");
	    String respuesta = sc.nextLine();
	    sc.close();
	    
	    // Termina la autenticacion enviando el resultado al autenticador
	    // Devuelve el resultado de la autenticación
	    return autenticador.finalizaAutenticacion(desafio, new RespuestaDesafioTemporal(respuesta));
	}

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {
		return true;
	}

}
