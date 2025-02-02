package main;

import java.util.Date;
import org.hibernate.Session;

import dao.*;
import models.*;
import utils.Consultas;

public class MainApp {

    public static void main(String[] args) {
        // Abro sesión
        Session session = HibernateUtil.getSession();

        try {
        	crud(session);
        	System.out.println("---------------------------------");
        	System.out.println("---------------------------------");
        	System.out.println("           CONSULTAS");
        	// Consultas
        	Consultas.getCursosUsuarios();
        	Consultas.getUsuariosCursos();
        	Consultas.getCursosByUsuarios(2);
        	Consultas.getUsuariosByCurso(2);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

	private static void crud(Session session) {
		// Insertar un curso
		System.out.println("Insertar curso");
		CursoDaoImpl cursoDao = new CursoDaoImpl(session);
		Curso curso1 = new Curso();
		curso1.setNombreCurso("Matemáticas Avanzadas");
		curso1.setDescripcion("Curso de matemáticas");
		curso1.setFechaInicio(new Date());
		curso1.setFechaFin(new Date()); 
		cursoDao.insert(curso1);

		Curso curso2 = new Curso();
		curso2.setNombreCurso("Programación en Java");
		curso2.setDescripcion("Aprende a programar en Java desde cero");
		curso2.setFechaInicio(new Date());
		curso2.setFechaFin(new Date()); 
		cursoDao.insert(curso2);
		System.out.println("Cursos insertados con éxito.");
		System.out.println("-------------------");

		// Insertar usuarios
		System.out.println("Insertar usuarios");
		UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(session);

		Usuario usuario1 = new Usuario();
		usuario1.setNombre("Ana García");
		usuario1.setEmail("ana@example.com");
		usuario1.setFechaRegistro(new Date());
		usuario1.setTelefono("123456789");
		usuarioDao.insert(usuario1);

		Usuario usuario2 = new Usuario();
		usuario2.setNombre("Carlos Pérez");
		usuario2.setEmail("carlos@example.com");
		usuario2.setFechaRegistro(new Date());
		usuario2.setTelefono("987654321");
		usuarioDao.insert(usuario2);
		System.out.println("Usuarios insertados con éxito.");
		System.out.println("-------------------");

		// Inscribir usuarios en cursos
		System.out.println("Inscribir usuarios en cursos");
		InscripcionDaoImpl inscripcionDao = new InscripcionDaoImpl(session);

		Inscripcion inscripcion1 = new Inscripcion();
		inscripcion1.setUsuario(usuario1);
		inscripcion1.setCurso(curso1);
		inscripcion1.setFechaInscripcion(new Date());
		inscripcionDao.insert(inscripcion1);

		Inscripcion inscripcion2 = new Inscripcion();
		inscripcion2.setUsuario(usuario2);
		inscripcion2.setCurso(curso1);
		inscripcion2.setFechaInscripcion(new Date());
		inscripcionDao.insert(inscripcion2);

		Inscripcion inscripcion3 = new Inscripcion();
		inscripcion3.setUsuario(usuario1);
		inscripcion3.setCurso(curso2);
		inscripcion3.setFechaInscripcion(new Date());
		inscripcionDao.insert(inscripcion3);
		System.out.println("Inscripciones realizadas con éxito.");
		System.out.println("-------------------");

		// Mostrar todos los cursos
		System.out.println("Mostrar todos los cursos");
		for (Curso curso : cursoDao.searchAll()) {
		    System.out.println("Curso: " + curso.getNombreCurso() + ", Descripción: " + curso.getDescripcion());
		}
		System.out.println("-------------------");

		// Mostrar todos los usuarios
		System.out.println("Mostrar todos los usuarios");
		for (Usuario usuario : usuarioDao.searchAll()) {
		    System.out.println("Usuario: " + usuario.getNombre() + ", Email: " + usuario.getEmail());
		}
		System.out.println("-------------------");

		// Mostrar todas las inscripciones
		System.out.println("Mostrar todas las inscripciones");
		for (Inscripcion inscripcion : inscripcionDao.searchAll()) {
		    System.out.println("Inscripción: Usuario " + inscripcion.getUsuario().getNombre() +
		            " en curso " + inscripcion.getCurso().getNombreCurso() +
		            ", Fecha: " + inscripcion.getFechaInscripcion());
		}
		System.out.println("-------------------");
	}

	
}