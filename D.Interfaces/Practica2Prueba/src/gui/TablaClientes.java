package gui;

import utils.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TablaClientes extends JPanel {

    private JTable table;

    public TablaClientes() {
        setLayout(new BorderLayout());

        // Crear el modelo de la tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Edad");
        model.addColumn("Provincia");
        model.addColumn("Email");

        // Crear la lista de clientes
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Juan", "Pérez", 30, "Buenos Aires", "juan@example.com"));
        clientes.add(new Cliente("Ana", "Gómez", 25, "Córdoba", "ana@example.com"));
        clientes.add(new Cliente("Luis", "Rodríguez", 40, "Mendoza", "luis@example.com"));
        clientes.add(new Cliente("María", "Fernández", 35, "Rosario", "maria@example.com"));
        clientes.add(new Cliente("Pedro", "Sánchez", 28, "Salta", "pedro@example.com"));
        clientes.add(new Cliente("Juan", "Pérez", 30, "Buenos Aires", "juan@example.com"));
        clientes.add(new Cliente("Ana", "Gómez", 25, "Córdoba", "ana@example.com"));
        clientes.add(new Cliente("Luis", "Rodríguez", 40, "Mendoza", "luis@example.com"));
        clientes.add(new Cliente("María", "Fernández", 35, "Rosario", "maria@example.com"));
        clientes.add(new Cliente("Pedro", "Sánchez", 28, "Salta", "pedro@example.com"));
        clientes.add(new Cliente("Juan", "Pérez", 30, "Buenos Aires", "juan@example.com"));
        clientes.add(new Cliente("Ana", "Gómez", 25, "Córdoba", "ana@example.com"));
        clientes.add(new Cliente("Luis", "Rodríguez", 40, "Mendoza", "luis@example.com"));
        clientes.add(new Cliente("María", "Fernández", 35, "Rosario", "maria@example.com"));
        clientes.add(new Cliente("Pedro", "Sánchez", 28, "Salta", "pedro@example.com"));
        clientes.add(new Cliente("Juan", "Pérez", 30, "Buenos Aires", "juan@example.com"));
        clientes.add(new Cliente("Ana", "Gómez", 25, "Córdoba", "ana@example.com"));
        clientes.add(new Cliente("Luis", "Rodríguez", 40, "Mendoza", "luis@example.com"));
        clientes.add(new Cliente("María", "Fernández", 35, "Rosario", "maria@example.com"));
        clientes.add(new Cliente("Pedro", "Sánchez", 28, "Salta", "pedro@example.com"));
        clientes.add(new Cliente("Juan", "Pérez", 30, "Buenos Aires", "juan@example.com"));
        clientes.add(new Cliente("Ana", "Gómez", 25, "Córdoba", "ana@example.com"));
        clientes.add(new Cliente("Luis", "Rodríguez", 40, "Mendoza", "luis@example.com"));
        clientes.add(new Cliente("María", "Fernández", 35, "Rosario", "maria@example.com"));
        clientes.add(new Cliente("Pedro", "Sánchez", 28, "Salta", "pedro@example.com"));
        clientes.add(new Cliente("Juan", "Pérez", 30, "Buenos Aires", "juan@example.com"));
        clientes.add(new Cliente("Ana", "Gómez", 25, "Córdoba", "ana@example.com"));
        clientes.add(new Cliente("Luis", "Rodríguez", 40, "Mendoza", "luis@example.com"));
        clientes.add(new Cliente("María", "Fernández", 35, "Rosario", "maria@example.com"));
        clientes.add(new Cliente("Pedro", "Sánchez", 28, "Salta", "pedro@example.com"));
        clientes.add(new Cliente("Juan", "Pérez", 30, "Buenos Aires", "juan@example.com"));
        clientes.add(new Cliente("Ana", "Gómez", 25, "Córdoba", "ana@example.com"));
        clientes.add(new Cliente("Luis", "Rodríguez", 40, "Mendoza", "luis@example.com"));
        clientes.add(new Cliente("María", "Fernández", 35, "Rosario", "maria@example.com"));
        clientes.add(new Cliente("Pedro", "Sánchez", 28, "Salta", "pedro@example.com"));

        // Agregar los clientes al modelo de la tabla
        for (Cliente cliente : clientes) {
            model.addRow(new Object[]{
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEdad(),
                cliente.getProvincia(),
                cliente.getEmail()
            });
        }

        // Crear la tabla con el modelo
        table = new JTable(model);
        table.setFont(new Font("Lexend", Font.PLAIN, 16)); // Cambiar el tamaño según sea necesario
        table.setFillsViewportHeight(true); // Hacer que la tabla llene el viewport

        // Permitir el ordenamiento en las columnas
        table.setAutoCreateRowSorter(true); // Activar el ordenamiento de filas

        // Crear y añadir el JScrollPane que contiene la tabla
        JScrollPane scrollPane = new JScrollPane(table);

        // Añadir el JScrollPane al panel
        add(scrollPane, BorderLayout.CENTER);
    }
}
