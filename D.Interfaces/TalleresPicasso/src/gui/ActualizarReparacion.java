package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import models.Cita;
import models.Usuario;

import javax.swing.JComboBox;

public class ActualizarReparacion extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel banner;
	private JPanel bottomPanel;
	private JPanel centerPanel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblMatricula;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lblFecha;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblNewLabel_2;
	private JLabel lblEstadoActual;
	private JLabel lblNuevoEstado;
	private JComboBox<String> comboBoxNuevoEstado;
	private JComboBox<String> comboBoxMatricula;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_4;
	private JLabel lblMarca;
	private JLabel lblModelo;
	private JLabel lblPrecio;
	private JTextField textFieldPrecio;
	private JLabel lblObservaciones;
	private JTextField textFieldObservaciones;

	public ActualizarReparacion(JFrame parent, Usuario usuario) {
		super(parent, "Registro", true);
		inicializarComponentes(usuario);
	}

	private void inicializarComponentes(Usuario usuario) {
		setResizable(false);
		setSize(747, 500);
		setTitle("Talleres Picasso");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ActualizarReparacion.class.getResource("/resources/cocheAzul.png")));
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
				actualizarDatos(usuario);
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
		centerPanel.setLayout(new GridLayout(0, 2, 10, 10));

		lblNewLabel_5 = new JLabel(" ");
		centerPanel.add(lblNewLabel_5);

		lblNewLabel_4 = new JLabel(" ");
		centerPanel.add(lblNewLabel_4);

		lblMatricula = new JLabel("Matricula");
		lblMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblMatricula);

		comboBoxMatricula = new JComboBox<String>();
		centerPanel.add(comboBoxMatricula);

		lbl1 = new JLabel("Marca");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lbl1);

		lblMarca = new JLabel(" ");
		centerPanel.add(lblMarca);

		lbl2 = new JLabel("Modelo");
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lbl2);

		lblModelo = new JLabel(" ");
		centerPanel.add(lblModelo);

		lblFecha = new JLabel("Estado Actual");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblFecha);

		lblEstadoActual = new JLabel(" ");
		centerPanel.add(lblEstadoActual);

		lblNuevoEstado = new JLabel("Nuevo Estado");
		lblNuevoEstado.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNuevoEstado);

		comboBoxNuevoEstado = new JComboBox<String>();
		centerPanel.add(comboBoxNuevoEstado);

		cargarDatos(usuario);
	}

	protected void cargarDatos(Usuario usuario) {
		// Agregar un ítem vacío o por defecto en el ComboBox
		comboBoxMatricula.addItem("");
		comboBoxNuevoEstado.addItem("pendiente");
		comboBoxNuevoEstado.addItem("en curso");
		comboBoxNuevoEstado.addItem("terminado");

		// Llenar el ComboBox con las matrículas de las citas del usuario
		List<Cita> listaCitas = usuario.getListaCitas();
		for (Cita cita : listaCitas) {
			comboBoxMatricula.addItem(cita.getMatricula());
		}

		// Establecer la selección por defecto en vacío
		comboBoxMatricula.setSelectedItem("");

		// Añadir un ActionListener para manejar el cambio de selección
		comboBoxMatricula.addActionListener(e -> {
			String matriculaSeleccionada = (String) comboBoxMatricula.getSelectedItem();

			// Solo realizar la acción si se ha seleccionado una matrícula válida
			if (matriculaSeleccionada != null && !matriculaSeleccionada.isEmpty()) {
				// Buscar la cita correspondiente a la matrícula seleccionada
				Cita citaSeleccionada = null;
				for (Cita cita : listaCitas) {
					if (cita.getMatricula().equals(matriculaSeleccionada)) {
						citaSeleccionada = cita;
						break;
					}
				}

				// Si encontramos la cita, actualizamos los JLabel con la información de la cita
				if (citaSeleccionada != null) {
					lblMarca.setText(citaSeleccionada.getMarca());
					lblModelo.setText(citaSeleccionada.getModelo());
					lblEstadoActual.setText(citaSeleccionada.getEstadoReparacion());
				}
			} else {
				// Si no hay matrícula seleccionada o está vacía, limpiamos los JLabel
				lblMarca.setText("");
				lblModelo.setText("");
				lblEstadoActual.setText("");
			}
		});
	}

	// Clase ActualizarReparacion (parte del código)
	protected void actualizarDatos(Usuario usuario) {
	    String estado = (String) comboBoxNuevoEstado.getSelectedItem();
	    lblEstadoActual.setText(estado);

	    // Buscar la cita correspondiente a la matrícula seleccionada
	    String matriculaSeleccionada = (String) comboBoxMatricula.getSelectedItem();
	    Cita citaSeleccionada = null;
	    
	    // Buscar la cita en la lista del usuario
	    for (Cita cita : usuario.getListaCitas()) {
	        if (cita.getMatricula().equals(matriculaSeleccionada)) {
	            citaSeleccionada = cita;
	            break;
	        }
	    }

	    // Si encontramos la cita correspondiente, actualizamos los datos
	    if (citaSeleccionada != null) {
	        // Actualizar el estado de la reparación
	        citaSeleccionada.setEstadoReparacion(estado);

	        // Si el estado es "terminado", obtener el precio y las observaciones
	        if (estado.equals("terminado")) {
	            // Mostrar los campos si no han sido añadidos ya
	            if (textFieldPrecio == null && textFieldObservaciones == null) {
	                // Crear los JLabel y JTextField para Precio y Observaciones
	                lblPrecio = new JLabel("Precio:");
	                lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
	                textFieldPrecio = new JTextField(10);
	                lblObservaciones = new JLabel("Observaciones:");
	                lblObservaciones.setHorizontalAlignment(SwingConstants.CENTER);
	                textFieldObservaciones = new JTextField(10);

	                // Añadir los JLabel y JTextField al panel
	                centerPanel.add(lblPrecio);
	                centerPanel.add(textFieldPrecio);
	                centerPanel.add(lblObservaciones);
	                centerPanel.add(textFieldObservaciones);

	                // Redibujar el panel para mostrar los nuevos componentes
	                centerPanel.revalidate();
	                centerPanel.repaint();
	            }
	        }

	        // Si el estado es "terminado", obtener los valores de los campos de texto
	        if (estado.equals("terminado")) {
	            try {
	                // Obtener los valores de los campos de texto
	                double precio = 0;
	                if (!textFieldPrecio.getText().isEmpty()) {
	                    precio = Double.parseDouble(textFieldPrecio.getText());
	                }
	                String observaciones = textFieldObservaciones.getText();

	                // Actualizar el precio y las observaciones en la Cita
	                citaSeleccionada.setImporte(precio);
	                citaSeleccionada.setObservaciones(observaciones);
	            } catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(this,
	                        "El precio debe ser un número válido.",
	                        "Error en el precio", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	        }
	    }

	    // Mostrar mensaje de éxito
	    JOptionPane.showMessageDialog(this,
	            "Reparación actualizada correctamente",
	            "Reparación Actualizada", JOptionPane.INFORMATION_MESSAGE);
	}


}
