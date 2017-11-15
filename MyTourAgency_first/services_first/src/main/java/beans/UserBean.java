package beans;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

import agency.UserTour;


public class UserBean {
	
	private Integer id;
	
	@NotEmpty(message = "{NotEmpty}")
	private String firstName;

	@NotEmpty(message = "{NotEmpty}")
	private String lastName;

	@NotEmpty(message = "{NotEmpty}")
	private String userName;

	@NotEmpty(message = "{NotEmpty}")
	private String password;	
	
	private Boolean enabled;
	
	private String discountBytour;
	
	private String tourReserved;
	
	private List<RoleBean> roles;

	private Set<UserTour> usertours;
	
	
	public UserBean() {
		this.roles = new ArrayList<>();
		this.setUsertours(new HashSet<>());
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getDiscountBytour() {
		return this.discountBytour;
	}

	public void setDiscountBytour(String discountBytour) {
		this.discountBytour = discountBytour;
	}
		
	public String getTourReserved() {
		return this.tourReserved;
	}

	public void setTourReserved(String tourReserved) {
		this.tourReserved = tourReserved;
	}
	
	
	public List<RoleBean> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleBean> roles) {
		this.roles = roles;
	}
	
	public Set<UserTour> getUsertours() {
		return usertours;
	}

	public void setUsertours(Set<UserTour> usertours) {
		this.usertours = usertours;
	}

	public Date getCurrentDate() {
		return new Date();
	}
			
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());		
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;		
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", enabled=" + enabled + "]";
	}

	
}
