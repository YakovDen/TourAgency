package daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agency.KindOfTour;
import dao.IKindOfTourDAO;
import daoImpl.base.BaseDAO;
import daoImpl.base.exception.DaoException;

/**
 * The class extends the standard methods of dao for info-object of tour.
 */
@Repository
public class KindOfTourDAOImpl extends BaseDAO<KindOfTour> implements IKindOfTourDAO {
	final Logger log = Logger.getLogger(KindOfTourDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<KindOfTour> getAllKindOfTours() throws DaoException {
		List<KindOfTour> kinds = new ArrayList<>();
		try {
			Session session = currentSession();
			String hql = "FROM KindOfTour";
			Query query = session.createQuery(hql);
			kinds = query.list();
		} catch (HibernateException e) {
			log.error("getAllKindOfTours ERROR\n" + e);
			throw new DaoException(e);
		}

		return kinds;
	}
}
