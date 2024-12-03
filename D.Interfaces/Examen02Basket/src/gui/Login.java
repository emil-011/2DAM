package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.MainApp;
import models.Usuario;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField passwordField;
	private JLabel lblCuenta;
	private JButton btnSesion;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1;

	public Login() {
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 408);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(30, 144, 255));
		contentPane.add(panel_3, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/resources/Logo.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.CENTER);
		GridLayout gl_panel_4 = new GridLayout();
		gl_panel_4.setVgap(25);
		gl_panel_4.setColumns(2);
		gl_panel_4.setRows(0);
		panel_4.setLayout(gl_panel_4);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_2_1);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_1_1);

		lblNewLabel_2 = new JLabel("Usuario");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_2, "cell 0 0,grow");

		txtUser = new JTextField();
		txtUser.setPreferredSize(new Dimension(20, 20));
		txtUser.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(txtUser, "cell 1 0,growx,aligny center");
		txtUser.setColumns(10);

		lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_1, "cell 0 1,alignx center,growy");

		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(passwordField, "cell 1 1,growx");

		lblCuenta = new JLabel("¿No tienes Cuenta?");
		lblCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToRegistrar();
			}

		});
		lblCuenta.setForeground(new Color(0, 0, 255));
		lblCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblCuenta, "cell 0 2,grow");

		btnSesion = new JButton("Login");
		btnSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iniciarSesion();
			}
		});
		panel_4.add(btnSesion, "cell 1 2,growx,aligny center");

		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_1_1_1);
	}

	protected void iniciarSesion() {
		String email = txtUser.getText();
		String pass = new String(passwordField.getPassword());

		for (Usuario us : MainApp.lstUsuarios) {
			if (email.equals(us.getEmail()) && pass.equals(us.getPass())) {
				if (us.getEsEntrenador()) {
					this.dispose();
					HomeEntrenador home = new HomeEntrenador(us);
					home.setVisible(true);
				} else {
					dispose();
					HomeJugador home = new HomeJugador(us);
					home.setVisible(true);
				}
				return;
			}
		}

		// Si no se encuenta ningún usuario válido
		JOptionPane.showMessageDialog(null, "El usuario es incorrecto o no existe", "Error", JOptionPane.ERROR_MESSAGE);
	}

	protected void goToRegistrar() {
		Registro reg = new Registro();
		reg.setVisible(true);

	}

}
