package swingApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import utils.CSVUtils;

public class CSVSwingApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String archivoCSV = "archivos/datos.csv";
	private String archivoDat = "archivos/datos.dat";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CSVSwingApp frame = new CSVSwingApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CSVSwingApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		setContentPane(contentPane);

		JLabel lblTitle = new JLabel("CSV Converter");
		lblTitle.setBounds(177, 11, 304, 66);
		lblTitle.setFont(new Font("Lexend", Font.BOLD, 32));

		JLabel lblCsvToBin = new JLabel("CSV file to binarie file");
		lblCsvToBin.setBounds(30, 90, 250, 50);
		lblCsvToBin.setFont(new Font("Lexend", Font.BOLD, 20));

		JLabel lblSortBin = new JLabel("Sort binarie file");
		lblSortBin.setBounds(68, 192, 250, 50);
		lblSortBin.setFont(new Font("Lexend", Font.BOLD, 20));

		JLabel lblBinToCSV = new JLabel("Binarie file to CSV  file");
		lblBinToCSV.setBounds(315, 90, 239, 50);
		lblBinToCSV.setFont(new Font("Lexend", Font.BOLD, 20));

		JLabel lblCsvToBinSorted = new JLabel("Binarie file to CSV sorted");
		lblCsvToBinSorted.setBounds(30, 295, 263, 50);
		lblCsvToBinSorted.setFont(new Font("Lexend", Font.BOLD, 20));

		JLabel lblSortCsv = new JLabel("Sort CSV file");
		lblSortCsv.setBounds(350, 192, 239, 50);
		lblSortCsv.setFont(new Font("Lexend", Font.BOLD, 20));

		/**
		 * Boton que pasa de .CSV a .dat
		 */
		JButton btnCSVToBin = new JButton("GO");
		btnCSVToBin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(btnCSVToBin,
						"¿Quieres convertir el archivo CSV a binario?");

				if (respuesta == JOptionPane.YES_OPTION) {
					try {
						CSVUtils.fichero_CSV_To_Binario(archivoCSV);
					} catch (IOException e1) {

					}

				}
			}
		});

		btnCSVToBin.setFont(new Font("Lexend", Font.BOLD, 24));
		btnCSVToBin.setForeground(new Color(26, 159, 29));
		btnCSVToBin.setBackground(new Color(197, 240, 153));
		btnCSVToBin.setBounds(75, 145, 150, 30);

		JButton btnCSVToBin_1 = new JButton("GO");
		btnCSVToBin_1.setBounds(75, 240, 150, 30);

		JButton btnCSVToBin_1_1 = new JButton("GO");
		btnCSVToBin_1_1.setBounds(80, 345, 150, 30);

		JLabel lblOpenFile = new JLabel("Open file");
		lblOpenFile.setBounds(373, 295, 239, 50);
		lblOpenFile.setFont(new Font("Lexend", Font.BOLD, 20));

		JButton btnBinToCSV = new JButton("GO");
		btnBinToCSV.setBounds(350, 145, 150, 30);
		contentPane.setLayout(null);
		contentPane.add(lblTitle);
		contentPane.add(lblCsvToBin);
		contentPane.add(lblBinToCSV);
		contentPane.add(lblCsvToBinSorted);
		contentPane.add(lblOpenFile);
		contentPane.add(btnCSVToBin_1_1);
		contentPane.add(btnCSVToBin_1);
		contentPane.add(lblSortBin);
		contentPane.add(btnCSVToBin);
		contentPane.add(btnBinToCSV);
		contentPane.add(lblSortCsv);

		JButton btnCSVToBin_2_1 = new JButton("GO");
		btnCSVToBin_2_1.setBounds(350, 240, 150, 30);
		contentPane.add(btnCSVToBin_2_1);

		JButton btnCSVToBin_1_1_1 = new JButton("GO");
		btnCSVToBin_1_1_1.setBackground(new Color(255, 0, 0));
		btnCSVToBin_1_1_1.setBounds(350, 345, 150, 30);
		contentPane.add(btnCSVToBin_1_1_1);
	}
}
