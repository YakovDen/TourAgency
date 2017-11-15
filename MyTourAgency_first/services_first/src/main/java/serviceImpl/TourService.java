package serviceImpl;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agency.Tour;
import agency.UserTour;
import dao.ITourDAO;
import daoImpl.base.exception.DaoException;
import service.ITourService;
/** service methods for tours: 
 *  There is added:
 * 1) to extract a list of tours(role touragent);
 * 2) add tour(role touragent);
 * 3) delete tour(role touragent);
 * 4) to extract a list of tours,pagination(role client);
 * 5) to extract a list of hot tours(role client)
 * 6) to extract a list of hot tours,pagination(role client);
 * 7) to reservation of tour(role client);
 * 8) to choice of current user(role client);
 * 9) to write  payd of tour in table usertour(role client);
 * 10) to pay/off pay of tour(role client);
 * 11) filter of the tour from date;
 * 12) to extract a list of desired tours,pagination(role client);
 **/


@Service
@Transactional
public class TourService implements ITourService  {
	final Logger log = Logger.getLogger(TourService.class);
	//ITourDAO tourDaoI = (ITourDAO)(new TourDAOImpl());
	
	@Autowired
	ITourDAO tourDaoI;
	
	public List<Tour> getAllTours() throws ServiceException{
		List<Tour> tourAll=null;
		 try {
	 	 
				tourAll = tourDaoI.getAllTours();				

			} catch (DaoException e) {
		 		log.error("transaction getAllTours ERROR \b" + e);
			}	
		 	return	tourAll;				
	
	}

	public List<Tour> getAllHotTour() {
		List<Tour> hotTour = null;
		try {
			hotTour = tourDaoI.getAllHotTour();
		} catch (DaoException e) {
			log.error("Error to list hotTour(role client) --- "+ e);
		}
		 return hotTour;
		
	}
	
	public List<Tour> addTour(Tour myTour) throws ServiceException{
		List<Tour> tourAdd = null;
		tourAdd = tourDaoI.addTour(myTour);
		 return tourAdd;
	}

	public void deleteTour(int id) throws ServiceException {
		tourDaoI.deleteTour(id);
	}
	
	public List<Tour> getAllKindTour(int page, int recordsPerPage) throws ServiceException {
		List<Tour> tourAllForUser = null;
		
		try {
			tourAllForUser = tourDaoI.getAllKindTour(page,recordsPerPage);
		} catch (DaoException e) {
			log.error(e);
		}
		 return tourAllForUser;
	}
	
	
	public List<Tour> getPaginationHotTour(int page, int recordsPerPage) throws ServiceException {
		List<Tour> hotTourAllForUser = null;
		
		try {
			hotTourAllForUser = tourDaoI.getPaginationHotTour(page,recordsPerPage);
		} catch (DaoException e) {
			log.error(e);
		}
		 return hotTourAllForUser;
	}
	
	public void getReservationTour(int id_tour, int id_user) throws ServiceException {
		
		try {
			tourDaoI.getReservationTour(id_tour, id_user);
		} catch (DaoException e) {
			log.error("Error to reservation of tour(role client)"+ e);
		}
		
	}

	 
	public List<Tour> getUserReservTour(int id_user) throws ServiceException {
		List<Tour> currentUser = null;
		try {
			currentUser = tourDaoI.getUserReservTour(id_user);
		} catch (DaoException e) {
		}
		 return currentUser;
	}
	
	public Set<UserTour> getUserReservAllTours(int id_user) throws ServiceException {
		Set<UserTour> currentUserWithTour = null;
		try {
			currentUserWithTour = tourDaoI.getUserReservAllTours(id_user);
		} catch (DaoException e) {
			log.error("Error to reservation of tour(role client) --- "+ e);
		}
		 return currentUserWithTour;
	}
	
	public void payTour(int idPaid, boolean isPressed) throws ServiceException {		
		
		try {
			tourDaoI.payTour(idPaid, isPressed);
		} catch (DaoException e) {
			log.error("Error to pay/off pay of tour(role client)--- "+ e);
		}
		
	}	
	
	public List<Tour> filterTour(Date dateBeginFrom, Date dateBeginBefore)throws ServiceException {
		List<Tour> desiredTours = null;
		
		try {
			desiredTours = tourDaoI.filterTour(dateBeginFrom, dateBeginBefore);
		} catch (DaoException e) {
			log.error("Error to filterTour of tour(role client)--- "+ e);
		}
		return desiredTours;		
	}
	
	public List<Tour> getPaginationDesiredTour(int page, int recordsPerPage, Date dateBeginFrom, Date dateBeginBefore) throws ServiceException {
		List<Tour> desiredToursDuringDate = null;
		
		try {
			desiredToursDuringDate = tourDaoI.getPaginationDesiredTour(page,recordsPerPage,dateBeginFrom,dateBeginBefore);
		} catch (DaoException e) {
			log.error("Error to getPaginationDesiredTour of desiredToursDuringDate(role client)--- "+ e);
		}
		 return desiredToursDuringDate;
	}
}
