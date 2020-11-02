import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemberDao {
	
	public ArrayList<Member> member;

	private int no = 1;
	
	public MemberDao(){
		members = new ArrayList<>();
	}
	
// 	public void removeMember(Member m) {
// 		member.remove(m);
// 	}

	public ArrayList<Member> getMember() {
		return member;
	}

	public void insertMember(Member m) {
		m.getMRegNum();
		no++;

		m.setMdate(getCurrenDate());

		member.add(m);
	}

	// 등록날짜
	private static String getCurrenDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();

		String t1 = format1.format(time);

		return t1;
	}
	
	public Member getMemberByLoginIdAndPw(String id, String pw) { 
	
		for(int i = 0; i < member.size(); i++) {
			Member m = member.get(i);
			if(m.getMRegId().equals(id) && m.getMRegPW().equals(pw)) {
				
				return m;
				
			}
		}
		return null;
	}
}
