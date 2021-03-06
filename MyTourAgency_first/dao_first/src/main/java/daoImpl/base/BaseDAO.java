package daoImpl.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.DAO;
import daoImpl.base.exception.DaoException;

/**
 * Class describes standard method used for persisting operations witn DB in
 * hibernate session.
 * 
 * @param <T>
 *            - type of persistent class
 */

@Repository
public class BaseDAO<T> implements DAO<T> {
	private static Logger log = Logger.getLogger(BaseDAO.class);
	private Transaction transaction = null;

	@Autowired
	private SessionFactory sessionFactory;

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void delete(T t) throws DaoException {

		try {
			Session session = currentSession();
			session.delete(t);
			if (t == null) {
				log.info("Hibernate: Tour Details not available for Tour ID:" + t);
			}

			log.info("Delete:" + t);
		} catch (HibernateException e) {
			log.error("Error delete in Dao" + e);
			transaction.rollback();
			throw new DaoException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public T load(Serializable id) throws DaoException {
		log.info("Load class by id:" + id);
		T t = null;
		try {
			Session session = currentSession();
			transaction = session.beginTransaction();
			t = (T) session.load(getPersistentClass(), id);
			log.info("load() clazz:" + t);
			session.isDirty();
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Error load() " + getPersistentClass() + " in Dao" + e);
			transaction.rollback();
			throw new DaoException(e);
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	public T get(Serializable id) throws DaoException {
		log.info("Get class by id:" + id);
		T t = null;
		try {
			Session session = currentSession();
			t = (T) session.get(getPersistentClass(), id);
			log.info("get() clazz:" + t);
		} catch (HibernateException e) {
			log.error("Error get " + getPersistentClass() + " in Dao" + e);
			throw new DaoException(e);
		}
		return t;
	}

	// Save object, using a generated id
	/*public void save(T t) throws DaoException {
		try {
			Session session = currentSession();
			transaction = session.beginTransaction();
			int personId = (Integer) session.save(t);
			System.out.println("Tour id created: " + personId);
			log.info("save(t):" + t);
			transaction.commit();
			log.info("Save (commit):" + t);
		} catch (HibernateException e) {
			log.error("Error save Tour in Dao" + e);
			transaction.rollback();
			throw new DaoException(e);
		}
	}*/

	public void save(T t) throws DaoException {
		try {
			Session session = currentSession();			
			session.save(t);			
			log.info("Save (commit):" + t);
		} catch (HibernateException e) {
			log.error("Error save in Dao" + e);
			transaction.rollback();
			throw new DaoException(e);
		}
	}
	
	// Save isn't configured to be retained through the INCREMENT
	public void saveId(T t) throws DaoException {

		try {
			Session session = currentSession();
			transaction = session.beginTransaction();
			Integer id = (Integer) session.save(t);
			System.out.println("Generated Identifier:" + id);
			log.info("save(id):" + id);
			transaction.commit();
			log.info("Save (commit):" + t);
		} catch (HibernateException e) {
			log.error("Error save in Dao" + e);
			transaction.rollback();
			throw new DaoException(e);
		}
	}

	/*public void saveOrUpdate(T t) throws DaoException {
		try {
			Session session = currentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(t);
			log.info("saveOrUpdate(t):" + t);
			transaction.commit();
			log.info("Save or update (commit):" + t);
		} catch (HibernateException e) {
			log.error("Error save or update in Dao" + e);
			transaction.rollback();
			throw new DaoException(e);
		}

	}*/
	public void saveOrUpdate(T t) throws DaoException {
		try {
			Session session = currentSession();			
			session.saveOrUpdate(t);
			log.info("saveOrUpdate(t):" + t);		
		} catch (HibernateException e) {
			log.error("Error save or update in Dao" + e);
			transaction.rollback();
			throw new DaoException(e);
		}

	}

	@SuppressWarnings("unchecked")
	private Class<T> getPersistentClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}
