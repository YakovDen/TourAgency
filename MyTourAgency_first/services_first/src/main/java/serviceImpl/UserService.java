package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import agency.User;

import beans.UserBean;
import dao.IUserDAO;
import daoImpl.base.exception.DaoException;
import service.EntityBeanConverter;
import service.IUserService;

/**
 * service methods class for user:
 * 1) for choice a user from the database by id;
 * 2) for choice a user from the database(registration);
 * 3) for a authentication of concrete user(createLoggedAccount);
 * 4) save user whith role - "user"(registration);  
 * 5) for extract the user whith a discount(role touragent);
 * 6) sets a discount for client(role touragent);
 * 7) delete(set NULL) a discount for client(role touragent);
 * 8) get list all users(during registration) in UserValidator;
 */

@Service
@Transactional
public class UserService implements IUserService {
	final Logger log = Logger.getLogger(UserService.class);	

	@Autowired
	IUserDAO userDaoI;
	
	@Autowired
	private EntityBeanConverter converter;

	int authen = 0;
		
	public UserBean getUserById(int id) {
		User user = null;
		UserBean bean = null;
		try {
			user = userDaoI.getUserById(id);
			bean = converter.convertToBean(user, UserBean.class, "user");
		} catch (DaoException e) {
			log.error("Error getUserById" + e);
		}
		return bean;
	}

	public UserBean getUserBean(String userName) {
		User user = null;
		UserBean bean = null;		
		try {
			user = userDaoI.getUser(userName);
			bean = converter.convertToBean(user, UserBean.class, "user");			
		} catch (DaoException e) {
			log.error("Error getUserBeanId" + e);
		}
		return bean;
	}
	
	public UserBean getUserByUserName(String userName){
		User user = null;
		UserBean bean = null;
		try {
			user = userDaoI.loadUserByName(userName);
			bean = converter.convertToBean(user, UserBean.class, "user");
		} catch (DaoException e) {
			log.error("Error getUserByUserName" + e);
		}
		return bean;
	}
		
	public List<User> getUserDiscount() {
		List<User> userDiscount = null;
		try {
			userDiscount = userDaoI.getUserDiscount();
		} catch (DaoException e) {
			log.error("Error getUserDiscount()" + e);
		}
		return userDiscount;
	}

	public void InsertDiscount(int id, String discountBytour) {
		try {
			userDaoI.InsertDiscount(id, discountBytour);
		} catch (DaoException e) {
			log.error("Error InsertDiscount()" + e);
		}

	}

	public void DeleteDiscount(int id, String discountBytour){
		userDaoI.deleteDiscount(id,discountBytour);
		
	}
	
	@Override
	public void saveUserRoleUser(UserBean userForm, Integer role_id) {
		User userEntity = converter.convertToEntity(userForm, User.class, "user");
		try {			
			userDaoI.saveUserRoleUser(userEntity, role_id);
		} catch (DaoException e) {
			log.error("Error saveUser" + e);
		}
	}
	
	public List<UserBean> getAllUsers() {
		//List<User> AllUsers = null;
		List<User> users = new ArrayList<>();
		List<UserBean> usersBean = new ArrayList<>();
		try {
			//AllUsers = userDaoI.getAllUsers();
			users = userDaoI.getAllUsers();
			usersBean.addAll(converter.convertToBeanList(users, UserBean.class, "user"));
			
		} catch (DaoException e) {
			log.error("Error getAllUsers()" + e);
		}

		//return AllUsers;
		return usersBean;
	}
	
}
