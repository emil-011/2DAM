package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import utils.Usuario;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.Cursor;
import java.awt.Toolkit;

public class VentanaRegistrarNuevoUsuario extends JFrame {
	
	private static final String USUARIOS_REGISTRADOS = "usuarios_registrados.csv";
	
	private JTextField txtEmail;
	private JLabel lblContrasenya;
	private JLabel lblEmail;
	private JPasswordField passwordField;
	private JLabel lblRegistar;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JLabel lblNombre;
	private JTextField txtApellidos;
	private JLabel lblApellidos;
	private JPasswordField passwordFieldConfirmation;
	private JLabel lblPerfilUsuario;
	private JLabel lblConfirmarContrasenya;
	private JDateChooser dateChooser;
	private JLabel lblFechaDeNacimiento;
	private JRadioButton rdbtnEntrenador;
	private JRadioButton rdbtnJugador;
	

	/**
	 * Create the frame.
	 */
	public VentanaRegistrarNuevoUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistrarNuevoUsuario.class.getResource("/resources/Logo.png")));
		setTitle("Registrar Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 692, 715);
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
		footer.setLayout(new GridLayout(3, 3, 10, 10));
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		footer.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_1 = new JLabel("   ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		footer.add(lblNewLabel_1);
		
		JLabel lblCancelar = new JLabel("Cancelar");
		lblCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCancelar.setBackground(new Color(220,220,220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCancelar.setBackground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblCancelar.setOpaque(true);
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblCancelar.setBackground(Color.WHITE);
		footer.add(lblCancelar);
		
		lblRegistar = new JLabel("Registrar");
		lblRegistar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		footer.add(lblRegistar);
		lblRegistar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegistar.setBackground(new Color(220,220,220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRegistar.setBackground(Color.white);	
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				registrarUsuario();
			}
		});
		lblRegistar.setBackground(new Color(255, 255, 255));
		lblRegistar.setOpaque(true);
		lblRegistar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistar.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		JLabel lblNewLabel_3 = new JLabel("");
		footer.add(lblNewLabel_3);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(null);
		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(110, 199, 124, 36);
		centerPanel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(285, 199, 221, 32);
		centerPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblContrasenya = new JLabel("Contraseña");
		lblContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContrasenya.setBounds(81, 256, 124, 36);
		centerPanel.add(lblContrasenya);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(285, 260, 221, 36);
		centerPanel.add(passwordField);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre.setBounds(110, 35, 124, 36);
		centerPanel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(285, 35, 221, 32);
		centerPanel.add(txtNombre);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblApellidos.setBounds(110, 93, 124, 36);
		centerPanel.add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(285, 93, 221, 32);
		centerPanel.add(txtApellidos);
		
		lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFechaDeNacimiento.setBounds(42, 140, 221, 36);
		centerPanel.add(lblFechaDeNacimiento);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(285, 140, 226, 31);
		centerPanel.add(dateChooser);
		
		lblConfirmarContrasenya = new JLabel("Confirmar Contraseña");
		lblConfirmarContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConfirmarContrasenya.setBounds(42, 325, 221, 36);
		centerPanel.add(lblConfirmarContrasenya);
		
		passwordFieldConfirmation = new JPasswordField();
		passwordFieldConfirmation.setBounds(285, 329, 221, 36);
		centerPanel.add(passwordFieldConfirmation);
		
		lblPerfilUsuario = new JLabel("Perfil de usuario");
		lblPerfilUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPerfilUsuario.setBounds(81, 394, 182, 36);
		centerPanel.add(lblPerfilUsuario);
		
		rdbtnEntrenador = new JRadioButton("Entrenador/a");
		rdbtnEntrenador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnEntrenador.setOpaque(false);
		rdbtnEntrenador.setBounds(288, 405, 109, 23);
		centerPanel.add(rdbtnEntrenador);
		
		rdbtnJugador = new JRadioButton("Jugador/a");
		rdbtnJugador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnJugador.setOpaque(false);
		rdbtnJugador.setBounds(414, 405, 109, 23);
		centerPanel.add(rdbtnJugador);
		
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
	
	protected void registrarUsuario() {

		if (txtNombre.getText().isEmpty() || txtApellidos.getText().isEmpty() || dateChooser.getDate() == null
				|| (!rdbtnEntrenador.isSelected() && !rdbtnJugador.isSelected()) || txtEmail.getText().isEmpty()
				|| passwordField.getPassword().length == 0 || passwordFieldConfirmation.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, "Error: Todos los campos deben estar rellenados.", "Campos Vacíos",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String perfil = "";
		
		if (rdbtnEntrenador.isSelected()) {
			perfil = "Entrenador/a";
		} else if (rdbtnJugador.isSelected()) {
			perfil = "Jugador/a";
		}

		if (!confirmarUsuarioRepetido()) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(USUARIOS_REGISTRADOS, true))) {
				String nombre = txtNombre.getText();
				String apellidos = txtApellidos.getText();
				Date fechaNacimiento = dateChooser.getDate();
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
					JOptionPane.showMessageDialog(this, "Usuario Creado Correctamente!!!", "Usuario creado",
							JOptionPane.INFORMATION_MESSAGE);					
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
		rdbtnEntrenador.setSelected(false);
		rdbtnJugador.setSelected(false);
	}
}
