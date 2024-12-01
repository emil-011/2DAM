package gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import utils.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JLabel lblBtnLogIn;
	private JLabel lblBtnRegister;
	private JPasswordField passwordField;
	private VentanaLogin login;
	private static final String USUARIOS_REGISTRADOS = "usuarios_registrados.csv";

	public LoginUsuario(VentanaLogin login) {

		try {
			UIManager.setLookAndFeel(new FlatMacDarkLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.login = login;
		setBackground(new Color(183, 243, 249));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(new Color(40, 117, 174));
		lblUsuario.setBounds(68, 41, 114, 36);
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 16));

		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					passwordField.requestFocusInWindow();
				}
			}
		});
		txtUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtUsuario.setBounds(192, 44, 210, 30);
		txtUsuario.setColumns(10);

		JLabel lblPassword = new JLabel("Constraseña");
		lblPassword.setForeground(new Color(40, 117, 174));
		lblPassword.setBounds(56, 88, 114, 36);
		lblPassword.setFont(new Font("Verdana", Font.BOLD, 16));

		add(lblPassword);
		add(lblUsuario);
		add(txtUsuario);

		lblBtnLogIn = new JLabel("Inicia Sesión");
		lblBtnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBtnLogIn.setBackground(new Color(50, 50, 200));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblBtnLogIn.setBackground(new Color(85, 182, 240));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				iniciarSesion();

			}
		});
		lblBtnLogIn.setFont(new Font("Verdana", Font.BOLD, 16));
		lblBtnLogIn.setForeground(new Color(255, 255, 255));
		lblBtnLogIn.setOpaque(true);
		lblBtnLogIn.setBackground(new Color(85, 182, 240));
		lblBtnLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblBtnLogIn.setBounds(267, 135, 133, 36);
		add(lblBtnLogIn);

		lblBtnRegister = new JLabel("Pulsa aquí para registrarte");
		lblBtnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBtnRegister.setBackground(new Color(50, 50, 200));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblBtnRegister.setBackground(new Color(85, 182, 240));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaRegistro registro = new VentanaRegistro();
				registro.setVisible(true);
			}
		});
		lblBtnRegister.setOpaque(true);
		lblBtnRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblBtnRegister.setForeground(Color.WHITE);
		lblBtnRegister.setFont(new Font("Verdana", Font.BOLD, 14));
		lblBtnRegister.setBackground(new Color(85, 182, 240));
		lblBtnRegister.setBounds(171, 182, 229, 31);
		add(lblBtnRegister);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					iniciarSesion();
				}
			}
		});
		passwordField.setFont(new Font("Verdana", Font.PLAIN, 12));
		passwordField.setBounds(192, 88, 210, 30);
		add(passwordField);
	}

	protected void iniciarSesion() {
		String usuario = txtUsuario.getText();
		char[] password = passwordField.getPassword();
		String passwordString = new String(password);
		String perfilUsuario = "";
		boolean inicioExitoso = false;

		StringBuilder updatedContent = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Usuario usuarioLogeado = null;

		try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_REGISTRADOS))) {
			String linea;

			while ((linea = reader.readLine()) != null) {
				String[] camposUsuario = linea.trim().split(";");
				String nombre = camposUsuario[0];
				String apellidos = camposUsuario[1];
				Date fechaNacimiento = null;

				// Attempt to parse the date and handle exceptions
				try {
					fechaNacimiento = sdf.parse(camposUsuario[2]);
				} catch (ParseException e) {
				}
				String perfil = camposUsuario[3];
				String email = camposUsuario[4];
				String contrasenya = camposUsuario[5];

				// Check if this is the correct user
				if (usuario.equals(email) && passwordString.equals(contrasenya)) {
					inicioExitoso = true;
					perfilUsuario = perfil;
					// Update login status to true for this user
					camposUsuario[6] = "true"; // Update active status
				}

				// Append updated line to StringBuilder
				updatedContent.append(String.join(";", camposUsuario)).append("\n");
				if (inicioExitoso) {
					usuarioLogeado = new Usuario(nombre, apellidos, fechaNacimiento, perfil, email, contrasenya,
							inicioExitoso);
				}
			}

			if (inicioExitoso) {
				// Write updated content back to the file
				try (FileWriter writer = new FileWriter(USUARIOS_REGISTRADOS)) {
					writer.write(updatedContent.toString());
				}

				// Redirect user based on profile
				if (perfilUsuario.equals("Cliente")) {
					VentanaCliente ventanaCliente = new VentanaCliente(usuarioLogeado);
					ventanaCliente.setVisible(true);
				} else {
					VentanaAdministracion ventanaAdmin = new VentanaAdministracion(usuarioLogeado);
					ventanaAdmin.setVisible(true);
				}
				login.cerrarVentana();
			} else {
				JOptionPane.showMessageDialog(this, "Error: Usuario o contraseña incorrectos.",
						"Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Error: No se pudo encontrar el archivo de usuarios.",
					"Archivo No Encontrado", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error: Problema al leer el archivo de usuarios.", "Error de Lectura",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
