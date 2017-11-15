package validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import beans.CountryBean;
import service.ICountryService;

@Component
public class CountryValidator implements Validator {

	@Autowired
	private ICountryService countryService;

	public boolean supports(Class<?> clazz) {
		return CountryBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		CountryBean countryBean = (CountryBean) obj;
		List<CountryBean> allCountry = new ArrayList<CountryBean>();
		allCountry = countryService.getAllCountry();
		for (CountryBean uniqeCountry : allCountry) {
			if (uniqeCountry.getName().equals(countryBean.getName())) {				
				String[] args = {(""+uniqeCountry.getName()+"")};				
				errors.rejectValue("name","label.validate.country.name", args,"");
				break;
			}

		}

	}	

}
