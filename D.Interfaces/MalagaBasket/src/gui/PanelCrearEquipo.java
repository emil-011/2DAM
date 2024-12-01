package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JYearChooser;

public class PanelCrearEquipo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel centerPanelEquipo;
	private JPanel panel_1;
	private JTextField txtNombre;
	private JLabel lblNewLabel;
	private JTextField txtHorario;
	private JComboBox<String> comboBoxEntrenador;
	private JLabel lblAnyo;
	private JLabel lblGenero;
	private JLabel lblHoraEntrenamiento;
	private JLabel lblEntrenador;
	private JRadioButton rdbtnFemenino;
	private JRadioButton rdbtnMasculino;
	private JYearChooser yearChooser;

	public PanelCrearEquipo() {
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		add(panel, BorderLayout.NORTH);

		JLabel lblAnyadeEquipo = new JLabel("Añade Equipo");
		lblAnyadeEquipo.setFont(new Font("Tahoma", Font.PLAIN, 28));
		panel.add(lblAnyadeEquipo);

		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
		fl_panel_1.setVgap(10);
		fl_panel_1.setHgap(20);
		add(panel_1, BorderLayout.SOUTH);

		JLabel lblAnyade = new JLabel("Añade");
		lblAnyade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblAnyade.setPreferredSize(new Dimension(175, 50));
		lblAnyade.setMaximumSize(new Dimension(10000, 10000));
		lblAnyade.setMinimumSize(new Dimension(200, 30));
		lblAnyade.setIconTextGap(10);
		lblAnyade.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAnyade.setBounds(new Rectangle(0, 0, 200, 200));
		lblAnyade.setOpaque(true);
		lblAnyade.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnyade.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAnyade.setBackground(new Color(6, 162, 255));
		panel_1.add(lblAnyade);

		centerPanelEquipo = new JPanel();
		centerPanelEquipo.setOpaque(false);
		add(centerPanelEquipo, BorderLayout.CENTER);
		centerPanelEquipo.setLayout(null);

		lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(0, 11, 450, 44);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		centerPanelEquipo.add(lblNewLabel);

		txtNombre = new JTextField();
		txtNombre.setBounds(390, 12, 419, 44);
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		centerPanelEquipo.add(txtNombre);
		txtNombre.setColumns(10);

		lblAnyo = new JLabel("Año");
		lblAnyo.setBounds(0, 94, 450, 44);
		lblAnyo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnyo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		centerPanelEquipo.add(lblAnyo);

		lblGenero = new JLabel("Género");
		lblGenero.setBounds(0, 188, 450, 44);
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 18));
		centerPanelEquipo.add(lblGenero);

		rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnFemenino.setOpaque(false);
		rdbtnFemenino.setBounds(390, 191, 203, 44);
		centerPanelEquipo.add(rdbtnFemenino);

		lblHoraEntrenamiento = new JLabel("Hora de Entrenamiento");
		lblHoraEntrenamiento.setBounds(0, 282, 450, 44);
		lblHoraEntrenamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraEntrenamiento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		centerPanelEquipo.add(lblHoraEntrenamiento);

		txtHorario = new JTextField();
		txtHorario.setBounds(390, 283, 419, 44);
		txtHorario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHorario.setColumns(10);
		centerPanelEquipo.add(txtHorario);

		lblEntrenador = new JLabel("Entrenador");
		lblEntrenador.setBounds(0, 365, 450, 44);
		lblEntrenador.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrenador.setFont(new Font("Tahoma", Font.PLAIN, 18));
		centerPanelEquipo.add(lblEntrenador);

		comboBoxEntrenador = new JComboBox<String>();
		comboBoxEntrenador.setBounds(390, 368, 419, 44);
		centerPanelEquipo.add(comboBoxEntrenador);

		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnMasculino.setOpaque(false);
		rdbtnMasculino.setBounds(608, 191, 203, 44);
		centerPanelEquipo.add(rdbtnMasculino);

		yearChooser = new JYearChooser();
		yearChooser.setBounds(new Rectangle(200, 50, 0, 0));
		yearChooser.getSpinner().setBounds(new Rectangle(200, 50, 0, 0));
		yearChooser.setBounds(390, 95, 419, 44);
		centerPanelEquipo.add(yearChooser);

	
	}

}
