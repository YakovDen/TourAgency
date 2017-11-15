package validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import beans.UserBean;
import service.IUserService;

@Component
public class UserValidator implements Validator{

	@Autowired
	private IUserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {		
		return UserBean.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {		
		UserBean userBean = (UserBean) obj;
		List<UserBean> allUser = new ArrayList<UserBean>();
		allUser = userService.getAllUsers();
		for (UserBean uniqeUser : allUser) {
			if (uniqeUser.getUserName().equals(userBean.getUserName()) ) {				
				String[] args = {(""+uniqeUser.getUserName()+"")};				
				errors.rejectValue("userName","label.validate.user.name", args,"");
				break;
			}
			else if(uniqeUser.getPassword().equals(userBean.getPassword())) {
				String[] args = {(""+uniqeUser.getPassword()+"")};				
				errors.rejectValue("password","label.validate.user.password", args,"");
				break;
			}
			
		}
	}

}
