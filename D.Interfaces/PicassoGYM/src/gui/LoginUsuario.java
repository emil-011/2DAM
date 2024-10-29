package gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

		try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_REGISTRADOS))) {
			String linea;
			boolean inicioExitoso = false;

			while ((linea = reader.readLine()) != null) {
				String[] camposUsuario = linea.trim().split(";");

				if (usuario.equals(camposUsuario[4]) && passwordString.equals(camposUsuario[5])) {
					inicioExitoso = true;
					perfilUsuario = camposUsuario[3];
				}
			}

			if (inicioExitoso) {
				if (perfilUsuario.equals("Cliente")) {
					VentanaCliente ventanaCliente = new VentanaCliente();
					ventanaCliente.setVisible(true);
				} else {
					VentanaAdministracion ventanaAdmin = new VentanaAdministracion();
					ventanaAdmin.setVisible(true);
				}
				// Cierra ventana de login después de iniciar sesion
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
