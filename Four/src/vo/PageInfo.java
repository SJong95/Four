package vo;

public class PageInfo {
	private int page;	// 현재 페이지
	private int maxPage;	// 최대 페이지
	private int startPage;	// 시작페이지
	private int endPage;	// 마지막 페이지
	private int listPage;	// 전체 글 갯수
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getListPage() {
		return listPage;
	}
	public void setListPage(int listPage) {
		this.listPage = listPage;
	}
	
}
