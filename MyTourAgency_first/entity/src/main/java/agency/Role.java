package agency;

import java.io.Serializable;
import java.util.HashSet;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

import javax.persistence.Table;

@Entity
@Table(name = "roles", catalog = "travelagency")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer role_id;	
	
	@Column(name = "role_name")
	private String name;
	
	@Column(name = "role_desc")
	private String description;		
	
	
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles")
	private Set<User> users = new HashSet<User>();

	public Role() {
		super();

	}
	
	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	} 
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String decription) {
		this.description = decription;
	} 
	
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", name=" + name + ", description=" + description + ", users=" + users
				+ "]";
	}
	
}
