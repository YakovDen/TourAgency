package controllers;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import beans.UserBean;
import service.CustomSecurityProvider;
import service.IUserService;

import validator.UserValidator;


@Controller
public class LoginController {
	final Logger log = Logger.getLogger(LoginController.class);
		
	@Autowired
	private CustomSecurityProvider securityProvider;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@ModelAttribute(value="userForm")
	private UserBean getDefaultUserBean() {
		return new UserBean();
	}

	@RequestMapping(value = "/login.html")
	public ModelAndView login(HttpServletRequest request, HttpSession session) {
		String denided = request.getParameter("denided");
		if (denided == null) {
			session.removeAttribute("error");
			session.removeAttribute("exception");
		}
		
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login.html?logout";
	}

	@RequestMapping(value = "/access_denided.html", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView accessDeniedPage(HttpSession session) {
		ModelAndView model = new ModelAndView("redirect:/login.html?denided");

		session.setAttribute("error", "ACCESS DENIED. TRY AGAIN.<br> ДОСТУП ЗАКРЫТ. ПОПРОБУЙТЕ ЕЩЕ РАЗ.");
		Exception exception = (Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (exception != null) {
			session.setAttribute("exception", exception.getMessage());
		}
		
		return model;
	}
		
	@RequestMapping(value = "/custom-auth.html", method = RequestMethod.GET)
	ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response){
		ModelAndView model = new ModelAndView("custom-auth");
	    model.addObject("userForm", new UserBean());
	    return model;
	}
		
	@RequestMapping(value = "/custom-auth.html")
	public String registration(Model model, @ModelAttribute("userForm") @Valid UserBean userForm,
			BindingResult result) {

		// validation code
		userValidator.validate(userForm, result);

		// Check validation errors
		if (result.hasErrors()) {
			return "custom-auth";
		}
		Integer role_id = 3;
		userService.saveUserRoleUser(userForm, role_id);

		String userName = userForm.getUserName();
		model.addAttribute("username", userName);		

		Authentication authentication = securityProvider.authenticate(userName);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "redirect:welcome.html";
	}

}
