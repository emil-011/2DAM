package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal extends JFrame {

	private JLabel lblNombreFooter;
	private JLabel lblYoutube;
	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JPanel panelCentral;
	private JLabel lblClientes;
	private JLabel lblProductos;
	private JLabel lblFacturas;
	private JLabel lblUsuario;
	private JPanel panelBotoneras;
	private JPanel panelTabla;

	public VentanaPrincipal() {

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/youtube.png"));
		setIconImage(icon.getImage());
		setTitle("Youtube");

		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(18, 18, 18));
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

		lblYoutube = new JLabel("    YOUTUBE");
		lblYoutube.setForeground(new Color(255, 255, 255));
		lblYoutube.setFont(new Font("Lexend", Font.BOLD, 32));
		ImageIcon originalIcon = new ImageIcon(VentanaPrincipal.class.getResource("/resources/youtube.png"));
		Image originalImage = originalIcon.getImage();
		Image scaledImage = originalImage.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
		lblYoutube.setIcon(new ImageIcon(scaledImage));
		panelSuperior.add(lblYoutube);

		panelInferior = new JPanel();
		panelInferior.setBackground(new Color(18, 18, 18));
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

		lblNombreFooter = new JLabel("Emilio Fernández Gallardo");
		lblNombreFooter.setForeground(new Color(255, 255, 255));
		lblNombreFooter.setFont(new Font("Lexend", Font.BOLD, 16));
		panelInferior.add(lblNombreFooter);

		panelCentral = new JPanel();
		panelCentral.setBackground(new Color(18, 18, 18));
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));

		panelBotoneras = new JPanel();
		panelCentral.add(panelBotoneras, BorderLayout.NORTH);
		panelBotoneras.setLayout(new GridLayout(1, 4));

		lblClientes = new JLabel("Clientes");
		lblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblClientes.setBackground(new Color(255, 50, 50));
				lblClientes.setForeground(Color.white);
				lblClientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblClientes.setBackground(new Color(230, 0, 0));
				lblClientes.setForeground(Color.black);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String[] options = { "Dar de Alta", "Dar de Baja", "Mostrar Lista", "Cancelar" };
				int response = JOptionPane.showOptionDialog(null, "¿Qué quieres hacer?", "Gestión de Clientes",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

				if (response == 0) {
					TablaClientes tablaClientes = new TablaClientes();
					AltaCliente formulario = new AltaCliente(tablaClientes);
					panelTabla.removeAll();
					panelTabla.add(formulario, BorderLayout.CENTER);
					panelTabla.revalidate();
					panelTabla.repaint();
				} else if (response == 1) {
					BajaCliente bajaClientes = new BajaCliente();
					panelTabla.removeAll();
					panelTabla.add(bajaClientes, BorderLayout.CENTER);
					panelTabla.revalidate();
					panelTabla.repaint();
				} else if (response == 2) {
					mostrarListaClientes();
				}
			}
		});
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientes.setForeground(Color.black);
		lblClientes.setBackground(new Color(230, 0, 0));
		lblClientes.setFont(new Font("Lexend", Font.BOLD, 24));
		lblClientes.setOpaque(true);
		panelBotoneras.add(lblClientes);

		lblProductos = new JLabel("Productos");
		lblProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblProductos.setBackground(new Color(255, 50, 50));
				lblProductos.setForeground(Color.white);
				lblProductos.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblProductos.setBackground(new Color(230, 0, 0));
				lblProductos.setForeground(Color.black);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String[] options = { "Dar de Alta", "Dar de Baja", "Mostrar Lista", "Cancelar" };
				int response = JOptionPane.showOptionDialog(null, "¿Qué quieres hacer?", "Gestión de Productos",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

				if (response == 0) {
					TablaProducto tablaProductos = new TablaProducto();
					AltaProducto formulario = new AltaProducto(tablaProductos);
					panelTabla.removeAll();
					panelTabla.add(formulario, BorderLayout.CENTER);
					panelTabla.revalidate();
					panelTabla.repaint();
				} else if (response == 1) {
					BajaProducto bajaProductos = new BajaProducto();
					panelTabla.removeAll();
					panelTabla.add(bajaProductos, BorderLayout.CENTER);
					panelTabla.revalidate();
					panelTabla.repaint();
				} else if (response == 2) {
					mostrarListaProductos();
				}
			}
		});
		lblProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductos.setForeground(Color.black);
		lblProductos.setBackground(new Color(230, 0, 0));
		lblProductos.setFont(new Font("Lexend", Font.BOLD, 24));
		lblProductos.setOpaque(true);
		panelBotoneras.add(lblProductos);

		lblFacturas = new JLabel("Facturas");
		lblFacturas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblFacturas.setBackground(new Color(255, 50, 50));
				lblFacturas.setForeground(Color.white);
				lblFacturas.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblFacturas.setBackground(new Color(230, 0, 0));
				lblFacturas.setForeground(Color.black);
			}
		});
		lblFacturas.setHorizontalAlignment(SwingConstants.CENTER);
		lblFacturas.setForeground(Color.black);
		lblFacturas.setBackground(new Color(230, 0, 0));
		lblFacturas.setFont(new Font("Lexend", Font.BOLD, 24));
		lblFacturas.setOpaque(true);
		panelBotoneras.add(lblFacturas);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblUsuario.setBackground(new Color(50, 50, 255));
				lblUsuario.setForeground(Color.white);
				lblUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblUsuario.setBackground(new Color(0, 0, 230));
				lblUsuario.setForeground(Color.black);
			}
		});
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setForeground(Color.black);
		lblUsuario.setBackground(new Color(0, 0, 230));
		lblUsuario.setFont(new Font("Lexend", Font.BOLD, 24));
		lblUsuario.setOpaque(true);
		panelBotoneras.add(lblUsuario);

		panelTabla = new JPanel();
		panelTabla.setLayout(new BorderLayout());
		panelCentral.add(panelTabla, BorderLayout.CENTER);
	}

	// Metodo para mostrar los clientes
	private void mostrarListaClientes() {
		panelTabla.removeAll();
		TablaClientes tablaClientes = new TablaClientes();
		tablaClientes.cargarClientesCSV();
		panelTabla.add(tablaClientes, BorderLayout.CENTER);
		panelTabla.revalidate();
		panelTabla.repaint();
	}

	// Metodo para mostrar los productos
	private void mostrarListaProductos() {
		panelTabla.removeAll();
		TablaProducto tablaProductos = new TablaProducto();
		tablaProductos.cargarProductosCSV();
		panelTabla.add(tablaProductos, BorderLayout.CENTER);
		panelTabla.revalidate();
		panelTabla.repaint();
	}
}
