package dao_first;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import agency.KindOfTour;
import agency.Tour;
import agency.TypeOfTour;
import dao.ITourDAO;
import daoImpl.base.exception.DaoException;

@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestAdd {
	final Logger log = Logger.getLogger(TestAdd.class);

	@Autowired
	private ITourDAO itd;

	Tour tt;
	int res = 0;// счетчик добавленных записей в таблицу tours
	int id_tour;// номер тестового тура

	@Before
	public void create() {

		TypeOfTour typeOfTour = new TypeOfTour(2, "Обычный");
		KindOfTour kindOfTour = new KindOfTour(3, "Шоппинг");
		Date dateOfBeginning = new Date(2015-10-10);
		Date dateEnd = new Date(2015-10-15);
		
		// создаю тестовый тур в БД	
		Tour myTour = new Tour();
		myTour.setDateOfBeginning(dateOfBeginning);
		myTour.setDateEnd(dateEnd);
		myTour.setName("Appolon");
		myTour.setNumberOfNights(5);
		myTour.setCost(50);
		myTour.setDiscount("(0-10)%");
		myTour.setTypeOfTour(typeOfTour);
		myTour.setKindOfTour(kindOfTour);

		itd.addTour(myTour);
		List<Tour> tour = new ArrayList<Tour>();
		try {
			tour = itd.getAllTours();
		} catch (DaoException e) {
			log.error("TestAddTours(getAllTours) ERROR\n" + e);
		}
		
		// тестовый объект тура, по которому идет сравнение из БД
		tt = new Tour();// объект, по которому идет сравнение
		tt.setDateOfBeginning(dateOfBeginning);
		tt.setDateEnd(dateEnd);
		tt.setName("Appolon");
		tt.setNumberOfNights(5);
		tt.setCost(50);
		tt.setDiscount("(0-10)%");
		tt.setTypeOfTour(typeOfTour);
		tt.setKindOfTour(kindOfTour);

		for (Tour element : tour) {
			// сравниваю по 2-м полям
			if (tt.getName().equals(element.getName())
					&& (tt.getDateOfBeginning().equals(element.getDateOfBeginning()))) {
				id_tour = element.getId_tour();
				res++;
			}

		}

	}

	@After
	public void clean() {
		// подчищаю базу после добавленной записи
		itd.deleteTour(id_tour);
		System.out.println("Rows Affected: ");

	}

	@Test
	public void test() {
		Assert.assertTrue(res > 0);// если запись добавлена, то тест прошел
	}

}
