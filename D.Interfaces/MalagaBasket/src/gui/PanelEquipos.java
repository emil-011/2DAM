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
import utils.Equipo;
import utils.Usuario;

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
	private int indice = 0;
	/**
	 * Create the panel.
	 */
	public PanelEquipos(Usuario us) {
		this.us = us;
		inicializarComponentes();
		cargaEquipos();
	}


	private void inicializarComponentes() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Equipos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(panel_2, BorderLayout.SOUTH);

		btnInscribir = new JButton("Inscribirte");
		btnInscribir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inscribir();
			}
		});
		panel_2.add(btnInscribir);

		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.EAST);

		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(6, 2, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("Equipo");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_1);

		cbEquipos = new JComboBox<String>();
		cbEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarCombos(e);
			}
		});
		panel_4.add(cbEquipos);

		JLabel lblNewLabel_2 = new JLabel("Año");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_2);

		lblAño = new JLabel("");
		lblAño.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblAño);

		JLabel lblNewLabel_6 = new JLabel("Genero");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_6);

		lblGenero = new JLabel("");
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblGenero);

		JLabel lblNewLabel_8 = new JLabel("Entrenador");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_8);

		lblEntrenador = new JLabel("");
		lblEntrenador.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblEntrenador);

		JLabel lblNewLabel_13 = new JLabel("Horario Entrenamiento");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_13);

		lblHorario = new JLabel("");
		lblHorario.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblHorario);

		JLabel lblNmeroDeJugadores = new JLabel("Número de Jugadores");
		lblNmeroDeJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNmeroDeJugadores);

		lblNumJugadores = new JLabel("");
		lblNumJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNumJugadores);
	}

	protected void inscribir() {
	    // Obtener equipos del usuario
	    List<Equipo> lstEquiposUsuario = ObtieneEquipoJugador(us);

	    // Validar selección en el ComboBox
	    if (cbEquipos.getSelectedItem() == null) {
	        JOptionPane.showMessageDialog(null, "Por favor, selecciona un equipo.", "Error",
	                JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    String nombreEquipo = cbEquipos.getSelectedItem().toString();
	    Equipo eqSeleccionado = null;

	    // Buscar equipo seleccionado
	    for (Equipo eq : MainApp.listaEquipos) {
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
	    if (eqSeleccionado.getListaJugadores().contains(us)) {
	        JOptionPane.showMessageDialog(null, "Ya estás inscrito en este equipo.", "Info",
	                JOptionPane.INFORMATION_MESSAGE);
	        return;
	    }

	    // Si el usuario no tiene equipo
	    if (lstEquiposUsuario.isEmpty()) {
	        // Inscribir directamente
	        eqSeleccionado.getListaJugadores().add(us);
	        JOptionPane.showMessageDialog(null, "Jugador inscrito correctamente al equipo " + nombreEquipo + ".");
	    } else {
	        // Confirmar cambio si ya pertenece a otro equipo
	        int respuesta = JOptionPane.showConfirmDialog(null, "¿Quiere cambiar de equipo?");
	        if (respuesta == JOptionPane.YES_OPTION) {
	            // Eliminar usuario del equipo actual
	            Equipo equipoActual = lstEquiposUsuario.get(0);
	            eliminaUsuarioDeEquipo(equipoActual, us);

	            // Inscribir en el nuevo equipo
	            eqSeleccionado.getListaJugadores().add(us);
	            JOptionPane.showMessageDialog(null, "Jugador cambiado al equipo " + nombreEquipo + ".");
	        } else {
	            return; // Cancelar inscripción
	        }
	    }

	    // Actualizar la interfaz
	    lblNumJugadores.setText(Integer.toString(eqSeleccionado.getListaJugadores().size()));
	    btnInscribir.setEnabled(false);
	}

	private void eliminaUsuarioDeEquipo(Equipo equipoActual, Usuario us2) {
		equipoActual.getListaJugadores().remove(us2);
	}

	protected void cargarCombos(ActionEvent e) {
		String nombreEquipo = cbEquipos.getSelectedItem().toString();
		Equipo eqSeleccionado = null;
		for (Equipo eq : MainApp.listaEquipos) {
			if (eq.getNombre().equals(nombreEquipo)) {
				eqSeleccionado = eq;
				break;
			}

		}
		if (eqSeleccionado != null) {
			lblAño.setText(Integer.toString(eqSeleccionado.getAnyo()));
			lblEntrenador.setText(
					eqSeleccionado.getEntrenador().getNombre() + " " + eqSeleccionado.getEntrenador().getApellidos());
			lblGenero.setText(eqSeleccionado.getGenero());
			lblHorario.setText(eqSeleccionado.getHorarioEntreno());
			lblNumJugadores.setText(Integer.toString(eqSeleccionado.getListaJugadores().size()));
		}
		List<Equipo> lstEquiposUsuario = ObtieneEquipoJugador(us);

		if (lstEquiposUsuario.contains(eqSeleccionado)) {
			btnInscribir.setEnabled(false);
		} else {
			btnInscribir.setEnabled(true);

		}

	}
	
	public List<Equipo> ObtieneEquipoJugador(Usuario user) {
		List<Equipo> lstEquipoUsuario = new ArrayList<Equipo>();
		for (Equipo eq : MainApp.listaEquipos) {
			if (user.isEntrenador() && eq.getEntrenador().equals(user)) {
				lstEquipoUsuario.add(eq);
			} else {
				if (eq.getListaJugadores().contains(user)) {
					lstEquipoUsuario.add(eq);
				}
			}
			;
		}
		return lstEquipoUsuario;
	}
	
	private void cargaEquipos() {
		for (Equipo eq : MainApp.listaEquipos) {
			if (((DefaultComboBoxModel<String>) cbEquipos.getModel()).getIndexOf(eq.getNombre()) == -1) {
				cbEquipos.addItem(eq.getNombre());
			}
		}
	}

}
