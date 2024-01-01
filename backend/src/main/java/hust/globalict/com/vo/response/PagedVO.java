package hust.globalict.com.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PagedVO {

	public PagedVO() {
	}

	@JsonProperty("page")
	private int currentPage;

	private int totalPage;

	private long totalRecord;

	@JsonProperty("size")
	private int pageSize;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
