package daoImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agency.Tour;
import agency.User;
import agency.UserTour;
import dao.ITourDAO;
import daoImpl.base.BaseDAO;
import daoImpl.base.exception.DaoException;
/**
 * The class extends the standard methods of dao
 * for info-object of tour. There is added:
 * 1) the method to extract a list of tours(role touragent);
 * 2) the metod add tour(role touragent);
 * 3) the metod delete tour(role touragent);
 * 4) the method to extract a list of tours(role client);
 * 5) to extract a list of hot tours(role client);
 * 6) to extract a list of hot tours,pagination(role client);
 * 7) the metod to reservation of tour(role client);
 * 8) the metod to pay/off pay of tour(role client);
 * 9) the metod to write  payd of tour in table usertour(role client);
 * 10) the metod to choice of current user(role client);
 * 11) the metod to choice of tours from  desired date
 * 12)to extract a list of desired tours,pagination(role client); 
 */
@Repository
public class TourDAOImpl extends BaseDAO<Tour> implements ITourDAO {
	final Logger log = Logger.getLogger(TourDAOImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;

	// список всех туров в роли touragent
	@SuppressWarnings("unchecked")
	public List<Tour> getAllTours() throws DaoException {		
		List<Tour> tours = new ArrayList<Tour>();

			Session session = currentSession();

			String hql = "FROM Tour";
			Query query = session.createQuery(hql);
			tours = query.list();
			for (Object result : tours) {
				log.info(result);
			}
			session.flush();
			session.clear();	

		return tours;
	}

	// добавление тура в роли touragent
	public List<Tour> addTour(Tour myTour) {
		List<Tour> tour = new ArrayList<Tour>();
		Session session = currentSession();
		// Tour myTour = new Tour(dateOfBeginning, dateEnd, name,numberOfNights, cost, discount, typeOfTour, kindOfTour, country);
		session.save(myTour);
		log.info("Save (commit):" + myTour);

		return tour;
	}

	// удаление тура в роли touragent
	public void deleteTour(int id) {
		Tour tour = null;
		try {
			tour = get(id);
			delete(tour);
		} catch (DaoException e) {
			log.error("Error get or delete Tour in Dao" + e);
		}

	}

	// метод вывода списка туров для бронирования заказчиком в роли-заказчик
	@SuppressWarnings("unchecked")
	public List<Tour> getAllKindTour(int page, int recordsPerPage) throws DaoException {				
		
		List<Tour> toursCustomer = new ArrayList<Tour>();
		try {			
			Session session = currentSession();
			Criteria criteria = session.createCriteria(Tour.class);
			int s = (page-1)*recordsPerPage;	
			criteria.setFirstResult(s);
			criteria.setMaxResults(recordsPerPage);
			criteria.addOrder(Order.asc("id_tour"));
			toursCustomer = criteria.list();
			log.info("toursCustomer (commit):" + toursCustomer);
			//session.flush();			
			} catch (HibernateException e) {
			log.error("Error extract List of tourCustomer for role user by Criteria in Dao "
					+ "getAllTours ERROR\n" + e);
			throw new DaoException(e);

		}

		return toursCustomer;
	}
	
	//список горящих туров
		@SuppressWarnings("unchecked")
		public List<Tour> getAllHotTour() throws DaoException {
			List<Tour> hotTour = new ArrayList<Tour>();
			//String var = "Горящий";
			Integer var = 1;
			try {			
				Session session = currentSession();
				/*SQLQuery sql =  session.createSQLQuery("SELECT ts.id_tour as id_tour, ts.name as name, c.name as country, ts.dateOfBeginning as dateOfBeginning,"
						+ "ts.dateEnd as dateEnd, ts.numberOfNights as numberOfNights, ts.cost as cost FROM travelagency.tours ts left join travelagency.typeoftour tt "
						+ "on ts.id_typeOfTour=tt.id_typeOfTour left join travelagency.country c on ts.id_country=c.id_country"
						+ " where tt.typeOfTour = '"+var+"'");				
				
				sql.addScalar("id_tour", StandardBasicTypes.INTEGER);
				sql.addScalar("name", StandardBasicTypes.STRING);
				sql.addScalar("country", StandardBasicTypes.STRING);
				sql.addScalar("dateOfBeginning", StandardBasicTypes.DATE);
				sql.addScalar("dateEnd", StandardBasicTypes.DATE);
				sql.addScalar("numberOfNights", StandardBasicTypes.INTEGER);
				sql.addScalar("cost", StandardBasicTypes.INTEGER);
				sql.setCacheable(true);
				sql.setResultTransformer(Transformers.aliasToBean(Tour.class));
				hotTour = sql.list();*/	
				
				String hql = "FROM Tour WHERE id_typeOfTour = :paramName ORDER BY id_tour ASC";
				Query query = session.createQuery(hql);
				query.setParameter("paramName", var);
				hotTour = query.list();				
				
				log.info("UserHotTour (commit):" + hotTour);
			} catch (HibernateException e) {
				log.error("Error user hot Tour in Dao" + e);
			}

			return hotTour;
			
		}
	
	//пагинация списка горящих туров
	@SuppressWarnings("unchecked")
	public List<Tour> getPaginationHotTour(int page, int recordsPerPage) throws DaoException {			
			
		List<Tour> hotToursCustomer = new ArrayList<Tour>();
		
		Integer var = 1;
		try {			
			Session session = currentSession();				
			
			String hql = "FROM Tour WHERE id_typeOfTour = :paramName ORDER BY id_tour ASC";
			Query query = session.createQuery(hql);
			query.setParameter("paramName", var);
			
			int s = (page-1)*recordsPerPage;
			query.setFirstResult(s);
			query.setMaxResults(recordsPerPage);
			hotToursCustomer = query.list();	
						
			log.info("hotToursCustomer (commit):" + hotToursCustomer);						
			} catch (HibernateException e) {
			log.error("Error extract List of hotTourCustomer for role user in Dao "
					+ "getPaginationHotTour ERROR\n" + e);
			throw new DaoException(e);

		}

		return hotToursCustomer;
	}	
	// в роли заказчик добавление записи об выборе в users и выбранного тура в
	// таблице сопряжения в usertour
	public void getReservationTour(int id_tour, int id_user) throws DaoException {
		UserTour usertour = new UserTour();

		try {

			Session session = currentSession();

			Tour tour = (Tour) session.get(Tour.class, id_tour);
			User user = (User) session.get(User.class, id_user);
			usertour.setTour(tour);
			usertour.setUser(user);
			tour.getUserTours().add(usertour);

			session.saveOrUpdate(usertour);

			String var = "Тур ' " + id_tour + " ' выбран";
			Query query = session.createQuery("update User set tourReserved = :tourReserved where id_user = :id_user");
			query.setParameter("tourReserved", var);
			query.setParameter("id_user", id_user);

			int result = query.executeUpdate();

			session.flush();
			session.clear();

			log.info("Reserved (commit):" + result);

		} catch (HibernateException e) {
			log.error("Error reserved Tour in Dao" + e);

		}

	}
		
	// в роли заказчик добавление записи об оплате в таблицу сопряжения в
	// usertour	
	public void payTour(int idPaid, boolean isPressed) throws DaoException {

		try {			
			Session session = currentSession();

			String hql = "UPDATE UserTour ut set isPaid =:isPaid WHERE ut.idUT = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", idPaid);
			query.setParameter("isPaid", isPressed);
			query.executeUpdate();

			session.flush();
			session.clear();

			log.info("Reserved (commit): Paid");

		} catch (HibernateException e) {
			log.error("Error reserved Tour in Dao" + e);
		}

	}

	// результирующая запись с выбранным туром и информацией об оплате для роли
	// заказчик
	public Set<UserTour> getUserReservAllTours(int id_user) throws DaoException {
		User user = new User();
		try {			
			Session session = currentSession();
			session.clear();
			
			String hql = "FROM User WHERE id_user = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id_user);
			user = (User) query.uniqueResult();

			session.flush();			
			
			log.info("UserReservTour (commit):" + user);
		} catch (HibernateException e) {
			log.error("Error user reserv Tour in Dao" + e);
		}

		return user.GetTours();

	}

	// выбор текущего клиента, оплачивающего тур для роли-заказчик
	@SuppressWarnings("unchecked")
	public List<Tour> getUserReservTour(int id_user) throws DaoException {
		User user = new User();
		List<Tour> tourForClient = new ArrayList<Tour>();
		try {			
			Session session = currentSession();

			String hql = "FROM User WHERE id_user = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id_user);
			tourForClient = query.list();
			user = (User) query.uniqueResult();

			log.info("UserReservTour (commit):" + user);
		} catch (HibernateException e) {
			log.error("Error user reserv Tour in Dao" + e);
		}
		return tourForClient;
	}
	
	// выбор списка туров, согласно желаемым датам
	@SuppressWarnings("unused")
	public List<Tour> filterTour(Date dateBeginFrom, Date dateBeginBefore) throws DaoException {
		List<Tour> allTours = new ArrayList<Tour>();
		List<Tour> desiredTours = new ArrayList<Tour>();
		Date first;
		Date second;

		try {
			Session session = currentSession();

			allTours = getAllTours();
			for (Tour result : allTours) {
				first = result.getDateOfBeginning();
				second = result.getDateEnd();

				if (first.after(dateBeginFrom) && second.before(dateBeginBefore)) {
					desiredTours.add(result);
				}

			}

			log.info("filterTour (commit):" + desiredTours);
		} catch (HibernateException e) {
			log.error("Error  filterTour in Dao" + e);
		}
		return desiredTours;
	}
		
		// метод пагинации списка туров,выранных за желаемый период в роли-заказчик
		@SuppressWarnings("unchecked")
		public List<Tour> getPaginationDesiredTour(int page, int recordsPerPage, Date dateBeginFrom, Date dateBeginBefore) throws DaoException {				
			
			List<Tour> desiredToursDuringDate = new ArrayList<Tour>();
			try {			
				Session session = currentSession();
				Criteria criteria = session.createCriteria(Tour.class).add(Restrictions.between("dateOfBeginning", dateBeginFrom, dateBeginBefore));
				int s = (page-1)*recordsPerPage;	
				criteria.setFirstResult(s);
				criteria.setMaxResults(recordsPerPage);
				criteria.addOrder(Order.asc("id_tour"));
				desiredToursDuringDate = criteria.list();
				log.info("desiredToursForDate (commit):" + desiredToursDuringDate);
				//session.flush();			
				} catch (HibernateException e) {
				log.error("Error extract List of desiredToursDuringDate for role user by Criteria in Dao "
						+ "getPaginationDesiredTour ERROR\n" + e);
				throw new DaoException(e);

			}

			return desiredToursDuringDate;
		}
			
}
