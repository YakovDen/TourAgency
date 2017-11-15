package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import agency.Country;
import service.ICountryService;

/**
 * класс CountryConvert конвертирует добавленный объект сountry,
 * при добавлении тура, в тип Country, так-как изначально при
 * выборе объекта через выпадающий список его тип String 
 * 
 * @author Den
 *
 */
@Component
public class CountryConverter implements Converter<String, Country> {
	
	@Autowired
	private ICountryService countryService;

	@Override
	public Country convert(String id) {
		Integer idObj = new Integer(id);
		return countryService.get(idObj);
	}

}
