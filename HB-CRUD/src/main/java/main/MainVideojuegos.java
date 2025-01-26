package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import dao.BibliotecaDaoImpl;
import dao.JuegosDaoImpl;
import dao.UsuarioDaoImpl;
import model.Biblioteca;
import model.Juegos;
import model.Usuario;

public class MainVideojuegos {

	public static void main(String[] args) {
		// Abro sesión
		Session session = HibernateUtil.getSession();

		try {

			insertarUsuarios(session);
			insertarJuegos(session);
			insertarBibliotecas(session);

			actualizarUsuario(session);
			actualizarJuego(session);

			mostrarUsuarios(session);

			mostrarJuegos(session);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	private static void insertarUsuarios(Session session) throws Exception {
		System.out.println("Insertar usuarios");
		UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(session);

		// Crear usuarios
		Usuario usuario1 = new Usuario("usuario1", "password123", "usuario1@correo.com", "Usuario Uno",
				new SimpleDateFormat("yyyy-MM-dd").parse("1990-05-10"), new Date(), "recuperacion123");
		Usuario usuario2 = new Usuario("usuario2", "password456", "usuario2@correo.com", "Usuario Dos",
				new SimpleDateFormat("yyyy-MM-dd").parse("1992-03-15"), new Date(), "recuperacion456");

		// Insertar usuarios
		usuarioDao.insert(usuario1);
		usuarioDao.insert(usuario2);

		System.out.println("Usuarios insertados correctamente.");
	}

	private static void insertarJuegos(Session session) throws Exception {
		System.out.println("Insertar juegos");
		JuegosDaoImpl juegosDao = new JuegosDaoImpl(session);

		Juegos juego1 = new Juegos(0, "Fortnite");
		Juegos juego2 = new Juegos(0, "Rainbow Six Siege");

		juegosDao.insert(juego1);
		juegosDao.insert(juego2);

		System.out.println("Juegos insertados correctamente.");
	}

	private static void insertarBibliotecas(Session session) throws Exception {
		System.out.println("Insertar bibliotecas");
		Usuario usuario1 = session.get(Usuario.class, 1);
		Usuario usuario2 = session.get(Usuario.class, 2);
		Juegos juego1 = session.get(Juegos.class, 1);
		Juegos juego2 = session.get(Juegos.class, 2);

		Biblioteca biblioteca1 = new Biblioteca(usuario1, juego1, "Jugando", true, false, new Date());
		Biblioteca biblioteca2 = new Biblioteca(usuario2, juego2, "No Jugado", false, true, new Date());

		BibliotecaDaoImpl bibliotecaDao = new BibliotecaDaoImpl(session);

		bibliotecaDao.insert(biblioteca1);
		bibliotecaDao.insert(biblioteca2);

		System.out.println("Bibliotecas insertadas correctamente.");
	}

	private static void actualizarUsuario(Session session) throws Exception {
		System.out.println("Actualizar usuario");

		UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(session);
		Usuario usuario = session.get(Usuario.class, 1);
		if (usuario != null) {
			usuario.setEmail("nuevoemail@correo.com");
			usuario.setNombreCompleto("Usuario Actualizado");
			usuarioDao.update(usuario);
			System.out.println("Usuario actualizado correctamente.");
		} else {
			System.out.println("No se encontró el usuario para actualizar.");
		}
	}

	private static void actualizarJuego(Session session) throws Exception {
		System.out.println("Actualizar juego");

		JuegosDaoImpl juegosDao = new JuegosDaoImpl(session);
		Juegos juego = session.get(Juegos.class, 1);
		if (juego != null) {
			juego.setTitulo("Fortnite Actualizado");
			juegosDao.update(juego);
			System.out.println("Juego actualizado correctamente.");
		} else {
			System.out.println("No se encontró el juego para actualizar.");
		}
	}

	private static void mostrarUsuarios(Session session) {
		System.out.println("Mostrar todos los usuarios:");
		UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl(session);
		List<Usuario> listaUsuarios = usuarioDaoImpl.searchAll();
		for (Usuario u : listaUsuarios) {
			System.out.println(u.getNombreUsuario() + " - " + u.getEmail());
		}
		System.out.println("-------------------");
	}

	private static void mostrarJuegos(Session session) {
		System.out.println("Mostrar todos los juegos:");
		JuegosDaoImpl juegosDaoImpl = new JuegosDaoImpl(session);
		List<Juegos> listaJuegos = juegosDaoImpl.searchAll();
		for (Juegos j : listaJuegos) {
			System.out.println(j.getTitulo());
		}
		System.out.println("-------------------");
	}
}
