package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Clase;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import java.util.ArrayList;
import java.util.List;

public class VentanaNuevaClase extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel banner;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JTextField txtProfesor;
	private JLabel lblEnviar;
	private JRadioButton rdBtnMorning;
	private JRadioButton rdBtnTarde;
	private JLabel lblTurno;

	// Lista para almacenar las clases
	private List<Clase> clases;

	public static void main(String[] args) {
		VentanaNuevaClase nuevaClase = new VentanaNuevaClase();
		nuevaClase.setVisible(true);
	}

	public VentanaNuevaClase() {
		// Inicializa la lista
		clases = new ArrayList<>();

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(650, 250, 600, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		banner = new JPanel();
		banner.setBounds(0, 0, 588, 50);
		contentPane.add(banner);
		banner.setLayout(new BorderLayout(0, 0));

		JLabel lblBanner = new JLabel("Nueva Clase");
		lblBanner.setForeground(new Color(255, 255, 255));
		lblBanner.setOpaque(true);
		lblBanner.setBackground(new Color(41, 191, 235));
		lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanner.setFont(new Font("Verdana", Font.BOLD, 24));
		banner.add(lblBanner);

		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNombre.setBounds(30, 76, 200, 40);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNombre.setBounds(213, 83, 300, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblProfesor = new JLabel("Profesor/a");
		lblProfesor.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblProfesor.setBounds(30, 140, 200, 40);
		contentPane.add(lblProfesor);

		txtProfesor = new JTextField();
		txtProfesor.setColumns(10);
		txtProfesor.setBounds(213, 146, 300, 30);
		txtProfesor.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(txtProfesor);

		lblTurno = new JLabel("Turno");
		lblTurno.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblTurno.setBounds(30, 206, 200, 40);
		contentPane.add(lblTurno);

		lblEnviar = new JLabel("Enviar");
		lblEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnviar.setBackground(new Color(30, 100, 200));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblEnviar.setBackground(new Color(41, 191, 235));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				registrarClase();
			}
		});
		lblEnviar.setForeground(new Color(255, 255, 255));
		lblEnviar.setOpaque(true);
		lblEnviar.setBackground(new Color(41, 191, 235));
		lblEnviar.setFont(new Font("Verdana", Font.BOLD, 20));
		lblEnviar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnviar.setBounds(213, 299, 200, 50);
		contentPane.add(lblEnviar);

		rdBtnMorning = new JRadioButton("Mañana");
		rdBtnMorning.setFont(new Font("Verdana", Font.BOLD, 12));
		rdBtnMorning.setBackground(new Color(255, 255, 255));
		rdBtnMorning.setBounds(213, 206, 109, 23);
		contentPane.add(rdBtnMorning);

		rdBtnTarde = new JRadioButton("Tarde");
		rdBtnTarde.setFont(new Font("Verdana", Font.BOLD, 12));
		rdBtnTarde.setBackground(Color.WHITE);
		rdBtnTarde.setBounds(213, 225, 109, 23);
		contentPane.add(rdBtnTarde);
	}

	protected void registrarClase() {
		if (txtNombre.getText().isEmpty() || txtProfesor.getText().isEmpty()
				|| (!rdBtnMorning.isSelected() && !rdBtnTarde.isSelected())) {
			JOptionPane.showMessageDialog(this, "Error: Todos los campos deben estar rellenados.", "Campos Vacíos",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!isRepetida()) {
			String turno = rdBtnMorning.isSelected() ? "Mañana" : "Tarde";
			Clase nuevaClase = new Clase(txtNombre.getText(), txtProfesor.getText(), turno);
			clases.add(nuevaClase);
			limpiarCampos();
			JOptionPane.showMessageDialog(this, "Clase registrada correctamente.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected boolean isRepetida() {
		String nombreClase = txtNombre.getText().trim();
		String turnoSeleccionado = rdBtnMorning.isSelected() ? "Mañana" : "Tarde";

		for (Clase clase : clases) {
			if (clase.getNombre().equals(nombreClase) && clase.getTurno().equals(turnoSeleccionado)) {
				JOptionPane.showMessageDialog(this, "Error: Ya existe una clase con este nombre y turno.",
						"Clase Duplicada", JOptionPane.ERROR_MESSAGE);
				return true;
			}
		}
		return false;
	}

	protected void limpiarCampos() {
		txtNombre.setText("");
		txtProfesor.setText("");
		rdBtnMorning.setSelected(false);
		rdBtnTarde.setSelected(false);
	}

}