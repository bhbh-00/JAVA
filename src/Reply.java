  

public class Reply {
	private int RegId; // 댓글등록번호
	private int parentId; // 부모번호
	private String Body; // 내용
	private String Nname; // 작성자
	private String date; // 등록날짜

	public int getId() {
		return RegId;
	}

	public int getRegId() {
		return RegId;
	}

	public void setRegId(int regId) {
		this.RegId = regId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		this.Body = body;
	}

	public String getNname() {
		return Nname;
	}

	public void setNname(String nname) {
		this.Nname = nname;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}