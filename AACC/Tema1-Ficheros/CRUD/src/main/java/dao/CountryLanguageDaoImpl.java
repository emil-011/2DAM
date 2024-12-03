package dao;

import java.sql.SQLException;
import java.util.List;

import models.CountryLanguage;


public class CountryLanguageDaoImpl implements CountryLanguageDao {

	private static CountryLanguageDaoImpl instance;

	static {
		instance = new CountryLanguageDaoImpl();
	}

	private CountryLanguageDaoImpl() {

	}

	public static CountryLanguageDaoImpl getInstance() {
		return instance;
	}

	@Override
	public int add(CountryLanguage countryLanguage) throws SQLException {
		return 0;
	}

	@Override
	public CountryLanguage getById(int id) throws SQLException {
		return null;
	}

	@Override
	public List<CountryLanguage> getAll() throws SQLException {
		return null;
	}

	@Override
	public int update(CountryLanguage countryLanguage) throws SQLException {
		return 0;
	}

	@Override
	public void delete(int id) throws SQLException {

	}

}
