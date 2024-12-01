package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.TallerPicassoMainApp;
import models.Cita;
import models.Usuario;
import java.util.List;

public class VentanaRegistro extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel banner;
	private JPanel bottomPanel;
	private JPanel centerPanel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JTextField txtNombre;
	private JLabel lblTelefono;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JLabel lblPass;
	private JLabel lblPassConfirmed;
	private JLabel lblMetodoContacto;
	private JPanel panel;
	private JRadioButton rdbEmail;
	private JRadioButton rdbTelefono;
	private final ButtonGroup radioButtonGroup = new ButtonGroup();
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JLabel lblNewLabel_2;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;

	/**
	 * Create the dialog.
	 */
	public VentanaRegistro(JFrame parent) {
		super(parent, "Registro", true);
		inicializarComponentes();		
	}

	private void inicializarComponentes() {
		setResizable(false);
		setSize(600, 600);
		setTitle("Talleres Picasso");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(VentanaRegistro.class.getResource("/resources/cocheAzul.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Cierra la ventana modal al hacer click en la "X"
		setBounds(750, 300, 500, 500);
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

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				crearCliente();
			}
		});
		btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bottomPanel.add(btnRegistrar);

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

		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		centerPanel.add(txtNombre);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		centerPanel.add(txtApellidos);

		lblTelefono = new JLabel("Teléfono");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		centerPanel.add(txtTelefono);

		lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		centerPanel.add(txtEmail);

		lblPass = new JLabel("Contraseña");
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblPass);

		passwordField = new JPasswordField();
		centerPanel.add(passwordField);

		lblPassConfirmed = new JLabel("Confirma Contrasela");
		lblPassConfirmed.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblPassConfirmed);

		passwordFieldConfirm = new JPasswordField();
		centerPanel.add(passwordFieldConfirm);

		lblMetodoContacto = new JLabel("Metodo de Contacto");
		lblMetodoContacto.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblMetodoContacto);

		panel = new JPanel();
		centerPanel.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		rdbTelefono = new JRadioButton("Teléfono");
		radioButtonGroup.add(rdbTelefono);
		rdbTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(rdbTelefono);

		rdbEmail = new JRadioButton("Email");
		radioButtonGroup.add(rdbEmail);
		rdbEmail.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(rdbEmail);
	}

	protected void crearCliente() {
	    String nombre = txtNombre.getText();
	    String apellido = txtApellidos.getText();
	    String telefono = txtTelefono.getText();
	    String email = txtEmail.getText();
	    String pass = new String(passwordField.getPassword());
	    String passConfirmed = new String(passwordFieldConfirm.getPassword());

	    // Validar que las contraseñas coincidan
	    if (!pass.equals(passConfirmed)) {
	        JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "ERROR", JOptionPane.ERROR_MESSAGE);
	        return; // Detener la ejecución del método
	    }

	    // Verificar si el usuario ya existe
	    boolean encontrado = false;
	    for (Usuario us : TallerPicassoMainApp.listaUsuarios) {
	        if (us.getEmail().equals(email)) {
	            encontrado = true;
	            break;
	        }
	    }

	    if (encontrado) {
	        JOptionPane.showMessageDialog(this, "El usuario ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    List<Cita> listaCitas = new ArrayList<>();
	    // Crear un nuevo usuario y añadirlo a la lista
	    Usuario nuevoUser = new Usuario(nombre, apellido, telefono, email, passConfirmed, false, listaCitas);
	    TallerPicassoMainApp.listaUsuarios.add(nuevoUser);

	    JOptionPane.showMessageDialog(this, "Usuario creado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

	    // Opcional: Cerrar la ventana después de crear el usuario
	    dispose();
	}


}
