package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import utils.Usuario;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelHomeEntrenador extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblEntrenadorCambiar;
	private JLabel lblHEntrenoCambiar;
	private JLabel lblGeneroCambia;
	private JLabel lblAnyoCambia;
	private JLabel lblNombreCambia;
	private JLabel lblEntrenador;
	private JLabel lblHoraEntrenamiento;
	private JLabel lblGenero;
	private JLabel lblAnyo;
	private JLabel lblNombre;
	private JPanel centerPanelEquipo;
	private JLabel lblNext;
	private JLabel lblPrevious;

	public PanelHomeEntrenador(Usuario usuario) {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		centerPanelEquipo = new JPanel();
		centerPanelEquipo.setLayout(null);
		centerPanelEquipo.setOpaque(false);
		panel.add(centerPanelEquipo, BorderLayout.CENTER);

		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(22, 84, 450, 44);
		centerPanelEquipo.add(lblNombre);

		lblAnyo = new JLabel("Año");
		lblAnyo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnyo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAnyo.setBounds(22, 167, 450, 44);
		centerPanelEquipo.add(lblAnyo);

		lblGenero = new JLabel("Género");
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGenero.setBounds(22, 261, 450, 44);
		centerPanelEquipo.add(lblGenero);

		lblHoraEntrenamiento = new JLabel("Hora de Entrenamiento");
		lblHoraEntrenamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraEntrenamiento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHoraEntrenamiento.setBounds(22, 355, 450, 44);
		centerPanelEquipo.add(lblHoraEntrenamiento);

		lblEntrenador = new JLabel("Entrenador");
		lblEntrenador.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrenador.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEntrenador.setBounds(22, 438, 450, 44);
		centerPanelEquipo.add(lblEntrenador);

		lblNombreCambia = new JLabel("");
		lblNombreCambia.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreCambia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombreCambia.setBounds(445, 84, 345, 44);
		centerPanelEquipo.add(lblNombreCambia);

		lblAnyoCambia = new JLabel("");
		lblAnyoCambia.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnyoCambia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAnyoCambia.setBounds(445, 167, 345, 44);
		centerPanelEquipo.add(lblAnyoCambia);

		lblGeneroCambia = new JLabel("");
		lblGeneroCambia.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneroCambia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGeneroCambia.setBounds(445, 261, 345, 44);
		centerPanelEquipo.add(lblGeneroCambia);

		lblHEntrenoCambiar = new JLabel("");
		lblHEntrenoCambiar.setHorizontalAlignment(SwingConstants.CENTER);
		lblHEntrenoCambiar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHEntrenoCambiar.setBounds(445, 355, 345, 44);
		centerPanelEquipo.add(lblHEntrenoCambiar);

		lblEntrenadorCambiar = new JLabel("");
		lblEntrenadorCambiar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrenadorCambiar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEntrenadorCambiar.setBounds(445, 438, 345, 44);
		centerPanelEquipo.add(lblEntrenadorCambiar);
		
		lblNext = new JLabel("");
		lblNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNext.setIcon(new ImageIcon(PanelHomeEntrenador.class.getResource("/resources/Derecha.png")));
		lblNext.setBounds(470, 538, 48, 25);
		centerPanelEquipo.add(lblNext);
		
		lblPrevious = new JLabel("");
		lblPrevious.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPrevious.setIcon(new ImageIcon(PanelHomeEntrenador.class.getResource("/resources/Izquierda.png")));
		lblPrevious.setBounds(337, 538, 65, 25);
		centerPanelEquipo.add(lblPrevious);
		

	}
}
