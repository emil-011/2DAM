package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;

public class VentanaRegistrarNuevoUsuario extends JDialog {
	
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
	private JRadioButton rdbtnEntrenador_1;
	private JRadioButton rdbtnJugador_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	
	public VentanaRegistrarNuevoUsuario(JFrame parent) {
		super(parent, "Registro", true);
		inicializarComponentes();
	}


	private void inicializarComponentes() {
		setModal(true);
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
		
		JLabel lblCancelar = new JLabel("Cancelar");
		lblCancelar.setPreferredSize(new Dimension(150, 50));
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
				lblRegistar.setBackground(new Color(220,220,220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRegistar.setBackground(Color.white);	
			}
			@Override
			public void mouseClicked(MouseEvent e) {
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
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		centerPanel.add(panel);
		
		rdbtnEntrenador_1 = new JRadioButton("Entrenador/a");
		buttonGroup.add(rdbtnEntrenador_1);
		rdbtnEntrenador_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnEntrenador_1.setOpaque(false);
		panel.add(rdbtnEntrenador_1);
		
		rdbtnJugador_1 = new JRadioButton("Jugador/a");
		buttonGroup.add(rdbtnJugador_1);
		rdbtnJugador_1.setOpaque(false);
		panel.add(rdbtnJugador_1);
		
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
	
}
