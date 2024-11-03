package gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Usuario;
import main.MainApp;

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
		String username = txtUsuario.getText().trim();
		String contrasenya = new String(passwordField.getPassword()).trim();

		// Verifica si se ingresaron las credenciales
		if (username.isEmpty() || contrasenya.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, complete ambos campos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Busca el usuario en la lista de usuarios registrados
		for (Usuario usuario : MainApp.getUsuariosRegistrados()) {
			if (usuario.getEmail().equals(username) && usuario.getContrasenya().equals(contrasenya)) {
				// Usuario encontrado, marcarlo como logueado
				usuario.setLogged(true);
				JOptionPane.showMessageDialog(this, "¡Inicio de sesión exitoso!", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				login.dispose();

				// Abre la ventana correspondiente según el perfil del usuario
				if (usuario.getPerfil().equals("Cliente")) {
					VentanaCliente ventanaCliente = new VentanaCliente(usuario);
					ventanaCliente.setVisible(true);
				} else {
					VentanaAdministracion ventanaAdmin = new VentanaAdministracion(usuario);
					ventanaAdmin.setVisible(true);
				}
				return;
			}
		}

		// Si no se encontró al usuario o las credenciales son incorrectas
		JOptionPane.showMessageDialog(this, "Credenciales incorrectas. Inténtalo de nuevo.", "Error",
				JOptionPane.ERROR_MESSAGE);

	}
}
