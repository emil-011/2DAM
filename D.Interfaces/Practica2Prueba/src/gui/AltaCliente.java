package gui;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import utils.Clientes;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;

public class AltaCliente extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblAnyadir;
	private JTextField textApellidos;
	private JTextField textNombre;
	private JTextField textEmail;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBoxProvincias;
	private TablaClientes tablaClientes;

	public AltaCliente(TablaClientes tablaClientes) {
		this.tablaClientes = tablaClientes;
		setLayout(new BorderLayout(0, 0));

		JPanel panelInferior = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new BorderLayout(0, 0));

		lblAnyadir = new JLabel("Añadir");
		lblAnyadir.setBackground(new Color(50, 175, 50));
		lblAnyadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAnyadir.setBackground(new Color(50, 225, 50));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblAnyadir.setBackground(new Color(50, 175, 50));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				agregarCliente();
			}
		});
		lblAnyadir.setFont(new Font("Lexend", Font.BOLD, 18));
		lblAnyadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnyadir.setOpaque(true);
		panelInferior.add(lblAnyadir);

		JPanel panelIzquierda = new JPanel();
		panelInferior.add(panelIzquierda, BorderLayout.WEST);
		JLabel espacioIzquierda = new JLabel("                                   ");
		espacioIzquierda.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panelIzquierda.add(espacioIzquierda);

		JPanel panelDerecha = new JPanel();
		panelInferior.add(panelDerecha, BorderLayout.EAST);
		JLabel espacioIzquierda_1 = new JLabel("                                   ");
		espacioIzquierda_1.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panelDerecha.add(espacioIzquierda_1);

		JLabel lblNewLabel_2 = new JLabel(" ");
		panelInferior.add(lblNewLabel_2, BorderLayout.SOUTH);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(188, 177, 177));
		add(panelSuperior, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Alta Clientes");
		lblTitulo.setForeground(new Color(8, 8, 8));
		lblTitulo.setFont(new Font("Lexend", Font.BOLD, 24));
		panelSuperior.add(lblTitulo);

		JPanel panelPrincipal = new JPanel();
		add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Campos para Nombre
		JPanel panelNombre = new JPanel();
		panelPrincipal.add(panelNombre);
		JLabel lblNombre = new JLabel("Nombre:     ");
		lblNombre.setFont(new Font("Lexend", Font.PLAIN, 24));
		panelNombre.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setFont(new Font("Lexend", Font.PLAIN, 18));
		textNombre.setColumns(10);
		panelNombre.add(textNombre);

		// Campos para Apellidos
		JPanel panelApellidos = new JPanel();
		panelPrincipal.add(panelApellidos);
		JLabel lblApellido = new JLabel("Apellidos:   ");
		lblApellido.setFont(new Font("Lexend", Font.PLAIN, 24));
		panelApellidos.add(lblApellido);

		textApellidos = new JTextField();
		textApellidos.setFont(new Font("Lexend", Font.PLAIN, 18));
		textApellidos.setColumns(10);
		panelApellidos.add(textApellidos);

		// Campos para Email
		JPanel panelEmail = new JPanel();
		panelPrincipal.add(panelEmail);
		JLabel lblEmail = new JLabel("Email:         ");
		lblEmail.setFont(new Font("Lexend", Font.PLAIN, 24));
		panelEmail.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Lexend", Font.PLAIN, 18));
		textEmail.setColumns(10);
		panelEmail.add(textEmail);

		// Campos para Provincia
		JPanel panelProvincia = new JPanel();
		panelPrincipal.add(panelProvincia);
		JLabel lblProvincia = new JLabel("Provincia:     ");
		lblProvincia.setFont(new Font("Lexend", Font.PLAIN, 24));
		panelProvincia.add(lblProvincia);

		// Arreglo con las provincias de Andalucía
		String[] provinciasAndalucia = { "Almería", "Cádiz", "Córdoba", "Granada", "Huelva", "Jaén", "Málaga",
				"Sevilla" };
		comboBoxProvincias = new JComboBox<>(provinciasAndalucia);
		comboBoxProvincias.setPreferredSize(new Dimension(150, 30));
		comboBoxProvincias.setFont(new Font("Lexend", Font.PLAIN, 18));
		panelProvincia.add(comboBoxProvincias);

		// Campos para Fecha de Nacimiento
		JPanel panelDate = new JPanel();
		panelPrincipal.add(panelDate);
		JLabel lblBirthDate = new JLabel("Birth Date:    ");
		lblBirthDate.setFont(new Font("Lexend", Font.PLAIN, 24));
		panelDate.add(lblBirthDate);

		dateChooser = new JDateChooser();
		dateChooser.setPreferredSize(new Dimension(150, 30));
		panelDate.add(dateChooser);
	}

	// Metodo para agregar cliente
	private void agregarCliente() {
		String nombre = textNombre.getText();
		String apellido = textApellidos.getText();
		String email = textEmail.getText();
		int edad = calcularEdad();
		String provincia = comboBoxProvincias.getSelectedItem().toString();

		// Mensaje de error si no se rellena algun campo
		if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || edad <= 0) {
			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Clientes cliente = new Clientes(nombre, apellido, edad, provincia, email);
		tablaClientes.agregarCliente(cliente);

		clear();
	}

	// Metodo para calcular la edad segun el date chooser
	private int calcularEdad() {
		Date fechaNacimiento = dateChooser.getDate();
		if (fechaNacimiento == null) {
			return 0;
		}

		Calendar fechaActual = Calendar.getInstance();
		Calendar fechaNac = Calendar.getInstance();
		fechaNac.setTime(fechaNacimiento);

		int edad = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
		if (fechaActual.get(Calendar.DAY_OF_YEAR) < fechaNac.get(Calendar.DAY_OF_YEAR)) {
			edad--;
		}

		return edad;
	}

	// Metodo para dejar en blanco los campos
	private void clear() {
		textNombre.setText("");
		textApellidos.setText("");
		textEmail.setText("");
		dateChooser.setDate(null);
		comboBoxProvincias.setSelectedIndex(0);
	}
}
