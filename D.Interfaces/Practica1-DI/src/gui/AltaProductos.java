// Clase AltaProductos
package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import utils.Producto;

import java.awt.Color;

public class AltaProductos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtNombre;
    private JTextField txtPrecioUnitario;
    private JLabel lblPerecedero;
    private JLabel lblPrecioUnitario;
    private JLabel lblNombre;
    private JCheckBox chckbxPerecedero;
	private ListarProductos listarProductos;

    // Constructor
    public AltaProductos(ListarProductos listarProductos) {
    	this.listarProductos = listarProductos;
        setTitle("Alta Productos");
        setBounds(825, 450, 321, 226);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(207, 224, 208));
        getContentPane().add(panel);
        panel.setLayout(null);

        txtNombre = new JTextField();
        txtNombre.setBounds(104, 11, 186, 20);
        panel.add(txtNombre);
        txtNombre.setColumns(10);

        lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(10, 14, 75, 14);
        panel.add(lblNombre);

        lblPrecioUnitario = new JLabel("Precio Unitario");
        lblPrecioUnitario.setBounds(10, 45, 90, 14);
        panel.add(lblPrecioUnitario);

        txtPrecioUnitario = new JTextField();
        txtPrecioUnitario.setColumns(10);
        txtPrecioUnitario.setBounds(104, 42, 186, 20);
        panel.add(txtPrecioUnitario);

        JButton btnAnyadir = new JButton("Añadir");
        btnAnyadir.setBackground(new Color(91, 142, 94));
        btnAnyadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres añadir al producto?",
                        "Confirmar acción", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    anyadirProducto();
                }
            }
        });
        btnAnyadir.setBounds(120, 106, 102, 36);
        panel.add(btnAnyadir);

        lblPerecedero = new JLabel("Es Perecedero");
        lblPerecedero.setBounds(10, 73, 104, 14);
        panel.add(lblPerecedero);

        chckbxPerecedero = new JCheckBox("");
        chckbxPerecedero.setBounds(120, 69, 124, 30);
        panel.add(chckbxPerecedero);

        setVisible(true);
    }

    protected void anyadirProducto() {
        String nombre = txtNombre.getText();
        String precio = txtPrecioUnitario.getText();
        boolean perecedero = chckbxPerecedero.isSelected();

        if (nombre.isEmpty() || precio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double precioUnitario = Double.parseDouble(precio);
            Producto producto = new Producto(nombre, precioUnitario, perecedero);
    
			listarProductos.getModelProducto().addElement(producto);

            // Reiniciar campos
            txtNombre.setText("");
            txtPrecioUnitario.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
