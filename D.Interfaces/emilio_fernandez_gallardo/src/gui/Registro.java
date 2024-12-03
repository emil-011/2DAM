package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.MainApp;
import modelos.Serie;
import modelos.Usuario;

import java.awt.Font;
import java.awt.Cursor;
import java.awt.Toolkit;

public class Registro extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtEmail;
	private JPasswordField passwordField;
	private JPanel centerPanel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JPasswordField passwordFieldConfirmation;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;

	public Registro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registro.class.getResource("/resources/logo.png")));
		setTitle("Register");
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		this.setModal(true);
		setBounds(100, 100, 528, 682);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 128, 128));
		contentPanel.add(panel_2, BorderLayout.EAST);

		panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		contentPanel.add(panel_3, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Registro de Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		panel_3.add(lblNewLabel);

		centerPanel = new JPanel();
		centerPanel.setBackground(new Color(128, 128, 128));
		contentPanel.add(centerPanel, BorderLayout.CENTER);
		GridLayout gl_centerPanel = new GridLayout();
		gl_centerPanel.setHgap(5);
		gl_centerPanel.setVgap(50);
		gl_centerPanel.setColumns(2);
		gl_centerPanel.setRows(7);
		centerPanel.setLayout(gl_centerPanel);
		
		lblNewLabel_7 = new JLabel("");
		centerPanel.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("");
		centerPanel.add(lblNewLabel_8);

		lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Verdana", Font.PLAIN, 14));
		centerPanel.add(txtNombre);
		txtNombre.setColumns(10);

		lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_2);

		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Verdana", Font.PLAIN, 14));
		centerPanel.add(txtApellidos);
		txtApellidos.setColumns(10);


		lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_4);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Verdana", Font.PLAIN, 14));
		centerPanel.add(txtEmail);
		txtEmail.setColumns(10);

		lblNewLabel_5 = new JLabel("Contraseña");
		lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_5);

		passwordField = new JPasswordField();
		centerPanel.add(passwordField);

		lblNewLabel_6 = new JLabel("Confirmar Contraseña");
		lblNewLabel_6.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNewLabel_6);
		
		passwordFieldConfirmation = new JPasswordField();
		centerPanel.add(passwordFieldConfirmation);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(0, 0, 0));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearUsuario();
			}
		});
		btnRegistrar.setActionCommand("OK");
		buttonPane.add(btnRegistrar);
		getRootPane().setDefaultButton(btnRegistrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		String email = txtEmail.getText();
		String password = new String(passwordField.getPassword());
		String confirmPasswordCamp = new String(passwordFieldConfirmation.getPassword());
		boolean encontrado = false;
		boolean esEntrenador = false;

		// Verificamos que estén todos los campos rellenos
		if (nombre.isBlank() || apellidos.isBlank() || email.isBlank() || password.isBlank()
				|| confirmPasswordCamp.isBlank()) {
			JOptionPane.showMessageDialog(null, "Debes completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			return; // Detenemos la ejecución del método
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

		List<Serie> listaSeries = new ArrayList<>();
		// Si todo está correcto, creamos el usuario
		Usuario newUser = new Usuario(nombre, apellidos, email, confirmPasswordCamp, listaSeries );
		MainApp.lstUsuarios.add(newUser);
		JOptionPane.showMessageDialog(null, "El usuario " + nombre + " ha sido creado con éxito");
		dispose();
	}

}
