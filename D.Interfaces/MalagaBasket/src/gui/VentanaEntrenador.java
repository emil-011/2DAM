package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import utils.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class VentanaEntrenador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblHome;
	private JLabel lblLogOut;
	private JLabel lblEquipos;
	private JPanel centerPanel;


	public VentanaEntrenador(Usuario usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEntrenador.class.getResource("/resources/Logo.png")));
		setTitle("Entrenador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1073, 816);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel banner = new JPanel();
		banner.setBackground(new Color(9, 175, 255));
		contentPane.add(banner, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaEntrenador.class.getResource("/resources/Logo.png")));
		banner.add(lblNewLabel);

		JPanel leftMenu = new JPanel();
		leftMenu.setBackground(new Color(119, 210, 255));
		contentPane.add(leftMenu, BorderLayout.WEST);
		leftMenu.setLayout(new GridLayout(4, 2, 50, 0));

		lblHome = new JLabel("Home");
		lblHome.setForeground(new Color(255, 255, 255));
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHome.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblHome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHome.setIcon(new ImageIcon(VentanaEntrenador.class.getResource("/resources/Home.png")));
		leftMenu.add(lblHome);

		lblEquipos = new JLabel("Equipos");
		lblEquipos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				centerPanel.removeAll();
				centerPanel.setLayout(new BorderLayout());
				JPanel panelEquipos = new PanelCrearEquipo();
				panelEquipos.setVisible(true);
				centerPanel.add(panelEquipos, BorderLayout.CENTER);
				centerPanel.revalidate();
				centerPanel.repaint();
			}
		});
		lblEquipos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEquipos.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblEquipos.setIcon(new ImageIcon(VentanaEntrenador.class.getResource("/resources/Equipo.png")));
		lblEquipos.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEquipos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipos.setForeground(Color.WHITE);
		lblEquipos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		leftMenu.add(lblEquipos);

		JLabel lblMisJugadores = new JLabel("Mis Jugadores");
		lblMisJugadores.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblMisJugadores.setIcon(new ImageIcon(VentanaEntrenador.class.getResource("/resources/Jugadores.png")));
		lblMisJugadores.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMisJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblMisJugadores.setForeground(Color.WHITE);
		lblMisJugadores.setFont(new Font("Tahoma", Font.PLAIN, 18));
		leftMenu.add(lblMisJugadores);

		lblLogOut = new JLabel("    Cerrar Sesión    ");
		lblLogOut.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblLogOut.setIcon(new ImageIcon(VentanaEntrenador.class.getResource("/resources/Logout.png")));
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
		
	}

}
