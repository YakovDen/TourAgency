package beans;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


public class TourDiscountBean {	

	@NotNull(message = "{NotNull}")
	private Integer userId;

	@NotBlank(message = "{NotEmpty}")
	private String discountByTour;

	public TourDiscountBean() {
		
	}

	public TourDiscountBean(Integer userId, String discountByTour) {		
		this.userId = userId;
		this.discountByTour = discountByTour;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDiscountByTour() {
		return discountByTour;
	}

	public void setDiscountByTour(String discountByTour) {
		this.discountByTour = discountByTour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discountByTour == null) ? 0 : discountByTour.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		TourDiscountBean other = (TourDiscountBean) obj;
		if (discountByTour == null) {
			if (other.discountByTour != null) {
				return false;
			}
		} else if (!discountByTour.equals(other.discountByTour)) {
			return false;
		}

		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "TourDiscountBean [userId=" + userId + ", discountByTour=" + discountByTour + "]";
	}
}
