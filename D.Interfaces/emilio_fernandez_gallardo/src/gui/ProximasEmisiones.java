package gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import main.MainApp;
import modelos.Serie;
import modelos.Temporada;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProximasEmisiones extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable tablaProximasEmisiones;
	private JTextField txtPlataforma;

	/**
	 * Create the frame.
	 */
	public ProximasEmisiones() {
		setTitle("Seriesfly");
		inicializarComponentes();
		cargarSeriesEnTabla();
	}

	private void inicializarComponentes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevaTemporada.class.getResource("/resources/logo.png")));
		setModal(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 893, 568);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Próximas Emisiones");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 42));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		panel.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("En el Proximo...");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		panel_2.add(panel_3);

		JRadioButton rdbSemana = new JRadioButton("Semana");
		rdbSemana.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroup.add(rdbSemana);
		rdbSemana.setFont(new Font("Verdana", Font.BOLD, 16));
		rdbSemana.setBackground(new Color(0, 0, 0));
		rdbSemana.setForeground(new Color(255, 255, 255));
		panel_3.add(rdbSemana);

		JRadioButton rdbMes = new JRadioButton("Mes");
		rdbMes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroup.add(rdbMes);
		rdbMes.setBackground(new Color(0, 0, 0));
		rdbMes.setFont(new Font("Verdana", Font.BOLD, 16));
		rdbMes.setForeground(new Color(255, 255, 255));
		panel_3.add(rdbMes);

		JRadioButton rdbAnyo = new JRadioButton("Año");
		rdbAnyo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroup.add(rdbAnyo);
		rdbAnyo.setBackground(new Color(0, 0, 0));
		rdbAnyo.setFont(new Font("Verdana", Font.BOLD, 16));
		rdbAnyo.setForeground(new Color(255, 255, 255));
		panel_3.add(rdbAnyo);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);

		JLabel lblNewLabel_2 = new JLabel("Plataforma");
		panel_4.add(lblNewLabel_2);

		txtPlataforma = new JTextField();
		txtPlataforma.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filtrarPorPlataforma();
			}
		});
		panel_4.add(txtPlataforma);
		txtPlataforma.setColumns(10);
		panel_1.add(btnCerrar);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tablaProximasEmisiones = new JTable();
		tablaProximasEmisiones.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "Serie", "Plataforma", "Temporada", "Episodio", "Fecha" }));
		scrollPane.setViewportView(tablaProximasEmisiones);
	}

	/**
	 * Carga las series a la tabla
	 */
	private void cargarSeriesEnTabla() {
		DefaultTableModel model = (DefaultTableModel) tablaProximasEmisiones.getModel();
		// Limpiar la tabla antes de cargar nuevos datos
		model.setRowCount(0); 

		// Para cada serie
		for (Serie serie : MainApp.lstSeries) {
			for (Temporada tp : serie.getLstTemporadas()) {
				model.addRow(new Object[] {
					serie.getNombre(),
					serie.getPlataforma(),
					tp.getNumeroTemporada(),
					tp.getNumerocapitulos(),
					tp.getInicioTemporada()			
				});
			}
		}
}
	
	/**
	 * Filtra jugadores por apellido o equipo
	 */
	protected void filtrarPorPlataforma() {
		DefaultTableModel model = (DefaultTableModel) tablaProximasEmisiones.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		tablaProximasEmisiones.setRowSorter(sorter);

		String plataformaFiltro = txtPlataforma.getText().trim();

		if (plataformaFiltro.isEmpty()) {
			 // Sin filtro
			sorter.setRowFilter(null);
		} else {
			List<RowFilter<Object, Object>> filtros = new ArrayList<>();

			// Filtro por equipo
			if (!plataformaFiltro.isEmpty()) {
				filtros.add(RowFilter.regexFilter("(?i)" + plataformaFiltro, 0));
			}

			// Combinar filtros
			RowFilter<Object, Object> filtroCombinado = RowFilter.andFilter(filtros);
			sorter.setRowFilter(filtroCombinado);
		}
	}

}
