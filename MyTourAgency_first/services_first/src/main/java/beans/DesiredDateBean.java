package beans;

import java.sql.Date;

public class DesiredDateBean {
	private Date dateBeginFrom;

	private Date dateBeginBefore;

	public Date getDateBeginFrom() {
		return dateBeginFrom;
	}

	public void setDateBeginFrom(Date dateBeginFrom) {
		this.dateBeginFrom = dateBeginFrom;
	}

	public Date getDateBeginBefore() {
		return dateBeginBefore;
	}

	public void setDateBeginBefore(Date dateBeginBefore) {
		this.dateBeginBefore = dateBeginBefore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateBeginFrom == null) ? 0 : dateBeginFrom.hashCode());
		result = prime * result + ((dateBeginBefore == null) ? 0 : dateBeginBefore.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)	return true;
		if (obj == null)	return false;
		if (getClass() != obj.getClass())	return false;
		
		DesiredDateBean other = (DesiredDateBean) obj;
		if (dateBeginFrom == null) {
			if (other.dateBeginFrom != null) return false;
		} else if (!dateBeginFrom.equals(other.dateBeginFrom))
			return false;
		
		if (dateBeginBefore == null) {
			if (other.dateBeginBefore != null) return false;
		} else if (!dateBeginBefore.equals(other.dateBeginBefore))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DesiredDateBean [dateBeginFrom =" + dateBeginFrom + ", dateBeginBefore=" + dateBeginBefore + "]";
	}

	
}
