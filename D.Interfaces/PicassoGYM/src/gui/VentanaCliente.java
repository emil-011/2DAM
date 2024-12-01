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

public class VentanaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblReservarClase;
	private JLabel lblCerrarSesion;
	private JLabel lblUsuario;
	private static final String CLIENTES_REGISTRADOS = "usuarios_registrados.csv";

	public static void main(String[] args) {
		Usuario usuario = new Usuario("Pepin", "Fernandez", new Date(), "cliente", "lolito@gmail.com",
				"1234", true);
		VentanaCliente cliente = new VentanaCliente(usuario);
		cliente.setVisible(true);
	}

	public VentanaCliente(Usuario usuario) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCliente.class.getResource("/resources/logoApp.png")));
		setTitle("Tools");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 250, 804, 622);
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
		lblBanner.setIcon(new ImageIcon(VentanaCliente.class.getResource("/resources/logoApp.png")));
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
		centerPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel(" ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 60));
		centerPanel.add(lblNewLabel_1, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel(" ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 60));
		centerPanel.add(lblNewLabel_2, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		centerPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		lblReservarClase = new JLabel("Reservar Clase");
		lblReservarClase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblReservarClase.setBackground(new Color(230, 230, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblReservarClase.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaReservarClase reservar = new VentanaReservarClase(usuario);
				reservar.setVisible(true);
			}
		});
		lblReservarClase.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblReservarClase.setOpaque(true);
		lblReservarClase.setIcon(new ImageIcon(VentanaCliente.class.getResource("/resources/apuntaAClase.png")));
		lblReservarClase.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReservarClase.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservarClase.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblReservarClase.setBackground(Color.WHITE);
		panel.add(lblReservarClase);

		lblCerrarSesion = new JLabel("Cerrar Sesión");
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
				cerrarSesion();
			}
		});
		lblCerrarSesion.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblCerrarSesion.setOpaque(true);
		lblCerrarSesion.setIcon(new ImageIcon(VentanaCliente.class.getResource("/resources/cierreSesion.png")));
		lblCerrarSesion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCerrarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCerrarSesion.setBackground(Color.WHITE);
		panel.add(lblCerrarSesion);
	}

	protected void cerrarSesion() {
		StringBuilder updatedContent = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(CLIENTES_REGISTRADOS))) {
			String line;

			while ((line = reader.readLine()) != null) {
				String[] datos = line.trim().split(";");

				if (datos[6].equals("true")) {
					datos[6] = "false";
				}

				// Append the (potentially modified) line to the updated content
				updatedContent.append(String.join(";", datos)).append("\n");
			}

			// Write the updated content back to the file
			try (FileWriter writer = new FileWriter(CLIENTES_REGISTRADOS)) {
				writer.write(updatedContent.toString());
			}

			// Close the administration window and open the login window
			dispose();
			VentanaLogin login = new VentanaLogin();
			login.setVisible(true);

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Error: No se pudo encontrar el archivo de usuarios.",
					"Archivo No Encontrado", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error: Problema al leer el archivo de usuarios.", "Error de Lectura",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
