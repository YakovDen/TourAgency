package dao;

import java.util.List;

import daoImpl.base.exception.DaoException;
import agency.User;

public interface IUserDAO extends DAO<User>{
	void InsertDiscount(int id, String discountBytour) throws DaoException;
	
	User getUserById(int id) throws DaoException;
	
	User getUser(String userName) throws DaoException;
	
	User loadUserByName(String userName) throws DaoException;
	
	List<User> getUserDiscount()throws DaoException;	
	
	void deleteDiscount(int id, String discountBytour);		

	void saveUserRoleUser(User userEntity, Integer role_id) throws DaoException;
	
	List<User> getAllUsers() throws DaoException;
	// в колонку discountByTour запись null после junit-теста
	void updateTest(String discountBytour, int id) throws DaoException;
	
	
    
}


