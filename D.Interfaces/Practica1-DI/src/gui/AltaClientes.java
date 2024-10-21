package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;
import utils.Cliente;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;

public class AltaClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBoxProvincias;
	private MainWindow mainWindow; // Referencia a MainWindow

	// Constructor
	public AltaClientes(MainWindow window) {
		this.mainWindow = window; // Recibe la referencia de MainWindow
		setTitle("Alta Clientes");
		setBounds(825, 450, 374, 263);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(202, 221, 203));
		getContentPane().add(panel);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nombre");

		JLabel lblApellidos = new JLabel("Apellidos");

		txtApellido = new JTextField();
		txtApellido.setColumns(10);

		dateChooser = new JDateChooser();

		JLabel lblNewLabel_1 = new JLabel("Edad");

		String[] listaProvincias = { "Málaga", "Jaén", "Córdoba", "Huelva", "Cádiz", "Sevilla", "Almería", "Granada" };
		comboBoxProvincias = new JComboBox<>(listaProvincias);
		comboBoxProvincias.setSelectedIndex(-1);

		JLabel lblNewLabel_2 = new JLabel("Provincia");

		JButton btnAnyadir = new JButton("Añadir");
		btnAnyadir.setBackground(new Color(109, 160, 112));
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres añadir al cliente?",
						"Confirmar acción", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					anyadirCliente();
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(64)
							.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblApellidos, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(64)
							.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(comboBoxProvincias, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(121)
					.addComponent(btnAnyadir, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel))
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblApellidos))
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(lblNewLabel_1))
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_2))
						.addComponent(comboBoxProvincias, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(btnAnyadir, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);

		setVisible(true);
	}

	protected void anyadirCliente() {
	    String nombre = txtNombre.getText();
	    String apellido = txtApellido.getText();
	    int edad = calcularEdad();
	    String provincia = comboBoxProvincias.getSelectedItem().toString();

	    boolean clienteExistente = false;

	    // Iteramos a través de los clientes en el modelo
	    for (int i = 0; i < mainWindow.getModelClientes().getSize(); i++) {
	        Cliente cliente = mainWindow.getModelClientes().getElementAt(i);
	        if (cliente.getNombre().equalsIgnoreCase(nombre) && cliente.getApellido().equalsIgnoreCase(apellido)) {
	            clienteExistente = true;       
	        }
	    }

	    if (clienteExistente) {
	        JOptionPane.showMessageDialog(null, "Ya existe un cliente igual");
	    } else {
	        Cliente cliente = new Cliente(nombre, apellido, edad, provincia);
	        // Añadir el cliente al modelo de la lista en MainWindow
	        mainWindow.getModelClientes().addElement(cliente);
	    }
	}


	protected int calcularEdad() {
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
}
