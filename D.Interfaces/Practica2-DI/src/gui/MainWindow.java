package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import utils.Cliente;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblClientes;
	private JLabel lblProductos;
	private JLabel lblFacturas;
	private JLabel lblUsuario;
	private JPanel centerTopPanel;
	private JPanel centerPanel;
	private JLabel lblYoutubePic;
	private JPanel topPanel;
	private JTable tablaClientes;
	private JPanel centerMidPanel;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 350, 725, 616);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		topPanel = new JPanel();
		topPanel.setBackground(new Color(24, 24, 24));
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Load and scale the image
		ImageIcon originalIcon = new ImageIcon(MainWindow.class.getResource("/resources/youtube.png"));
		Image originalImage = originalIcon.getImage();
		int width = 150; // Adjustable width
		int height = 100; // Adjustable height
		Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		// Create JLabel to show the scaled image
		lblYoutubePic = new JLabel(scaledIcon);
		lblYoutubePic.setHorizontalAlignment(SwingConstants.LEFT);
		lblYoutubePic.setPreferredSize(new Dimension(width, height));

		// Add image to the top panel
		topPanel.add(lblYoutubePic);

		// Center panel with labels
		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		centerTopPanel = new JPanel();
		FlowLayout fl_centerTopPanel = (FlowLayout) centerTopPanel.getLayout();
		fl_centerTopPanel.setVgap(0);
		fl_centerTopPanel.setHgap(0);
		centerPanel.add(centerTopPanel, BorderLayout.NORTH);

		// Create labels with no preferred size and touching edges
		lblClientes = new JLabel("Clientes");
		lblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblClientes.setBackground(new Color(255, 50, 50));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblClientes.setBackground(new Color(231, 22, 22));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// Logic for showing clients can be added here if needed.
			}
		});
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientes.setFont(new Font("Lexend", Font.BOLD, 24));
		lblClientes.setBackground(new Color(231, 22, 22));
		lblClientes.setForeground(new Color(13, 13, 13));
		lblClientes.setOpaque(true);
		lblClientes.setPreferredSize(new Dimension(180, 40));
		centerTopPanel.add(lblClientes);

		lblProductos = new JLabel("Productos");
		lblProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblProductos.setBackground(new Color(255, 50, 50));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblProductos.setBackground(new Color(231, 22, 22));
			}
		});
		lblProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductos.setBackground(new Color(231, 22, 22));
		lblProductos.setForeground(new Color(13, 13, 13));
		lblProductos.setFont(new Font("Lexend", Font.BOLD, 24));
		lblProductos.setOpaque(true);
		lblProductos.setPreferredSize(new Dimension(180, 40));
		centerTopPanel.add(lblProductos);

		lblFacturas = new JLabel("Facturas");
		lblFacturas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblFacturas.setBackground(new Color(255, 50, 50));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblFacturas.setBackground(new Color(231, 22, 22));
			}
		});
		lblFacturas.setHorizontalAlignment(SwingConstants.CENTER);
		lblFacturas.setBackground(new Color(231, 22, 22));
		lblFacturas.setForeground(new Color(13, 13, 13));
		lblFacturas.setFont(new Font("Lexend", Font.BOLD, 24));
		lblFacturas.setPreferredSize(new Dimension(180, 40));
		lblFacturas.setOpaque(true);
		centerTopPanel.add(lblFacturas);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblUsuario.setBackground(new Color(50, 50, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblUsuario.setBackground(new Color(22, 22, 231));
			}
		});
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBackground(new Color(22, 22, 231));
		lblUsuario.setForeground(new Color(13, 13, 13));
		lblUsuario.setFont(new Font("Lexend", Font.BOLD, 24));
		lblUsuario.setOpaque(true);
		lblUsuario.setPreferredSize(new Dimension(180, 40));
		centerTopPanel.add(lblUsuario);

		centerMidPanel = new JPanel();
		centerPanel.add(centerMidPanel, BorderLayout.CENTER); // Change to CENTER for the table
		centerMidPanel.setLayout(new BorderLayout());

		// Create the table to show clients
		tablaClientes = new JTable();
		tablaClientes.setFont(new Font("Lexend", Font.PLAIN, 16));
		JScrollPane scrollPane = new JScrollPane(tablaClientes);
		centerMidPanel.add(scrollPane, BorderLayout.CENTER);

		// Load clients into the table
		cargarTabla();
	}

	private List<Cliente> cargarClientes() {
		List<Cliente> clientes = new ArrayList<>();

		clientes.add(new Cliente("Juan", "Pérez", 30, "Buenos Aires", "juan@example.com"));
		clientes.add(new Cliente("María", "Gómez", 25, "Córdoba", "maria@example.com"));
		clientes.add(new Cliente("Carlos", "Sánchez", 28, "Mendoza", "carlos@example.com"));
		clientes.add(new Cliente("Laura", "Martínez", 22, "Rosario", "laura@example.com"));
		clientes.add(new Cliente("Andrés", "López", 35, "Salta", "andres@example.com"));
		clientes.add(new Cliente("Lucía", "Fernández", 27, "Tucumán", "lucia@example.com"));
		clientes.add(new Cliente("Javier", "Ramírez", 32, "La Plata", "javier@example.com"));
		clientes.add(new Cliente("Sofía", "Cruz", 26, "San Juan", "sofia@example.com"));
		clientes.add(new Cliente("Diego", "Torres", 29, "Neuquén", "diego@example.com"));
		clientes.add(new Cliente("Valentina", "Morales", 31, "Chaco", "valentina@example.com"));
		clientes.add(new Cliente("Ricardo", "Hernández", 34, "Formosa", "ricardo@example.com"));
		clientes.add(new Cliente("Gabriela", "Vásquez", 24, "Santa Fe", "gabriela@example.com"));
		clientes.add(new Cliente("Fernando", "Salinas", 38, "Jujuy", "fernando@example.com"));
		clientes.add(new Cliente("Camila", "Jiménez", 23, "Córdoba", "camila@example.com"));

		return clientes;
	}

	private void cargarTabla() {
		List<Cliente> clientes = cargarClientes();

		// Define column names
		String[] columnNames = { "Nombre", "Apellido", "Edad", "Email", "Ciudad" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		// Add client data to the model
		for (Cliente cliente : clientes) {
			model.addRow(new Object[] { cliente.getNombre(), cliente.getApellido(), cliente.getEdad(),
					cliente.getProvincia(), cliente.getEmail() });
		}

		// Set the model to the table
		tablaClientes.setModel(model);

		// Instanciamos el TableRowSorter y lo añadimos al JTable
		TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(model);
		tablaClientes.setRowSorter(elQueOrdena);
	}
}
