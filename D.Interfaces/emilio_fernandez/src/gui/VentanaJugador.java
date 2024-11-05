package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import utils.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class VentanaJugador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblHome;
	private JLabel lblLogOut;
	private JLabel lblEquipos;
	private JPanel centerPanel;
	private static final String EQUIPOS = "equipos.csv";
	private static final String USUARIOS_REGISTRADOS = "usuarios_registrados.csv";

	public VentanaJugador(Usuario usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaJugador.class.getResource("/resources/Logo.png")));
		setTitle("Jugador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel banner = new JPanel();
		banner.setBackground(new Color(9, 175, 255));
		contentPane.add(banner, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaJugador.class.getResource("/resources/Logo.png")));
		banner.add(lblNewLabel);

		JPanel leftMenu = new JPanel();
		leftMenu.setBackground(new Color(119, 210, 255));
		contentPane.add(leftMenu, BorderLayout.WEST);
		leftMenu.setLayout(new GridLayout(3, 2, 50, 0));

		lblHome = new JLabel("Home");
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome.setForeground(new Color(255, 255, 255));
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHome.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblHome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHome.setIcon(new ImageIcon(VentanaJugador.class.getResource("/resources/Home.png")));
		leftMenu.add(lblHome);

		lblEquipos = new JLabel("Equipos");
		lblEquipos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEquipos.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblEquipos.setIcon(new ImageIcon(VentanaJugador.class.getResource("/resources/Equipo.png")));
		lblEquipos.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEquipos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipos.setForeground(Color.WHITE);
		lblEquipos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		leftMenu.add(lblEquipos);

		lblLogOut = new JLabel("    Cerrar Sesión    ");
		lblLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cerrarSesion();
			}
		});
		lblLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogOut.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblLogOut.setIcon(new ImageIcon(VentanaJugador.class.getResource("/resources/Logout.png")));
		lblLogOut.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogOut.setForeground(Color.WHITE);
		lblLogOut.setFont(new Font("Tahoma", Font.PLAIN, 18));
		leftMenu.add(lblLogOut);

		JPanel footer = new JPanel();
		footer.setBackground(new Color(9, 175, 255));
		contentPane.add(footer, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel(" ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 38));
		footer.add(lblNewLabel_1);

		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		mainPanel.add(panel, BorderLayout.WEST);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		mainPanel.add(panel_1, BorderLayout.SOUTH);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		mainPanel.add(panel_2, BorderLayout.NORTH);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		mainPanel.add(panel_4, BorderLayout.EAST);

		centerPanel = new JPanel();
		centerPanel.setBackground(new Color(255, 255, 255));
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(null);

		JLabel lblError = new JLabel("Actualmente no tienes equipo");
		lblError.setBounds(200, 40, 400, 378);
		lblError.setVerticalTextPosition(SwingConstants.TOP);
		lblError.setIcon(new ImageIcon(VentanaJugador.class.getResource("/resources/ImgJugadorError.png")));
		lblError.setHorizontalTextPosition(SwingConstants.CENTER);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 28));
		centerPanel.add(lblError);
	}

	protected void escogerPanel(Usuario usuario) {
		try (BufferedReader reader = new BufferedReader(new FileReader(EQUIPOS))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				String[] datosEquipo = linea.trim().split(";");

				if (datosEquipo[4].equals(usuario.getNombre())) {
					// Cargamos los equipos que tengan su nombre
					centerPanel.removeAll();
					centerPanel.setLayout(new BorderLayout());
					JPanel panelEquipos = new PanelHomeEntrenador(usuario);
					panelEquipos.setVisible(true);
					centerPanel.add(panelEquipos, BorderLayout.CENTER);
					centerPanel.revalidate();
					centerPanel.repaint();
				} else {
					// Pantalla de "error"
					centerPanel.removeAll();
					centerPanel.setLayout(new BorderLayout());
					JPanel panelEquipos = new PanelErrorEquipo();
					panelEquipos.setVisible(true);
					centerPanel.add(panelEquipos, BorderLayout.CENTER);
					centerPanel.revalidate();
					centerPanel.repaint();

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void cerrarSesion() {

		StringBuilder updatedContent = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_REGISTRADOS))) {
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
			try (FileWriter writer = new FileWriter(USUARIOS_REGISTRADOS)) {
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
