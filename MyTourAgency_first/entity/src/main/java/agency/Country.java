package agency;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * класс Country с добавленными свойствами 
 * 
 * @author Den 
 *
 */
@Entity
@Table(name = "country", catalog = "travelagency")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_country")
	private Integer id_country;
	
	//@NotEmpty	
	@Column(name = "name")			
	private String name;
	
	//@NotEmpty	
	@Column(name = "description")
	private String description;
	
	@OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "country")
	private List<Tour> tours = new ArrayList<>();
	
	@OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "country")
	private List<City> cities = new ArrayList<>();
	
	
	public Country(){
		
	}
	
	public Country(Integer id_country, String name, String description) {
		super();
		this.id_country = id_country;		
		this.name = name;
		this.description = description;
		
	}
		

	public Integer getId_country() {
		return id_country;
	}

	public void setId_country(Integer id_country) {
		this.id_country = id_country;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}
	
	
	@Override
	public String toString() {
		return "Country [id_country=" + id_country + ", name=" + name + ", description=" + description + "]";
	}
	
			
}
