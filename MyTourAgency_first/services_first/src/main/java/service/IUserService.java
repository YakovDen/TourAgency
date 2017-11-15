package service;
import java.util.List;

import agency.User;
import beans.UserBean;


public interface IUserService {	
		//для туроператора выбор user		
		UserBean getUserById(int id);
		
		UserBean getUserBean(String userName);
		
		UserBean getUserByUserName(String userName);
		
		List<User> getUserDiscount();
		
		//в роли турагента, вставка скидки на тур для user
		void InsertDiscount(int id, String discountBytour);
		
		void DeleteDiscount(int id, String discountBytour);				
		
		void saveUserRoleUser(UserBean userForm, Integer role_id);
		
		List<UserBean> getAllUsers();

}
