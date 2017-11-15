package agency;

import java.io.Serializable;
import java.sql.Date;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.*;

/**
 * класс Тур с добавленными свойствами видов и типов тура
 * 
 * @author Den
 * 
 */
@Entity
@Table(name = "tours", catalog = "travelagency")
public class Tour implements Serializable {
	private static final long serialVersionUID = 1L;

	public Tour() {

	}

	public Tour(Date dateOfBeginning, Date dateEnd, String name, int numberOfNights, int cost, String discount,
			TypeOfTour typeOfTour, KindOfTour kindOfTour, Country country ,Set<UserTour> usertours, City city) {

		this.dateOfBeginning = dateOfBeginning;
		this.dateEnd = dateEnd;
		this.name = name;
		this.numberOfNights = numberOfNights;
		this.cost = cost;
		this.discount = discount;
		this.typeOfTour = typeOfTour;
		this.kindOfTour = kindOfTour;
		this.country = country;
		this.usertours = usertours;
		this.city = city;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tour")
	private int id_tour;

	@Column(name = "dateOfBeginning")
	private Date dateOfBeginning;
	//private String dateOfBeginning;

	/** return tour date */
	@Column(name = "dateEnd")
	private Date dateEnd;
	//private String dateEnd;

	@Column(name = "name")
	private String name;

	@Column(name = "numberOfNights")
	private int numberOfNights;

	@Column(name = "cost")
	private int cost;

	@Column(name = "discount")
	private String discount;

	@ManyToOne
	@JoinColumn(name = "id_typeOfTour")
	private TypeOfTour typeOfTour;

	@ManyToOne
	@JoinColumn(name = "id_kindOfTour")
	private KindOfTour kindOfTour;
	
	@ManyToOne
	@JoinColumn(name = "id_country")
	private Country country;
	
	@ManyToOne
	@JoinColumn(name = "id_city")
	private City city;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.tour")
	private Set<UserTour> usertours = new HashSet<UserTour>();
		
	public Set<UserTour> getUserTours() {
		return usertours;
	}

	public void setUserTours(Set<UserTour> usertours) {
		this.usertours = usertours;
	}

	public int getId_tour() {
		return id_tour;
	}

	public void setId_tour(int id_tour) {
		this.id_tour = id_tour;
	}

	public Date getDateOfBeginning() {
		return dateOfBeginning;
	}

	public void setDateOfBeginning(Date dateOfBeginning) {
		this.dateOfBeginning = dateOfBeginning;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfNights() {
		return numberOfNights;
	}

	public void setNumberOfNights(int numberOfNights) {
		this.numberOfNights = numberOfNights;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public KindOfTour getKindOfTour() {
		return kindOfTour;
	}

	public void setKindOfTour(KindOfTour kindOfTour) {
		this.kindOfTour = kindOfTour;
	}

	public TypeOfTour getTypeOfTour() {
		return typeOfTour;
	}

	public void setTypeOfTour(TypeOfTour typeOfTour) {
		this.typeOfTour = typeOfTour;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
		
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "Tour [id_tour=" + id_tour + ", dateOfBeginning=" + dateOfBeginning + ", dateEnd=" + dateEnd + ", name="
				+ name + ", numberOfNights=" + numberOfNights + ", cost=" + cost + ", discount=" + discount
				+ ", typeOfTour=" + typeOfTour + ", kindOfTour=" + kindOfTour + ", country=" + country +", city=" + city +"]";
	}
	
		
}
