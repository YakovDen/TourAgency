package beans;

import org.hibernate.validator.constraints.NotEmpty;

public class CityBean {	
		
		public CityBean(){
			
		}
		
		public CityBean(int cityId, String name, String description) {
			super();
			this.cityId = cityId;
			this.name = name;
			this.description = description;
		}
				
		private int cityId;		
		
		@NotEmpty(message = "{NotEmpty}")
		private String name;
		
		@NotEmpty(message = "{NotEmpty}")
		private String description;
		
		
		public int getCityId() {
			return cityId;
		}

		public void setCityId(int cityId) {
			this.cityId = cityId;
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
			return "CityBean [cityId=" + cityId + ", name=" + name + ", description=" + description + "]";
		}
		

}
