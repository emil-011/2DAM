package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class PanelErrorEquipo extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelErrorEquipo() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		centerPanel.setBackground(Color.WHITE);
		panel.add(centerPanel);
		
		JLabel lblError = new JLabel("Actualmente no tienes equipo");
		lblError.setVerticalTextPosition(SwingConstants.TOP);
		lblError.setIcon(new ImageIcon(PanelErrorEquipo.class.getResource("/resources/ImgJugadorError.png")));
		lblError.setHorizontalTextPosition(SwingConstants.CENTER);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblError.setBounds(233, 98, 400, 378);
		centerPanel.add(lblError);

	}

}
