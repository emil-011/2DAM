package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JRadioButton;

public class VentanaNuevaClase extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel banner;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JTextField txtProfesor;
	private JLabel lblEnviar;
	private static final String CLASES = "clases.csv";
	private JRadioButton rdBtnMorning;
	private JRadioButton rdBtnTarde;
	private JLabel lblTurno;

	public static void main(String[] args) {
		VentanaNuevaClase nuevaClase = new VentanaNuevaClase();
		nuevaClase.setVisible(true);
	}

	public VentanaNuevaClase() {

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
			String turno = "";

			if (rdBtnMorning.isSelected()) {
				turno = "Mañana";
			} else if (rdBtnTarde.isSelected()) {
				turno = "Tarde";
			}

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLASES, true))) {
				writer.write(txtNombre.getText() + ";" + txtProfesor.getText() + ";" + turno);
				writer.newLine();
				limpiarCampos();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected boolean isRepetida() {
		try (BufferedReader reader = new BufferedReader(new FileReader(CLASES))) {
			String linea;
			String nombreClase = txtNombre.getText().trim();
			String turnoSeleccionado = rdBtnMorning.isSelected() ? rdBtnMorning.getText() : rdBtnTarde.getText();
			while ((linea = reader.readLine()) != null) {
				String[] datosClase = linea.trim().split(";");
				// Compara el nombre de la clase y el turno seleccionado
				if (datosClase[0].equals(nombreClase) && datosClase[2].equals(turnoSeleccionado)) {
					JOptionPane.showMessageDialog(this, "Error: Ya existe una clase con este nombre y turno.",
							"Clase Duplicada", JOptionPane.ERROR_MESSAGE);
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
