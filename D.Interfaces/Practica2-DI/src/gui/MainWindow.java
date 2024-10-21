package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import utils.Cliente;


public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultListModel<Cliente> modelClientes;

    /**
     * Create the frame.
     */
    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(750, 350, 736, 495);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0)); // No padding around contentPane
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(24, 24, 24));
        contentPane.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Load and scale the image
        ImageIcon originalIcon = new ImageIcon(MainWindow.class.getResource("/resources/youtube.png"));
        Image originalImage = originalIcon.getImage();
        int width = 150; // Adjustable width
        int height = 100; // Adjustable height
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Create JLabel to show the scaled image
        JLabel lblYoutubePic = new JLabel(scaledIcon);
        lblYoutubePic.setHorizontalAlignment(SwingConstants.LEFT);
        lblYoutubePic.setPreferredSize(new Dimension(width, height));
        
        // Add image to the top panel
        topPanel.add(lblYoutubePic);
        
        // Center panel with labels
        JPanel centerPanel = new JPanel();
        contentPane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // No gaps

        // Create labels with no preferred size and touching edges
        JLabel lblClientes = new JLabel("Clientes");
        lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
        lblClientes.setFont(new Font("Lexend", Font.BOLD, 24));
        lblClientes.setBackground(new Color(231, 22, 22)); // Background color
        lblClientes.setOpaque(true);
        lblClientes.setPreferredSize(new Dimension(180,40));
        centerPanel.add(lblClientes);

        JLabel lblProductos = new JLabel("Productos");
        lblProductos.setHorizontalAlignment(SwingConstants.CENTER);
        lblProductos.setBackground(new Color(231, 22, 22)); // Background color
        lblProductos.setFont(new Font("Lexend", Font.BOLD, 24));
        lblProductos.setOpaque(true);
        lblProductos.setPreferredSize(new Dimension(180,40));
        centerPanel.add(lblProductos);

        JLabel lblFacturas = new JLabel("Facturas");
        lblFacturas.setHorizontalAlignment(SwingConstants.CENTER);
        lblFacturas.setBackground(new Color(231, 22, 22)); // Background color
        lblFacturas.setFont(new Font("Lexend", Font.BOLD, 24));
        lblFacturas.setPreferredSize(new Dimension(180,40));
        lblFacturas.setOpaque(true);
        centerPanel.add(lblFacturas);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setBackground(new Color(231, 22, 22)); // Background color
        lblUsuario.setFont(new Font("Lexend", Font.BOLD, 24));
        lblUsuario.setOpaque(true);
        lblUsuario.setPreferredSize(new Dimension(180,40));
        centerPanel.add(lblUsuario);

        // Bottom panel
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        
        JLabel lblNewLabel_4 = new JLabel("New label");
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(31)
                    .addComponent(lblNewLabel_4)
                    .addContainerGap(606, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, gl_panel.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNewLabel_4))
        );
        panel.setLayout(gl_panel);
        
        modelClientes = new DefaultListModel<>();
    }

    // Method to return the list model of clients
    public DefaultListModel<Cliente> getModelClientes() {
        return modelClientes;
    }
}
