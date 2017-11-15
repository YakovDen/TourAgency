package dao;

import java.util.List;

import agency.KindOfTour;
import daoImpl.base.exception.DaoException;

public interface IKindOfTourDAO extends DAO<KindOfTour> {

	List<KindOfTour> getAllKindOfTours() throws DaoException;
}
