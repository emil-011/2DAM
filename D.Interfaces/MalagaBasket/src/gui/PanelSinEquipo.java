package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class PanelSinEquipo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNoTienesEquipo;

	/**
	 * Create the panel.
	 */
	public PanelSinEquipo() {
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		lblNoTienesEquipo = new JLabel("Actualmente no tienes equipo");
		lblNoTienesEquipo.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNoTienesEquipo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNoTienesEquipo.setVerticalTextPosition(SwingConstants.TOP);
		lblNoTienesEquipo.setIcon(new ImageIcon(PanelSinEquipo.class.getResource("/resources/ImgJugadorError.png")));
		lblNoTienesEquipo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNoTienesEquipo);

	}

}
