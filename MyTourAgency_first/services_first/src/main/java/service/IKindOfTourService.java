package service;

import java.util.List;

import agency.KindOfTour;

public interface IKindOfTourService {
	KindOfTour get(int id_kindOfTour); 

	List<KindOfTour> getAllKindOfTours();
}
