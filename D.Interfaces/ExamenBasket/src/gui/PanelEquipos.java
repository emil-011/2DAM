package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.MainApp;
import models.Equipo;
import models.Usuario;

public class PanelEquipos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> cbEquipos;
	private JLabel lblAño;
	private JLabel lblGenero;
	private JLabel lblEntrenador;
	private JLabel lblNumJugadores;
	private JLabel lblHorario;
	private Usuario us;
	private JButton btnInscribir;
	private JPanel panelCentral;
	private JPanel panel_3;
	private JPanel panel_2;
	private JPanel panel_1;
	private JPanel panel;

	public PanelEquipos(Usuario us) {
		this.us = us;
		inicializarComponentes();
		cargaEquipos();
	}

	private void inicializarComponentes() {
		setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Equipos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		panel_1 = new JPanel();
		add(panel_1, BorderLayout.WEST);

		panel_2 = new JPanel();
		FlowLayout fl_panel_2 = (FlowLayout) panel_2.getLayout();
		fl_panel_2.setAlignment(FlowLayout.RIGHT);
		add(panel_2, BorderLayout.SOUTH);

		btnInscribir = new JButton("Inscribirte");
		btnInscribir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inscribir();
			}
		});
		panel_2.add(btnInscribir);

		panel_3 = new JPanel();
		add(panel_3, BorderLayout.EAST);

		panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(6, 2, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("Equipo");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNewLabel_1);

		cbEquipos = new JComboBox<String>();
		cbEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarCombos(e);
			}
		});
		panelCentral.add(cbEquipos);

		JLabel lblNewLabel_2 = new JLabel("Año");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNewLabel_2);

		lblAño = new JLabel("");
		lblAño.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblAño);

		JLabel lblNewLabel_6 = new JLabel("Genero");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNewLabel_6);

		lblGenero = new JLabel("");
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblGenero);

		JLabel lblNewLabel_8 = new JLabel("Entrenador");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNewLabel_8);

		lblEntrenador = new JLabel("");
		lblEntrenador.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblEntrenador);

		JLabel lblNewLabel_13 = new JLabel("Horario Entrenamiento");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNewLabel_13);

		lblHorario = new JLabel("");
		lblHorario.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblHorario);

		JLabel lblNmeroDeJugadores = new JLabel("Número de Jugadores");
		lblNmeroDeJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNmeroDeJugadores);

		lblNumJugadores = new JLabel("");
		lblNumJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNumJugadores);
	}

	/**
	 * Inscribe al usuario a un nuevo equipo
	 */
	protected void inscribir() {
		// Obtener equipos del usuario
		List<Equipo> lstEquiposUsuario = obtieneEquipoJugador(us);

		// Validar selección en el ComboBox
		if (cbEquipos.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Por favor, selecciona un equipo.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String nombreEquipo = cbEquipos.getSelectedItem().toString();
		Equipo eqSeleccionado = null;

		// Buscar equipo seleccionado
		for (Equipo eq : MainApp.lstEquipos) {
			if (eq.getNombre().equals(nombreEquipo)) {
				eqSeleccionado = eq;
				break;
			}
		}

		// Validar que el equipo exista
		if (eqSeleccionado == null) {
			JOptionPane.showMessageDialog(null, "El equipo seleccionado no existe.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Verificar si el usuario ya pertenece al equipo seleccionado
		if (eqSeleccionado.getLstJugadores().contains(us)) {
			JOptionPane.showMessageDialog(null, "Ya estás inscrito en este equipo.", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Si el usuario no tiene equipo
		if (lstEquiposUsuario.isEmpty()) {
			// Inscribir directamente
			eqSeleccionado.getLstJugadores().add(us);
			JOptionPane.showMessageDialog(null, "Jugador inscrito correctamente al equipo " + nombreEquipo + ".");
		} else {
			// Confirmar cambio si ya pertenece a otro equipo
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Quiere cambiar de equipo?");
			if (respuesta == JOptionPane.YES_OPTION) {
				// Eliminar usuario del equipo actual
				Equipo equipoActual = lstEquiposUsuario.get(0);
				eliminaUsuarioDeEquipo(equipoActual, us);

				// Inscribir en el nuevo equipo
				eqSeleccionado.getLstJugadores().add(us);
				JOptionPane.showMessageDialog(null, "Jugador cambiado al equipo " + nombreEquipo + ".");
			} else {
				return; // Cancelar inscripción
			}
		}

		// Actualizar la interfaz
		lblNumJugadores.setText(Integer.toString(eqSeleccionado.getLstJugadores().size()));
		btnInscribir.setEnabled(false);
	}

	/**
	 * Quita al usuario del equipo actual
	 * @param equipoActual Equipo actual en el que esta inscrito el usuario
	 * @param usuario Usuario del equipo
	 */
	private void eliminaUsuarioDeEquipo(Equipo equipoActual, Usuario usuario) {
		equipoActual.getLstJugadores().remove(usuario);
	}

	/**
	 * Actualiza los labels según cuando se actualiza el comboBox
	 * @param e
	 */
	protected void cargarCombos(ActionEvent e) {
		String nombreEquipo = cbEquipos.getSelectedItem().toString();
		Equipo eqSeleccionado = null;
		for (Equipo eq : MainApp.lstEquipos) {
			if (eq.getNombre().equals(nombreEquipo)) {
				eqSeleccionado = eq;
				break;
			}

		}
		if (eqSeleccionado != null) {
			lblAño.setText(Integer.toString(eqSeleccionado.getAnyoCreacion()));
			lblEntrenador.setText(
					eqSeleccionado.getEntrenador().getNombre() + " " + eqSeleccionado.getEntrenador().getApellidos());
			lblGenero.setText(eqSeleccionado.getGenero());
			lblHorario.setText(eqSeleccionado.getHorario());
			lblNumJugadores.setText(Integer.toString(eqSeleccionado.getLstJugadores().size()));
		}
		List<Equipo> lstEquiposUsuario = obtieneEquipoJugador(us);

		if (lstEquiposUsuario.contains(eqSeleccionado)) {
			btnInscribir.setEnabled(false);
		} else {
			btnInscribir.setEnabled(true);

		}

	}

	/**
	 * Carga los equipos en la comboBox
	 */
	private void cargaEquipos() {
		for (Equipo eq : MainApp.lstEquipos) {
			if (((DefaultComboBoxModel<String>) cbEquipos.getModel()).getIndexOf(eq.getNombre()) == -1) {
				cbEquipos.addItem(eq.getNombre());
			}
		}
	}

	/**
	 * Metodo que obtiene los equipos de un jugador
	 * 
	 * @param user Usuario del que se van a obtener los equipos
	 * @return Lista de equipos del usuario
	 */
	private List<Equipo> obtieneEquipoJugador(Usuario usuario) {
		List<Equipo> lstEquipoUsuario = new ArrayList<Equipo>();
		for (Equipo eq : MainApp.lstEquipos) {
			if (usuario.getEsEntrenador() && eq.getEntrenador().equals(usuario)) {
				lstEquipoUsuario.add(eq);
			} else {
				if (eq.getLstJugadores().contains(usuario)) {
					lstEquipoUsuario.add(eq);
				}
			}
			;
		}
		return lstEquipoUsuario;
	}

}
