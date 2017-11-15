package beans;

public class PaginationCreatorTourInfo {
	private Integer noOfPages;
	private Integer currentPage;
	private Integer recordsPerPage;

	public PaginationCreatorTourInfo() {
		noOfPages = 0;
		currentPage = 1;
		recordsPerPage = 4;
	}

	public Integer getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(Integer noOfPages) {
		this.noOfPages = noOfPages;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getRecordsPerPage() {
		return recordsPerPage;
	}
	
	public void incrementPage() {
		currentPage = currentPage + 1;
	}

	public void decrementPage() {
		currentPage = currentPage - 1;
	}

}
