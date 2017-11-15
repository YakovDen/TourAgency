package controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import advices.UserInfoAdvice;
import agency.Tour;
import agency.UserTour;
import beans.PaginationInfo;
import beans.DesiredDateBean;
import beans.PaginationDesiredTourInfo;
import beans.PaginationHotTourInfo;
import beans.TourReservationBean;
import service.ITourService;

@Controller
@SessionAttributes("tourDate")   // Store filter on Session
public class CustomerController {

	@Autowired
	private ITourService tourService;	
	
	@ModelAttribute(value = "tourDate")
	private DesiredDateBean getDefaultDesiredDate() {
		return new DesiredDateBean();
	}
	
	
	@RequestMapping(value = "/customer.html")
	public ModelAndView showCustomerPage(HttpSession session) {
		ModelAndView model = new ModelAndView("customer");

		List<Tour> toursReserved = getReservedTours(session);
		model.addObject("toursReserved", toursReserved);

		return model;
	}

	@RequestMapping(value = "/reserve-tour.html")
	public ModelAndView reserveTour(TourReservationBean reservation, UserInfoAdvice userInfoAdvice, HttpSession session) {
		session.removeAttribute("tourIdError");		
		try {			
			tourService.getReservationTour(reservation.getTourId(), userInfoAdvice.getUserId());
			
		} catch(Exception e) {
			session.setAttribute("tourIdError", "Tour with this number doesn't exist or this tour is already selected");
		}

		ModelAndView model = new ModelAndView("redirect:customer.html");
		return model;
	}	

	@RequestMapping(value = "/tourcount.html")
	public ModelAndView tourCount(UserInfoAdvice userInfoAdvice) {		 
		Set<UserTour> usertour = tourService.getUserReservAllTours(userInfoAdvice.getUserId());
		ModelAndView model = new ModelAndView("usertours");
		model.addObject("allToursForClient", usertour);
		
		return model;
	}

	@RequestMapping(value = "/pay-tour.html")
	public ModelAndView payTour(@RequestParam Integer userTourId, UserInfoAdvice userInfoAdvice) {	
		tourService.payTour(userTourId, true);		
		Set<UserTour> usertour = tourService.getUserReservAllTours(userInfoAdvice.getUserId());		
		ModelAndView model = new ModelAndView("usertours");
		model.addObject("allToursForClient", usertour);
		
		return model;
	}

	@RequestMapping(value = "/un-pay-tour.html")
	public ModelAndView unPayTour(@RequestParam Integer userTourId, UserInfoAdvice userInfoAdvice) {	
		tourService.payTour(userTourId, false);		
		Set<UserTour> usertour = tourService.getUserReservAllTours(userInfoAdvice.getUserId());		
		ModelAndView model = new ModelAndView("usertours");
		model.addObject("allToursForClient", usertour);
		
		return model;
	}

	@RequestMapping(value = "/previous-page.html")
	public ModelAndView showPreviousPage(HttpSession session) {
		PaginationInfo info = getPaginationInfo(session);
		info.decrementPage();

		ModelAndView model = new ModelAndView("redirect:customer.html");
		return model;
	}

	@RequestMapping(value = "/next-page.html")
	public ModelAndView showNextPage(HttpSession session) {
		PaginationInfo info = getPaginationInfo(session);
		info.incrementPage();

		ModelAndView model = new ModelAndView("redirect:customer.html");
		return model;
	}

	@RequestMapping(value = "/go-to-page.html")
	public ModelAndView showNextPage(HttpSession session,
			@RequestParam Integer page) {
		PaginationInfo info = getPaginationInfo(session);
		info.setCurrentPage(page);

		ModelAndView model = new ModelAndView("redirect:customer.html");
		return model;
	}

	@ModelAttribute(value = "tourForClient")	
	private List<Tour> getToursForClient(UserInfoAdvice userInfoAdvice) {
		
		int userId = userInfoAdvice.getUserId();				
		List<Tour> tctour = tourService.getUserReservTour(userId);
		return tctour;
		
	}

	@ModelAttribute(value = "reservation")
	public TourReservationBean getDefaultReservation() {
		return new TourReservationBean();
	}

	private List<Tour> getReservedTours(HttpSession session) {
		PaginationInfo info = getPaginationInfo(session);

		List<Tour> total = tourService.getAllTours();
		int noOfRecords = total.size();
		List<Tour> tcs = tourService.getAllKindTour(info.getCurrentPage(), info.getRecordsPerPage());

		// расчет количества страниц в отображаемой таблице
		Integer noOfPages = (int) Math.ceil(noOfRecords * 1.0 / info.getRecordsPerPage());
		info.setNoOfPages(noOfPages);

		return tcs;
	}

	private PaginationInfo getPaginationInfo(HttpSession session) {
		PaginationInfo info = (PaginationInfo) session.getAttribute("paginationInfo");
		if (info == null) {
			info = new PaginationInfo();
			session.setAttribute("paginationInfo", info);
		}

		return info;
	}
	
	//the list of hot tour and pagination
	@RequestMapping(value = "/hot-tour.html")	
	public ModelAndView showPageHotTour(HttpSession session) {				
		ModelAndView model = new ModelAndView("hot_tour");
		List<Tour> hotTour = getLastMinuteTour(session);
		model.addObject("allHotTours", hotTour);
		
		return model;
	}
		
	private List<Tour> getLastMinuteTour(HttpSession session) {
		PaginationHotTourInfo info = getPaginationHotTourInfo(session);

		List<Tour> total = tourService.getAllHotTour();
		int noOfRecords = total.size();
		List<Tour> tcs = tourService.getPaginationHotTour(info.getCurrentPage(), info.getRecordsPerPage());

		// расчет количества страниц в отображаемой таблице
		Integer noOfPages = (int) Math.ceil(noOfRecords * 1.0 / info.getRecordsPerPage());
		info.setNoOfPages(noOfPages);

		return tcs;
	}
		
	@RequestMapping(value = "/hot-tour_previous-page.html")
	public ModelAndView showHotPreviousPage(HttpSession session) {
		PaginationHotTourInfo info = getPaginationHotTourInfo(session);
		info.decrementPage();

		ModelAndView model = new ModelAndView("redirect:hot-tour.html");
		return model;
	}

	@RequestMapping(value = "/hot-tour_next-page.html")
	public ModelAndView showHotNextPage(HttpSession session) {
		PaginationHotTourInfo info = getPaginationHotTourInfo(session);
		info.incrementPage();

		ModelAndView model = new ModelAndView("redirect:hot-tour.html");
		return model;
	}

	@RequestMapping(value = "/hot-tour_go-to-page.html")
	public ModelAndView showHotNextPage(HttpSession session,
			@RequestParam Integer page) {
		PaginationHotTourInfo info = getPaginationHotTourInfo(session);
		info.setCurrentPage(page);

		ModelAndView model = new ModelAndView("redirect:hot-tour.html");
		return model;
	}

	private PaginationHotTourInfo getPaginationHotTourInfo(HttpSession session) {
		PaginationHotTourInfo info = (PaginationHotTourInfo) session.getAttribute("paginationHotTourInfo");
		if (info == null) {
			info = new PaginationHotTourInfo();
			session.setAttribute("paginationHotTourInfo", info);
		}

		return info;
	}
	
	//the list of desired tour and pagination
	@RequestMapping(value = "/desired-date.html")	
	public ModelAndView filterDateTour(HttpSession session,@ModelAttribute("tourDate") DesiredDateBean desiredDateBean) {		
		ModelAndView model = new ModelAndView("desired_date");
		List<Tour> desiredTourDate = getDesiredTour(session, desiredDateBean);		
				
		model.addObject("allDesiredTours", desiredTourDate);
		return model;
	}
	
	
	private PaginationDesiredTourInfo getPaginationDesiredTourInfo(HttpSession session) {
		PaginationDesiredTourInfo info = (PaginationDesiredTourInfo) session.getAttribute("paginationDesiredTourInfo");
		if (info == null) {
			info = new PaginationDesiredTourInfo();
			session.setAttribute("paginationDesiredTourInfo", info);
		}

		return info;
	}
	
	private List<Tour> getDesiredTour(HttpSession session, DesiredDateBean desiredDateBean) {	
		PaginationDesiredTourInfo info = getPaginationDesiredTourInfo(session);	
		
		List<Tour> total = tourService.filterTour(desiredDateBean.getDateBeginFrom(),desiredDateBean.getDateBeginBefore());			
		int noOfRecords = total.size();		
		List<Tour> tourdesired = tourService.getPaginationDesiredTour(info.getCurrentPage(), info.getRecordsPerPage(),
				desiredDateBean.getDateBeginFrom(),desiredDateBean.getDateBeginBefore());		
		
		// расчет количества страниц в отображаемой таблице
		Integer noOfPages = (int) Math.ceil(noOfRecords * 1.0 / info.getRecordsPerPage());
		info.setNoOfPages(noOfPages);

		return tourdesired;
	}
	
	@RequestMapping(value = "/desired-date_previous-page.html")
	public ModelAndView showDesiredPreviousPage(HttpSession session) {
		PaginationDesiredTourInfo info = getPaginationDesiredTourInfo(session);		
		info.decrementPage();

		ModelAndView model = new ModelAndView("redirect:desired-date.html");
		return model;
	}

	@RequestMapping(value = "/desired-date_next-page.html")
	public ModelAndView showDesiredNextPage(HttpSession session) {
		PaginationDesiredTourInfo info = getPaginationDesiredTourInfo(session);		
		info.incrementPage();
		
		ModelAndView model = new ModelAndView("redirect:desired-date.html");
		return model;
	}

	@RequestMapping(value = "/desired-date_go-to-page.html")
	public ModelAndView showDesiredNextPage(HttpSession session,
			@RequestParam Integer page) {
		PaginationDesiredTourInfo info = getPaginationDesiredTourInfo(session);
		info.setCurrentPage(page);

		ModelAndView model = new ModelAndView("redirect:desired-date.html");
		return model;
	}
	
}	
