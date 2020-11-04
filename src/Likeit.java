// 데이터 구조화 클래스 = DTO, VO
public class Likeit {
	private int likeId; // 좋아요 번호
	private int parentId; // 게시물(원글) 번호
	private int MemberId; 
	private String LDate; 
	
	
	public Likeit() {
		
	}
	
	public Likeit(int parentId, int memberId) {
		this.parentId = parentId;
		MemberId = memberId;
	}
	
	public Likeit(int parentId, int memberId, String lDate) {
		this.parentId = parentId;
		MemberId = memberId;
		LDate = lDate;
	}
	
	public int getLikeId() {
		return likeId;
	}


	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}


	public int getParentId() {
		return parentId;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	public int getMemberId() {
		return MemberId;
	}


	public void setMemberId(int memberId) {
		MemberId = memberId;
	}


	public String getLDate() {
		return LDate;
	}


	public void setLDate(String lDate) {
		LDate = lDate;
	}
	
	
}
