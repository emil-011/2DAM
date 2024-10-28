package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginUsuario loginUsuario;
	private JLabel imgLogin;
	private JPanel topPanel;
	private JLabel lblBanner;
	private JLabel lblBienvenida;

	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/resources/logoApp.png")));
		setTitle("GYM Picasso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 900, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(65, 206, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));

		topPanel = new JPanel();
		topPanel.setBackground(new Color(181, 243, 249));
		mainPanel.add(topPanel, BorderLayout.NORTH);

		lblBanner = new JLabel("GYM Picasso");
		lblBanner.setIcon(new ImageIcon(Login.class.getResource("/resources/logoApp.png")));
		lblBanner.setFont(new Font("Verdana", Font.BOLD, 32));
		lblBanner.setForeground(new Color(22, 101, 143));
		topPanel.add(lblBanner);

		JPanel centerPanel = new JPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		ImageIcon originalIcon = new ImageIcon(Login.class.getResource("/resources/imgLogin.png"));
		Image originalImage = originalIcon.getImage();
		Image scaledImage = originalImage.getScaledInstance(400, 325, Image.SCALE_SMOOTH);

		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		imgLogin = new JLabel("");
		imgLogin.setOpaque(true);
		imgLogin.setBackground(new Color(255, 255, 255));
		imgLogin.setIcon(scaledIcon);
		centerPanel.add(imgLogin, BorderLayout.WEST);

		JPanel panelRight = new JPanel();
		centerPanel.add(panelRight, BorderLayout.CENTER);
		panelRight.setLayout(new BorderLayout(0, 0));

		JPanel panelLogIn = new JPanel();
		panelRight.add(panelLogIn);
		panelLogIn.setLayout(new GridLayout(1, 0, 0, 0));
		loginUsuario = new LoginUsuario();
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportView(loginUsuario);
		panelLogIn.add(scrollPane);

		JPanel panelBienvenida = new JPanel();
		panelBienvenida.setPreferredSize(new Dimension(100, 100));
		panelBienvenida.setBackground(new Color(41, 191, 235));
		panelRight.add(panelBienvenida, BorderLayout.NORTH);
		panelBienvenida.setLayout(new BorderLayout(0, 0));

		lblBienvenida = new JLabel("Bienvenido/a a la aplicación Gym Picasso");
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setForeground(new Color(255, 255, 255));
		lblBienvenida.setFont(new Font("Verdana", Font.PLAIN, 20));
		panelBienvenida.add(lblBienvenida);
		
		
		
	
	}
	
	
	
	

}
