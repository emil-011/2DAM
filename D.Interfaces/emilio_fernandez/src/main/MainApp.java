package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import gui.VentanaEntrenador;
import gui.VentanaJugador;
import gui.VentanaLogin;
import utils.Usuario;

public class MainApp {

	private static final String USUARIOS_REGISTRADOS = "usuarios_registrados.csv";

	public static void main(String[] args) {
		Usuario usuarioLogueado = checkLoggedInUser();

		if (usuarioLogueado != null) {
			String perfil = usuarioLogueado.getPerfil();
			if (perfil.equals("Jugador/a")) {
				VentanaJugador ventana = new VentanaJugador(checkLoggedInUser());
				ventana.setVisible(true);
			} else {
				VentanaEntrenador ventana = new VentanaEntrenador(checkLoggedInUser());
				ventana.setVisible(true);
			}
		} else {
			// No user is logged in, show the login window
			VentanaLogin lg = new VentanaLogin();
			lg.setVisible(true);
		}
	}

	private static Usuario checkLoggedInUser() {
		try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_REGISTRADOS))) {
			String linea;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			while ((linea = reader.readLine()) != null) {
				String[] camposUsuario = linea.trim().split(";");

				// Check if the user is logged in
				if (camposUsuario[6].equals("true")) {
					// Create a new Usuario instance and return it
					return new Usuario(camposUsuario[0], camposUsuario[1], sdf.parse(camposUsuario[2]),
							camposUsuario[3], camposUsuario[4], camposUsuario[5], true);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}