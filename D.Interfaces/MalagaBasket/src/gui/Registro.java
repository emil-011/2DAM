package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

import main.MainApp;
import utils.Usuario;

import javax.swing.JRadioButton;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;

public class Registro extends JDialog {

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
	private JPanel panel;
	private JRadioButton rdbtnEntrenador;
	private JRadioButton rdbtnJugador;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public Registro(JFrame parent) {
		super(parent, "Registro", true);
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Registro.class.getResource("/resources/Logo.png")));
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
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/resources/Logo.png")));
		banner.add(lblNewLabel);

		JPanel footer = new JPanel();
		footer.setBackground(new Color(28, 175, 255));
		contentPane.add(footer, BorderLayout.SOUTH);

		JLabel lblCancelar = new JLabel("Cancelar");
		lblCancelar.setPreferredSize(new Dimension(150, 50));
		lblCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCancelar.setBackground(new Color(220, 220, 220));
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
		footer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		lblCancelar.setOpaque(true);
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblCancelar.setBackground(Color.WHITE);
		footer.add(lblCancelar);

		lblRegistar = new JLabel("Registrar");
		lblRegistar.setPreferredSize(new Dimension(150, 50));
		lblRegistar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		footer.add(lblRegistar);
		lblRegistar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegistar.setBackground(new Color(220, 220, 220));
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

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 2, 0, 40));

		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		centerPanel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		centerPanel.add(txtNombre);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		centerPanel.add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		centerPanel.add(txtApellidos);

		lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		centerPanel.add(lblFechaDeNacimiento);

		dateChooser = new JDateChooser();
		centerPanel.add(dateChooser);

		lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		centerPanel.add(lblEmail);

		txtEmail = new JTextField();
		centerPanel.add(txtEmail);
		txtEmail.setColumns(10);

		lblContrasenya = new JLabel("Contraseña");
		lblContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 20));
		centerPanel.add(lblContrasenya);

		passwordField = new JPasswordField();
		centerPanel.add(passwordField);

		lblConfirmarContrasenya = new JLabel("Confirmar Contraseña");
		lblConfirmarContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmarContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 20));
		centerPanel.add(lblConfirmarContrasenya);

		passwordFieldConfirmation = new JPasswordField();
		centerPanel.add(passwordFieldConfirmation);

		lblPerfilUsuario = new JLabel("Perfil de usuario");
		lblPerfilUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfilUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		centerPanel.add(lblPerfilUsuario);

		panel = new JPanel();
		centerPanel.add(panel);

		rdbtnEntrenador = new JRadioButton("Entrenador/a");
		rdbtnEntrenador.setActionCommand("entrenador"); // Identificador para entrenador
		buttonGroup.add(rdbtnEntrenador);
		rdbtnEntrenador.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnEntrenador.setOpaque(false);
		panel.add(rdbtnEntrenador);

		rdbtnJugador = new JRadioButton("Jugador/a");
		rdbtnJugador.setActionCommand("jugador"); // Identificador para jugador
		buttonGroup.add(rdbtnJugador);
		rdbtnJugador.setOpaque(false);
		panel.add(rdbtnJugador);

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
		String nombre = txtNombre.getText().trim();
		String apellido = txtApellidos.getText().trim();
		Date fechaNac = dateChooser.getDate();
		String email = txtEmail.getText().trim();
		String pass = new String(passwordField.getPassword());
		String passConfirmed = new String(passwordFieldConfirmation.getPassword());

		// Validar campos vacíos
		if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || pass.isEmpty() || passConfirmed.isEmpty() || fechaNac == null) {
			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Validar selección de perfil
		if (buttonGroup.getSelection() == null) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione un perfil (Entrenador/a o Jugador/a).", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Obtener perfil seleccionado
		String perfil = buttonGroup.getSelection().getActionCommand();
		boolean esEntrenador = perfil.equals("entrenador");

		// Validar que las contraseñas coincidan
		if (!pass.equals(passConfirmed)) {
			JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Verificar si el usuario ya existe
		boolean encontrado = false;
		for (Usuario us : MainApp.listaUsuarios) {
			if (us.getEmail().equals(email)) {
				encontrado = true;
				break;
			}
		}

		if (encontrado) {
			JOptionPane.showMessageDialog(this, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Crear nuevo usuario
		Usuario nuevoUser = new Usuario(nombre, apellido, fechaNac, email, pass, esEntrenador);
		MainApp.listaUsuarios.add(nuevoUser);

		JOptionPane.showMessageDialog(this, "Usuario creado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

		dispose(); // Cerrar la ventana
	}

}
