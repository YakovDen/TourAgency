package dao;

import java.util.List;

import agency.Country;
import daoImpl.base.exception.DaoException;

public interface ICountryDAO extends DAO<Country> {
	// список всех стран в роли touragent
	List<Country> getAllCountry() throws DaoException;

	// добавление страны для роли оператора
	public List<Country> addCountry(Country country);

	// удаление страны для роли оператора
	public void deleteCountry(int id);

}
