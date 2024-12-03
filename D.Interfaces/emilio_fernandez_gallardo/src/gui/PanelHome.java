package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import main.MainApp;
import modelos.Serie;

public class PanelHome extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tablaSeries;
	private JTextField txtPlataforma;

	/**
	 * Create the panel.
	 */
	public PanelHome() {
		inicializaComponentes();
		cargarSeriesEnTabla();
	}



	private void inicializaComponentes() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Plataforma");
		panel.add(lblNewLabel);
		
		txtPlataforma = new JTextField();
		panel.add(txtPlataforma);
		txtPlataforma.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tablaSeries = new JTable();
		scrollPane.setViewportView(tablaSeries);
		tablaSeries.setModel(new DefaultTableModel(new Object[][] { { null, null, null}, },
				new String[] { "Serie", "Plataforma", "Numero de Temporadas" }));
		scrollPane.setViewportView(tablaSeries);
	}
	
	

	/**
	 * Carga las series a la tabla
	 */
	private void cargarSeriesEnTabla() {
		DefaultTableModel model = (DefaultTableModel) tablaSeries.getModel();
		// Limpiar la tabla antes de cargar nuevos datos
		model.setRowCount(0); 

		// Para cada serie
		for (Serie serie : MainApp.lstSeries) {
				model.addRow(new Object[] {
					serie.getNombre(),
					serie.getPlataforma(),
					serie.getLstTemporadas().size()
				});
		}
}
	
	/**
	 * Filtra jugadores por apellido o equipo
	 */
	protected void filtrarPorPlataforma() {
		DefaultTableModel model = (DefaultTableModel) tablaSeries.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		tablaSeries.setRowSorter(sorter);

		String plataformaFiltro = txtPlataforma.getText().trim();

		if (plataformaFiltro.isEmpty()) {
			 // Sin filtro
			sorter.setRowFilter(null);
		} else {
			List<RowFilter<Object, Object>> filtros = new ArrayList<>();

			// Filtro por equipo
			if (!plataformaFiltro.isEmpty()) {
				filtros.add(RowFilter.regexFilter("(?i)" + plataformaFiltro, 0)); // Columna 0: Equipo
			}

			// Combinar filtros
			RowFilter<Object, Object> filtroCombinado = RowFilter.andFilter(filtros);
			sorter.setRowFilter(filtroCombinado);
		}
	}
	

}
