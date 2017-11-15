package serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agency.TypeOfTour;
import dao.ITypeOfTourDAO;
import daoImpl.base.exception.DaoException;
import service.ITypeOfTourService;

@Service
@Transactional
public class TypeOfTourService implements ITypeOfTourService {
	final Logger log = Logger.getLogger(TypeOfTourService.class);
	
	@Autowired
	ITypeOfTourDAO typeOfTourDaoI;

	@Override
	public TypeOfTour get(int id_typeOfTour) throws ServiceException{		
		TypeOfTour type = null;
		try {
			type = typeOfTourDaoI.get(id_typeOfTour);				
		} catch (DaoException e) {
	 		log.error("transaction getTypeOfTour ERROR \b" + e);
		}	
		return type;
	}

	@Override
	public List<TypeOfTour> getAllTypeOfTours() {
		List<TypeOfTour> types = null;
		
		try {
			types = typeOfTourDaoI.getAllTypeOfTours();
		} catch (DaoException e) {
			log.error("transaction getAllTypeOfTours ERROR \b" + e);
		}
		
		return types;
	}

}
