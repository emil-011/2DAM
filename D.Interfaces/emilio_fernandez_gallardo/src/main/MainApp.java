package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gui.Login;
import modelos.Serie;
import modelos.Temporada;
import modelos.Usuario;

public class MainApp {

	public static List<Usuario> lstUsuarios;
	public static List<Serie> lstSeries;
	public static List<Temporada> lstTemporadas;

	public static void main(String[] args) {

		lstUsuarios = new ArrayList<>();
		lstSeries = new ArrayList<>();
		lstTemporadas = new ArrayList<>();

		Serie serie = new Serie("Arcane", "Netflix", lstTemporadas);
		Serie serie2 = new Serie("Hora de Aventuras", "HBO", lstTemporadas);
		lstSeries.add(serie);
		lstSeries.add(serie2);

		LocalDate fechaActual = LocalDate.now();
		Temporada temporada1Arcane = new Temporada(1, fechaActual, fechaActual, 9);

		lstTemporadas.add(temporada1Arcane);

		Usuario us1 = new Usuario("Emilio", "Fernandez", "e", "1234", lstSeries);
		Usuario us2 = new Usuario("Paco", "Paquez", "paco", "1234", lstSeries);

		lstUsuarios.add(us1);
		lstUsuarios.add(us2);

		Login lg = new Login();
		lg.setVisible(true);

	}

}
