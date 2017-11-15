package dao;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import daoImpl.base.exception.DaoException;
import agency.Tour;
import agency.UserTour;



public interface ITourDAO extends DAO<Tour>{
		//список всех туров, включая типы туров для роли туроператора
		public List<Tour> getAllTours () throws DaoException;
		//добавление тура для роли оператора		
		public List<Tour> addTour(Tour myTour);
		//удаление тура для роли оператора
		//public List<Tour> deleteTour(int id);
		public void deleteTour(int id);
		//для роли заказчик, метод вывода списка туров и их видов		
		public List<Tour>getAllKindTour(int page, int recordsPerPage) throws DaoException;
		//бронирование(выбор) тура заказчиком	
		public void getReservationTour(int id_tour, int id_user) throws DaoException;
		//вывод выбранного заказчиком тура с довавлением индекса в поле
		public List<Tour> getUserReservTour(int id_user) throws DaoException;
		//вывод всех туров,забронированных клиентом
		public Set<UserTour> getUserReservAllTours(int id_user) throws DaoException;		
		//добавление записи об оплате в таблице связи usertour
		public void payTour(int idPaid,boolean isPressed) throws DaoException;
		//список всех горящих туров
		public List<Tour> getAllHotTour() throws DaoException;
		//пагинация списка горящих туров 
		public List<Tour>getPaginationHotTour(int page,int recordsPerPage) throws DaoException;
		//фильтр туров по дате заезда
		public List<Tour> filterTour(Date dateBeginFrom, Date dateBeginBefore) throws DaoException;
		//пагинация списка туров за выбранный желаемый период
		public List<Tour> getPaginationDesiredTour(int page, int recordsPerPage, Date dateBeginFrom, Date dateBeginBefore) throws DaoException;		
		
}
