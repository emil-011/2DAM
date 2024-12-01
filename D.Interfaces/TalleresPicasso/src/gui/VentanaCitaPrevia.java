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

import com.toedter.calendar.JDateChooser;

import main.TallerPicassoMainApp;
import models.Cita;

public class VentanaCitaPrevia extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel banner;
	private JPanel bottomPanel;
	private JPanel centerPanel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblMatricula;
	private JLabel lblMarca;
	private JTextField txtMatricula;
	private JLabel lblModelo;
	private JLabel lblFecha;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblNewLabel_2;
	private JDateChooser dateChooser;

	/**
	 * Create the dialog.
	 */
	public VentanaCitaPrevia(JFrame parent) {
		super(parent, "Registro", true);
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		setResizable(false);
		setSize(747, 500);
		setTitle("Talleres Picasso");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(VentanaCitaPrevia.class.getResource("/resources/cocheAzul.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Cierra la ventana modal al hacer click en la "X"
		setBounds(750, 300, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		banner = new JPanel();
		banner.setBackground(new Color(0, 0, 128));
		contentPane.add(banner, BorderLayout.NORTH);

		lblNewLabel_1 = new JLabel("Registro Cliente");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		banner.add(lblNewLabel_1);

		bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		lblNewLabel = new JLabel("     ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 54));
		bottomPanel.add(lblNewLabel);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registrarCita();
			}
		});
		btnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bottomPanel.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		bottomPanel.add(btnCancelar);

		lblNewLabel_2 = new JLabel("     ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 54));
		bottomPanel.add(lblNewLabel_2);

		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 2, 10, 40));

		lblMatricula = new JLabel("Matricula");
		lblMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblMatricula);

		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		centerPanel.add(txtMatricula);

		lblMarca = new JLabel("Marca");
		lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblMarca);

		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		centerPanel.add(txtMarca);

		lblModelo = new JLabel("Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblModelo);

		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		centerPanel.add(txtModelo);

		lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblFecha);

		dateChooser = new JDateChooser();
		centerPanel.add(dateChooser);
	}

	protected void registrarCita() {
		Date fechaCita = dateChooser.getDate();
		int contador = 0;
		for (Cita ct : TallerPicassoMainApp.listaCitas) {
			if (ct.getFecha().equals(fechaCita)) {
				contador++;
			}
		}

		if (contador >= 2) {
			JOptionPane.showMessageDialog(this,
					"No hay disponibilidad para la fecha seleccionada. Por favor, elija otra fecha.",
					"Fecha no disponible", JOptionPane.ERROR_MESSAGE);
		} else {
			String matricula = txtMatricula.getText();
			String marca = txtMarca.getText();
			String modelo = txtModelo.getText();

			Cita nuevaCita = new Cita(matricula, marca, modelo, fechaCita, "pendiente", 0.0, "");
			TallerPicassoMainApp.listaCitas.add(nuevaCita);

			JOptionPane.showMessageDialog(this,
					"Cita registrada con éxito para la fecha: " + new SimpleDateFormat("dd/MM/yyyy").format(fechaCita),
					"Cita registrada", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}

	}

}
