package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import agency.City;
import service.ICityService;
/**
 * класс CityConvert конвертирует добавленный объект сity,
 * при добавлении тура, в тип City, так-как изначально при
 * выборе объекта через выпадающий список его тип String 
 * 
 * @author Den
 *
 */
@Component
public class CityConverter implements Converter<String, City> {
	
	@Autowired
	private ICityService cityService;

	@Override
	public City convert(String id) {
		Integer idObj = new Integer(id);
		return cityService.get(idObj);
	}

}