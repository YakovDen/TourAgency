package advices;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import beans.UserBean;
import beans.security.LoggedAccountBean;


// Spring-аннотация будет создавать Model-атрибуты ПЕРЕД ЛЮБЫМ ЗАПРОСОМ
// На какой-бы контроллер (и метод) эти запросы не поступали.
// Иначе - это ГЛОБАЛЬНЫЕ MODEL-аттрибуты.
@ControllerAdvice
public class UserInfoAdvice {

	@ModelAttribute("userInfo")
    public LoggedAccountBean populateUserInfo() {		
		return getUser();    }

	public Integer getUserId() {
		return getUser().getUserId();}
	
	private Authentication getAuthentication() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
		
		
	private LoggedAccountBean getUser() {
		Authentication authentication = getAuthentication();
		Object principal = authentication.getPrincipal();
		
		if (principal instanceof String) {
			UserBean userBean = new UserBean();
			userBean.setFirstName("Anonimus User");			
			
			LoggedAccountBean bean = new LoggedAccountBean(userBean);
			return bean;
		}
		
		return ((LoggedAccountBean) principal);		
	}
}
