package dao;

import java.util.List;

import agency.TypeOfTour;
import daoImpl.base.exception.DaoException;

public interface ITypeOfTourDAO extends DAO<TypeOfTour>{

	public List<TypeOfTour> getAllTypeOfTours() throws DaoException;
}
