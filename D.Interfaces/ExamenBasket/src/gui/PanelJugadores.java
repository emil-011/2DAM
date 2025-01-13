package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import main.MainApp;
import models.Equipo;
import models.Usuario;

public class PanelJugadores extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtEquipo;
	private JTextField txtApellidos;
	private JTable table;

	public PanelJugadores() {
		inicializarComponentes();
		cargarJugadoresEnTabla();
	}

	private void inicializarComponentes() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Jugadores");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("Equipo");
		panel_1.add(lblNewLabel_1);
		
		txtEquipo = new JTextField();
		txtEquipo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filtrarJugadores();		
			}
		});
		panel_1.add(txtEquipo);
		txtEquipo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		panel_1.add(lblNewLabel_2);
		
		txtApellidos = new JTextField();
		txtApellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filtrarJugadores();

			}
		});
		panel_1.add(txtApellidos);
		txtApellidos.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Equipo", "Nombre", "Apellidos", "Fecha nacimiento", "Email"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(102);
		table.getColumnModel().getColumn(1).setPreferredWidth(116);
		table.getColumnModel().getColumn(2).setPreferredWidth(127);
		table.getColumnModel().getColumn(3).setPreferredWidth(97);
		table.getColumnModel().getColumn(4).setPreferredWidth(142);
		scrollPane.setViewportView(table);
	}

	/**
	 * Carga los jugadores a la tabla
	 */
	private void cargarJugadoresEnTabla() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		// Limpiar la tabla antes de cargar nuevos datos
		model.setRowCount(0); 

		// Para cada equipo
		for (Equipo equipo : MainApp.lstEquipos) {
			// Coger los datos de cada jugador
			for (Usuario jugador : equipo.getLstJugadores()) {
				model.addRow(new Object[] {
					equipo.getNombre(),
					jugador.getNombre(),
					jugador.getApellidos(),
					jugador.getFechaNacimiento(),
					jugador.getEmail()
				});
			}
		}
	}

	/**
	 * Filtra jugadores por apellido o equipo
	 */
	protected void filtrarJugadores() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);

		String equipoFiltro = txtEquipo.getText().trim();
		String apellidoFiltro = txtApellidos.getText().trim();

		if (equipoFiltro.isEmpty() && apellidoFiltro.isEmpty()) {
			 // Sin filtro
			sorter.setRowFilter(null);
		} else {
			List<RowFilter<Object, Object>> filtros = new ArrayList<>();

			// Filtro por equipo
			if (!equipoFiltro.isEmpty()) {
				filtros.add(RowFilter.regexFilter("(?i)" + equipoFiltro, 0)); // Columna 0: Equipo
			}

			// Filtro por apellidos
			if (!apellidoFiltro.isEmpty()) {
				filtros.add(RowFilter.regexFilter("(?i)" + apellidoFiltro, 2)); // Columna 2: Apellidos
			}

			// Combinar filtros
			RowFilter<Object, Object> filtroCombinado = RowFilter.andFilter(filtros);
			sorter.setRowFilter(filtroCombinado);
		}
	}

}
