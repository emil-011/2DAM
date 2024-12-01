package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import gui.VentanaLogin;
import models.Cita;
import models.Usuario;

public class TallerPicassoMainApp {

    public static List<Usuario> listaUsuarios;
    public static List<Cita> listaCitas;

    public static void main(String[] args) throws ParseException {

        // Inicializar las listas de usuarios y citas
        listaUsuarios = new ArrayList<>();
        listaCitas = new ArrayList<>();

        // Crear algunos usuarios
        Usuario usuario3 = new Usuario("Emilio", "Fernandez", "123456789", "e", "1234", true, listaCitas);

        listaUsuarios.add(usuario3);

        // Crear citas
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Cita cita1 = new Cita("1234ABC", "Toyota", "Corolla", dateFormat.parse("05/12/2024"), "pendiente", 0.0, "");
        Cita cita2 = new Cita("43252ABC", "Mitshubishi", "Lancer", dateFormat.parse("05/12/2024"), "pendiente", 0.0, "");
        Cita cita3 = new Cita("1244CBC", "Seat", "Leon", dateFormat.parse("05/12/2024"), "pendiente", 0.0, "");

        listaCitas.add(cita1);
        listaCitas.add(cita2);
        listaCitas.add(cita3);

        // Mostrar la ventana de login
        VentanaLogin lg = new VentanaLogin();
        lg.setVisible(true);
        
    }
}