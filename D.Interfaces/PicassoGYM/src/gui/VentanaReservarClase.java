package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class VentanaReservarClase extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblClase;
	private JLabel lblEnviar;
	private static final String CLASES = "clases.csv";
	private JComboBox<String> comboBoxClase;
	private JComboBox<String> comboBoxTurno;
	
	public static void main(String[] args) {
		VentanaReservarClase reserva = new VentanaReservarClase();
		reserva.setVisible(true);
	}

	public VentanaReservarClase() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(650, 250, 452, 370);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblClase = new JLabel("Clase");
		lblClase.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblClase.setBounds(30, 90, 96, 40);
		contentPane.add(lblClase);

		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblTurno.setBounds(30, 180, 75, 40);
		contentPane.add(lblTurno);

		lblEnviar = new JLabel("Reservar");
		lblEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEnviar.setBackground(new Color(30, 100, 200));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblEnviar.setBackground(new Color(41, 191, 235));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		lblEnviar.setForeground(new Color(255, 255, 255));
		lblEnviar.setOpaque(true);
		lblEnviar.setBackground(new Color(41, 191, 235));
		lblEnviar.setFont(new Font("Verdana", Font.BOLD, 20));
		lblEnviar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnviar.setBounds(130, 251, 200, 50);
		contentPane.add(lblEnviar);

		comboBoxClase = new JComboBox<String>();
		comboBoxClase.setBounds(130, 90, 250, 40);
		contentPane.add(comboBoxClase);

		JLabel lblBanner = new JLabel("Reservar Clase");
		lblBanner.setBounds(0, 0, 436, 50);
		contentPane.add(lblBanner);
		lblBanner.setForeground(new Color(255, 255, 255));
		lblBanner.setOpaque(true);
		lblBanner.setBackground(new Color(41, 191, 235));
		lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanner.setFont(new Font("Verdana", Font.BOLD, 24));

		comboBoxTurno = new JComboBox<String>();
		comboBoxTurno.setBounds(130, 180, 250, 40);
		contentPane.add(comboBoxTurno);

		cargarClases();
		comboBoxTurno.setSelectedIndex(-1);
		comboBoxClase.setSelectedIndex(-1);
	}

	protected void cargarClases() {
		try (BufferedReader reader = new BufferedReader(new FileReader(CLASES))) {
			String linea = "";
			while((linea = reader.readLine()) != null) {
				String[] datosClases = linea.trim().split(";");
				comboBoxClase.addItem(datosClases[0]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
