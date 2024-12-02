package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.MainApp;
import models.Equipo;
import models.Usuario;
import java.awt.Toolkit;

public class HomeEntrenador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Usuario usuarioLogado;
	private JLayeredPane panelCentral;

	/**
	 * Create the frame.
	 * 
	 * @param us1
	 */
	public HomeEntrenador(Usuario us1) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeEntrenador.class.getResource("/resources/Logo.png")));
		setTitle("Home Entrenador");
		this.usuarioLogado = us1;
		inicializarComponentes();
		cargarPanelInicial(usuarioLogado);
	}

	private void inicializarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelSup = new JPanel();
		panelSup.setBackground(new Color(30, 144, 255));
		contentPane.add(panelSup, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(HomeEntrenador.class.getResource("/resources/Logo.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelSup.add(lblNewLabel);

		JPanel panelIzq = new JPanel();
		panelIzq.setBackground(new Color(30, 144, 255));
		contentPane.add(panelIzq, BorderLayout.WEST);
		panelIzq.setLayout(new GridLayout(4, 1, 0, 25));

		JLabel lblHome = new JLabel("Home");
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarPanelInicial(usuarioLogado);
			}
		});
		lblHome.setForeground(new Color(255, 255, 255));
		lblHome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHome.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setIcon(new ImageIcon(HomeEntrenador.class.getResource("/resources/Home.png")));
		panelIzq.add(lblHome);

		JLabel lblEquipo = new JLabel("Equipo");
		lblEquipo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEquipo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verPanelEquipos();
			}
		});
		lblEquipo.setForeground(new Color(255, 255, 255));
		lblEquipo.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblEquipo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEquipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo.setIcon(new ImageIcon(HomeEntrenador.class.getResource("/resources/Equipo.png")));
		panelIzq.add(lblEquipo);

		JLabel lblJugadores = new JLabel("Mis jugadores");
		lblJugadores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblJugadores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verPanelJugadores();
			}
		});
		lblJugadores.setForeground(new Color(255, 255, 255));
		lblJugadores.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugadores.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblJugadores.setIcon(new ImageIcon(HomeEntrenador.class.getResource("/resources/Jugadores.png")));
		panelIzq.add(lblJugadores);

		JLabel lblCerrar = new JLabel("Cerrar Sesión");
		lblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Login ven = new Login();
				ven.setVisible(true);
			}
		});
		lblCerrar.setForeground(new Color(255, 255, 255));
		lblCerrar.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblCerrar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrar.setIcon(new ImageIcon(HomeEntrenador.class.getResource("/resources/Logout.png")));
		panelIzq.add(lblCerrar);

		JPanel panelInf = new JPanel();
		panelInf.setBackground(new Color(30, 144, 255));
		contentPane.add(panelInf, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(211, 211, 211));
		contentPane.add(panel_3, BorderLayout.EAST);

		panelCentral = new JLayeredPane();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
	}

	/**
	 * Carga un panel u otro según si tiene equipo o no
	 * 
	 * @param usuario cargarPanel para ese usuario
	 */
	private void cargarPanelInicial(Usuario usuarioLogado) {
		List<Equipo> equipoJugador = ObtieneEquipoJugador(usuarioLogado);
		if (equipoJugador == null || equipoJugador.isEmpty()) {
			PanelSinEquipo pan1 = new PanelSinEquipo();
			panelCentral.add(pan1);
			panelCentral.revalidate();
			panelCentral.repaint();
		} else {
			panelCentral.removeAll();
			PanelMiEquipo pan1 = new PanelMiEquipo(equipoJugador);
			panelCentral.add(pan1);
			panelCentral.revalidate();
			panelCentral.repaint();
		}

	}
	
	/**
	 * Metodo que obtiene los equipos de un jugador
	 * @param user Usuario del que se van a obtener los equipos
	 * @return Lista de equipos del usuario
	 */
	private List<Equipo> ObtieneEquipoJugador(Usuario user) {
		List<Equipo> lstEquipoUsuario = new ArrayList<Equipo>();
		for (Equipo eq : MainApp.lstEquipos) {
			if (user.getEsEntrenador() && eq.getEntrenador().equals(user)) {
				lstEquipoUsuario.add(eq);
			} else {
				if (eq.getLstJugadores().contains(user)) {
					lstEquipoUsuario.add(eq);
				}
			}
			;
		}
		return lstEquipoUsuario;
	}


	protected void verPanelEquipos() {
		panelCentral.removeAll();
		PanelAddEquipos pan1 = new PanelAddEquipos();
		panelCentral.add(pan1);
		panelCentral.revalidate();
		panelCentral.repaint();

	}

	protected void verPanelJugadores() {
		panelCentral.removeAll();
		PanelJugadores pan1 = new PanelJugadores();
		panelCentral.add(pan1);
		panelCentral.revalidate();
		panelCentral.repaint();
	}

}
