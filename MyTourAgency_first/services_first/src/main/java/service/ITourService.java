package service;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import agency.Tour;
import agency.UserTour;



public interface ITourService {

	//список всех туров, включая типы туров для роли туроператора
			public List<Tour> getAllTours();
			//добавление тура для роли оператора			
			public List<Tour> addTour(Tour myTour);
			//удаление тура для роли оператора
			public void deleteTour(int id);			
			//для роли заказчик, метод вывода списка туров и их видов
			//public List<Tour> getAllKindTour();
			public List<Tour> getAllKindTour(int page, int recordsPerPage);
			//бронирование(выбор) тура заказчиком	
			public void getReservationTour(int id_tour, int id_user);
			//вывод выбранного заказчиком тура с довавлением индекса в поле
			public List<Tour> getUserReservTour(int id_user);
			//вывод списка туров,забронированных заказчиком
			public Set<UserTour> getUserReservAllTours(int id_user);
			//оплата выбранного тура
			public void payTour(int idPaid,boolean isPressed);
			//список горящих туров
			List<Tour> getAllHotTour();
			//пагинация горящих туров
			public List<Tour> getPaginationHotTour(int page, int recordsPerPage);
			//фильтр туров по дате заезда
			public List<Tour> filterTour(Date dateBeginFrom, Date dateBeginBefore);
			//пагинация списка туров за выбранный желаемый период
			public List<Tour> getPaginationDesiredTour(int page, int recordsPerPage, Date dateBeginFrom, Date dateBeginBefore);
			//
			
		
}
