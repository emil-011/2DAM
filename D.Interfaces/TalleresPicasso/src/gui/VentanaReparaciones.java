package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import main.TallerPicassoMainApp;
import models.Cita;
import models.Usuario;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaReparaciones extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel banner;
	private JPanel bottomPanel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnVolver;
	private JLabel lblNewLabel_2;
	private JScrollPane scrollPane;
	private JTable tablaReparaciones;

	/**
	 * Create the dialog.
	 */
	public VentanaReparaciones(JFrame parent, Usuario usuario) {
		super(parent, "Registro", true);
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		setResizable(false);
		setSize(747, 500);
		setTitle("Talleres Picasso");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(VentanaReparaciones.class.getResource("/resources/cocheAzul.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Cierra la ventana modal al hacer click en la "X"
		setBounds(750, 300, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		banner = new JPanel();
		banner.setBackground(new Color(0, 0, 128));
		contentPane.add(banner, BorderLayout.NORTH);

		lblNewLabel_1 = new JLabel("Ventana Reparaciones");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		banner.add(lblNewLabel_1);

		bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		lblNewLabel = new JLabel("     ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 54));
		bottomPanel.add(lblNewLabel);

		btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bottomPanel.add(btnVolver);

		lblNewLabel_2 = new JLabel("     ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 54));
		bottomPanel.add(lblNewLabel_2);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tablaReparaciones = new JTable();
		scrollPane.setViewportView(tablaReparaciones);
		
		cargarDatosTabla();
		
	}	

	private void cargarDatosTabla() {
	    // Definir las columnas de la tabla
	    String[] columnas = { "Matrícula", "Fecha Cita", "Estado Reparación", "Importe", "Observaciones" };

	    // Crear un modelo de tabla con las columnas definidas
	    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

	    // Recorrer las citas de la lista global y agregarlas a la tabla
	    for (Cita cita : TallerPicassoMainApp.listaCitas) {
	        // Crear una fila para la cita con sus datos
	        Object[] fila = {
	            cita.getMatricula(),
	            new SimpleDateFormat("dd/MM/yyyy").format(cita.getFecha()), 
	            cita.getEstadoReparacion(), 
	            cita.getImporte(), 
	            cita.getObservaciones() 
	        };

	        // Añadir la fila al modelo de la tabla
	        modelo.addRow(fila);
	    }

	    // Asignamos el modelo a la tabla
	    tablaReparaciones.setModel(modelo);
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
	    tablaReparaciones.setRowSorter(sorter);
	}

}
