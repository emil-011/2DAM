package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelos.Serie;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Cursor;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import java.awt.Toolkit;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLayeredPane layeredPane;

	public static List<Serie> listaSeriesFavoritas = new ArrayList<>();
	
	/**
	 * Create the frame.
	 */
	public Home() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/resources/logo.png")));
		setTitle("Pelisfly");
		inicializarComponentes();
		verPanelHome();
	}

	private void inicializarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 893, 568);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Seriefly");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/resources/logo.png")));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 42));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		panel.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_3_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_3_1.setBackground(Color.BLACK);
		panel_2.add(panel_3_1);

		JLabel lblHome = new JLabel("Home    ");
		lblHome.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	verPanelHome();
		    }
		});

		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setForeground(Color.RED);
		lblHome.setFont(new Font("Verdana", Font.BOLD, 16));
		panel_3_1.add(lblHome);

		JLabel lblMisSeries = new JLabel("Mis Series      ");
		lblMisSeries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verPanelMisSeries();
			}
		});
		lblMisSeries.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMisSeries.setForeground(Color.RED);
		lblMisSeries.setFont(new Font("Verdana", Font.BOLD, 16));
		panel_3_1.add(lblMisSeries);

		JLabel lblHome_1_1 = new JLabel("Añadir");
		lblHome_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verPanelAnyadir();
			}
		});
		lblHome_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome_1_1.setForeground(Color.RED);
		lblHome_1_1.setFont(new Font("Verdana", Font.BOLD, 16));
		panel_3_1.add(lblHome_1_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_2.add(panel_3);

		JLabel lblNewLabel_3 = new JLabel("Cerrar Sesion");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Login lg = new Login();
				lg.setVisible(true);
			}
		});
		lblNewLabel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_3.setForeground(new Color(255, 0, 0));
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 16));
		panel_3.add(lblNewLabel_3);
				
		layeredPane = new JLayeredPane();
		layeredPane.setLayout(new BorderLayout());
		contentPane.add(layeredPane, BorderLayout.CENTER);


	}
	
	protected void verPanelHome() {
		layeredPane.removeAll();
		PanelHome pan1 = new PanelHome();
		layeredPane.add(pan1);
		layeredPane.revalidate();
		layeredPane.repaint();
	}
	
	protected void verPanelMisSeries() {
		layeredPane.removeAll();
		PanelMisSeries pan1 = new PanelMisSeries();
		layeredPane.add(pan1);
		layeredPane.revalidate();
		layeredPane.repaint();
	}
	
	protected void verPanelAnyadir() {
		layeredPane.removeAll();
		PanelAnyadir pan1 = new PanelAnyadir();
		layeredPane.add(pan1);
		layeredPane.revalidate();
		layeredPane.repaint();
	}

}
