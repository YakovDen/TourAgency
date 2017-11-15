package daoImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import agency.Role;
import agency.User;


import dao.IUserDAO;
import daoImpl.base.BaseDAO;
import daoImpl.base.exception.DaoException;
/**
* The class extends the standard DAO methods for user. 
* It adds methods:
* 1) it a method for choice a user from the database by id;
* 2) for choice a user from the database(registration); 
* 3) it a method for a authentication of concrete user(createLoggedAccount);
* 4) it a method save user whith role - "user"(registration); 
* 5) it a method for extract the user whith a discount(role touragent);
* 6) it a method sets a discount for client(role touragent);
* 7) it a method delete a discount for client(role touragent);
* 8) it a method get list all users(during registration) in UserValidator;
* 9) it a method sets a null in column discountByTour for user №7 (junit-test).
* */
@Repository
public class UserDAOImpl extends BaseDAO<User> implements IUserDAO {
	final Logger log = Logger.getLogger(UserDAOImpl.class);
	
	public UserDAOImpl() {		
		
	}
	
	private static UserDAOImpl thisClientDAOImpl;

	public static UserDAOImpl getClientDAOImpl() {
		if (thisClientDAOImpl == null)
			thisClientDAOImpl = new UserDAOImpl();
		return thisClientDAOImpl;
	}

	// выбор залогиненного пользователя по Id
	public User getUserById(int id) throws DaoException {	
		User user = new User();
		try {
			Session session = currentSession();
			String hql = "FROM User WHERE id_user = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);

			user = (User) query.uniqueResult();

		} catch (HibernateException e) {
			log.error("getUserById ERROR\n" + e);
			throw new DaoException(e);
		}
		return user;
	}

	// выбор пользователя после регистрации по username
	public User getUser(String userName) throws DaoException {		
		User user = null;
		try {
			Session session = currentSession();
			String hql = "FROM User WHERE username = :username";
			Query query = session.createQuery(hql);
			query.setParameter("username", userName);

			user = (User) query.uniqueResult();

		} catch (HibernateException e) {
			log.error("getUserId ERROR\n" + e);
			throw new DaoException(e);
		}
		return user;
	}
	
	// выбор залогиненного пользователя
	public User loadUserByName(String userName) throws DaoException{		
				User user = new User();
				try {
					Session session = currentSession();
					String hql = "FROM User WHERE username = :userName";
					Query query = session.createQuery(hql);
					query.setParameter("userName", userName);
					query.uniqueResult();
					user = (User) query.uniqueResult();

				} catch (HibernateException e) {
					log.error("loadUserByName ERROR\n" + e);
					throw new DaoException(e);
				}
				return user;
	}
	
	// регистрация user с ролью "regular_user"
	public void saveUserRoleUser(User userEntity, Integer role_id) throws DaoException {
		Set<Role> roleList = new HashSet<Role>();

		try {
			Session session = currentSession();
			String hql = "FROM Role WHERE role_id = :role_id";
			Query query = session.createQuery(hql);
			query.setParameter("role_id", role_id);
			roleList.add((Role) query.uniqueResult());

			userEntity.setRoles(roleList);

			session.save(userEntity);

		} catch (HibernateException e) {
			log.error("saveUserRoleUser(User userEntity, Integer role_id) ERROR\n" + e);
			throw new DaoException(e);
		}
	}
	
	// вывод usera со скидкой
	@SuppressWarnings({ "unchecked" })
	public List<User> getUserDiscount() throws DaoException {

		List<User> clients = new ArrayList<User>();
		try {
			Session session = currentSession();
			String hql = "FROM User";
			Query query = session.createQuery(hql);
			clients = query.list();
			for (Object result : clients) {
				log.info(result);
			}
		} catch (HibernateException e) {
			log.error("getUserDiscount ERROR\n" + e);
			throw new DaoException(e);
		}
		return clients;
	}

	//установка туроператором скидки пользователю
	public void InsertDiscount(int id, String discountBytour) throws DaoException {
		Session session = currentSession();
		try {
			User user = (User) session.get(User.class, id);
			user.setDiscountBytour(discountBytour);
			session.saveOrUpdate(user);
		} catch (HibernateException e) {
			log.error("getInsertDiscount ERROR\n" + e);
			throw new DaoException(e);
		}

	}
	
	//удаление скидки для клиента в роли турагент
	public void deleteDiscount(int id, String discountBytour){
		Session session = currentSession();
				
        Query query = session
                .createQuery("update User set discountBytour= :discountBytour where id= :id");
        query.setParameter("discountBytour", null);
        query.setLong("id", id);		      
        query.executeUpdate();
	}
	
	//формирование списка всех пользователей для junit-test
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() throws DaoException {
		List<User> clients = new ArrayList<User>();
		try {
			Session session = currentSession();
			String hql = "FROM User";
			Query query = session.createQuery(hql);
			clients = query.list();
			for (Object result : clients) {
				log.info(result);
			}
		} catch (HibernateException e) {
			log.error("getAllUsers ERROR\n" + e);
			throw new DaoException(e);
		}
		return clients;
	}
	/**
	 * обнуление колонки discountByTour для тестового юзера №7 для junit-теста
	 */
	public void updateTest(String discountBytour, int id) throws DaoException {
		try {
			Session session = currentSession();
			String hql = "UPDATE User set discountBytour = :discount WHERE id_user = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			query.setParameter("discount", discountBytour);
			query.executeUpdate();

			session.flush();
			session.clear();
		} catch (HibernateException e) {
			log.error("TestSave ERROR\n" + e);
			throw new DaoException(e);
		}

	}
}
