package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BajaProducto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblEliminar;
	private TablaProducto tablaProductos; // Instancia de la tabla

	public BajaProducto() {
		setLayout(new BorderLayout(0, 0));

		this.tablaProductos = new TablaProducto();
		add(tablaProductos, BorderLayout.CENTER); // Añadir la tabla al centro

		tablaProductos.cargarProductosCSV();

		JPanel panelInferior = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new BorderLayout(0, 0));

		lblEliminar = new JLabel("Eliminar");
		lblEliminar.setBackground(new Color(245, 31, 31));
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEliminar.setBackground(new Color(200, 31, 31));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblEliminar.setBackground(new Color(245, 31, 31));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				tablaProductos.eliminarProductoSeleccionado();
			}
		});
		lblEliminar.setFont(new Font("Lexend", Font.BOLD, 18));
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setOpaque(true);
		panelInferior.add(lblEliminar);

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

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(188, 177, 177));
		add(panelSuperior, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Baja productos");
		lblTitulo.setForeground(new Color(8, 8, 8));
		lblTitulo.setFont(new Font("Lexend", Font.BOLD, 24));
		panelSuperior.add(lblTitulo);
	}
}
