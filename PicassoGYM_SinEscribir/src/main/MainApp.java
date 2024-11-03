package main;

import gui.VentanaCliente;
import gui.VentanaAdministracion;
import gui.VentanaLogin;
import utils.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
	private static List<Usuario> usuariosRegistrados = new ArrayList<>(); // Lista estática para los usuarios

	public static void main(String[] args) {
		cargarUsuariosRegistrados(); // Carga de usuarios registrados
		Usuario usuarioLogueado = checkLoggedInUser(); // Verificación de usuario logueado

		if (usuarioLogueado != null) {
			// User is logged in, open the corresponding window based on user profile
			String perfil = usuarioLogueado.getPerfil();
			if (perfil.equals("Cliente")) {
				VentanaCliente ventanaCliente = new VentanaCliente(usuarioLogueado);
				ventanaCliente.setVisible(true);
			} else {
				VentanaAdministracion ventanaAdmin = new VentanaAdministracion(usuarioLogueado);
				ventanaAdmin.setVisible(true);
			}
		} else {
			// No user is logged in, show the login window
			VentanaLogin lg = new VentanaLogin();
			lg.setVisible(true);
		}
	}

	private static void cargarUsuariosRegistrados() {
		// Método para cargar usuarios desde un archivo o crear usuarios predeterminados
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

		try {
			// Agregando usuarios predeterminados a la lista
			usuariosRegistrados.add(new Usuario("Emilio", "Fernandez", sdf.parse("15-Ene-1990"), "Administracion",
					"emilio@gmail.com", "1234", false));
			usuariosRegistrados.add(new Usuario("Ana", "García", sdf.parse("20-Feb-1985"), "Cliente",
					"ana.garcia@example.com", "password456", false));
			usuariosRegistrados.add(new Usuario("Luis", "Fernández", sdf.parse("05-Mar-1992"), "Administacion",
					"luis.fernandez@example.com", "adminpass", false));
			usuariosRegistrados.add(new Usuario("María", "López", sdf.parse("10-Abr-1988"), "Cliente",
					"maria.lopez@example.com", "maria123", false));
			usuariosRegistrados.add(new Usuario("Pedro", "Martínez", sdf.parse("25-May-1995"), "Cliente",
					"pedro.martinez@example.com", "pedro123", false));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static List<Usuario> getUsuariosRegistrados() {
		// Método para obtener la lista de usuarios
		return usuariosRegistrados;
	}

	private static Usuario checkLoggedInUser() {
		// Método para verificar si hay un usuario logueado
		for (Usuario usuario : usuariosRegistrados) {
			if (usuario.isLogged()) { // Suponiendo que hay un método isLogueado en Usuario
				return usuario; // Retorna el usuario logueado
			}
		}
		return null; // Si no hay ningún usuario logueado
	}

}
