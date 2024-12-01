package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.MainApp;
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
import java.awt.Dialog.ModalExclusionType;
import java.awt.Toolkit;
import java.awt.GridLayout;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JLabel lblContrasenya;
	private JLabel lblEmail;
	private JPasswordField passwordField;
	private JLabel lblLoginButton;

	public VentanaLogin() {
		inicializarComponentes();
	}

	private void inicializarComponentes() {
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
		centerPanel.setLayout(new GridLayout(0, 2, 40, 50));
		
		JLabel lblEmail_1_1 = new JLabel("");
		lblEmail_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		centerPanel.add(lblEmail_1_1);
		
		JLabel lblEmail_1 = new JLabel("");
		lblEmail_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		centerPanel.add(lblEmail_1);

		lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		centerPanel.add(lblEmail);

		txtUsuario = new JTextField();
		centerPanel.add(txtUsuario);
		txtUsuario.setColumns(10);

		lblContrasenya = new JLabel("Contraseña");
		lblContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 20));
		centerPanel.add(lblContrasenya);

		passwordField = new JPasswordField();
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
		
				JLabel lblNoTienesCuenta = new JLabel("¿No tienes cuenta?");
				lblNoTienesCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblNoTienesCuenta.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						abrirRegistro();
					}
				});
				lblNoTienesCuenta.setForeground(new Color(0, 0, 255));
				lblNoTienesCuenta.setHorizontalAlignment(SwingConstants.CENTER);
				lblNoTienesCuenta.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblNoTienesCuenta.setBackground(new Color(255, 255, 255));
				centerPanel.add(lblNoTienesCuenta);
		lblLoginButton.setBackground(new Color(6, 162, 255));
		lblLoginButton.setOpaque(true);
		lblLoginButton.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginButton.setFont(new Font("Tahoma", Font.BOLD, 26));
		centerPanel.add(lblLoginButton);
		
		JLabel lblEmail_1_1_1 = new JLabel("");
		lblEmail_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		centerPanel.add(lblEmail_1_1_1);
		
		JLabel lblEmail_1_1_2 = new JLabel("");
		lblEmail_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		centerPanel.add(lblEmail_1_1_2);

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
		String username = txtUsuario.getText();
		String pass = new String(passwordField.getPassword());

		boolean usuarioEncontrado = false;

		for (Usuario us : MainApp.listaUsuarios) {
			if (us.getEmail().equals(username) && us.getContrasenya().equals(pass)) {
				if (us.isEntrenador()) {
					VentanaEntrenador ve = new VentanaEntrenador(us);
					ve.setVisible(true);
					usuarioEncontrado = true;
				} else {
					VentanaJugador vc = new VentanaJugador(us);
					vc.setVisible(true);
					dispose();
					usuarioEncontrado = true;
				}

			}
		}

		if (!usuarioEncontrado) {
			JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	protected void abrirRegistro() {
		VentanaRegistrarNuevoUsuario newUser = new VentanaRegistrarNuevoUsuario(this);
		newUser.setVisible(true);
	}
	
}
