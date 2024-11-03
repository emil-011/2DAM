package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import utils.Usuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VentanaAdministracion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAnyadirClase;
	private JLabel lblReservas;
	private JLabel lblVerClientes;
	private JLabel lblCerrarSesion;
	private static final String CLIENTES_REGISTRADOS = "usuarios_registrados.csv";
	private JLabel lblUsuario;

	public static void main(String[] args) {
		
//		VentanaAdministracion ventana = new VentanaAdministracion();
//		ventana.setVisible(true); // Muestra la ventana
	}

	public VentanaAdministracion(Usuario usuario) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaAdministracion.class.getResource("/resources/logoApp.png")));
		setTitle("Tools");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 250, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Panel superior con título y logo
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(181, 243, 249));
		contentPane.add(topPanel, BorderLayout.NORTH);

		JLabel lblBanner = new JLabel("   GYM Picasso");
		lblBanner.setIcon(new ImageIcon(VentanaAdministracion.class.getResource("/resources/logoApp.png")));
		lblBanner.setForeground(new Color(22, 101, 143));
		lblBanner.setFont(new Font("Verdana", Font.BOLD, 32));
		topPanel.add(lblBanner);

		// Panel inferior con nombre y fecha
		JPanel footer = new JPanel();
		footer.setBackground(new Color(181, 243, 249));
		contentPane.add(footer, BorderLayout.SOUTH);

		lblUsuario = new JLabel("");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = sdf.format(new Date());
		lblUsuario.setText(usuario.getNombre() + " " + usuario.getApellidos() + " " + formattedDate);
		lblUsuario.setForeground(new Color(22, 100, 143));
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 20));
		footer.add(lblUsuario);

		JPanel leftGap = new JPanel();
		leftGap.setBackground(new Color(255, 255, 255));
		contentPane.add(leftGap, BorderLayout.WEST);

		JLabel lblNewLabel_5 = new JLabel("                              ");
		leftGap.add(lblNewLabel_5);

		JPanel rightGap = new JPanel();
		rightGap.setBackground(new Color(255, 255, 255));
		contentPane.add(rightGap, BorderLayout.EAST);

		JLabel lblNewLabel_6 = new JLabel("                              ");
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		rightGap.add(lblNewLabel_6);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(2, 2, 0, 0));

		lblAnyadirClase = new JLabel("Añade Clase");
		lblAnyadirClase.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnyadirClase.setOpaque(true);
		lblAnyadirClase.setBackground(new Color(255, 255, 255));
		lblAnyadirClase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAnyadirClase.setBackground(new Color(230, 230, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblAnyadirClase.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaNuevaClase nuevaClase = new VentanaNuevaClase();
				nuevaClase.setVisible(true);
			}
		});
		lblAnyadirClase.setIcon(new ImageIcon(VentanaAdministracion.class.getResource("/resources/addClase.png")));
		lblAnyadirClase.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnyadirClase.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAnyadirClase.setVerticalTextPosition(SwingConstants.BOTTOM);
		centerPanel.add(lblAnyadirClase);

		lblReservas = new JLabel("Ver Reservas");
		lblReservas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblReservas.setBackground(new Color(255, 255, 255));
		lblReservas.setOpaque(true);
		lblReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblReservas.setBackground(new Color(230, 230, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblReservas.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaListarReservas listarReservas = new VentanaListarReservas();
				listarReservas.setVisible(true);
			}
		});
		lblReservas.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblReservas.setIcon(new ImageIcon(VentanaAdministracion.class.getResource("/resources/listarReservas.png")));
		lblReservas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReservas.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblReservas);

		lblVerClientes = new JLabel("Ver Clientes");
		lblVerClientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVerClientes.setBackground(new Color(255, 255, 255));
		lblVerClientes.setOpaque(true);
		lblVerClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblVerClientes.setBackground(new Color(230, 230, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblVerClientes.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaListarClientes tablaClientes = new VentanaListarClientes();
				tablaClientes.setVisible(true);
			}
		});
		lblVerClientes.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblVerClientes.setIcon(new ImageIcon(VentanaAdministracion.class.getResource("/resources/listarUsuarios.png")));
		lblVerClientes.setHorizontalTextPosition(SwingConstants.CENTER);
		lblVerClientes.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblVerClientes);

		lblCerrarSesion = new JLabel("Cerrar Sesión");
		lblCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCerrarSesion.setBackground(new Color(255, 255, 255));
		lblCerrarSesion.setOpaque(true);
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCerrarSesion.setBackground(new Color(230, 230, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblCerrarSesion.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				cerrarSesion(usuario);
			}
		});
		lblCerrarSesion.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblCerrarSesion.setIcon(new ImageIcon(VentanaAdministracion.class.getResource("/resources/cierreSesion.png")));
		lblCerrarSesion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCerrarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblCerrarSesion);
	}

	protected void cerrarSesion(Usuario usuario) {
	    // Cambiar el estado del usuario a no logueado
	    usuario.setLogged(false);

	    // Cerrar la ventana actual
	    dispose();

	    // Mostrar la ventana de login
	    VentanaLogin login = new VentanaLogin();
	    login.setVisible(true);
	}

}
