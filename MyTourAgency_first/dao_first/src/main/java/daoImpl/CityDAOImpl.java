package daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agency.City;
import agency.Country;
import dao.ICityDAO;
import daoImpl.base.BaseDAO;
import daoImpl.base.exception.DaoException;

/**
 * The class extends the standard methods of dao for info-object of city.
 * There is added:
 * 1) the method to extract a list of cities(role touragent);
 */

@Repository
public class CityDAOImpl extends BaseDAO<City> implements ICityDAO{	
	final Logger log = Logger.getLogger(CountryDAOImpl.class);

	// список всех городов в роли touragent
	@SuppressWarnings("unchecked")
	@Override
	public List<City> getAllCity() throws DaoException {
		List<City> cities = new ArrayList<>();
		try {
			Session session = currentSession();
			String hql = "FROM City";
			Query query = session.createQuery(hql);
			cities = query.list();
		} catch (HibernateException e) {
			log.error("getAllCity ERROR\n" + e);
			throw new DaoException(e);
		}

		return cities;
	}

	// добавление города в роли touragent
	public List<City> addCity(City city) {
		List<City> addCities = new ArrayList<City>();
		addCities.add(city);

		Session session = currentSession();
		session.save(city);

		log.info("Save (commit):" + city);

		return addCities;
	}
	
	// удаление города в роли touragent
		public void deleteCity(int id) {
			City city = null;
			try {
				city = get(id);
				delete(city);
			} catch (DaoException e) {
				log.error("Error get or delete City in Dao" + e);
			}

		}
}
