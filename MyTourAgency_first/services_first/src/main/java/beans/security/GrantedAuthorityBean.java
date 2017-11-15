package beans.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityBean implements GrantedAuthority{
	private static final long serialVersionUID = 1L;
	
	private String role;

	public GrantedAuthorityBean(String role) {
		this.role = role;
	}

	public String getAuthority() {
		return role;
	}

	@Override
	public String toString() {
		return "GrantedAuthorityBean [role=" + role + "]";
	}
	
}
