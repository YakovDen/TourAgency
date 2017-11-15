package serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agency.City;
import beans.CityBean;
import dao.ICityDAO;
import daoImpl.base.exception.DaoException;
import service.EntityBeanConverter;
import service.ICityService;

/** service methods for cities: 
 *  There is added:
 * 1) to extract a city(role touragent);
 * 2) to extract a list of cities(role touragent); 
 * 3) add city(role touragent); 
 * 4) delete city(role touragent);
 */

@Service
@Transactional
public class CityService implements ICityService {
	final Logger log = Logger.getLogger(CityService.class);
	
	@Autowired
	public ICityDAO cityDaoI;
	
	@Autowired
	private EntityBeanConverter converter;
	
	@Override
	public City get(int id) throws ServiceException {
		City city = null;
		try {
			city = cityDaoI.get(id);
		} catch (DaoException e) {
			log.error("transaction getCity ERROR \b" + e);

		}
		return city;
	}

	@Override
	public List<CityBean> getAllCity() {
		List<City> cities = null;
		List<CityBean> citiesBean = null;
		
		try {
			cities = cityDaoI.getAllCity();
		} catch (DaoException e) {
			log.error("transaction getAllCity ERROR \b" + e);
		}
		
		citiesBean = converter.convertToBeanList(cities,CityBean.class, "city");
		
		return citiesBean;
	}
	
	@Override
	public void addCity(CityBean cityBean) throws ServiceException{		
		
		City entity;		
		entity = converter.convertToEntity(cityBean, City.class, "city");			
		cityDaoI.addCity(entity);
		
	}
	
	@Override
	public void deleteCity(int id) throws ServiceException {
		cityDaoI.deleteCity(id);
	}


}
