package gui;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;

public class FormularioAltaCliente extends JPanel {
    private JLabel lblAnyadir;
    private JTextField textApellidos;
    private JTextField textNombre;
    private JTextField textField_2;

    public FormularioAltaCliente() {
        setLayout(new BorderLayout(0, 0));
        
        JPanel panelInferior = new JPanel();
        add(panelInferior, BorderLayout.SOUTH);
        panelInferior.setLayout(new BorderLayout(0, 0));
        
        lblAnyadir = new JLabel("Añadir");
        lblAnyadir.setFont(new Font("Lexend", Font.BOLD, 18));
        lblAnyadir.setHorizontalAlignment(SwingConstants.CENTER);
        lblAnyadir.setBackground(new Color(128, 255, 128));
        lblAnyadir.setOpaque(true);
        panelInferior.add(lblAnyadir);
        
        JPanel panelIzquierda = new JPanel();
        panelInferior.add(panelIzquierda, BorderLayout.WEST);
        
        JLabel espacioIzquierda = new JLabel("                                   ");
        espacioIzquierda.setFont(new Font("Tahoma", Font.PLAIN, 36));
        panelIzquierda.add(espacioIzquierda);
        
        JPanel panelDerecha = new JPanel();
        panelInferior.add(panelDerecha, BorderLayout.EAST);
        
        JLabel espacioIzquierda_1 = new JLabel("                                   ");
        espacioIzquierda_1.setFont(new Font("Tahoma", Font.PLAIN, 36));
        panelDerecha.add(espacioIzquierda_1);
        
        JLabel lblNewLabel_2 = new JLabel(" ");
        panelInferior.add(lblNewLabel_2, BorderLayout.SOUTH);
        
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(188, 177, 177));
        add(panelSuperior, BorderLayout.NORTH);
        
        JLabel lblTitulo = new JLabel("Alta Clientes");
        lblTitulo.setForeground(new Color(8, 8, 8));
        lblTitulo.setFont(new Font("Lexend", Font.BOLD, 24));
        panelSuperior.add(lblTitulo);
        
        // Cambiar el layout a GridLayout con 4 filas y 1 columna
        JPanel panelPrincipal = new JPanel();
        add(panelPrincipal, BorderLayout.CENTER);
        panelPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JPanel panelApellidos = new JPanel();
        panelPrincipal.add(panelApellidos);
        
        JLabel lblEspacioIzquierda_2 = new JLabel("                                                          ");
        lblEspacioIzquierda_2.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelApellidos.add(lblEspacioIzquierda_2);
        
        JLabel lblNombre_1 = new JLabel("Nombre:     ");
        lblNombre_1.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelApellidos.add(lblNombre_1);
        
        textNombre = new JTextField();
        textNombre.setFont(new Font("Lexend", Font.PLAIN, 18));
        textNombre.setColumns(10);
        panelApellidos.add(textNombre);
        
        JLabel lblEspacioIzquierda_1_1 = new JLabel("                                                          ");
        lblEspacioIzquierda_1_1.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelApellidos.add(lblEspacioIzquierda_1_1);
        
        JLabel lblNewLabel = new JLabel(" ");
        panelPrincipal.add(lblNewLabel);
        
        JPanel panelNombre = new JPanel();
        panelPrincipal.add(panelNombre);
        
        JLabel lblEspacioIzquierda = new JLabel("                                                          ");
        lblEspacioIzquierda.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelNombre.add(lblEspacioIzquierda);
        
        JLabel lblApellido = new JLabel("Apellidos:   ");
        lblApellido.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelNombre.add(lblApellido);
        
        textApellidos = new JTextField();
        textApellidos.setFont(new Font("Lexend", Font.PLAIN, 18));
        textApellidos.setColumns(10);
        panelNombre.add(textApellidos);
        
        JLabel lblEspacioIzquierda_1 = new JLabel("                                                          ");
        lblEspacioIzquierda_1.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelNombre.add(lblEspacioIzquierda_1);
        
        JLabel lblNewLabel_1 = new JLabel(" ");
        panelPrincipal.add(lblNewLabel_1);
        
        JPanel panelEmail = new JPanel();
        panelPrincipal.add(panelEmail);
        
        JLabel lblEspacioIzquierda_3 = new JLabel("                                                          ");
        lblEspacioIzquierda_3.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelEmail.add(lblEspacioIzquierda_3);
        
        JLabel lblEmail = new JLabel("Email:         ");
        lblEmail.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelEmail.add(lblEmail);
        
        textField_2 = new JTextField();
        textField_2.setFont(new Font("Lexend", Font.PLAIN, 18));
        textField_2.setColumns(10);
        panelEmail.add(textField_2);
        
        JLabel lblEspacioIzquierda_1_2 = new JLabel("                                                          ");
        lblEspacioIzquierda_1_2.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelEmail.add(lblEspacioIzquierda_1_2);
        
        JLabel lblNewLabel_1_1 = new JLabel(" ");
        panelPrincipal.add(lblNewLabel_1_1);
        
        JPanel panelEmail_1 = new JPanel();
        panelPrincipal.add(panelEmail_1);
        
        JLabel lblEspacioIzquierda_3_1 = new JLabel("                                                          ");
        lblEspacioIzquierda_3_1.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelEmail_1.add(lblEspacioIzquierda_3_1);
        
        JLabel lblEmail_1 = new JLabel("Provincia:           ");
        lblEmail_1.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelEmail_1.add(lblEmail_1);
        
        // Arreglo con las provincias de Andalucía
        String[] provinciasAndalucia = { "Almería", "Cádiz", "Córdoba", "Granada", "Huelva", "Jaén", "Málaga", "Sevilla" };
        
        JComboBox<String> comboBox = new JComboBox<>(provinciasAndalucia);
        comboBox.setFont(new Font("Lexend", Font.PLAIN, 18));
        panelEmail_1.add(comboBox);
        
        JLabel lblEspacioIzquierda_1_2_2 = new JLabel("                                                          ");
        lblEspacioIzquierda_1_2_2.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelEmail_1.add(lblEspacioIzquierda_1_2_2);
        
        JPanel panelDate = new JPanel();
        panelPrincipal.add(panelDate);
        panelDate.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel lblEspacioIzquierda_3_1_1 = new JLabel("                                                    ");
        lblEspacioIzquierda_3_1_1.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelDate.add(lblEspacioIzquierda_3_1_1);
        
        JLabel lblBirthDate = new JLabel("Birth Date:               ");
        lblBirthDate.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelDate.add(lblBirthDate);
        
        JDateChooser dateChooser = new JDateChooser();
        panelDate.add(dateChooser);
        
        JLabel lblEspacioIzquierda_1_2_1_1 = new JLabel("                                                     ");
        lblEspacioIzquierda_1_2_1_1.setFont(new Font("Lexend", Font.PLAIN, 24));
        panelDate.add(lblEspacioIzquierda_1_2_1_1);
    }
}
