// Clase ListarProductos
package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import utils.Producto;
import java.awt.Color;

public class ListarProductos extends JFrame {

    private DefaultListModel<Producto> modelProductos;
    private static final long serialVersionUID = 1L;
    private JList<Producto> jListProductos;

    // Constructor
    public ListarProductos() {
        // Configura la ventana (tamaño, agregar lista, etc.)
        setTitle("Listar Productos");
        setBounds(825, 450, 484, 346);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel para la lista de productos
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 448, 285);
        panel.add(scrollPane);

        modelProductos = new DefaultListModel<>();
        jListProductos = new JList<>(modelProductos);
        jListProductos.setBackground(new Color(207, 224, 208));
        scrollPane.setViewportView(jListProductos);

        productosDefault();
        setVisible(true);
    }   
    
    private void productosDefault() {
    	modelProductos.addElement(new Producto("Manzanas", 1.20, true));
    	modelProductos.addElement(new Producto("Plátanos", 1.10, true));
    	modelProductos.addElement(new Producto("Naranjas", 0.90, true));
    	modelProductos.addElement(new Producto("Uvas", 2.50, true));
    	modelProductos.addElement(new Producto("Fresas", 3.00, true));
    	modelProductos.addElement(new Producto("Leche", 0.80, true));
    	modelProductos.addElement(new Producto("Huevos", 2.00, true));
    	modelProductos.addElement(new Producto("Yogur", 0.75, true));
    	modelProductos.addElement(new Producto("Pan", 1.50, true));
    	modelProductos.addElement(new Producto("Arroz", 1.00, false));
    	modelProductos.addElement(new Producto("Pasta", 0.90, false));
    	modelProductos.addElement(new Producto("Azúcar", 1.30, false));
    	modelProductos.addElement(new Producto("Sal", 0.50, false));
    	modelProductos.addElement(new Producto("Aceite de Oliva", 5.00, false));
    	modelProductos.addElement(new Producto("Vinagre", 1.20, false));
    	modelProductos.addElement(new Producto("Cereal", 2.50, false));
    	modelProductos.addElement(new Producto("Mermelada", 3.40, true));
    	modelProductos.addElement(new Producto("Queso", 2.80, true));
    	modelProductos.addElement(new Producto("Jamon", 4.50, true));
    	modelProductos.addElement(new Producto("Carne de Pollo", 6.00, true));
    	modelProductos.addElement(new Producto("Carne de Res", 10.00, true));
    	modelProductos.addElement(new Producto("Pescado", 8.00, true));
    	modelProductos.addElement(new Producto("Verduras Mixtas", 1.50, true));
    	modelProductos.addElement(new Producto("Patatas", 0.80, true));
    	modelProductos.addElement(new Producto("Cebollas", 0.60, true));
    	modelProductos.addElement(new Producto("Ajo", 0.40, true));
    	modelProductos.addElement(new Producto("Tomates", 1.00, true));
    	modelProductos.addElement(new Producto("Pepinos", 0.70, true));
    	modelProductos.addElement(new Producto("Lentejas", 1.50, false));
    	modelProductos.addElement(new Producto("Frijoles", 1.00, false));
    	modelProductos.addElement(new Producto("Garbanzos", 1.20, false));
    	modelProductos.addElement(new Producto("Chocolate", 2.00, false));
    	modelProductos.addElement(new Producto("Galletas", 1.50, false));
    	modelProductos.addElement(new Producto("Snack Salado", 1.80, false));
    	modelProductos.addElement(new Producto("Bebida Energética", 2.50, false));
    	modelProductos.addElement(new Producto("Café", 3.00, false));
    	modelProductos.addElement(new Producto("Té", 2.00, false));

    }
    
    public DefaultListModel<Producto> getModelProducto(){
		return modelProductos;
	}
}
