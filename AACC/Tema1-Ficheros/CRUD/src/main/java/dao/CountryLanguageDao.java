package dao;

import java.sql.SQLException;
import java.util.List;

import models.CountryLanguage;

public interface CountryLanguageDao {

	int add(CountryLanguage countryLanguage) throws SQLException;

	CountryLanguage getById(int id) throws SQLException;

	List<CountryLanguage> getAll() throws SQLException;

	int update(CountryLanguage countryLanguage) throws SQLException;

	void delete(int id) throws SQLException;

}
