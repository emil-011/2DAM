package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import com.toedter.calendar.JDateChooser;

import utils.Producto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class AltaProducto extends JPanel {
	private JLabel lblAnyadir;
	private JTextField textPrecio;
	private JTextField textNombre;
	private TablaProducto tablaProductos;
	private JCheckBox chckbxPercedero;
	private JLabel lblEsPerecedero;
	private JLabel lblNombre_1;
	private JLabel lblPrecio;

	public AltaProducto(TablaProducto tablaProductos) {
		this.tablaProductos = tablaProductos;
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
				agregarproducto();
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

		JLabel lblTitulo = new JLabel("Alta productos");
		lblTitulo.setForeground(new Color(8, 8, 8));
		lblTitulo.setFont(new Font("Lexend", Font.BOLD, 24));
		panelSuperior.add(lblTitulo);

		JPanel panelPrincipal = new JPanel();
		add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panelNombre = new JPanel();
		panelPrincipal.add(panelNombre);
		lblNombre_1 = new JLabel("Nombre:     ");
		lblNombre_1.setFont(new Font("Lexend", Font.PLAIN, 24));
		panelNombre.add(lblNombre_1);

		textNombre = new JTextField();
		textNombre.setFont(new Font("Lexend", Font.PLAIN, 18));
		textNombre.setColumns(10);
		panelNombre.add(textNombre);

		JPanel panelApellidos = new JPanel();
		panelPrincipal.add(panelApellidos);
		lblPrecio = new JLabel("Precio:    ");
		lblPrecio.setFont(new Font("Lexend", Font.PLAIN, 24));
		panelApellidos.add(lblPrecio);

		textPrecio = new JTextField();
		textPrecio.setFont(new Font("Lexend", Font.PLAIN, 18));
		textPrecio.setColumns(10);
		panelApellidos.add(textPrecio);

		JPanel panelPerecedero = new JPanel();
		panelPrincipal.add(panelPerecedero);

		lblEsPerecedero = new JLabel("Es perecedero:    ");
		lblEsPerecedero.setFont(new Font("Lexend", Font.PLAIN, 24));
		panelPerecedero.add(lblEsPerecedero);

		chckbxPercedero = new JCheckBox("");
		chckbxPercedero.setPreferredSize(new Dimension(50, 50));
		chckbxPercedero.setFont(new Font("Tahoma", Font.PLAIN, 54));
		panelPerecedero.add(chckbxPercedero);

		String[] provinciasAndalucia = { "Almería", "Cádiz", "Córdoba", "Granada", "Huelva", "Jaén", "Málaga",
				"Sevilla" };
	}

	// Metodo para agregar productos
	private void agregarproducto() {
		String nombre = textNombre.getText();
		double precio = Double.parseDouble(textPrecio.getText());
		boolean perecedero = chckbxPercedero.isSelected();

		// Verificamos que el campo no este vacío
		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Creamos producto
		Producto producto = new Producto(nombre, precio, perecedero);
		// Lo agregamos a la tabla
		tablaProductos.agregarProducto(producto);
		clear();
	}

	// Borramos los campos
	private void clear() {
		textNombre.setText("");
		textPrecio.setText("");

	}
}
