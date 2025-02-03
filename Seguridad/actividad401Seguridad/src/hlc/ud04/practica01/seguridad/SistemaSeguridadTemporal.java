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
	 * Autentica un usuario
	 * @return Usuario - Si se autentica correctamente
	 */
	@Override
	public Usuario autentica() {
	    @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	    
	    // Solicitamos el nombre de usuario
	    System.out.print("Introduzca el identificador del usuario: ");
	    String usuario = sc.nextLine();
	    
	    // Creaamos un nuevo desafio
	    DesafioTemporal desafio = (DesafioTemporal)autenticador.iniciaAutenticacion(usuario);
	    
	    System.out.print("Introduzca el PIN actual del usuario (6 cifras): ");
	    String respuesta = sc.nextLine();
	    
	    // Mandamos el resultado al autenticador
	    return autenticador.finalizaAutenticacion(desafio, new RespuestaDesafioTemporal(respuesta));
	}

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {
		return true;
	}

}
