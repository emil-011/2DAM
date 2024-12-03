package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.MainApp;
import utils.Equipo;
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
import java.util.ArrayList;
import java.util.List;
import java.awt.Toolkit;
import javax.swing.JLayeredPane;

public class PantallaJugador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblHome;
	private JLabel lblLogOut;
	private JLabel lblEquipos;
	private Usuario usuarioLogeado;
	private JLayeredPane layeredPane;

	public PantallaJugador(Usuario usuarioLogeado) {
		inicializarComponentes();
		mostrarPanel(usuarioLogeado);
		this.usuarioLogeado = usuarioLogeado;
	}

	private void inicializarComponentes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaJugador.class.getResource("/resources/Logo.png")));
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
		lblNewLabel.setIcon(new ImageIcon(PantallaJugador.class.getResource("/resources/Logo.png")));
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
		lblHome.setIcon(new ImageIcon(PantallaJugador.class.getResource("/resources/Home.png")));
		leftMenu.add(lblHome);

		lblEquipos = new JLabel("Equipos");
		lblEquipos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEquipos.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblEquipos.setIcon(new ImageIcon(PantallaJugador.class.getResource("/resources/Equipo.png")));
		lblEquipos.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEquipos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipos.setForeground(Color.WHITE);
		lblEquipos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		leftMenu.add(lblEquipos);

		lblLogOut = new JLabel("    Cerrar Sesión    ");
		lblLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		lblLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogOut.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblLogOut.setIcon(new ImageIcon(PantallaJugador.class.getResource("/resources/Logout.png")));
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

		layeredPane = new JLayeredPane();
		mainPanel.add(layeredPane, BorderLayout.NORTH);
	}

	protected void mostrarPanel(Usuario usuarioLogeado) {
		List<Equipo> listaEquiposJugador = ObtieneEquipoJugador(usuarioLogeado);
		if (listaEquiposJugador != null && listaEquiposJugador.size() > 0) {
			layeredPane.removeAll();
			PanelMiEquipo miEquipo = new PanelMiEquipo(listaEquiposJugador);
			layeredPane.add(miEquipo);
			layeredPane.repaint();
			layeredPane.revalidate();

		} else {
			layeredPane.removeAll();
			PanelSinEquipo sinEquipo = new PanelSinEquipo();
			layeredPane.add(sinEquipo);
			layeredPane.repaint();
			layeredPane.revalidate();
		}
	}

	public List<Equipo> ObtieneEquipoJugador(Usuario usuario) {
		List<Equipo> listaEquiposUsuario = new ArrayList<>();
		for (Equipo eq : MainApp.listaEquipos) {
			if (usuario.isEntrenador() && eq.getEntrenador().equals(usuario)) {
				listaEquiposUsuario.add(eq);
			} else {
				listaEquiposUsuario.add(eq);
				return listaEquiposUsuario;
			}
		}
		return null;
	}
}
