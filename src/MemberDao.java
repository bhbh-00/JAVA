import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemberDao {
	
	public ArrayList<Member> Member;
	
	private int no = 1;
	
	public MemberDao() {
		Member = new ArrayList<>();
	}

	public void removesignup(Member s) {
		Member.remove(s);
	}
	
	public ArrayList<Member> getMember() {
		return Member;
	}
	
	public void insertsignup(Member s) {
		s.setMRegNum(no);
		no++;

		s.setMdate(getCurrenDate());

		Member.add(s);
	}
	
	// 등록날짜
	private static String getCurrenDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();

		String t1 = format1.format(time);

		return t1;
	}

}
