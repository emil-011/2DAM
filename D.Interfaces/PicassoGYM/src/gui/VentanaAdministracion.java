package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class VentanaAdministracion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdministracion frame = new VentanaAdministracion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAdministracion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 622);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(181, 243, 249));
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel lblBanner = new JLabel("GYM Picasso");
		lblBanner.setIcon(new ImageIcon(VentanaAdministracion.class.getResource("/resources/logoApp.png")));
		lblBanner.setForeground(new Color(22, 101, 143));
		lblBanner.setFont(new Font("Verdana", Font.BOLD, 32));
		topPanel.add(lblBanner);
		
		JPanel footer = new JPanel();
		footer.setBackground(new Color(181, 243, 249));
		contentPane.add(footer, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Emilio Fernández Gallardo 28/10/2024");
		lblNewLabel.setForeground(new Color(22, 100, 143));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		footer.add(lblNewLabel);
	}

}
