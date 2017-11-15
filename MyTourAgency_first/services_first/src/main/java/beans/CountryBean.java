package beans;

import org.hibernate.validator.constraints.NotEmpty;

public class CountryBean {	
	
	private Integer countryId;	
	
	@NotEmpty(message = "{NotEmpty}")	
	private String name;
	
	@NotEmpty(message = "{NotEmpty}")	
	private String description;
	
	public CountryBean() {
		
	}
	
	public CountryBean(Integer countryId, String name, String description) {
		super();
		this.countryId = countryId;
		this.name = name;
		this.description = description;
		
	}
	
	
	
		
	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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
	
	

	@Override
	public String toString() {
		return "CountryBean [countryId=" + countryId + ", name=" + name + ", description=" + description +"]";
	}
	

}
