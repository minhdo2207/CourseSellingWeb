package neu.com.vo.response;

import java.util.List;

public class PagedResult<T> {

	public PagedResult() {
	}

	private PagedVO paging;

	private List<T> elements;

	public PagedVO getPaging() {
		return paging;
	}

	public void setPaging(PagedVO paging) {
		this.paging = paging;
	}

	public List<T> getElements() {
		return elements;
	}

	public void setElements(List<T> elements) {
		this.elements = elements;
	}

}
