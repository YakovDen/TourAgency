package service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import agency.Country;
import beans.CountryBean;

public interface ICountryService {
	//извлечение страны
	Country get(int id_country);

	// список всех стран в роли touragent	
	List<CountryBean> getAllCountry();

	// добавление страны для роли оператора	
	public void addCountry(CountryBean countryBean) throws ServiceException;

	// удаление страны для роли оператора
	public void deleteCountry(int id);

}
