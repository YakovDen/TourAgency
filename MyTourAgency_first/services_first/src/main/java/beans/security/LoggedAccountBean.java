package beans.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import beans.RoleBean;
import beans.UserBean;

public class LoggedAccountBean implements UserDetails {

	private static final long serialVersionUID = 1L;

	private UserBean userBean;

	private Collection<GrantedAuthorityBean> authorities;

	public LoggedAccountBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public LoggedAccountBean(UserBean userBean, Collection<GrantedAuthorityBean> authorities) {
		this.userBean = userBean;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public Integer getUserId() {
		return userBean.getId();
	}
	
	public String getFirstName() {
		return userBean.getFirstName();
	}
	
	public String getLastName() {
		return userBean.getLastName();
	}		
	
	@Override
	public String getUsername() {
		return userBean.getUserName();
	}
	
	@Override
	public String getPassword() {
		return userBean.getPassword();
	}

	public String getDiscountBytour() {
		return userBean.getDiscountBytour();
	}
	
	public String getTourReserved() {
		return userBean.getTourReserved();
	}
	
	public List<RoleBean> getRoles() {
		return userBean.getRoles();
	}
	
	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	@Override
	public boolean isEnabled() {		
		return false;
	}

}
