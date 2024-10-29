package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaListarClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final String CLIENTES_REGISTRADOS = "usuarios_registrados.csv";
	private JPanel banner;
	private JLabel lblListaDeClientes;
	private JScrollPane scrollPane;
	private JTable tablaClientes;
	private JPanel footer;
	private JLabel lblApellido;
	private JTextField textApellido;
	private JLabel lblFiltrar;

	public static void main(String[] args) {
		VentanaListarClientes ventana = new VentanaListarClientes();
		ventana.setVisible(true); // Muestra la ventana
	}

	public VentanaListarClientes() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(650, 250, 700, 502);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		banner = new JPanel();
		contentPane.add(banner, BorderLayout.NORTH);
		banner.setLayout(new GridLayout(1, 1, 0, 0));

		lblListaDeClientes = new JLabel("Lista de Clientes");
		lblListaDeClientes.setOpaque(true);
		lblListaDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeClientes.setForeground(Color.WHITE);
		lblListaDeClientes.setFont(new Font("Verdana", Font.BOLD, 24));
		lblListaDeClientes.setBackground(new Color(41, 191, 235));
		banner.add(lblListaDeClientes);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tablaClientes = new JTable();
		tablaClientes.setFont(new Font("Verdana", Font.PLAIN, 12));
		cargarClientesCSV();

		scrollPane.setViewportView(tablaClientes);

		footer = new JPanel();
		contentPane.add(footer, BorderLayout.SOUTH);

		lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Verdana", Font.BOLD, 14));

		textApellido = new JTextField();
		textApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					filtrar(); 
				}
			}
		});
		textApellido.setFont(new Font("Verdana", Font.PLAIN, 12));
		textApellido.setPreferredSize(new Dimension(100, 30));
		textApellido.setColumns(10);

		lblFiltrar = new JLabel("Filtrar");
		lblFiltrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblFiltrar.setBackground(new Color(41, 100, 200));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblFiltrar.setBackground(new Color(41, 191, 235));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				filtrar();
			}

		});
		lblFiltrar.setOpaque(true);
		lblFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltrar.setForeground(Color.WHITE);
		lblFiltrar.setFont(new Font("Verdana", Font.BOLD, 20));
		lblFiltrar.setBackground(new Color(41, 191, 235));
		GroupLayout gl_footer = new GroupLayout(footer);
		gl_footer.setHorizontalGroup(gl_footer.createParallelGroup(Alignment.LEADING).addGroup(gl_footer
				.createSequentialGroup().addGap(156).addComponent(lblApellido)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(textApellido, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE).addGap(26)
				.addComponent(lblFiltrar, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE).addGap(93)));
		gl_footer.setVerticalGroup(gl_footer.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_footer.createSequentialGroup().addContainerGap(30, Short.MAX_VALUE)
						.addGroup(gl_footer.createParallelGroup(Alignment.BASELINE).addComponent(lblApellido)
								.addComponent(textApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFiltrar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addGap(26)));
		footer.setLayout(gl_footer);
	}

	protected void filtrar() {
		String apellidoBuscado = textApellido.getText().trim().toLowerCase();

		if (apellidoBuscado.isBlank() && apellidoBuscado.isEmpty()) {
			cargarClientesCSV();
		} else {

			DefaultTableModel modelo = (DefaultTableModel) tablaClientes.getModel();

			DefaultTableModel modeloFiltrado = new DefaultTableModel();
			modeloFiltrado.addColumn("Nombre");
			modeloFiltrado.addColumn("Apellidos");
			modeloFiltrado.addColumn("Fecha de Nacimiento");
			modeloFiltrado.addColumn("Email");

			for (int i = 0; i < modelo.getRowCount(); i++) {
				String apellido = modelo.getValueAt(i, 1).toString().toLowerCase();
				if (apellido.startsWith(apellidoBuscado)) {
					// Si el apellido contiene el texto buscado, lo agregamos al modelo
					Object[] fila = new Object[modelo.getColumnCount()];
					for (int j = 0; j < modelo.getColumnCount(); j++) {
						fila[j] = modelo.getValueAt(i, j);
					}
					modeloFiltrado.addRow(fila);
				}
			}
			tablaClientes.setModel(modeloFiltrado);
		}
	}

	protected void cargarClientesCSV() {
		DefaultTableModel tablaModel = new DefaultTableModel();
		tablaModel.addColumn("Nombre");
		tablaModel.addColumn("Apellidos");
		tablaModel.addColumn("Fecha de Nacimiento");
		tablaModel.addColumn("Email");

		try (BufferedReader reader = new BufferedReader(new FileReader(CLIENTES_REGISTRADOS))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] datos = line.split(";");
				String nombre = datos[0];
				String apellidos = datos[1];
				String fechaNacimiento = datos[2];
				String email = datos[4];

				tablaModel.addRow(new Object[] { nombre, apellidos, fechaNacimiento, email });
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		tablaClientes.setModel(tablaModel);
		tablaClientes.getColumnModel().getColumn(3).setPreferredWidth(150);
		tablaClientes.setAutoCreateRowSorter(true);
	}
}
