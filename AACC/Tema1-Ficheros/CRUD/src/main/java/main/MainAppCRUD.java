package main;

import java.sql.SQLException;
import java.util.List;
import dao.CityDao;
import dao.CityDaoImpl;
import models.City;

public class MainAppCRUD {

	public static void main(String[] args) {
//		testInsertDao();
//		testSelectAllDao();
		testSelectByIdDao();
//		testInsertDao();
//		testDeleteDao();

	}

	public static void testInsertDao() {
		CityDao dao = CityDaoImpl.getInstance();
		// Se pone el id automaticamente
		City ciudad = new City(0, "Marte", "ESP", "Si", 2);
		City ciudad2 = new City(0, "Malaga", "ESP", "Málaga", 2);
		City ciudad3 = new City(0, "Rincón de la Victoria", "ESP", "Axarquía", 2);

		City[] ciudades = {ciudad, ciudad2, ciudad3};
		int nInserted = 0;
		try {
			for (City city : ciudades) {
				dao.add(city);
				nInserted++;
			}
			
			
			System.out.println("El numero de registros insertados es: " + nInserted);

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
			City city = dao.getById(4087);
			
			System.out.println("\n" + city + "\n");
			
			city.setDistrict("Andalusia");
			
			dao.update(city);

			System.out.println(city);
			
			City city2 = dao.getById(4086);
			System.out.println("\n" + city2 + "\n");
			
			city2.setDistrict("Andalusia");
			dao.update(city2);
			System.out.println(city2);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void testDeleteDao() {
		CityDao dao = CityDaoImpl.getInstance();

		try {
			City city = dao.getById(4083);
			
			dao.delete(4083);

			System.out.println("Ciudad: " + city + " borrada de la base de datos");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}