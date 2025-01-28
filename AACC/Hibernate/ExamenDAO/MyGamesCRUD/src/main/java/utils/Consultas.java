package utils;

import java.util.List;
import org.hibernate.query.Query;
import main.HibernateUtil;

public class Consultas {

	public static void getCursosUsuarios() {
		Query<Object[]> query = null;
		String hql = """
				    SELECT c.nombreCurso, u.nombre
				    FROM Inscripcion i
				    JOIN i.curso c
				    JOIN i.usuario u
				    ORDER BY c.nombreCurso, u.nombre
				""";

		query = HibernateUtil.getSession().createQuery(hql, Object[].class);
		List<Object[]> resultados = query.list();

		for (Object[] resultado : resultados) {
			System.out.println("Curso: " + resultado[0] + ", Usuario: " + resultado[1]);
		}
	}

	public static void getUsuariosCursos() {
		Query<Object[]> query = null;
		String hql = """
				    SELECT u.nombre, c.nombreCurso, i.fechaInscripcion
				    FROM Inscripcion i
				    JOIN i.usuario u
				    JOIN i.curso c
				    ORDER BY u.nombre, i.fechaInscripcion
				""";

		query = HibernateUtil.getSession().createQuery(hql, Object[].class);
		List<Object[]> resultados = query.list();

		for (Object[] resultado : resultados) {
			System.out.println(
					"Usuario: " + resultado[0] + ", Curso: " + resultado[1] + ", Fecha Inscripción: " + resultado[2]);
		}
	}

	public static void getCursosByUsuarios(int idUsuario) {
		Query<Object[]> query = null;
		String hql = """
				    SELECT c.nombreCurso, c.descripcion
				    FROM Inscripcion i
				    JOIN i.usuario u
				    JOIN i.curso c
				    WHERE u.idUsuario = :idUsuario
				""";

		query = HibernateUtil.getSession().createQuery(hql, Object[].class);
		query.setParameter("idUsuario", idUsuario);
		List<Object[]> resultados = query.list();

		for (Object[] resultado : resultados) {
			System.out.println("Nombre Curso: " + resultado[0] + ", Descripción: " + resultado[1]);
		}
	}

	public static void getUsuariosByCurso(int idCurso) {
		Query<Object[]> query = null;
		String hql = """
				    SELECT u.nombre, u.email
				    FROM Inscripcion i
				    JOIN i.usuario u
				    JOIN i.curso c
				    WHERE c.idCurso = :idCurso
				""";

		query = HibernateUtil.getSession().createQuery(hql, Object[].class);
		query.setParameter("idCurso", idCurso);
		List<Object[]> resultados = query.list();

		for (Object[] resultado : resultados) {
			System.out.println("Nombre del usuario: " + resultado[0] + ", Email: " + resultado[1]);
		}
	}

//	public static void getCursoMasPopular() {
//		String hql = """
//	        SELECT c.nombreCurso, c.descripcion
//	        FROM Curso c
//	    """;
//		
//		Query<Object[]> query = HibernateUtil.getSession().createQuery(hql, Object[].class);
//		List<Object[]> resultados = query.list();
//		
//		for (Object[] resultado : resultados) {
//			System.out.println("Curso: " + resultado[0] + ", Descripción " + resultado[1]);
//		}
//	}

}
