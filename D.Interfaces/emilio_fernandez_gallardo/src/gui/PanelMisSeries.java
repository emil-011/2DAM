package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import main.MainApp;
import modelos.Serie;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

public class PanelMisSeries extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JComboBox<String> comboBoxSeries;
	private JLabel lblPlataforma;
	private JLabel lblNumeroTemporadas;

	/**
	 * Create the panel.
	 */
	public PanelMisSeries() {
		inicializaComponentes();
		cargaSeries();
	}



	private void inicializaComponentes() {
		setBackground(new Color(85, 85, 85));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBackground(new Color(85, 85, 85));
		add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Añadir a mis series");
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(btnNewButton);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(85, 85, 85));
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(3, 2, 20, 40));
		
		JLabel lblSerie_1 = new JLabel("Serie");
		lblSerie_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		mainPanel.add(lblSerie_1);
		
		comboBoxSeries = new JComboBox<String>();
		comboBoxSeries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarCombos(e);
			}
		});
		mainPanel.add(comboBoxSeries);
		
		JLabel lblPlat = new JLabel("Plataforma");
		lblPlat.setFont(new Font("Tahoma", Font.BOLD, 18));
		mainPanel.add(lblPlat);
		
		lblPlataforma = new JLabel("");
		mainPanel.add(lblPlataforma);
		
		JLabel lblSerie = new JLabel("Número de temporadas");
		lblSerie.setFont(new Font("Tahoma", Font.BOLD, 16));
		mainPanel.add(lblSerie);
		
		lblNumeroTemporadas = new JLabel("");
		mainPanel.add(lblNumeroTemporadas);
	}
	
	/**
	 * Inscribe al usuario a un nuevo equipo
	 */
	protected void inscribir() {

		// Validar selección en el ComboBox
		if (comboBoxSeries.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Por favor, selecciona un equipo.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String nombreSerie = comboBoxSeries.getSelectedItem().toString();
		Serie serieSeleccionada  = null;

		// Buscar equipo seleccionado
		for (Serie se : MainApp.lstSeries) {
			if (se.getNombre().equals(nombreSerie)) {
				serieSeleccionada = se;
				break;
			}
		}

		// Validar que el equipo exista
		if (serieSeleccionada == null) {
			JOptionPane.showMessageDialog(null, "El equipo seleccionado no existe.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Verificar si la serie seleccionada ya esta en favoritos
		if (serieSeleccionada.getLstTemporadas().contains(serieSeleccionada)) {
			JOptionPane.showMessageDialog(null, "Ya estás inscrito en este equipo.", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

			// Confirmar cambio si ya pertenece a otro equipo
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Quiere añadir la serie a favoritos");
			if (respuesta == JOptionPane.YES_OPTION) {
				// Inscribir en el nuevo equipo
				Home.listaSeriesFavoritas.add(serieSeleccionada);
			} else {
				return;
			}
		}

//		// Actualizar la interfaz
//		lblNumJugadores.setText(Integer.toString(eqSeleccionado.getLstJugadores().size()));
//		btnInscribir.setEnabled(false);
//	}

//	/**
//	 * Actualiza los labels según cuando se actualiza el comboBox
//	 * @param e
//	 */
//	protected void cargarCombos(ActionEvent e) {
//		String nombreEquipo = cbEquipos.getSelectedItem().toString();
//		Equipo eqSeleccionado = null;
//		for (Equipo eq : MainApp.lstEquipos) {
//			if (eq.getNombre().equals(nombreEquipo)) {
//				eqSeleccionado = eq;
//				break;
//			}
//
//		}
//		if (eqSeleccionado != null) {
//			lblAño.setText(Integer.toString(eqSeleccionado.getAnyoCreacion()));
//			lblEntrenador.setText(
//					eqSeleccionado.getEntrenador().getNombre() + " " + eqSeleccionado.getEntrenador().getApellidos());
//			lblGenero.setText(eqSeleccionado.getGenero());
//			lblHorario.setText(eqSeleccionado.getHorario());
//			lblNumJugadores.setText(Integer.toString(eqSeleccionado.getLstJugadores().size()));
//		}
//		List<Equipo> lstEquiposUsuario = obtieneEquipoJugador(us);
//
//		if (lstEquiposUsuario.contains(eqSeleccionado)) {
//			btnInscribir.setEnabled(false);
//		} else {
//			btnInscribir.setEnabled(true);
//
//		}
//
//	}

	/**
	 * Carga los equipos en la comboBox
	 */
	private void cargaSeries() {
		for (Serie eq : MainApp.lstSeries) {
			if (((DefaultComboBoxModel<String>) comboBoxSeries.getModel()).getIndexOf(eq.getNombre()) == -1) {
				comboBoxSeries.addItem(eq.getNombre());
			}
		}
	}
	
	protected void cargarCombos(ActionEvent e) {
		String nombreSerie = comboBoxSeries.getSelectedItem().toString();
		Serie serieSelec = null;
		for (Serie se : MainApp.lstSeries) {
			if (se.getNombre().equals(nombreSerie)) {
				serieSelec = se;
				break;
			}

		}
		if (serieSelec != null) {
			lblPlataforma.setText(serieSelec.getPlataforma());
			lblNumeroTemporadas.setText(Integer.toString(serieSelec.getLstTemporadas().size()));

		}

	}
}
