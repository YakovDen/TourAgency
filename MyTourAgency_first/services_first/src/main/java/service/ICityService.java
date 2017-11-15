package service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import agency.City;
import beans.CityBean;

public interface ICityService {
	//извлечение города
	City get(int id);

	// список всех городов в роли touragent
	List<CityBean> getAllCity();	

	// добавление города для роли оператора
	public void addCity(CityBean cityBean) throws ServiceException;
	
	//удаление города для роли оператора
	public void deleteCity(int id);

}
