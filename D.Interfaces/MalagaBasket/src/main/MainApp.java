package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import gui.VentanaLogin;
import utils.Usuario;

public class MainApp {

	public static List<Usuario> listaUsuarios;

	public static void main(String[] args) throws ParseException {

		listaUsuarios = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Usuario usuario1 = new Usuario("Emilio", "Fernandez", dateFormat.parse("05/12/2024"), "e", "1234",
				false);
		Usuario usuario2 = new Usuario("Emilio", "Fernandez", dateFormat.parse("05/12/2024"), "f", "1234",
				true);

		listaUsuarios.add(usuario1);
		listaUsuarios.add(usuario2);

		VentanaLogin lg = new VentanaLogin();
		lg.setVisible(true);

	}

}