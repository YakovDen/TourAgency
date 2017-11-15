package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import agency.KindOfTour;
import service.IKindOfTourService;
/**
 * класс KindOfTourConverter конвертирует добавленный объект KindOfTour,
 * при добавлении тура, в тип KindOfTour, так-как изначально при
 * выборе объекта через выпадающий список его тип String 
 * 
 * @author Den
 *
 */
@Component
public class KindOfTourConverter implements Converter<String, KindOfTour> {

	@Autowired
	private IKindOfTourService kindOfTourService;

	@Override
	public KindOfTour convert(String id) {
		Integer idObj = new Integer(id);
		return kindOfTourService.get(idObj);
	}
}
