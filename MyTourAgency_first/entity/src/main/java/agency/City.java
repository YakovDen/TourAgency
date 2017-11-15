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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * класс City с добавленными свойствами видов и типов тура
 * 
 * @author Den
 *
 */

@Entity
@Table(name = "city", catalog = "travelagency")
public class City implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public City(){
		
	}
	
	public City(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
		
	@Column(name = "name")
	private String name;
		
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "countryId")
	private Country country;
	
	@OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "city")
	private List<Tour> tours = new ArrayList<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}
		
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

			
}


