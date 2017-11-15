package controllers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import agency.KindOfTour;
import agency.Tour;
import agency.TypeOfTour;
import agency.User;
import beans.CityBean;
import beans.CountryBean;
import beans.PaginationCreatorTourInfo;
import beans.TourDiscountBean;
import service.ICityService;
import service.ICountryService;
import service.IKindOfTourService;
import service.ITourService;
import service.ITypeOfTourService;
import service.IUserService;
import validator.CityValidator;
import validator.CountryValidator;



/**
 *  класс сервлета для управления кабинетом администратора(турагента)
 *  с использованием паттерна Command 
 */
@Controller
@PreAuthorize(value="hasRole('admin')")	//Аннотация, которая не дает доступ ко ВСЕМ методам текущего контроллера, если:
										// 1. Пользователь не авторизован.
										// 2. Или авторизован, но не имеет роль ADMIN.
public class AdminController {

	
	final Logger log = Logger.getLogger(AdminController.class);
		
	@Autowired
	private CountryValidator countryValidator;
	
	@Autowired
	private CityValidator cityValidator;
		
	@Autowired
	private ITourService tourService;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private ITypeOfTourService typeOfTourService;

	@Autowired
	private IKindOfTourService kindOfTourService;
	
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private ICityService cityService;
	
	@ModelAttribute(value="tour")
	private Tour getDefaultTour() {
		return new Tour();
	}
	
	@ModelAttribute(value="tours")
	private List<Tour> getAllTours() {
		return tourService.getAllTours();
	}

	@ModelAttribute(value="clients")
	private List<User> getAllClients() {
		return userService.getUserDiscount();
	}	

	@ModelAttribute(value = "tourDiscount")
	private TourDiscountBean getDefaultTourDiscount() {
		return new TourDiscountBean();
	}
		
	@ModelAttribute(value = "typeOfTours")
	private List<TypeOfTour> getAllTypeOfTours() {
		return typeOfTourService.getAllTypeOfTours();
	}

	@ModelAttribute(value = "kindOfTours")
	private List<KindOfTour> getAllKindOfTours() {
		return kindOfTourService.getAllKindOfTours();
	}
	
	@ModelAttribute(value = "countriesBean")
	private List<CountryBean> getAllCountry() {
		return countryService.getAllCountry();
	}
		
	@ModelAttribute(value="countryBean")
	private CountryBean getDefaultCountryBean() {
		return new CountryBean();
	}
		
	@ModelAttribute(value="cityBean")
	private CityBean getDefaultCity() {
		return new CityBean();
	} 
		
	@ModelAttribute(value = "citiesBean")
	private List<CityBean> getAllCity() {
		return cityService.getAllCity();
	}
	
	@RequestMapping(value="/touragent.html")
	public ModelAndView showTourAgentPage(HttpSession session) {
		ModelAndView model = new ModelAndView("touragent");
		
		List<Tour> tours = getReservedTours(session);
		model.addObject("tours", tours);
		return model;
	}
//-----------------------------------------------------------
	private List<Tour> getReservedTours(HttpSession session) {
		PaginationCreatorTourInfo info = getPaginationCreatorTourInfo(session);

		List<Tour> total = tourService.getAllTours();
		int noOfRecords = total.size();
		List<Tour> toursCreator = tourService.getAllKindTour(info.getCurrentPage(), info.getRecordsPerPage());

		// расчет количества страниц в отображаемой таблице
		Integer noOfPages = (int) Math.ceil(noOfRecords * 1.0 / info.getRecordsPerPage());
		info.setNoOfPages(noOfPages);

		return toursCreator;
	}
	
	@RequestMapping(value = "/creator-tour_previous-page.html")
	public ModelAndView showPreviousPage(HttpSession session) {
		PaginationCreatorTourInfo info = getPaginationCreatorTourInfo(session);
		info.decrementPage();

		ModelAndView model = new ModelAndView("redirect:touragent.html");
		return model;
	}

	@RequestMapping(value = "/creator-tour_next-page.html")
	public ModelAndView showNextPage(HttpSession session) {
		PaginationCreatorTourInfo info = getPaginationCreatorTourInfo(session);
		info.incrementPage();

		ModelAndView model = new ModelAndView("redirect:touragent.html");
		return model;
	}

	@RequestMapping(value = "/creator-tour_go-to-page.html")
	public ModelAndView showNextPage(HttpSession session,
			@RequestParam Integer page) {
		PaginationCreatorTourInfo info = getPaginationCreatorTourInfo(session);
		info.setCurrentPage(page);

		ModelAndView model = new ModelAndView("redirect:touragent.html");
		return model;
	}
	
	private PaginationCreatorTourInfo getPaginationCreatorTourInfo(HttpSession session) {
		PaginationCreatorTourInfo info = (PaginationCreatorTourInfo) session.getAttribute("paginationCreatorTourInfo");
		if (info == null) {
			info = new PaginationCreatorTourInfo();
			session.setAttribute("paginationCreatorTourInfo", info);
		}

		return info;
	}
//---------------------------------------------------------
	@RequestMapping(value="/add-tour.html")
	public ModelAndView addTour(Tour tour) {
		tourService.addTour(tour);

		ModelAndView model = new ModelAndView("redirect:touragent.html");
		return model;
	}
	
	@RequestMapping(value="/delete-tour.html")
	public ModelAndView deleteTour(@RequestParam Integer idTour) {
		tourService.deleteTour(idTour);

		ModelAndView model = new ModelAndView("redirect:touragent.html");
		return model;
	}

	@RequestMapping(value = "/discount-tour.html")
	public ModelAndView discountTour(HttpSession session, @ModelAttribute("tourDiscount") @Valid TourDiscountBean discount,
			BindingResult result) {

		ModelAndView model = new ModelAndView("touragent");
		List<Tour> tours = getReservedTours(session);
		model.addObject("tours", tours);
		
		// Check validation errors
		if (result.hasErrors()) {			
			return model;
		} else {

			userService.InsertDiscount(discount.getUserId(), discount.getDiscountByTour());
			return new ModelAndView("redirect:touragent.html");
		}
	}

	@RequestMapping(value = "/delete-discount-tour.html")
	public ModelAndView deleteDiscountTour(HttpSession session, @RequestParam Integer idUser) {

		ModelAndView model = new ModelAndView("touragent");
		List<Tour> tours = getReservedTours(session);
		model.addObject("tours", tours);

		
			userService.DeleteDiscount(idUser, null);
			return new ModelAndView("redirect:touragent.html");
	

	}
	
	@RequestMapping(value = "/add-country.html")
	public ModelAndView addCountry(HttpSession session, Locale locale, @ModelAttribute("countryBean") @Valid CountryBean countryBean,
			BindingResult result) {

		ModelAndView model = new ModelAndView("touragent");		
		List<Tour> tours = getReservedTours(session);
		model.addObject("tours", tours);
		
		// validation code
		countryValidator.validate(countryBean, result);

		// Check validation errors
		if (result.hasErrors()) {
			return model;
		} else {			
			countryService.addCountry(countryBean);
			return new ModelAndView("redirect:touragent.html");
		}
	}

	@RequestMapping(value = "/delete-country.html")
	public ModelAndView deleteCountry(@RequestParam Integer country) {		
	
		 countryService.deleteCountry(country);		

		ModelAndView model = new ModelAndView("redirect:touragent.html");
		return model;
	}
	
	@RequestMapping(value = "/add-city.html")
	public ModelAndView addCountry(HttpSession session, Locale locale, @ModelAttribute("cityBean") @Valid CityBean cityBean,
			BindingResult result) {
		
		ModelAndView model = new ModelAndView("touragent");
		List<Tour> tours = getReservedTours(session);
		model.addObject("tours", tours);
		
		// validation code
		cityValidator.validate(cityBean, result);				
		
		// Check validation errors
		if (result.hasErrors()) {
			return model;
		} else {
			cityService.addCity(cityBean);
			return new ModelAndView("redirect:touragent.html");
		}
				
	}
	
	@RequestMapping(value = "/delete-city.html")
	public ModelAndView deleteCity(@RequestParam Integer city) {	
		 cityService.deleteCity(city);		

		ModelAndView model = new ModelAndView("redirect:touragent.html");
		return model;
	}
	
}
