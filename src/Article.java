
public class Article {
	private int RegId; // 등록번호
	private String Title; // 제목
	private String Body; // 내용
	private String Nname; // 작성자
	private String date; // 등록날짜
	private int Views; // 조회수

	public Article(int RegId, String Title, String Body, String Nname, String date) {
		this.RegId = RegId;
		this.Title = Title;
		this.Body = Body;
		this.Nname = Nname;
		this.date = date;

	}


	public Article() {
		// 오버로딩
	}

	// 캡슐화
	public int getRegId() {
		return RegId;
	}

	public void setRegId(int regId) {
		RegId = regId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}
	
	public String getNname() {
		return Nname;
	}

	public void setNname(String nname) {
		Nname = nname;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getViews() {
		return Views;
	}

	public void setViews(int views) {
		this.Views = views;
	}
	
	// 검색기능 1.제목 2.내용 3.제목+내용 4.작성자
	public String getPropertiesByFlag(int flag) {
		String str = "";
		
		if(flag == 1) {
			str = this.getTitle();
		} else if(flag == 2) {
			str = this.getBody();
		} else if(flag == 3) {
			str = this.getTitle() + this.getBody();
		} else {
			str = this.getNname();
		}
		
		return str;
		
	}
}
