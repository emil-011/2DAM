package main;

import gui.VentanaCliente;
import gui.VentanaAdministracion;
import gui.VentanaLogin;
import utils.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;


public class MainApp {

	private static final String USUARIOS_REGISTRADOS = "usuarios_registrados.csv";

	public static void main(String[] args) {
		Usuario usuarioLogueado = checkLoggedInUser();

		if (usuarioLogueado != null) {
			try {
				UIManager.setLookAndFeel(new FlatMacDarkLaf());
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
			// User is logged in, open the corresponding window based on user profile
			String perfil = usuarioLogueado.getPerfil();
			if (perfil.equals("cliente")) {
				VentanaCliente ventanaCliente = new VentanaCliente(checkLoggedInUser());
				ventanaCliente.setVisible(true);
			} else {
				VentanaAdministracion ventanaAdmin = new VentanaAdministracion(checkLoggedInUser());
				ventanaAdmin.setVisible(true);
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
