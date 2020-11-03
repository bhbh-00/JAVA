
public class Member {
	
	private int MRegNum; // 회원가입 등록고유번호
	private String MRegId; // 회원가입 아이디
	private String MRegPW; // 회원가입 PW
	private String MRegNN; // 회원가입 닉네임
	private String Mdate; // 회원가입 등록날짜
	
	public Member( ) {
		// 오버로딩
	}
	
	public Member (int RegId, String Id, String Pw, String NN, String date) {
		this.MRegNum = RegId;
		this.MRegId = Id;
		this.MRegPW = Pw;
		this.MRegNN = NN;
		this.Mdate = date;
	}
	
	public int getMRegNum() {
		return MRegNum;
	}
	public void setMRegNum(int mRegNum) {
		MRegNum = mRegNum;
	}
	public String getMRegId() {
		return MRegId;
	}
	public void setMRegId(String mRegId) {
		MRegId = mRegId;
	}
	public String getMRegPW() {
		return MRegPW;
	}
	public void setMRegPW(String mRegPW) {
		MRegPW = mRegPW;
	}
	public String getMRegNN() {
		return MRegNN;
	}
	public void setMRegNN(String mRegNN) {
		MRegNN = mRegNN;
	}
	public String getMdate() {
		return Mdate;
	}
	public void setMdate(String mdate) {
		Mdate = mdate;
	}
	
	
}
