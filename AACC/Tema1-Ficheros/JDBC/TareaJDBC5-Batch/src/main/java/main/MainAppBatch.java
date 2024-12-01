package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import conexion.Conexion;

public class MainAppBatch {

	// Consulta para insertar usuarios
	private static final String INSERTAR_USUARIO = "INSERT INTO usuarios (nombre, clave) VALUES (?, ?)";
	private static final int BATCH_SIZE = 5; // Tamaño del batch

	public static void main(String[] args) throws Exception {
		// Mapa de usuarios con nombre como clave y contraseña como valor
		Map<String, String> usuarios = new HashMap<>();
		usuarios.put("lolito", "yepa");
		usuarios.put("laura", "abc123");
		usuarios.put("javier", "password1");
		usuarios.put("marta", "pass@word2");
		usuarios.put("alberto", "clave123");
		usuarios.put("cristina", "qwerty2024");
		usuarios.put("andres", "andres2024");
		usuarios.put("sandra", "sandra_789");
		usuarios.put("pedro", "pedro987");
		usuarios.put("lucia", "lucia_pass");
		usuarios.put("david", "david567");
		usuarios.put("eva", "evita_123");
		usuarios.put("pablo", "pablo2024");
		usuarios.put("silvia", "silvia999");
		usuarios.put("gonzalo", "gonzaloX");
		usuarios.put("angela", "angela_pass");
		usuarios.put("raul", "raul999");
		usuarios.put("sofia", "sofia@pass");
		usuarios.put("alejandro", "ale_pass");
		usuarios.put("veronica", "vero123");

		// Conectamos con la base de datos
		try (Connection conn = Conexion.conectar()) {
			try {
				conn.setAutoCommit(false);

				// Preparamos la sentencia
				PreparedStatement pst = conn.prepareStatement(INSERTAR_USUARIO);
				int count = 0;

				// Recorremos el mapa y añadimos cada inserción al batch
				for (Map.Entry<String, String> entry : usuarios.entrySet()) {
					pst.setString(1, entry.getKey());
					pst.setString(2, entry.getValue());
					pst.addBatch();
					count++;

					// Ejecutamos el batch cuando el contador alcanza el tamaño del batch (5)
					if (count % BATCH_SIZE == 0) {
						// Ejecutar el batch
						pst.executeBatch();
						pst.clearBatch();
					}
				}

				// Ejecutamos cualquier inserción restante que no haya sido ejecutada
				pst.executeBatch();
				pst.clearBatch();

				
				conn.commit();
				System.out.println("Usuarios insertados correctamente");

			} catch (Exception e) {
				System.err.println("Error al insertar los usuarios");
				conn.rollback();
			}
		} catch (SQLException e) {
			System.err.println("Error al conector con la base de datos " + e.getMessage());
		}
	}
}
