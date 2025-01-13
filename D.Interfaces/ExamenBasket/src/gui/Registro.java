package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import main.MainApp;
import models.Usuario;

public class Registro extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtEmail;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirm;
	private final ButtonGroup grupoPerfiles = new ButtonGroup();
	private JDateChooser dateChooser;
	private JPanel centerPanel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JRadioButton rdbEntrenador;
	private JRadioButton rdbJugador;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;

	public Registro() {
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		this.setModal(true);
		setBounds(100, 100, 528, 682);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(30, 144, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		panel_1 = new JPanel();
		contentPanel.add(panel_1, BorderLayout.WEST);

		panel_2 = new JPanel();
		contentPanel.add(panel_2, BorderLayout.EAST);

		panel_3 = new JPanel();
		panel_3.setBackground(new Color(30, 144, 255));
		contentPanel.add(panel_3, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Registro.class.getResource("/resources/Logo.png")));
		panel_3.add(lblNewLabel);

		centerPanel = new JPanel();
		contentPanel.add(centerPanel, BorderLayout.CENTER);
		GridLayout gl_centerPanel = new GridLayout();
		gl_centerPanel.setVgap(30);
		gl_centerPanel.setColumns(2);
		gl_centerPanel.setRows(0);
		centerPanel.setLayout(gl_centerPanel);

		lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_1);

		txtNombre = new JTextField();
		centerPanel.add(txtNombre);
		txtNombre.setColumns(10);

		lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_2);

		txtApellidos = new JTextField();
		centerPanel.add(txtApellidos);
		txtApellidos.setColumns(10);

		lblNewLabel_3 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_3);

		dateChooser = new JDateChooser();
		centerPanel.add(dateChooser);

		lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_4);

		txtEmail = new JTextField();
		centerPanel.add(txtEmail);
		txtEmail.setColumns(10);

		lblNewLabel_5 = new JLabel("Contraseña");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_5);

		passwordField = new JPasswordField();
		centerPanel.add(passwordField);

		lblNewLabel_6 = new JLabel("Confirmar Contraseña");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_6);

		passwordConfirm = new JPasswordField();
		centerPanel.add(passwordConfirm);

		lblNewLabel_7 = new JLabel("Perfil de Usuario");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_7);

		JPanel panel = new JPanel();
		centerPanel.add(panel);

		rdbJugador = new JRadioButton("Jugador");
		rdbJugador.setHorizontalAlignment(SwingConstants.CENTER);
		grupoPerfiles.add(rdbJugador);
		panel.add(rdbJugador);

		rdbEntrenador = new JRadioButton("Entrenador");
		grupoPerfiles.add(rdbEntrenador);
		panel.add(rdbEntrenador);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(30, 144, 255));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearUsuario();
			}
		});
		btnRegistrar.setActionCommand("OK");
		buttonPane.add(btnRegistrar);
		getRootPane().setDefaultButton(btnRegistrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancelar.setActionCommand("Cancel");
		buttonPane.add(btnCancelar);

	}

	protected void crearUsuario() {
		String nombre = txtNombre.getText();
		String apellidos = txtApellidos.getText();
		Date fechaNacimientoCruda = dateChooser.getDate();
		String email = txtEmail.getText();
		String password = new String(passwordField.getPassword());
		String confirmPasswordCamp = new String(passwordConfirm.getPassword());
		boolean encontrado = false;
		boolean esEntrenador = false;

		// Verificamos que estén todos los campos rellenos
		if (nombre.isBlank() || apellidos.isBlank() || email.isBlank() || password.isBlank()
				|| confirmPasswordCamp.isBlank() || fechaNacimientoCruda == null) {
			JOptionPane.showMessageDialog(null, "Debes completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			return; // Detenemos la ejecución del método
		}

		// Verificamos que se seleccione un perfil de usuario
		if (!rdbEntrenador.isSelected() && !rdbJugador.isSelected()) {
			JOptionPane.showMessageDialog(null, "Debes elegir un perfil de usuario", "Error",
					JOptionPane.ERROR_MESSAGE);
			return; // Detenemos la ejecución del método
		}

		// Determinamos el perfil seleccionado
		if (rdbEntrenador.isSelected()) {
			esEntrenador = true;
		}

		// Verificamos que las contraseñas sean iguales
		if (!password.equals(confirmPasswordCamp)) {
			JOptionPane.showMessageDialog(null, "La contraseña no coincide con la de confirmación", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			return; // Detenemos la ejecución del método
		}

		// Verificamos si el usuario ya existe
		for (Usuario us : MainApp.lstUsuarios) {
			if (us.getEmail().equals(email)) {
				encontrado = true;
				break;
			}
		}

		if (encontrado) {
			JOptionPane.showMessageDialog(null, "El usuario a crear ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
			return; // Detenemos la ejecución del método
		}

		// Si todo está correcto, creamos el usuario
		Usuario newUser = new Usuario(nombre, apellidos, fechaNacimientoCruda, esEntrenador, email,
				confirmPasswordCamp);
		MainApp.lstUsuarios.add(newUser);
		JOptionPane.showMessageDialog(null, "El usuario " + nombre + " ha sido creado con éxito");
		dispose();
	}

}
