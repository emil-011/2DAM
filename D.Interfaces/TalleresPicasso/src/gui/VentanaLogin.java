package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.TallerPicassoMainApp;
import models.Usuario;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel banner;
	private JPanel bottomPanel;
	private JPanel centerPanel;
	private JLabel lblTalleresPicasso;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JLabel lblnoTienesCuenta;
	private JButton btnLogIn;
	private JLabel lblNewLabel;
	private JPanel userLabelPanel;
	private JPanel userTextFieldPanel;
	private JPanel passwordLabelPanel;
	private JPanel passwordFieldPanel;
	private JPanel noAccountLabelPanel;
	private JPanel loginButtonPanel;

	/**
	 * Create the frame.
	 */
	public VentanaLogin() {
		inicializarComponentes();

	}

	private void inicializarComponentes() {
		setTitle("Talleres Picasso");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource("/resources/cocheAzul.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 300, 500, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(133, 200, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		banner = new JPanel();
		banner.setBackground(new Color(133, 200, 250));
		contentPane.add(banner, BorderLayout.NORTH);

		lblTalleresPicasso = new JLabel("Talleres Picasso");
		lblTalleresPicasso.setForeground(new Color(0, 64, 128));
		lblTalleresPicasso.setFont(new Font("Tahoma", Font.PLAIN, 56));
		lblTalleresPicasso.setIcon(new ImageIcon(VentanaLogin.class.getResource("/resources/cocheAzul.png")));
		lblTalleresPicasso.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblTalleresPicasso.setHorizontalTextPosition(SwingConstants.CENTER);

		banner.add(lblTalleresPicasso);

		bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(133, 200, 250));
		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		lblNewLabel = new JLabel("     ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 54));
		bottomPanel.add(lblNewLabel);

		centerPanel = new JPanel();
		centerPanel.setBackground(new Color(133, 200, 250));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(3, 2, 10, 10)); // Espaciado entre filas y columnas

		// Panel envolvente para lblUsuario
		userLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		userLabelPanel.setBackground(new Color(133, 200, 250));
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 24));
		userLabelPanel.add(lblUsuario);
		centerPanel.add(userLabelPanel);

		// Panel envolvente para txtUsername
		userTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		userTextFieldPanel.setBackground(new Color(133, 200, 250));
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUsername.setPreferredSize(new Dimension(200, 30));
		userTextFieldPanel.add(txtUsername);
		centerPanel.add(userTextFieldPanel);

		// Panel envolvente para lblPassword
		passwordLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		passwordLabelPanel.setBackground(new Color(133, 200, 250));
		lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passwordLabelPanel.add(lblPassword);
		centerPanel.add(passwordLabelPanel);

		// Panel envolvente para passwordField
		passwordFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		passwordFieldPanel.setBackground(new Color(133, 200, 250));
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setPreferredSize(new Dimension(200, 30));
		passwordFieldPanel.add(passwordField);
		centerPanel.add(passwordFieldPanel);

		// Panel envolvente para lblnoTienesCuenta
		noAccountLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		noAccountLabelPanel.setBackground(new Color(133, 200, 250));
		lblnoTienesCuenta = new JLabel("¿No tienes cuenta?");
		lblnoTienesCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblnoTienesCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirVentanaRegistro();
			}
		});
		lblnoTienesCuenta.setForeground(new Color(0, 0, 255));
		lblnoTienesCuenta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		noAccountLabelPanel.add(lblnoTienesCuenta);
		centerPanel.add(noAccountLabelPanel);

		// Panel envolvente para btnLogIn
		loginButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		loginButtonPanel.setBackground(new Color(133, 200, 250));
		btnLogIn = new JButton("Inicia Sesión");
		btnLogIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iniciarSesion();
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogIn.setPreferredSize(new Dimension(200, 30));
		loginButtonPanel.add(btnLogIn);
		centerPanel.add(loginButtonPanel);
	}

	protected void iniciarSesion() {
		String username = txtUsername.getText();
		String pass = new String(passwordField.getPassword());

	    boolean usuarioEncontrado = false;

		for (Usuario us : TallerPicassoMainApp.listaUsuarios) {
			if (us.getEmail().equals(username) && us.getContrasenya().equals(pass)) {
				if (us.isEsEmpleado()) {
					VentanaEmpleado ve = new VentanaEmpleado(us);
					ve.setVisible(true);
					usuarioEncontrado = true;
				} else {
					VentanaCliente vc = new VentanaCliente(us);
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

	protected void abrirVentanaRegistro() {
		VentanaRegistro rg = new VentanaRegistro(this);
		rg.setVisible(true);

	}

}
