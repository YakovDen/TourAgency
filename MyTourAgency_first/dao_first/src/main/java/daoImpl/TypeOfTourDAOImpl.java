package daoImpl;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agency.TypeOfTour;
import dao.ITypeOfTourDAO;
import daoImpl.base.BaseDAO;
import daoImpl.base.exception.DaoException;
/**
 * The class extends the standard methods of dao
 * for type of tour
 */
@Repository
public class TypeOfTourDAOImpl extends BaseDAO<TypeOfTour> implements ITypeOfTourDAO{	
	final Logger log = Logger.getLogger(TypeOfTourDAOImpl.class);

	@SuppressWarnings({ "unchecked" })
	public List<TypeOfTour> getAllTypeOfTours() throws DaoException {

		List<TypeOfTour> types = new ArrayList<>();
		try {
			Session session = currentSession();
			String hql = "FROM TypeOfTour";
			Query query = session.createQuery(hql);
			types = query.list();
		} catch (HibernateException e) {
			log.error("getAllTypesOfTour ERROR\n" + e);
			throw new DaoException(e);
		}

		return types;
	}

}
