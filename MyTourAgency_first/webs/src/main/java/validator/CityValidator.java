package validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import beans.CityBean;

import service.ICityService;

@Component
public class CityValidator implements Validator{
	
	@Autowired
	private ICityService cityService;

	public boolean supports(Class<?> clazz) {
		return CityBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		CityBean cityBean = (CityBean) obj;
		List<CityBean> allCity = new ArrayList<CityBean>();
		allCity = cityService.getAllCity();
		for (CityBean uniqeCity : allCity) {
			if (uniqeCity.getName().equals(cityBean.getName())) {				
				String[] args = {(""+uniqeCity.getName()+"")};				
				errors.rejectValue("name","label.validate.city.name", args,"");
				break;
			}

		}

	}	

}
