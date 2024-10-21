package gui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import utils.Cliente;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;

public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JList<Cliente> listClientes;
    private DefaultListModel<Cliente> modelClientes;

    /**
     * Create the frame.
     */
    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(750, 350, 517, 402);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnClientes = new JMenu("Clientes");
        menuBar.add(mnClientes);
        
        JMenuItem mnAltaCliente = new JMenuItem("Alta Cliente");
        mnAltaCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAltaCliente();
            }
        });
        mnClientes.add(mnAltaCliente);

        JMenuItem mnBajaCliente = new JMenuItem("Baja Cliente");
        mnBajaCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaBajaCliente();
            }
        });
        mnClientes.add(mnBajaCliente);
        
        JMenu mnProductos = new JMenu("Productos");
        menuBar.add(mnProductos);
        
        JMenuItem mnAltaProductos = new JMenuItem("Alta Productos");
        mnAltaProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAltaProductos();
            }
        });
        mnProductos.add(mnAltaProductos);
        
        JMenuItem mnListarProductos = new JMenuItem("Listar Productos");
        mnListarProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaListarProductos();
            }
        });
        mnProductos.add(mnListarProductos);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        
        modelClientes = new DefaultListModel<>();  
        listClientes = new JList<>(modelClientes);
        listClientes.setBackground(new Color(202, 221, 203));
        scrollPane.setViewportView(listClientes); 
                
        
        clientesDefault();
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.add(scrollPane);
    }

    private void clientesDefault() {
        modelClientes.addElement(new Cliente("Carlos", "García Pérez", 35, "Sevilla"));
        modelClientes.addElement(new Cliente("Ana", "López Sánchez", 28, "Granada"));
        modelClientes.addElement(new Cliente("Javier", "Martínez Gómez", 42, "Córdoba"));
        modelClientes.addElement(new Cliente("Laura", "Rodríguez Fernández", 30, "Málaga"));
        modelClientes.addElement(new Cliente("Pedro", "González Díaz", 26, "Jaén"));
        modelClientes.addElement(new Cliente("María", "Fernández Ruiz", 38, "Almería"));
        modelClientes.addElement(new Cliente("David", "Pérez López", 29, "Huelva"));
        modelClientes.addElement(new Cliente("Elena", "Sánchez García", 33, "Cádiz"));
        modelClientes.addElement(new Cliente("José", "Martín López", 45, "Sevilla"));
        modelClientes.addElement(new Cliente("Isabel", "García Fernández", 40, "Granada"));
        modelClientes.addElement(new Cliente("Miguel", "López Martín", 32, "Córdoba"));
        modelClientes.addElement(new Cliente("Lucía", "Hernández Pérez", 25, "Málaga"));
        modelClientes.addElement(new Cliente("Antonio", "García López", 50, "Jaén"));
        modelClientes.addElement(new Cliente("Carmen", "Martínez Rodríguez", 27, "Almería"));
        modelClientes.addElement(new Cliente("Rafael", "Sánchez Pérez", 37, "Huelva"));
        modelClientes.addElement(new Cliente("Victoria", "González Ruiz", 31, "Cádiz"));
        modelClientes.addElement(new Cliente("Luis", "Martín Sánchez", 41, "Sevilla"));
        modelClientes.addElement(new Cliente("Patricia", "Hernández López", 34, "Granada"));
        modelClientes.addElement(new Cliente("Francisco", "Pérez García", 47, "Córdoba"));
        modelClientes.addElement(new Cliente("Raquel", "Martínez Hernández", 29, "Málaga"));
        modelClientes.addElement(new Cliente("Enrique", "González Martín", 39, "Jaén"));
        modelClientes.addElement(new Cliente("Sandra", "Rodríguez Martínez", 32, "Almería"));
        modelClientes.addElement(new Cliente("Luis", "Fernández Sánchez", 44, "Huelva"));
        modelClientes.addElement(new Cliente("José María", "García Martín", 36, "Cádiz"));
        modelClientes.addElement(new Cliente("Elena", "Hernández Sánchez", 31, "Sevilla"));
        modelClientes.addElement(new Cliente("Martín", "López Hernández", 33, "Granada"));
        modelClientes.addElement(new Cliente("Raul", "García López", 28, "Córdoba"));
        modelClientes.addElement(new Cliente("Beatriz", "Sánchez López", 30, "Málaga"));		
    }

    // Método para devolver el modelo de lista de clientes
    public DefaultListModel<Cliente> getModelClientes() {
        return modelClientes;
    }
        
    private void abrirVentanaAltaCliente() {
        AltaClientes ventanaAltaCliente = new AltaClientes(this);
        ventanaAltaCliente.setVisible(true);
    }

    private void abrirVentanaBajaCliente() {
        BajaClientes ventanaBajaCliente = new BajaClientes(modelClientes);
        ventanaBajaCliente.setVisible(true);
    }

    private void abrirVentanaAltaProductos() {
        ListarProductos ventanaListarProductos = new ListarProductos();
        AltaProductos ventanaAltaProductos = new AltaProductos(ventanaListarProductos);
        ventanaAltaProductos.setVisible(true);
    }


    private void abrirVentanaListarProductos() {
        ListarProductos ventanaListarProductos = new ListarProductos();
        ventanaListarProductos.setVisible(true);
    }

	

}
