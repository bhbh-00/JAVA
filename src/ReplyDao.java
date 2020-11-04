import java.util.ArrayList;

public class ReplyDao {
	
	public ArrayList<Reply> replies;

	private int no = 1;

	public ReplyDao() {
		replies = new ArrayList<Reply>();
//		Reply list1 = new Reply(1, "제목1", "내용1", "익명", getCurrenDate());
//		Reply list2 = new Reply(2, "제목2", "내용2", "익명", getCurrenDate());
//		Reply list3 = new Reply(3, "제목3", "내용3", "익명", getCurrenDate());

//		replies.add(list1);
//		replies.add(list2);
//		replies.add(list3);

	}

	// 등록번호
	public void insertReply(Reply r) {
		r.setRegId(no);
		no++;

		r.setDate(Util.getCurrenDate());

		replies.add(r);
	}

	// 제거
	public void removeReply(Reply r) {
		replies.remove(r);
	}

	//
	public Reply getReplyByRegId(int targetId) {
		for (int i = 0; i < replies.size(); i++) {
			int id = replies.get(i).getRegId();
			if (id == targetId) {
				return replies.get(i);
			}
		}
		return null;
	}
	
	
	public ArrayList<Reply> getReplies() {
		return replies;
	}

	public ArrayList<Reply> getRepliesByParentId(int parentId) {
		ArrayList<Reply> searchedReplies = new ArrayList<>();
		for (int i = 0; i < replies.size(); i++) {
			Reply reply = replies.get(i);
			if (reply.getParentId() == parentId) {
				searchedReplies.add(reply);
			}
		}
		return searchedReplies;
	} 


	public ArrayList<Reply> getreplies() {
		return replies;
	}

}
