import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

		r.setDate(getCurrenDate());

		replies.add(r);
	}

	// 제거
	public void removeReply(Reply r) {
		replies.remove(r);
	}

	// 등록날짜
	private String getCurrenDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();

		String t1 = format1.format(time);

		return t1;
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


//	// 제목과 내용으로 검색
//	public ArrayList<Reply> getSearchedReplyByflag(int flag, String Keyword) {
//
//		ArrayList<Reply> SearchedReply = new ArrayList<>();
//
//		for (int i = 0; i < replies.size(); i++) {
//			Reply replies2 = replies.get(i);
//
//			String str = replies2.getPropertiesByFlag(flag); // Properties : 속성
//			if (str.contains(Keyword)) {
//				SearchedReply.add(replies2);
//			}
//		}
//
//		return SearchedReply;
//	}

//	// 내용으로 검색
//	public ArrayList<Reply> getSearchedReplyByBody(String Keyword) {
//		ArrayList<Reply> SearchedReply = new ArrayList<>();
//
//		for (int i = 0; i < replies.size(); i++) {
//			Reply replies2 = replies.get(i);
//
//			String str = replies.getBody(); // 각 게시물 내용
//			if (str.contains(Keyword)) {
//				SearchedReply.add(replies);
//			}
//		}
//
//		return SearchedReply;
//
//	}
	
	public ArrayList<Reply> getreplies() {
		return replies;
	}

}
