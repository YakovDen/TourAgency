package serviceImpl.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import service.CustomSecurityProvider;
import service.IUserService;
import beans.RoleBean;
import beans.UserBean;
import beans.security.GrantedAuthorityBean;
import beans.security.LoggedAccountBean;



public class CustomAuthenticationProvider  implements AuthenticationProvider, CustomSecurityProvider {
		
	@Autowired
	private IUserService userService;

	public CustomAuthenticationProvider() {
	}
	
	//registration user
	public Authentication authenticate(String userName) throws AuthenticationException {
		
		LoggedAccountBean loggedAccount = createLoggedAccount(userName);		
		String password = loggedAccount.getPassword();	
		 
        if (!password.equals(loggedAccount.getPassword())) {
            throw new BadCredentialsException("Wrong password.<br> Неправильный пароль");
        }
                
        if(loggedAccount.isAccountNonLocked() == false){
			throw new LockedException ("User Account is locked!<br>Аккаунт заблокирован!", new Exception());
		}
             
		           
        return new UsernamePasswordAuthenticationToken(loggedAccount, password, loggedAccount.getAuthorities());
	}	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {		
		LoggedAccountBean loggedAccount = createLoggedAccount(authentication);
		String password = (String) authentication.getCredentials();			
		 
        if (!password.equals(loggedAccount.getPassword())) {
			throw new BadCredentialsException("Wrong password.<br> Неправильный пароль");
		}
               
		if (!loggedAccount.isAccountNonLocked()) {
			throw new LockedException("User Account is locked!<br>Аккаунт заблокирован!", new Exception());
		}
		        
		return new UsernamePasswordAuthenticationToken(loggedAccount, password, loggedAccount.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

	private LoggedAccountBean createLoggedAccount(Authentication authentication) {
		return createLoggedAccount(authentication.getName());
	}
	
	private LoggedAccountBean createLoggedAccount(String userName) {
		UserBean user = userService.getUserByUserName(userName);	
		List<RoleBean> roles = user.getRoles();	
		
		Collection<GrantedAuthorityBean> authorities = getUserAuthorities(roles);
		LoggedAccountBean loggedAccount = new LoggedAccountBean (user, authorities);

		return loggedAccount;
	}
	
	private Collection<GrantedAuthorityBean> getUserAuthorities(List<RoleBean> roles) {
		Collection<GrantedAuthorityBean> authorities = new ArrayList<>();

		for (RoleBean role : roles) {
			GrantedAuthorityBean bean = new GrantedAuthorityBean(role.getName());
			authorities.add(bean);
		}

		return authorities;
	}
}
