package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.CallableStatement;

import conexion.Conexion;

public class MainAppProcedimientos {

    private static final String PROCEDURE = "CALL Empleados_por_sexo(?)";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String respuesta = "";
        
        // Conectamos con la base de datos
        try (Connection conn = Conexion.conectar()) {
            CallableStatement st = conn.prepareCall(PROCEDURE);
            
            do {
                System.out.print("Para mostrar hombres (H) para mujeres (M): ");
                respuesta = sc.nextLine().toUpperCase();

                if (respuesta.equals("H")) {
                    st.setString(1, "Hombre");
                } else if (respuesta.equals("M")) {
                    st.setString(1, "Mujer");
                } else {
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    continue; // Salta al siguiente ciclo
                }

                // Ejecutar el procedimiento almacenado
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String cargo = rs.getString("cargo");
                        String tratamiento = rs.getString("tratamiento");

                        // Imprimir los datos en un formato atractivo y alineado
                        System.out.printf(
                                "\u001B[32mId: \u001B[0m%-5d | \u001B[33mNombre: \u001B[0m%-8s | \u001B[30mCargo: \u001B[0m%-26s | \u001B[36mTratamiento: \u001B[0m%-10s%n",
                                id, nombre, cargo, tratamiento);
                    }
                }

            } while (!respuesta.isBlank() && !respuesta.isEmpty() && !respuesta.equals("H") && !respuesta.equals("M"));

        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
