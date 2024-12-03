package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.MainApp;
import modelos.Usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Toolkit;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private JLabel lblLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/resources/logo.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(88, 88, 88));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("Seriefly");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 36, 36));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 64));
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/resources/logo.png")));
		panel.add(lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("Inicio de Sesion");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_3.add(lblNewLabel_2);

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(3, 2, 0, 0));

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblUsuario);

		txtUsuario = new JTextField();
		panel_4.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblContrasenya = new JLabel("Contraseña");
		lblContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblContrasenya);

		passwordField = new JPasswordField();
		panel_4.add(passwordField);

		JLabel lblnoTienesCuenta = new JLabel("¿No tienes cuenta?");
		lblnoTienesCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirRegistro();
			}
		});
		lblnoTienesCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblnoTienesCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblnoTienesCuenta.setForeground(new Color(0, 0, 255));
		panel_4.add(lblnoTienesCuenta);

		lblLogin = new JLabel("Inicia Sesión");
		lblLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblLogin.setBackground(new Color(245, 30, 30));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblLogin.setBackground(new Color(255, 0, 0));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				iniciarSesion();
			}
		});
		lblLogin.setBackground(new Color(255, 0, 0));
		lblLogin.setOpaque(true);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblLogin);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(88, 88, 88));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirProximasEmisiones();
			}
		});
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setBackground(new Color(128, 128, 128));
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/resources/imgLogin.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel, BorderLayout.CENTER);
	}

	protected void iniciarSesion() {
		String email = txtUsuario.getText();
		String pass = new String(passwordField.getPassword());

		for (Usuario us : MainApp.lstUsuarios) {
			if (email.equals(us.getEmail()) && pass.equals(us.getPass())) {
				Home home = new Home();
				home.setVisible(true);
				dispose();
				return;
			}
		}

		// Si no se encuenta ningún usuario válido
		JOptionPane.showMessageDialog(null, "El usuario es incorrecto o no existe", "Error", JOptionPane.ERROR_MESSAGE);
	}

	protected void abrirRegistro() {
		Registro rg = new Registro();
		rg.setVisible(true);

	}

	protected void abrirProximasEmisiones() {
		ProximasEmisiones pe = new ProximasEmisiones();
		pe.setVisible(true);
	}

}
