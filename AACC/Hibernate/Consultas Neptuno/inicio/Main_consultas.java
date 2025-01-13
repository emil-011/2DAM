package inicio;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.query.Query;
import modelo_neptuno.Categorias;
import modelo_neptuno.Pedidos;
import modelo_neptuno.Productos;

public class Main_consultas {

	public static void main(String[] args) {
		// Abro sesi�n
		Session session = HibernateUtil.getSession();
		
		Query query = null;

		try {
			// Inicio transacci�n
			session.getTransaction().begin();

			
			// 1.- Mostrar el nombre del producto y el nombre de la categor�a de todos los productos que contengan la letra Q en el nombre.		
			System.out.println("");
			System.out.println("1.- Mostrar el nombre del producto y el nombre de la categor�a de todos los productos que contengan la letra Q en el nombre.");

			// SIN JOIN
//			query = session.createQuery(
//					"SELECT p, p.categorias " + 
//					"FROM Productos p " + 
//					"WHERE p.producto LIKE '%q%' " + 
//					"ORDER BY categoria, producto");
			
			// CON JOIN hace lo mismo porque Categoria s�lo hay una
			query = session.createQuery(
					"SELECT p, p.categorias " + 
					"FROM Productos p JOIN p.categorias c " + 
					"WHERE p.producto LIKE '%q%' " + 
					"ORDER BY p.categorias.categoria, producto", Object[].class);
			
			List<Object[]> lista1 = query.list();

			for (Object[] elem : lista1) {
				Productos p = (Productos) elem[0];
				Categorias c = (Categorias) elem[1];
				
				System.out.println(c.getCategoria() + " | " + p.getProducto());
			}

			
			// 2.- Mostrar el n�mero de pedido y el pa�s del cliente de los pedidos de mayo del a�o 1997
			System.out.println("");
			System.out.println("2.- Mostrar el n�mero de pedido y el pa�s del cliente de los pedidos de mayo del a�o 1997");

			query = session.createQuery(
					"SELECT p.id, p.clientes.pais " + 
					"FROM Pedidos p " + 
					"WHERE YEAR(p.fechaPedido) = 1997 AND MONTH(p.fechaPedido) = 5", Object[].class);
					// "WHERE p.fechaPedido BETWEEN '1997-05-01' AND '1997-05-31'");
			
			List<Object[]> lista2 = query.list();

			for (Object[] elem : lista2) {
				System.out.println(elem[0] + " | " + elem[1]);
			}			

			// 3.- Mostrar fecha del pedido, cantidad y el nombre producto, y el c�digo del pedido para los c�digos de pedido 10285 o 10298.
			System.out.println("");
			System.out.println("3.- Mostrar fecha del pedido, cantidad y el nombre producto, y el c�digo del pedido para los c�digos de pedido 10285 o 10298.");

			query = session.createQuery(
					"SELECT p.fechaPedido, d.cantidad, d.productos.producto, p.id " + 
					"FROM Pedidos p JOIN p.detalleses d " +
					"WHERE p.id = 10285 OR p.id = 10298 ", Object[].class);
			
			List<Object[]> lista3 = query.list();

			for (Object[] elem : lista3) {
				System.out.println(elem[0] + " | " + elem[1] + " | " + elem[2] + " | " + elem[3]);
			}
			
			// 4.- �Cu�nto se factura cada mes? Mostrar el a�o, el mes y el total.
			System.out.println("");
			System.out.println("4.- �Cu�nto se factura cada mes? Mostrar el a�o, el mes y el total.");

			query = session.createQuery(
					"SELECT year(p.fechaPedido), month(p.fechaPedido), SUM(d.cantidad * d.precioUnidad * (1 - d.descuento)) " + 
					"FROM Pedidos p JOIN p.detalleses d " +
					"GROUP BY year(p.fechaPedido), month(p.fechaPedido) " + 
					"ORDER BY SUM(d.cantidad * d.precioUnidad * (1 - d.descuento)) ", Object[].class);
			
			List<Object[]> lista4 = query.list();

			for (Object[] elem : lista4) {
				System.out.println(elem[0] + " | " + elem[1] + " | " + elem[2] );
			}
			
			// 5.- Los pedidos que hizo la empleada Nancy
			System.out.println("");
			System.out.println("5.- Los pedidos que hizo la empleada Nancy");

			// CROSS JOIN. Ineficiente
			query = session.createQuery(
					"SELECT p " + 
					"FROM Pedidos p " + 
					"WHERE p.empleados.nombre = 'Nancy' ", Object[].class);
			
			// INNER JOIN. Eficiente
			query = session.createQuery(
					"SELECT p " + 
					"FROM Pedidos p JOIN p.empleados e " + 
					"WHERE e.nombre = 'Nancy' ", Object[].class);
			
			List<Pedidos> lista5 = query.list();

			for (Pedidos elem : lista5) {
				System.out.println(elem);
			}
			
			// 6.- Mostrar los pedidos de Anton (c�digo cliente).
			System.out.println("");
			System.out.println("6.- Mostrar los pedidos de Anton (c�digo cliente).");

			query = session.createQuery(
					"SELECT p " + 
					"FROM Pedidos p JOIN p.clientes c " + 
					"WHERE c.codigo = 'ANTON' ", Object[].class);
			
			List<Pedidos> lista6 = query.list();

			for (Pedidos elem : lista6) {
				System.out.println(elem);
			}
			
			// 7.- Cu�ntos productos hay de cada categor�a y el precio medio
			System.out.println("");
			System.out.println("7.- Cu�ntos productos hay de cada categor�a y el precio medio");

			query = session.createQuery(
					"SELECT c.categoria, COUNT(p.id), AVG(p.precioUnidad) " + 
					"FROM Productos p JOIN p.categorias c " + 
					"GROUP BY c.categoria", Object[].class);
			List<Object[]> lista7 = query.list();

			for (Object[] elem : lista7) {
				System.out.println(elem[0] + " | " + elem[1] + " | " + elem[2]);
			}			
			
			
			// 8.- Mostrar los pedidos que tienen productos en la categor�a 2 o 3
			System.out.println("");
			System.out.println("8.- Mostrar los pedidos que tienen productos en la categor�a 2 o 3");

			// EJEMPLO CON JOIN
			query = session.createQuery(
					"SELECT p " + 
					"FROM Pedidos p JOIN p.detalleses d JOIN d.productos pr JOIN pr.categorias c " + 
					"WHERE c.id IN (2,3)", Object[].class);
			
			// EJEMPLO SIN JOIN. Juego con detalles directamente. 
			// Problema de rendimiento. Detalles tiene m�s l�neas que pedido y la consulta es con cross join. No optimizado
//			query = session.createQuery(
//					"SELECT d " + 
//					"FROM Detalles d " +
//					"WHERE d.productos.categorias.id in (2,3)");
			
			
			List<Object> lista8 = query.list();

			for (Object elem : lista8) {
				System.out.println(elem);
			}
			
			
			// 9.- Clientes que pidieron queso en julio de 1996
			System.out.println("");
			System.out.println("9.- Clientes que pidieron queso en julio de 1996");

			query = session.createQuery(
					"SELECT DISTINCT c.empresa " +
					"FROM Clientes c JOIN c.pedidoses p JOIN p.detalleses d JOIN d.productos pr " + 
					"WHERE p.fechaPedido BETWEEN '1996-07-01' AND '1996-07-31' " +
					"AND pr.producto LIKE '%queso%' ", Object[].class);
			
			List<Object> lista9 = query.list();

			for (Object elem : lista9) {
				System.out.println(elem);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
