package creaUusarios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GeneradorUsuarios {
    private static final String FILE_NAME = "usuarios_registrados.csv";
    private static final int TOTAL_USERS = 5000;
    private static final String NOMBRE = "usuario"; // Nombre fijo
    private static final String APELLIDO = "apellido"; // Apellido fijo
    private static final String FECHA_NACIMIENTO = "01-mar-2005"; // Fecha de nacimiento fija
    private static final String PERFIL = "Cliente"; // Perfil fijo
    private static final String CONTRASENYA = "1234"; // Contraseña fija

    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 1; i <= TOTAL_USERS; i++) {
                // Generar el correo electrónico
                String email = NOMBRE + i + "@" + "gmail.com";
                // Escribir la línea en el archivo
                writer.write(NOMBRE + i + ";" + APELLIDO + i + ";" + FECHA_NACIMIENTO + ";" + PERFIL + ";" + email + ";" + CONTRASENYA);
                writer.newLine();
            }
            System.out.println("Se han generado " + TOTAL_USERS + " usuarios en el archivo " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

