package dao;

import java.sql.SQLException;
import java.util.List;

import models.City;
import models.Country;

public class CountryDaoImpl implements CountryDao {

	private static CountryDaoImpl instance;

	static {
		instance = new CountryDaoImpl();
	}

	private CountryDaoImpl() {

	}

	public static CountryDaoImpl getInstance() {
		return instance;
	}

	@Override
	public int add(Country country) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Country getById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Country> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Country city) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

}
