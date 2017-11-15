package serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agency.KindOfTour;
import dao.IKindOfTourDAO;
import daoImpl.base.exception.DaoException;
import service.IKindOfTourService;

@Service
@Transactional
public class KindOfTourService implements IKindOfTourService {
	final Logger log = Logger.getLogger(KindOfTourService.class);

	@Autowired
	IKindOfTourDAO kindOfTourDaoI;

	@Override
	public KindOfTour get(int id_kindOfTour) throws ServiceException {
		KindOfTour kind = null;
		try {
			kind = kindOfTourDaoI.get(id_kindOfTour);
		} catch (DaoException e) {
			log.error("transaction getKindOfTour ERROR \b" + e);

		}
		return kind;
	}

	@Override
	public List<KindOfTour> getAllKindOfTours() {
		List<KindOfTour> kinds = null;
		
		try {
			kinds = kindOfTourDaoI.getAllKindOfTours();
		} catch (DaoException e) {
			log.error("transaction getAllKindOfTours ERROR \b" + e);
		}
		
		return kinds;
	}

}
