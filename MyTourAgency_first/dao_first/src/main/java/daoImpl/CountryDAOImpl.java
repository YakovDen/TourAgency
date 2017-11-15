package daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agency.Country;
import dao.ICountryDAO;
import daoImpl.base.BaseDAO;
import daoImpl.base.exception.DaoException;

/**
 * The class extends the standard methods of dao for info-object of country.
 * There is added:
 * 1) the method to extract a list of countries(role touragent);
 * 2) the metod add country(role touragent);
 */

@Repository
public class CountryDAOImpl extends BaseDAO<Country> implements ICountryDAO{
	final Logger log = Logger.getLogger(CountryDAOImpl.class);

	// список всех стран в роли touragent
	@SuppressWarnings("unchecked")
	@Override
	public List<Country> getAllCountry() throws DaoException {
		List<Country> countries = new ArrayList<>();
		try {
			Session session = currentSession();
			String hql = "FROM Country";
			Query query = session.createQuery(hql);
			countries = query.list();
		} catch (HibernateException e) {
			log.error("getAllCountry ERROR\n" + e);
			throw new DaoException(e);
		}

		return countries;
	}
	
	// добавление страны в роли touragent
	public List<Country> addCountry(Country country) {
		List<Country> addCountries = new ArrayList<Country>();
		addCountries.add(country);
		
		Session session = currentSession();		
		session.save(country);
		
		log.info("Save (commit):" + country);

		return addCountries;
	}
	
	// удаление страны в роли touragent
	public void deleteCountry(int id) {
		Country country = null;
		try {
			country = get(id);
			delete(country);
		} catch (DaoException e) {
			log.error("Error get or delete Country in Dao" + e);
		}

	}

}
