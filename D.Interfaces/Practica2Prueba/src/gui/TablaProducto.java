package gui;

import utils.Producto;

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

public class TablaProducto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private static final String CSV_FILE_PRODUCTOS = "lista_productos.csv";

	public TablaProducto() {
		setLayout(new BorderLayout());

		// Creamos el modelo de la tabla con columnas
		String[] columnNames = { "Nombre", "Precio/u", "Perecedero" };
		tableModel = new DefaultTableModel(columnNames, 0);

		// Creamos la tabla con el modelo
		table = new JTable(tableModel);
		table.setFont(new Font("Lexend", Font.PLAIN, 16));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}

	// Método para cargar los datos del CSV
	public void cargarProductosCSV() {
		// Limpiamos la tabla antes de cargar nuevos datos
		tableModel.setRowCount(0);

		try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PRODUCTOS))) {
			String line;
			while ((line = br.readLine()) != null) {
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

	// Método para agregar producto al csv
	public void agregarProducto(Producto producto) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PRODUCTOS, true))) {
			String line = producto.getNombre() + "," + producto.getPrecioUnitario() + "," + producto.isPerecedero();

			writer.write(line);
			writer.newLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Método para eliminar el producto seleccionado en la tabla
	public void eliminarProductoSeleccionado() {
		int selectedRow = table.getSelectedRow();

		if (selectedRow != -1) { 
			String nombreProducto = (String) table.getValueAt(selectedRow, 0);

			// Eliminamos el producto del archivo CSV
			eliminarProductoCSV(nombreProducto);

			// Recargamos la tabla después de eliminar
			cargarProductosCSV();
		} else {
			JOptionPane.showMessageDialog(this, "Por favor selecciona un producto para eliminar.");
		}
	}

	// Método para eliminar el producto del archivo CSV
	private void eliminarProductoCSV(String nombreProducto) {
		List<String> productosRestantes = new ArrayList<>(); // Lista temporal para almacenar los productos que no serán
																// eliminados

		try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PRODUCTOS))) {
			String line;

			// Leemos el archivo CSV línea por línea
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				String nombre = data[0].trim(); // Suponiendo que el nombre está en la primera columna

				// Si el nombre no coincide con el producto a eliminar, guardamos la línea
				if (!nombre.equalsIgnoreCase(nombreProducto)) {
					productosRestantes.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Sobrescribimos el archivo CSV con los productos restantes
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PRODUCTOS))) {
			for (String producto : productosRestantes) {
				writer.write(producto);
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
