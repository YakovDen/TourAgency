package serviceImpl;

import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agency.Country;
import beans.CountryBean;
import dao.ICountryDAO;
import daoImpl.base.exception.DaoException;
import service.EntityBeanConverter;
import service.ICountryService;
/** service methods for countries: 
 *  There is added:
 * 1) to extract a country(role touragent);
 * 2) to extract a list of countries(role touragent);
 * 3) add country(role touragent);
 * 4) delete country(role touragent);
 **/

@Service
@Transactional
public class CountryService implements ICountryService{
	final Logger log = Logger.getLogger(CountryService.class);
	
	@Autowired
	public ICountryDAO countryDaoI;
	
	@Autowired
	private EntityBeanConverter converter;
	
	@Override
	public Country get(int id_country) throws ServiceException {
		Country country = null;
		try {
			country = countryDaoI.get(id_country);
		} catch (DaoException e) {
			log.error("transaction getCountry ERROR \b" + e);

		}
		return country;
	}
	
	@Override
	public List<CountryBean> getAllCountry() {
		List<Country> countries = null;	
		List<CountryBean> countriesBean = null;
		
		try {
			countries = countryDaoI.getAllCountry();
		} catch (DaoException e) {
			log.error("transaction getAllCountry ERROR \b" + e);
		}
		
		countriesBean = converter.convertToBeanList(countries, CountryBean.class, "country");
		
		return countriesBean;
	}	
	
	@Override
	public void addCountry(CountryBean countryBean) throws ServiceException{
		
		Country entity;		
		entity = converter.convertToEntity(countryBean, Country.class, "country");			
		countryDaoI.addCountry(entity);				
		
	}
		
	@Override
	public void deleteCountry(int id) throws ServiceException {
		countryDaoI.deleteCountry(id);
	}

}
