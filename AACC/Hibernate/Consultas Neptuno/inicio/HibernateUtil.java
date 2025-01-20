package inicio;

import java.io.BufferedReader;
import java.io.FileReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;

import models.Usuario;



public class HibernateUtil {

	private static final String CURSO = "2DAM";
	private static SessionFactory sessionFactory;
	private static Session session;

	/**
	 * Método que devuelve el objeto Session.
	 * 
	 * @return
	 *         <ul>
	 *         <li>Si la sesión no est� creada: la crea y la abre.</li>
	 *         <li>Si la sesión está creada: simplemente devuelve la sesión
	 *         abierta.</li>
	 *         </ul>
	 */
	public static Session getSession() {
		if (sessionFactory == null) {
			session = getSessionFactory().openSession();
		}

		return session;
	}

	/**
	 * Método que cierra el objeto Session de HibernateUtil
	 */
	public static void closeSession() {
		Session session = ThreadLocalSessionContext.unbind(sessionFactory);
		if (session != null) {
			session.close();
		}
		closeSessionFactory();
	}

	private static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		}
		return sessionFactory;
	}

	private static void closeSessionFactory() {
		if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
			sessionFactory.close();
		}
	}

	/**
	 * Método para añadir usuarios
	 * 
	 * @param ruta Ruta del archivo de usuarios
	 */
	public static void anyadirUsuario(String ruta) {
		Session session = null;
		Transaction tx = null;
		int count = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			// Obtenemos la sesion
			session = getSessionFactory().openSession();
			// Abrimos transacción
			tx = session.beginTransaction();

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(",");
				// Obtenemos los datos de los usuarios del CSV
				String apellido = datos[0].trim();
				String nombre = datos[1].trim();
				String user = CURSO + nombre.substring(0, 2) + apellido.substring(0, 2);
				// Creamos los usuarios
				Usuario usuario = new Usuario(nombre, apellido, user);
				// Guardamos usuario
				session.merge(usuario);
				count++;

				// Limpiamos si es par
				if (count % 2 == 0) {
					session.flush();
					session.clear();
				}
			}
			// Hacemos commit
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
	}

}
