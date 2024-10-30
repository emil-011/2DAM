package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class VentanaReservarClase extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblClase;
	private JLabel lblEnviar;
	private static final String CLASES = "clases.csv";
	private JComboBox<String> comboBoxClase;
	private JComboBox<String> comboBoxTurno;
	private Usuario usuario;

	public static void main(String[] args) {
		Usuario usuario = new Usuario("Pepin", "Fernandez", new Date(), "cliente", "lolito@gmail.com",
				"1234", true);
		VentanaReservarClase reserva = new VentanaReservarClase(usuario);
		reserva.setVisible(true);
	}

	public VentanaReservarClase(Usuario usuario) {
		this.usuario = usuario;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 400, 452, 370);
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
				reservarClase();
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
		comboBoxClase.setSelectedIndex(-1);
		comboBoxTurno.setSelectedIndex(-1);

		comboBoxClase.addActionListener(e -> cargarTurnos((String) comboBoxClase.getSelectedItem()));
	}

	protected void reservarClase() {
	        // Get selected class and turn
	        String clase = (String) comboBoxClase.getSelectedItem();
	        String turno = (String) comboBoxTurno.getSelectedItem();
	        
	        if (clase == null || turno == null) {
	            JOptionPane.showMessageDialog(this, "Por favor, seleccione una clase y un turno.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // Build the reservation entry
	        String reservationEntry = usuario.getNombre() + ";" + usuario.getApellidos() + ";" + clase + ";" + turno;

	        // Write the reservation to the CSV file
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservas.csv", true))) {
	            writer.write(reservationEntry);
	            writer.newLine();
	            JOptionPane.showMessageDialog(this, "Clase reservada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(this, "Error al guardar la reserva.", "Error", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
	        }
	    }
	    

	protected void cargarClases() {
		Set<String> clasesSet = new HashSet<>(); // Set para evitar duplicados
		try (BufferedReader reader = new BufferedReader(new FileReader(CLASES))) {
			String linea = "";
			while ((linea = reader.readLine()) != null) {
				String[] datosClases = linea.trim().split(";");
				String clase = datosClases[0];
				if (clasesSet.add(clase)) {
					comboBoxClase.addItem(clase);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void cargarTurnos(String claseSeleccionada) {
		comboBoxTurno.removeAllItems();
		try (BufferedReader reader = new BufferedReader(new FileReader(CLASES))) {
			String linea = "";
			while ((linea = reader.readLine()) != null) {
				String[] datosClases = linea.trim().split(";");
				String clase = datosClases[0];
				String turno = datosClases[2];
				if (clase.equals(claseSeleccionada)) {
					comboBoxTurno.addItem(turno);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}