package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.SwingConstants;

import utils.Equipo;

import javax.swing.ImageIcon;

public class PanelMiEquipo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel central;
	private JLabel lblNombre_2;
	private JLabel lblAnteriorEquipo;
	private JLabel lblSiguienteEquipo;
	private JLabel lblNombre;
	private JLabel lblAnyo;
	private JLabel lblGenero;
	private JLabel lblHorario;
	private JLabel lblEntrenador;
	private JPanel footer;

	public PanelMiEquipo(List<Equipo> listaEquiposUsuario) {
		inicializarComponentes();
		cargarDatos(listaEquiposUsuario);

	}
	
	

	private void cargarDatos(List<Equipo> listaEquiposUsuario) {
		if (listaEquiposUsuario.size() == 1) {
			this.footer.setVisible(false);
		} else  if (listaEquiposUsuario.size() > 0){
			this.footer.setVisible(true);
			lblNombre.setText(listaEquiposUsuario.getFirst().getNombre());
			lblAnyo.setText(Integer.toString(listaEquiposUsuario.getFirst().getAnyo()));
			lblGenero.setText(listaEquiposUsuario.getFirst().getGenero());
			lblHorario.setText(listaEquiposUsuario.getFirst().getNombre());
			lblEntrenador.setText(listaEquiposUsuario.getFirst().getEntrenador().getNombre() + " " + listaEquiposUsuario.getFirst().getEntrenador().getApellidos());
		}

		
		
	}

	private void inicializarComponentes() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Mi Equipo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		panel.add(lblNewLabel);

		footer = new JPanel();
		add(footer, BorderLayout.SOUTH);

		lblAnteriorEquipo = new JLabel("");
		lblAnteriorEquipo.setIcon(new ImageIcon(PanelMiEquipo.class.getResource("/resources/Izquierda.png")));
		footer.add(lblAnteriorEquipo);

		lblSiguienteEquipo = new JLabel("");
		lblSiguienteEquipo.setIcon(new ImageIcon(PanelMiEquipo.class.getResource("/resources/Derecha.png")));
		footer.add(lblSiguienteEquipo);

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.WEST);

		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.EAST);

		central = new JPanel();
		add(central, BorderLayout.CENTER);
		central.setLayout(new GridLayout(6, 2, 0, 0));

		lblNombre_2 = new JLabel("Nombre");
		lblNombre_2.setHorizontalAlignment(SwingConstants.CENTER);
		central.add(lblNombre_2);

		lblNombre = new JLabel("");
		central.add(lblNombre);

		JLabel lblNombre_2_1 = new JLabel("Año");
		lblNombre_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		central.add(lblNombre_2_1);

		lblAnyo = new JLabel("");
		central.add(lblAnyo);

		JLabel lblNombre_2_2_1 = new JLabel("Género");
		lblNombre_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		central.add(lblNombre_2_2_1);

		lblGenero = new JLabel("");
		central.add(lblGenero);

		JLabel lblNombre_1_1 = new JLabel("Horario");
		lblNombre_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		central.add(lblNombre_1_1);

		lblHorario = new JLabel("");
		central.add(lblHorario);

		JLabel lblNombre_1_3 = new JLabel("Entrenador/a");
		lblNombre_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		central.add(lblNombre_1_3);

		lblEntrenador = new JLabel("");
		central.add(lblEntrenador);

		JLabel lblNombre_1_3_1 = new JLabel("");
		lblNombre_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		central.add(lblNombre_1_3_1);

		JLabel lblNombre_1_4 = new JLabel("");
		central.add(lblNombre_1_4);
	}

}
