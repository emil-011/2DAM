package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblReservarClase;
	private JLabel lblCerrarSesion;

	public VentanaCliente() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaCliente.class.getResource("/resources/logoApp.png")));
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

		JLabel lblNewLabel = new JLabel("Emilio Fernández Gallardo");
		lblNewLabel.setForeground(new Color(22, 100, 143));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		footer.add(lblNewLabel);

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
}
