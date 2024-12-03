package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import utils.Usuario;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
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
import java.awt.Dialog.ModalExclusionType;
import java.awt.Toolkit;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JLabel lblContrasenya;
	private JLabel lblEmail;
	private JPasswordField passwordField;
	private JLabel lblLoginButton;
	private static final String USUARIOS_REGISTRADOS = "usuarios_registrados.csv";

	public VentanaLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource("/resources/Logo.png")));
		setTitle("Login");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 598);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(28, 175, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel banner = new JPanel();
		banner.setBackground(new Color(28, 175, 255));
		contentPane.add(banner, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaLogin.class.getResource("/resources/Logo.png")));
		banner.add(lblNewLabel);
		
		JPanel footer = new JPanel();
		footer.setBackground(new Color(28, 175, 255));
		contentPane.add(footer, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("   ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		footer.add(lblNewLabel_1);
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(null);
		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(105, 77, 124, 36);
		centerPanel.add(lblEmail);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(280, 77, 221, 32);
		centerPanel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblContrasenya = new JLabel("Contraseña");
		lblContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContrasenya.setBounds(105, 143, 124, 36);
		centerPanel.add(lblContrasenya);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(280, 147, 221, 36);
		centerPanel.add(passwordField);
		
		lblLoginButton = new JLabel("LOGIN");
		lblLoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblLoginButton.setBackground(new Color(6, 100, 200));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblLoginButton.setBackground(new Color(6, 162, 255));
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				iniciarSesion();
			}
		});
		lblLoginButton.setBackground(new Color(6, 162, 255));
		lblLoginButton.setOpaque(true);
		lblLoginButton.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginButton.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblLoginButton.setBounds(266, 242, 184, 56);
		centerPanel.add(lblLoginButton);
		
		JLabel lblNoTienesCuenta = new JLabel("¿No tienes cuenta?");
		lblNoTienesCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNoTienesCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaRegistrarNuevoUsuario newUser = new VentanaRegistrarNuevoUsuario();
				newUser.setVisible(true);
			}
		});
		lblNoTienesCuenta.setForeground(new Color(0, 0, 255));
		lblNoTienesCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoTienesCuenta.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNoTienesCuenta.setBackground(new Color(255, 255, 255));
		lblNoTienesCuenta.setBounds(67, 246, 177, 56);
		centerPanel.add(lblNoTienesCuenta);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(28, 175, 255));
		contentPane.add(leftPanel, BorderLayout.WEST);
		
		JLabel lblNewLabel_2 = new JLabel("         ");
		leftPanel.add(lblNewLabel_2);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(28, 175, 255));
		contentPane.add(rightPanel, BorderLayout.EAST);
		
		JLabel lblNewLabel_2_1 = new JLabel("         ");
		rightPanel.add(lblNewLabel_2_1);
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

				try {
					fechaNacimiento = sdf.parse(camposUsuario[2]);
				} catch (ParseException e) {
				}
				String perfil = camposUsuario[3];
				String email = camposUsuario[4];
				String contrasenya = camposUsuario[5];

				if (usuario.equals(email) && passwordString.equals(contrasenya)) {
					inicioExitoso = true;
					perfilUsuario = perfil;		
					camposUsuario[6] = "true";
				}

				updatedContent.append(String.join(";", camposUsuario)).append("\n");
				if (inicioExitoso) {
					usuarioLogeado = new Usuario(nombre, apellidos, fechaNacimiento, perfil, email, contrasenya,
							inicioExitoso);
				}
			}

			if (inicioExitoso) {
				
				try (FileWriter writer = new FileWriter(USUARIOS_REGISTRADOS)) {
					writer.write(updatedContent.toString());
				}

				
				if (perfilUsuario.equals("Jugador/a")) {
					VentanaJugador ventanaJugador = new VentanaJugador(usuarioLogeado);
					ventanaJugador.setVisible(true);
				} else {
					VentanaEntrenador ventanaEntrenador = new VentanaEntrenador(usuarioLogeado);
					ventanaEntrenador.setVisible(true);
				}
				dispose();
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
