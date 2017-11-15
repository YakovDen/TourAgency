package dao;

import java.util.List;

import agency.City;
import daoImpl.base.exception.DaoException;

public interface ICityDAO extends DAO<City>{

	// список всех городов в роли touragent
	List<City> getAllCity() throws DaoException;

	// добавление города для роли оператора
	public List<City> addCity(City city);
	
	// удаление города для роли оператора
	public void deleteCity(int id);

}
