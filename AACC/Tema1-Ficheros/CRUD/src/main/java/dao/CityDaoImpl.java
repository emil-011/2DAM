package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import models.City;

public class CityDaoImpl implements CityDao {

	private static final String INSERT_QUERY = "INSERT INTO city (name, countryCode, district, population) VALUES (?,?,?,?)";
	private static final String SELECT_BY_ID = """
			SELECT * FROM city WHERE id = ?
			""";
	private static final String SELECT_ALL = "SELECT * FROM city";
	private static final String UPDATE = """
			UPDATE city
			SET name = ?, countryCode = ?,
			district = ?, population = ?
			WHERE id = ?
			""";
	private static final String DELETE = "DELETE FROM city WHERE id = ?";
	private static CityDaoImpl instance;

	static {
		instance = new CityDaoImpl();
	}

	private CityDaoImpl() {

	}

	public static CityDaoImpl getInstance() {
		return instance;
	}

	@Override
	public int add(City city) throws SQLException {
		int result;
		try (Connection conn = Conexion.conectar(); PreparedStatement pst = conn.prepareStatement(INSERT_QUERY)) {

			pst.setString(1, city.getName());
			pst.setString(2, city.getCountryCode());
			pst.setString(3, city.getDistrict());
			pst.setInt(4, city.getPopulation());

			result = pst.executeUpdate();
		}

		return result;
	}

	@Override
	public City getById(int id) throws SQLException {
		City result = null;
		try (Connection conn = Conexion.conectar(); PreparedStatement pst = conn.prepareStatement(SELECT_BY_ID)) {
			pst.setInt(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
//					int id_city = rs.getInt("id");
					String name = rs.getString("name");
					String countryCode = rs.getString("countryCode");
					String district = rs.getString("district");
					int population = rs.getInt("population");

					result = new City(id, name, countryCode, district, population);
				}
			}
		}

		return result;
	}

	@Override
	public List<City> getAll() throws SQLException {
		List<City> resultado = new ArrayList<>();
		try (Connection conn = Conexion.conectar(); PreparedStatement pst = conn.prepareStatement(SELECT_ALL)) {

			ResultSet rs = pst.executeQuery();

			City city;
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String countryCode = rs.getString("countryCode");
				String district = rs.getString("district");
				int population = rs.getInt("population");

				city = new City(id, name, countryCode, district, population);

				resultado.add(city);
			}
		}

		return resultado;
	}

	@Override
	public int update(City city) throws SQLException {
		int result = 0;
		try (Connection conn = Conexion.conectar(); PreparedStatement pst = conn.prepareStatement(UPDATE)) {
			pst.setString(1, city.getName());
			pst.setString(2, city.getCountryCode());
			pst.setString(3, city.getDistrict());
			pst.setInt(4, city.getPopulation());
			pst.setInt(5, city.getId());
			
			result = pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public void delete(int id) throws SQLException {
		try (Connection conn = Conexion.conectar(); PreparedStatement pst = conn.prepareStatement(DELETE)) {
			pst.setInt(1, id);
			pst.executeUpdate();
		}
	}

}
