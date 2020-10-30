import java.util.ArrayList;
import java.util.Scanner;

public class createboard {
	static ReplyDao RDao = new ReplyDao();
	static ArticleDao ADao = new ArticleDao();
	static MemberDao MDao = new MemberDao();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Hello world~!");

		while (true) {
			System.out.println();
			System.out.println("원하는 명령어를 입력해주세요.");

			String cmd = sc.next();
			// 종료 exit
			if (cmd.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			// 게시물추가 add - 등록번호, 제목, 내용 추가
			if (cmd.equals("add")) {

				Article a = new Article();

				System.out.println("게시물의 제목을 입력해주세요.");
				String title = sc.next();
				a.setTitle(title);

				System.out.println("게시물의 내용을 입력해주세요.");
				String body = sc.next();
				a.setBody(body);
				a.setNname("익명");

				ADao.insertArticle(a);
				System.out.println("게시물이 추가 되었습니다.");
			}
			// 게시물목록 보기 list -
			if (cmd.equals("list")) {

				ArrayList<Article> articles = ADao.getArticles();
				printArticles(articles);

			}
			// 수정 update - 제목과 내용을 수정하는 기능
			if (cmd.equals("update")) {
				System.out.println("수정할 게시물을 입력해주세요.");
				int TargetID = sc.nextInt();
				Article Target = ADao.getArticleByRegId(TargetID);
				if (Target == null) {
					System.out.println("해당 게시물이 존재 하지않습니다.");
				} else {
					System.out.println("게시물의 제목을 입력해주세요.");
					String NewTitle = sc.next();
					System.out.println("게시물의 내용을 입력해주세요.");
					String NewBody = sc.next();
					Target.setTitle(NewTitle);
					Target.setBody(NewBody);
					System.out.println("게시물이 수정 되었습니다.");
				}
			}

			// 게시물삭제 del - 등록번호,제목,내용 삭제 기능
			if (cmd.equals("del")) {

				ArrayList<Article> articles = ADao.getArticles();

				System.out.println("게시물을 삭제하시겠습니까?");
				System.out.println("삭제 할 게시물의 번호을 입력해주세요.");
				int TargetDelid = sc.nextInt();

				Article Target = ADao.getArticleByRegId(TargetDelid);

				if (Target == null) {
					System.out.println("해당 게시물은 존재 하지않습니다.");
				} else {
					ADao.removeArticle(Target);
				}

			}
			// 게시물(고유번호,제목,내용) 읽기 기능 + 상세보기
			if (cmd.equals("read")) {
				System.out.println("게시물을 선택해주세요.");
				int targetId = sc.nextInt();

				Article target = ADao.getArticleByRegId(targetId);

				if (target == null) {

					System.out.println("게시물이 존재하지 않습니다.");

				} else {

					target.setViews(target.getViews() + 1);
					printArticle(target);

					while (true) {

						System.out.println("상세보기 기능을 선택해주세요. 1.댓글 등록, 2.좋아요, 3. 수정, 4.삭제, 5.목록으로");

						int readCmd = sc.nextInt();

						if (readCmd == 1) {
							Reply r = new Reply();

							System.out.println("댓글 내용을 입력해주세요:");
							String body = sc.next();
							r.setParentId(target.getRegId());
							r.setBody(body);
							r.setNname("익명");

							RDao.insertReply(r);
							System.out.println("댓글이 등록되었습니다.");
							printArticle(target);

						} else if (readCmd == 2) {
							System.out.println("좋아요");
						} else if (readCmd == 3) {
							System.out.println("수정");
						} else if (readCmd == 4) {
							System.out.println("삭제");
						} else if (readCmd == 5) {
							break;
						}
					}
				}
			}
			// 검색기능
			if (cmd.equals("search")) {
				System.out.println("검색 항목을 선택해주세요. 1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자");
				int flag = sc.nextInt();
				System.out.println("검색 키워드를 입력해주세요");
				String keyword = sc.next();
				ArrayList<Article> searchedArticles;
				searchedArticles = ADao.getSearchedArticleByflag(flag, keyword);

				printArticles(searchedArticles);
			}
			// 회원가입 기능
			if (cmd.equals("signup")) {
				Member m = new Member();

				System.out.println("==== 회원 가입을 진행합니다 ====");
				System.out.println(
						);
				System.out.println("아이디를 입력해주세요");
				String Id = sc.next();
				m.setMRegId(Id);
				System.out.println("비밀번호를 입력해주세요(숫자로만)");
				int PW = sc.nextInt();
				m.setMRegPW(PW);
				System.out.println("닉네임을 입력해주세요");
				String NN = sc.next();
				m.setMRegNN(NN);
				
				MDao.insertsignup(m);
				
				System.out.println("회원가입이 완료 되었습니다.");
			}
			// 로그인
			if (cmd.equals("signin")) {
				System.out.println("======= 로그인 =======");
				System.out.println();
				System.out.print("아이디를 입력해주세요. : ");
				String id = sc.next();
				System.out.print("비밀번호를 입력해주세요. : ");
				String pw = sc.next();
				
			 
			}
		}

	}

	private static void printArticles(ArrayList<Article> articleList) {
		for (int i = 0; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			System.out.println("번호 : " + article.getRegId());
			System.out.println("제목 : " + article.getTitle());
			System.out.println("등록날짜 : " + article.getDate());
			System.out.println("작성자 : " + article.getNname());
			System.out.println("조회수 : " + article.getViews());
			System.out.println("===================");
		}
	}

	private static void printReplies(ArrayList<Reply> replyList) {
		for (int i = 0; i < replyList.size(); i++) {
			Reply reply = replyList.get(i);
			System.out.println("내용 : " + reply.getBody());
			System.out.println("작성자 : " + reply.getNname());
			System.out.println("등록날짜 : " + reply.getDate());
			System.out.println("===================");
		}
	}

	private static void printArticle(Article target) {
		System.out.println("==== " + target.getRegId() + " ====");
		System.out.println("번호 : " + target.getRegId());
		System.out.println("제목 : " + target.getTitle());
		System.out.println("내용 : " + target.getBody());
		System.out.println("등록날짜 : " + target.getDate());
		System.out.println("조회수 : " + target.getBody());
		System.out.println("===============");
		System.out.println("================댓글==============");

		ArrayList<Reply> replies = RDao.getRepliesByParentId(target.getRegId());
		printReplies(replies);

	}
}