package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal extends JFrame {

    private JLabel lblNombreFooter;
    private JLabel lblYoutube;
    private JPanel panelSuperior;
    private JPanel panelInferior;
    private JPanel panelCentral;
    private JLabel lblClientes;
    private JLabel lblProductos;
    private JLabel lblFacturas;
    private JLabel lblUsuario;
    private JPanel panelBotoneras;
    private JPanel panelTabla;

    public VentanaPrincipal() {
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panelPrincipal = new JPanel();
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        panelPrincipal.setLayout(new BorderLayout(0, 0));

        panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(18, 18, 18));
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        lblYoutube = new JLabel("    YOUTUBE");
        lblYoutube.setForeground(new Color(255, 255, 255));
        lblYoutube.setFont(new Font("Lexend", Font.BOLD, 32));
        ImageIcon originalIcon = new ImageIcon(VentanaPrincipal.class.getResource("/resources/youtube.png"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
        lblYoutube.setIcon(new ImageIcon(scaledImage));
        panelSuperior.add(lblYoutube);

        panelInferior = new JPanel();
        panelInferior.setBackground(new Color(18, 18, 18));
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        lblNombreFooter = new JLabel("Emilio Fernández Gallardo");
        lblNombreFooter.setForeground(new Color(255, 255, 255));
        lblNombreFooter.setFont(new Font("Lexend", Font.BOLD, 16));
        panelInferior.add(lblNombreFooter);

        panelCentral = new JPanel();
        panelCentral.setBackground(new Color(18, 18, 18));
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelCentral.setLayout(new BorderLayout(0, 0));

        panelBotoneras = new JPanel();
        panelCentral.add(panelBotoneras, BorderLayout.NORTH);
        panelBotoneras.setLayout(new GridLayout(1, 4));

        // Etiquetas con eventos de mouse
        lblClientes = new JLabel("Clientes");
        lblClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblClientes.setBackground(new Color(255, 50, 50));
                lblClientes.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblClientes.setBackground(new Color(230, 0, 0));
                lblClientes.setForeground(Color.black);
            }
        });
        lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
        lblClientes.setForeground(Color.black);
        lblClientes.setBackground(new Color(230, 0, 0));
        lblClientes.setFont(new Font("Lexend", Font.BOLD, 18));
        lblClientes.setOpaque(true);

        lblProductos = new JLabel("Productos");
        lblProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblProductos.setBackground(new Color(255, 50, 50));
                lblProductos.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblProductos.setBackground(new Color(230, 0, 0));
                lblProductos.setForeground(Color.black);
            }
        });
        lblProductos.setHorizontalAlignment(SwingConstants.CENTER);
        lblProductos.setForeground(Color.black);
        lblProductos.setBackground(new Color(230, 0, 0));
        lblProductos.setFont(new Font("Lexend", Font.BOLD, 18));
        lblProductos.setOpaque(true);

        lblFacturas = new JLabel("Facturas");
        lblFacturas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblFacturas.setBackground(new Color(255, 50, 50));
                lblFacturas.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblFacturas.setBackground(new Color(230, 0, 0));
                lblFacturas.setForeground(Color.black);
            }
        });
        lblFacturas.setHorizontalAlignment(SwingConstants.CENTER);
        lblFacturas.setForeground(Color.black);
        lblFacturas.setBackground(new Color(230, 0, 0));
        lblFacturas.setFont(new Font("Lexend", Font.BOLD, 18));
        lblFacturas.setOpaque(true);

        lblUsuario = new JLabel("Usuario");
        lblUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblUsuario.setBackground(new Color(50, 50, 255));
                lblUsuario.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblUsuario.setBackground(new Color(0, 0, 230));
                lblUsuario.setForeground(Color.black);
            }
        });
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setForeground(Color.black);
        lblUsuario.setBackground(new Color(0, 0, 230));
        lblUsuario.setFont(new Font("Lexend", Font.BOLD, 18));
        lblUsuario.setOpaque(true);

        // Añadir las etiquetas al panelBotoneras
        panelBotoneras.add(lblClientes);
        panelBotoneras.add(lblProductos);
        panelBotoneras.add(lblFacturas);
        panelBotoneras.add(lblUsuario);

        // Panel para la tabla
        panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout()); // Usar un BorderLayout para el panelTabla
        panelCentral.add(panelTabla, BorderLayout.CENTER);

        // Crear la tabla de clientes
        TablaClientes tablaClientes = new TablaClientes(); 
        panelTabla.add(tablaClientes, BorderLayout.CENTER); // Añadimos directamente la tabla sin un JScrollPane extra
    }
}
