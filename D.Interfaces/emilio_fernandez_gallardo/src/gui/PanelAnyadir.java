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
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelAnyadir extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelAnyadir() {
		inicializaComponentes();
	}



	private void inicializaComponentes() {
		
		setBackground(new Color(85, 85, 85));
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNuevaSerie = new JLabel("Nueva Serie");
		lblNuevaSerie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventanaNuevaTemporada();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNuevaSerie.setBackground(new Color(30,30,30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNuevaSerie.setBackground(new Color(85,85,85));
			}
		});
		lblNuevaSerie.setBackground(new Color(85, 85, 85));
		lblNuevaSerie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNuevaSerie.setOpaque(true);
		lblNuevaSerie.setForeground(new Color(255, 255, 255));
		lblNuevaSerie.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNuevaSerie.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNuevaSerie);
		
		
		JLabel lblNuevaTemporada = new JLabel("Nueva Temporada");
		lblNuevaTemporada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventanaNuevaSerie();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNuevaTemporada.setBackground(new Color(30,30,30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNuevaTemporada.setBackground(new Color(85,85,85));
			}
		});	
		lblNuevaTemporada.setBackground(new Color(85, 85, 85));
		lblNuevaTemporada.setOpaque(true);
		lblNuevaTemporada.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNuevaTemporada.setForeground(new Color(255, 255, 255));
		lblNuevaTemporada.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNuevaTemporada.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNuevaTemporada);
	}
	

	protected void ventanaNuevaTemporada() {
		NuevaSerie ne = new NuevaSerie();
		ne.setVisible(true);
	}



	protected void ventanaNuevaSerie() {
		NuevaTemporada ne = new NuevaTemporada();
		ne.setVisible(true);
	}



	


	

}
