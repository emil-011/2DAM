package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JYearChooser;

import main.MainApp;
import models.Equipo;
import models.Usuario;

public class PanelAddEquipos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private final ButtonGroup botonesGenero = new ButtonGroup();
	private JComboBox<Usuario> cbEntrenador;
	private JTextField txtHorario;
	private JPanel panelBotones;
	private JYearChooser añoEquipo;
	private JRadioButton rbtFem;
	private JRadioButton rbtMasc;
	private JPanel centerPanel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;

	/**
	 * Create the panel.
	 */
	public PanelAddEquipos() {
		inicializarComponentes();
		cargaEquipos();
	}

	private void inicializarComponentes() {
		setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Añade Equipo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);

		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(5, 2, 0, 0));

		lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_1);

		txtNombre = new JTextField();
		centerPanel.add(txtNombre);
		txtNombre.setColumns(10);

		lblNewLabel_2 = new JLabel("Año");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_2);

		añoEquipo = new JYearChooser();
		centerPanel.add(añoEquipo);

		lblNewLabel_3 = new JLabel("Genero");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_3);

		panelBotones = new JPanel();
		centerPanel.add(panelBotones);
		panelBotones.setLayout(new GridLayout(1, 1, 0, 0));

		rbtFem = new JRadioButton("Femenino");
		botonesGenero.add(rbtFem);
		rbtFem.setHorizontalAlignment(SwingConstants.CENTER);
		panelBotones.add(rbtFem);

		rbtMasc = new JRadioButton("Masculino");
		botonesGenero.add(rbtMasc);
		panelBotones.add(rbtMasc);

		lblNewLabel_4 = new JLabel("Horario Entrenamiento");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_4);

		txtHorario = new JTextField();
		centerPanel.add(txtHorario);
		txtHorario.setColumns(10);

		lblNewLabel_5 = new JLabel("Entrenador");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_5);

		cbEntrenador = new JComboBox<Usuario>();
		centerPanel.add(cbEntrenador);

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("Añadir");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addEquipo();
			}
		});
		panel_2.add(btnAdd);
	}

	/**
	 * Añade equipo a la lista de equipos
	 */
	protected void addEquipo() {
		String nombre = txtNombre.getText();
		int año = añoEquipo.getYear();
		String genero = null;
		if (rbtMasc.isSelected()) {
			genero = "Masculino";
		} else {
			genero = "Femenino";
		}
		String horario = txtHorario.getText();
		Usuario Entrenador = (Usuario) cbEntrenador.getSelectedItem();
		Equipo nuevoEquipo = new Equipo(nombre, año, genero, horario, new ArrayList<Usuario>(), Entrenador);
		JOptionPane.showMessageDialog(null, "El equipo " + nombre + " fue añadido correctamente");
		MainApp.lstEquipos.add(nuevoEquipo);

	}

	/**
	 * Carga todos los equipos creados
	 */
	private void cargaEquipos() {
		DefaultComboBoxModel<Usuario> model = (DefaultComboBoxModel<Usuario>) cbEntrenador.getModel();
		for (Usuario eq : MainApp.lstUsuarios) {
			if (eq.getEsEntrenador() && model.getIndexOf(eq) == -1) { // Evita duplicados
				model.addElement(eq);
			}
		}
	}

}
