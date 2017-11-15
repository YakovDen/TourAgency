package service;

import java.util.List;

import agency.TypeOfTour;

public interface ITypeOfTourService {

	TypeOfTour get(int id_typeOfTour); 

	List<TypeOfTour> getAllTypeOfTours();
}
