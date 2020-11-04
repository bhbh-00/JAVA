import java.util.ArrayList;

public class MemberDao {

	public ArrayList<Member> member;

	private int no = 1;

	public MemberDao() {
		member = new ArrayList<>();

		Member m1 = new Member(1, "sony7", "7777", "손흥민", Util.getCurrenDate());
		Member m2 = new Member(2, "adc123", "a123", "ABC", Util.getCurrenDate());
		Member m3 = new Member(3, "gugu9", "9999", "구구단", Util.getCurrenDate());

		member.add(m1);
		member.add(m2);
		member.add(m3);
	}

	public void removeMember(Member m) {
		member.remove(m);
	}

	public ArrayList<Member> getMember() {
		return member;
	}

	public void insertMember(Member m) {
		m.getMRegNum();
		no++;

		m.setMdate(Util.getCurrenDate());

		member.add(m);
	}
	
	public Member getMemberByLoginIdAndPw(String id, String pw) {

		for (int i = 0; i < member.size(); i++) {
			Member m = member.get(i);
			if (m.getMRegId().equals(id) && m.getMRegPW().equals(pw)) {

				return m;

			}
		}
		return null;
	}

	public Member getMemberById(int id) {
		for (int i = 0; i < member.size(); i++) {
			Member m = member.get(i);
			if (m.getMRegNum() == id) {
				return m;
			}
		}
		return null;
	}
}
