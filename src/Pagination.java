
public class Pagination {

	private int currentpageNo = 1;
	private int totalCntOfItems; // 전체 게시물 개수

	private int startPageNo = 1; // 시작 페이지 번호
	private int ItemsCntPage = 3; // 페이지당 출력 게시물 갯수
	private int PageCntPerBlock = 5; // 한 페이지 블럭 당 페이지 갯수

	public int getcurrentpageBlock() {
		int currentpageBlock = (int) Math.ceil(((double) currentpageNo / PageCntPerBlock)); // 현재 페이지 블럭
		return currentpageBlock;
	}

	public int getendPageNo() {
		return (int) Math.ceil(((double) totalCntOfItems / ItemsCntPage)); // 마지막 페이지 번호(올림 매서드);
	}

	public int getstartIndex() {
		// 해당 페이지의 게시물 목록의 첫 인덱스
		return (currentpageNo - 1) * ItemsCntPage;

	}

	public int getendIndex() {
		// 해당 페이지의 게시물 목록의 마지막 인덱스
		return getstartIndex() + ItemsCntPage;
	}

	public int getCurrentpageNo() {
		return currentpageNo;
	}

	public void setCurrentpageNo(int currentpageNo) {
		this.currentpageNo = currentpageNo;
	}

	public int getTotalCntOfItems() {
		return totalCntOfItems;
	}

	public void setTotalCntOfItems(int totalCntOfItems) {
		this.totalCntOfItems = totalCntOfItems;
	}

	public int getStartPageNo() {
		return startPageNo;
	}

	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}

	public int getItemsCntPage() {
		return ItemsCntPage;
	}

	public void setItemsCntPage(int itemsCntPage) {
		ItemsCntPage = itemsCntPage;
	}

	public int getPageCntPerBlock() {
		return PageCntPerBlock;
	}

	public void setPageCntPerBlock(int pageCntPerBlock) {
		PageCntPerBlock = pageCntPerBlock;
	}

}
