package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import gui.Login;
import utils.Equipo;
import utils.Usuario;

public class MainApp {

	public static List<Usuario> listaUsuarios;
	public static List<Equipo> listaEquipos;

	public static void main(String[] args) throws ParseException {

		listaUsuarios = new ArrayList<>();
		listaEquipos = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Usuario usuario1 = new Usuario("Emilio", "Fernandez", dateFormat.parse("05/12/2024"), "e", "1234",
				true);
		Usuario usuario2 = new Usuario("Emilio", "Fernandez", dateFormat.parse("05/12/2024"), "f", "1234",
				true);

		listaUsuarios.add(usuario1);
		listaUsuarios.add(usuario2);
		
		List<Usuario> lstJugadoresCadeteA = new ArrayList<Usuario>();
		lstJugadoresCadeteA.add(usuario2);
		
		Equipo eq1 = new Equipo("Cadete A",2010, "Femenino", "Lunes-Miercoles 15:00-17:30", lstJugadoresCadeteA,usuario1);
		Equipo eq2 = new Equipo("Juvenil B",2004, "Masculino", "Martes-Viernes 16:00-19:30", new ArrayList<Usuario>(),usuario1);
		
		listaEquipos.add(eq1);
		listaEquipos.add(eq2);
		

		Login lg = new Login();
		lg.setVisible(true);

	}

}