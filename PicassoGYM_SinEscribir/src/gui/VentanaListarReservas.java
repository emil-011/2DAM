package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import utils.Reserva;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class VentanaListarReservas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel banner;
    private JLabel lblListaDeReservas;
    private JScrollPane scrollPane;
    private JTable tablaReservas;
    private JPanel footer;
    private JLabel lblApellido;
    private JTextField textApellido;
    private JLabel lblFiltrar;

    // Lista de reservas
    private List<Reserva> reservas;

    public static void main(String[] args) {
        VentanaListarReservas ventana = new VentanaListarReservas();
        ventana.setVisible(true);
    }

    public VentanaListarReservas() {
        // Inicializar la lista de reservas
        reservas = new ArrayList<>();
        // Aquí agregar reservas de ejemplo (nombre, apellidos, clase, turno)
        reservas.add(new Reserva("Juan", "Pérez", "Matemáticas", "Mañana"));
        reservas.add(new Reserva("Ana", "García", "Historia", "Tarde"));
        // Puedes agregar más reservas aquí para pruebas

        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(650, 250, 700, 502);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        banner = new JPanel();
        contentPane.add(banner, BorderLayout.NORTH);
        banner.setLayout(new GridLayout(1, 1, 0, 0));

        lblListaDeReservas = new JLabel("Lista de Reservas");
        lblListaDeReservas.setOpaque(true);
        lblListaDeReservas.setHorizontalAlignment(SwingConstants.CENTER);
        lblListaDeReservas.setForeground(Color.WHITE);
        lblListaDeReservas.setFont(new Font("Verdana", Font.BOLD, 24));
        lblListaDeReservas.setBackground(new Color(41, 191, 235));
        banner.add(lblListaDeReservas);

        scrollPane = new JScrollPane();
        scrollPane.setBackground(new Color(255, 255, 255));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tablaReservas = new JTable();
        tablaReservas.setFont(new Font("Verdana", Font.PLAIN, 12));
        cargarReservas();

        scrollPane.setViewportView(tablaReservas);

        footer = new JPanel();
        contentPane.add(footer, BorderLayout.SOUTH);

        lblApellido = new JLabel("Apellido");
        lblApellido.setFont(new Font("Verdana", Font.BOLD, 14));

        textApellido = new JTextField();
        textApellido.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtrar(); 
                }
            }
        });
        textApellido.setFont(new Font("Verdana", Font.PLAIN, 12));
        textApellido.setPreferredSize(new Dimension(100, 30));
        textApellido.setColumns(10);

        lblFiltrar = new JLabel("Filtrar");
        lblFiltrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblFiltrar.setBackground(new Color(41, 100, 200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblFiltrar.setBackground(new Color(41, 191, 235));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                filtrar();
            }
        });
        lblFiltrar.setOpaque(true);
        lblFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
        lblFiltrar.setForeground(Color.WHITE);
        lblFiltrar.setFont(new Font("Verdana", Font.BOLD, 20));
        lblFiltrar.setBackground(new Color(41, 191, 235));
        
        GroupLayout gl_footer = new GroupLayout(footer);
        gl_footer.setHorizontalGroup(gl_footer.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_footer.createSequentialGroup()
                .addGap(156)
                .addComponent(lblApellido)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(textApellido, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
                .addGap(26)
                .addComponent(lblFiltrar, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                .addGap(93)));
        gl_footer.setVerticalGroup(gl_footer.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, gl_footer.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(gl_footer.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(textApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFiltrar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                .addGap(26)));
        footer.setLayout(gl_footer);
    }

    protected void filtrar() {
        String apellidoBuscado = textApellido.getText().trim().toLowerCase();

        if (apellidoBuscado.isBlank()) {
            cargarReservas();
        } else {
            DefaultTableModel modeloFiltrado = new DefaultTableModel();
            modeloFiltrado.addColumn("Nombre");
            modeloFiltrado.addColumn("Apellidos");
            modeloFiltrado.addColumn("Clase");
            modeloFiltrado.addColumn("Turno");

            for (Reserva reserva : reservas) {
                if (reserva.getApellidos().toLowerCase().startsWith(apellidoBuscado)) {
                    modeloFiltrado.addRow(new Object[]{reserva.getNombre(), reserva.getApellidos(), reserva.getClase(), reserva.getTurno()});
                }
            }
            tablaReservas.setModel(modeloFiltrado);
        }
    }

    protected void cargarReservas() {
        DefaultTableModel tablaModel = new DefaultTableModel();
        tablaModel.addColumn("Nombre");
        tablaModel.addColumn("Apellidos");
        tablaModel.addColumn("Clase");
        tablaModel.addColumn("Turno");

        for (Reserva reserva : reservas) {
            tablaModel.addRow(new Object[]{reserva.getNombre(), reserva.getApellidos(), reserva.getClase(), reserva.getTurno()});
        }
        
        tablaReservas.setModel(tablaModel);
        tablaReservas.setAutoCreateRowSorter(true);
    }
}
