package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUser;
	private JTextField textPswd;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/resources/besos.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(141, 167, 143, 74);
		lblUsuario.setFont(new Font("Lexend", Font.BOLD, 28));
		contentPane.add(lblUsuario);
		
		JLabel lblContrasenya = new JLabel("Contraseña");
		lblContrasenya.setFont(new Font("Lexend", Font.BOLD, 28));
		lblContrasenya.setBounds(124, 283, 179, 74);
		contentPane.add(lblContrasenya);
		
		textUser = new JTextField();
		textUser.setBounds(141, 255, 128, 33);
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		textPswd = new JTextField();
		textPswd.setBounds(141, 355, 136, 33);
		contentPane.add(textPswd);
		textPswd.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(255, 0, 128));
		btnNewButton.setFont(new Font("Lexend", Font.BOLD, 32));
		btnNewButton.setBounds(141, 415, 136, 49);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/resources/besos.jpg")));
		lblNewLabel.setBounds(-186, 0, 658, 501);
		contentPane.add(lblNewLabel);
	}
}
