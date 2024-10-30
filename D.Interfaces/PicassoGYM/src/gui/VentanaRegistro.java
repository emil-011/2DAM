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
import com.toedter.calendar.JDateChooser;
import utils.Usuario;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel banner;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtEmail;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirmation;
	private JLabel lblEnviar;
	private JComboBox<String> comboBox;
	private static final String USUARIOS_REGISTRADOS = "usuarios_registrados.csv";
	private JDateChooser dateChooser;

	public VentanaRegistro() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(650, 250, 600, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		banner = new JPanel();
		banner.setBounds(0, 0, 588, 50);
		contentPane.add(banner);
		banner.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Registro de Usuario");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(41, 191, 235));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 24));
		banner.add(lblNewLabel);

		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNombre.setBounds(30, 76, 200, 40);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNombre.setBounds(213, 83, 300, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblApellidos.setBounds(30, 140, 200, 40);
		contentPane.add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(213, 146, 300, 30);
		txtApellidos.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(txtApellidos);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblFechaDeNacimiento.setBounds(30, 206, 200, 40);
		contentPane.add(lblFechaDeNacimiento);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(213, 213, 300, 30);
		contentPane.add(dateChooser);

		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblPerfil.setBounds(30, 271, 200, 40);
		contentPane.add(lblPerfil);

		comboBox = new JComboBox<String>();
		comboBox.addItem("Cliente");
		comboBox.addItem("Administracion");
		comboBox.setSelectedItem(-1);
		comboBox.setBounds(213, 278, 300, 30);
		contentPane.add(comboBox);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEmail.setBounds(30, 335, 200, 40);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(213, 341, 300, 30);
		txtEmail.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(txtEmail);

		JLabel lblConstrasenya = new JLabel("Constraseña");
		lblConstrasenya.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblConstrasenya.setBounds(30, 402, 200, 40);
		contentPane.add(lblConstrasenya);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Verdana", Font.PLAIN, 14));
		passwordField.setBounds(213, 406, 300, 30);
		contentPane.add(passwordField);

		JLabel lblRepiteConstrasea = new JLabel("Repite Constraseña");
		lblRepiteConstrasea.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblRepiteConstrasea.setBounds(30, 467, 200, 40);
		contentPane.add(lblRepiteConstrasea);

		passwordFieldConfirmation = new JPasswordField();
		passwordFieldConfirmation.setFont(new Font("Verdana", Font.PLAIN, 14));
		passwordFieldConfirmation.setBounds(213, 474, 300, 30);
		contentPane.add(passwordFieldConfirmation);

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
				registrarUsuario();
			}
		});
		lblEnviar.setForeground(new Color(255, 255, 255));
		lblEnviar.setOpaque(true);
		lblEnviar.setBackground(new Color(41, 191, 235));
		lblEnviar.setFont(new Font("Verdana", Font.BOLD, 20));
		lblEnviar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnviar.setBounds(202, 536, 200, 50);
		contentPane.add(lblEnviar);
	}

	protected void registrarUsuario() {

		if (txtNombre.getText().isEmpty() || txtApellidos.getText().isEmpty() || dateChooser.getDate() == null
				|| comboBox.getSelectedItem() == null || txtEmail.getText().isEmpty()
				|| passwordField.getPassword().length == 0 || passwordFieldConfirmation.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, "Error: Todos los campos deben estar rellenados.", "Campos Vacíos",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!confirmarUsuarioRepetido()) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(USUARIOS_REGISTRADOS, true))) {
				String nombre = txtNombre.getText();
				String apellidos = txtApellidos.getText();
				Date fechaNacimiento = dateChooser.getDate();
				String perfil = comboBox.getSelectedItem().toString();
				String email = txtEmail.getText();
				char[] contrasenya = passwordField.getPassword();
				String passwordString = new String(contrasenya);
				char[] contrasenyaConfirmada = passwordFieldConfirmation.getPassword();
				boolean estaLoggeado = false;

				if (Arrays.equals(contrasenya, contrasenyaConfirmada)) {
					Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, perfil, email, passwordString,
							estaLoggeado);

					writer.write(usuario.toString());
					writer.newLine();
					limpiarCampos();
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Error: Las contraseñas no coinciden.", "Contraseña Incorrecta",
							JOptionPane.ERROR_MESSAGE);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected boolean confirmarUsuarioRepetido() {
		try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_REGISTRADOS))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				String[] datosUsuario = linea.trim().split(";");
				if (datosUsuario[4].equals(txtEmail.getText())) {
					JOptionPane.showMessageDialog(this, "Error: Ya existe una cuenta con este email.",
							"Email Duplicado", JOptionPane.ERROR_MESSAGE);
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
		txtApellidos.setText("");
		txtEmail.setText("");
		passwordField.setText("");
		passwordFieldConfirmation.setText("");
		dateChooser.setDate(null);
		comboBox.setSelectedIndex(-1);
	}
}
