package gui;

import utils.Clientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TablaClientes extends JPanel {

	private JTable table;
	private DefaultTableModel tableModel;
	private static final String CSV_FILE = "lista_clientes.csv";

	public TablaClientes() {
		setLayout(new BorderLayout());

		String[] columnNames = { "Nombre", "Apellido", "Edad", "Provincia", "Email" };
		tableModel = new DefaultTableModel(columnNames, 0); // Inicializar el modelo sin filas

		// Creamos la tabla con el modelo
		table = new JTable(tableModel);
		table.setFont(new Font("Lexend", Font.PLAIN, 16));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}

	// Método para cargar los datos del CSV
	public void cargarClientesCSV() {
		// Limpiamos la tabla antes de cargar nuevos datos
		tableModel.setRowCount(0);

		try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				tableModel.addRow(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public JTable getTable() {
		return table;
	}

	// Método para escribir los clientes en el archivo
	public void agregarCliente(Clientes cliente) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE, true))) {
			String line = cliente.getNombre() + "," + cliente.getApellido() + "," + cliente.getEdad() + ","
					+ cliente.getProvincia() + "," + cliente.getEmail();

			writer.write(line);
			writer.newLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Método para eliminar el cliente seleccionado en la tabla
	public void eliminarClienteSeleccionado() {
		// Obtenemos la fila seleccionada
		int selectedRow = table.getSelectedRow();
		// Verificamos que hay una fila seleccionada
		if (selectedRow != -1) {
			// Obtenemos el nombre del cliente de la primera columna
			String nombreCliente = (String) table.getValueAt(selectedRow, 0);

			// Eliminamos el cliente del archivo CSV
			eliminarClienteDelCSV(nombreCliente);

			// Recargamos la tabla después de eliminar
			cargarClientesCSV();
		} else {
			JOptionPane.showMessageDialog(this, "Por favor selecciona un cliente para eliminar.");
		}
	}

	// Método para eliminar el cliente del archivo CSV
	private void eliminarClienteDelCSV(String nombreCliente) {
		// Lista temporal para almacenar los clientes que no serán eliminados
		List<String> clientesRestantes = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				String nombre = data[0].trim();

				// Si el nombre no coincide con el cliente a eliminar, guardamos la línea
				if (!nombre.equalsIgnoreCase(nombreCliente)) {
					clientesRestantes.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Sobreescribimos el archivo CSV con los clientes restantes
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
			for (String cliente : clientesRestantes) {
				writer.write(cliente);
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
