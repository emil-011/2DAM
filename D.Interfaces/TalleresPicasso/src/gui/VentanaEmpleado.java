package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Usuario;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel banner;
	private JPanel bottomPanel;
	private JPanel centerPanel;
	private JLabel lblTalleresPicasso;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPanel panel;
	private JPanel optionsPanel;
	private JLabel lblBienvenida;
	private JLabel lblCitaPrevia;
	private JLabel lblReparaciones;
	private JLabel lblNewLabel_3;

	/**
	 * Create the frame.
	 */
	public VentanaEmpleado(Usuario usuario) {
		inicializarComponentes(usuario);

	}

	private void inicializarComponentes(Usuario usuario) {
		setTitle("Talleres Picasso");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEmpleado.class.getResource("/resources/cocheAzul.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 300, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		banner = new JPanel();
		banner.setBackground(new Color(128, 128, 255));
		contentPane.add(banner, BorderLayout.NORTH);
		
		lblNewLabel_1 = new JLabel("               ");
		banner.add(lblNewLabel_1);

		lblTalleresPicasso = new JLabel("Talleres Picasso");
		lblTalleresPicasso.setForeground(new Color(0, 64, 128));
		lblTalleresPicasso.setFont(new Font("Tahoma", Font.PLAIN, 56));
		lblTalleresPicasso.setIcon(new ImageIcon(VentanaEmpleado.class.getResource("/resources/cocheAzul.png")));

		banner.add(lblTalleresPicasso);
		
		lblNewLabel_2 = new JLabel("                ");
		banner.add(lblNewLabel_2);

		bottomPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) bottomPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		lblNewLabel = new JLabel("     ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 54));
		bottomPanel.add(lblNewLabel);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaLogin lg = new VentanaLogin();
				lg.setVisible(true);
			}
		});
		lblNewLabel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_3.setIcon(new ImageIcon(VentanaEmpleado.class.getResource("/resources/logout.png")));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		bottomPanel.add(lblNewLabel_3);

		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		centerPanel.add(panel, BorderLayout.NORTH);
		
		lblBienvenida = new JLabel("Bienvenido/a " + usuario.getNombre() + " " + usuario.getApellidos());
		lblBienvenida.setHorizontalAlignment(SwingConstants.LEFT);
		lblBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblBienvenida);
		
		optionsPanel = new JPanel();
		centerPanel.add(optionsPanel, BorderLayout.CENTER);
		optionsPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		lblCitaPrevia = new JLabel("Actualizar estado de reparacion");
		lblCitaPrevia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pedirCitaPreviaVentana(usuario);
			}
		});
		lblCitaPrevia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCitaPrevia.setIcon(new ImageIcon(VentanaEmpleado.class.getResource("/resources/reparaciones.png")));
		lblCitaPrevia.setHorizontalAlignment(SwingConstants.CENTER);
		lblCitaPrevia.setVerticalTextPosition(SwingConstants.BOTTOM); // Texto debajo del ícono
		lblCitaPrevia.setHorizontalTextPosition(SwingConstants.CENTER); // Texto centrado
		optionsPanel.add(lblCitaPrevia);

		lblReparaciones = new JLabel("Ver mis trabajos");
		lblReparaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verReparaciones(usuario);
			}
		});
		lblReparaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblReparaciones.setIcon(new ImageIcon(VentanaEmpleado.class.getResource("/resources/listadoReparaciones.png")));
		lblReparaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblReparaciones.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblReparaciones.setHorizontalTextPosition(SwingConstants.CENTER);
		optionsPanel.add(lblReparaciones);

	}

	protected void pedirCitaPreviaVentana(Usuario usuario) {
		ActualizarReparacion var = new ActualizarReparacion(this, usuario);
		var.setVisible(true);
		
	}
	
	protected void verReparaciones(Usuario usuario) {
		VentanaReparaciones reparaciones = new VentanaReparaciones(null, usuario);
		reparaciones.setVisible(true);
		
	}

}
