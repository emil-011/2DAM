package main;

import java.sql.SQLException;
import java.util.List;

import dao.CityDao;
import dao.CityDaoImpl;
import models.City;

public class MainAppCRUD {

	public static void main(String[] args) {
		// testInsertDao();
		// testSelectAllDao();
		testSelectByIdDao();

	}

	public static void testInsertDao() {
		CityDao dao = CityDaoImpl.getInstance();
		City ciudad = new City("Marte", "ESP", "Si", 2);

		try {
			int n = dao.add(ciudad);
			System.out.println("El numero de registros insertados es: " + n);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void testSelectAllDao() {
		CityDao dao = CityDaoImpl.getInstance();

		try {
			List<City> listaCiudades = dao.getAll();

			if (listaCiudades.isEmpty()) {
				System.out.println("No hay ciudades registradas");
			} else {
				listaCiudades.forEach(System.out::println);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void testSelectByIdDao() {
		CityDao dao = CityDaoImpl.getInstance();

		try {
			City city = dao.getById(3333);

			System.out.println("\n" + city);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}