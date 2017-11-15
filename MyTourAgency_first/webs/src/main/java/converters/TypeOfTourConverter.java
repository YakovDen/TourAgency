package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import agency.TypeOfTour;
import service.ITypeOfTourService;
/**
 * класс TypeOfTourConverter конвертирует добавленный объект TypeOfTour,
 * при добавлении тура, в тип TypeOfTour, так-как изначально при
 * выборе объекта через выпадающий список его тип String 
 * 
 * @author Den
 *
 */
@Component
public class TypeOfTourConverter implements Converter<String, TypeOfTour> {

	@Autowired
	private ITypeOfTourService typeOfTourService;

	@Override
	public TypeOfTour convert(String id) {
		Integer idObj = new Integer(id);
		return typeOfTourService.get(idObj);
	}
}
