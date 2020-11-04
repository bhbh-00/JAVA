import java.util.ArrayList;
import java.util.Scanner;

public class App {

	ReplyDao RDao = new ReplyDao();
	ArticleDao ADao = new ArticleDao();
	MemberDao MDao = new MemberDao();
	Member loginedMember = null;
	LikeitDao LDao = new LikeitDao();

	public void start() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Hello world~!");

		while (true) {

			if (loginedMember == null) {
				System.out.println();
				System.out.println("원하는 명령어를 입력해주세요.");
			} else {
				System.out.println();
				System.out
						.println("명령어 입력 [ " + loginedMember.getMRegId() + " (" + loginedMember.getMRegNN() + ") ] : ");
			}

			String cmd = sc.nextLine();
			// 종료 exit
			if (cmd.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			// 로그인 기능
			if (cmd.equals("help")) {
				System.out.println("Article");
				System.out.println("[add: 게시물 추가 / list : 게시물 목록 조회 / read : 게시물 조회 / search : 검색 / sort : 정렬]");
				System.out.println("Member");
				System.out.println(
						"[signup : 회원가입 / signin : 로그인 / findpass : 비밀번호 찾기 / findid : 아이디 찾기 / logout : 로그아웃 / myinfo : 나의 정보 확인 및 수정]");
			}
			// 게시물추가 add - 등록번호, 제목, 내용 추가
			if (cmd.equals("Article add")) {

				if (!isLogin()) {
					continue;
				} else {

					Article a = new Article();

					System.out.println("게시물의 제목을 입력해주세요.");
					String title = sc.nextLine();
					a.setTitle(title);

					System.out.println("게시물의 내용을 입력해주세요.");
					String body = sc.nextLine();
					a.setBody(body);
					a.setMid(loginedMember.getMRegNum());

					ADao.insertArticle(a);
					System.out.println("게시물이 추가 되었습니다.");
				}

			}
			// 게시물 목록 보기 list
			if (cmd.equals("Article list")) {

				ArrayList<Article> articles = ADao.getArticles();
				printArticles(articles);

			}
			// 수정 update - 제목과 내용을 수정하는 기능
			if (cmd.equals("Article update")) {

				if (!isLogin()) {

					continue;

				} else {
					System.out.println("수정할 게시물을 입력해주세요.");
					int TargetID = Integer.parseInt(sc.nextLine());

					Article Target = ADao.getArticleByRegId(TargetID);

					if (Target == null) {
						System.out.println("해당 게시물이 존재 하지않습니다.");
					} else {
						System.out.println("게시물의 제목을 입력해주세요.");
						String NewTitle = sc.nextLine();
						System.out.println("게시물의 내용을 입력해주세요.");
						String NewBody = sc.nextLine();
						Target.setTitle(NewTitle);
						Target.setBody(NewBody);

						System.out.println("게시물이 수정 되었습니다.");
					}
				}

			}

			// 게시물삭제 delete - 등록번호,제목,내용 삭제 기능

			if (cmd.equals("Article delete")) {

				if (!isLogin()) {

					continue;

				} else {

					ArrayList<Article> articles = ADao.getArticles();

					System.out.println("게시물을 삭제하시겠습니까?");
					System.out.println("삭제 할 게시물의 번호을 입력해주세요.");
					int TargetDelid = Integer.parseInt(sc.nextLine());
					sc.nextLine();
					Article Target = ADao.getArticleByRegId(TargetDelid);

					if (Target == null) {
						System.out.println("해당 게시물은 존재 하지않습니다.");
					} else {
						ADao.removeArticle(Target);
					}
				}

			}
			// 게시물(고유번호,제목,내용) 읽기 기능 + 상세보기
			if (cmd.equals("Article read")) {

				System.out.println("게시물을 선택해주세요.");
				int targetId = Integer.parseInt(sc.nextLine());

				Article target = ADao.getArticleByRegId(targetId);

				if (target == null) {

					System.out.println("게시물이 존재하지 않습니다.");

				} else {

					target.setViews(target.getViews() + 1);
					printArticle(target);

					while (true) {

						System.out.println("상세보기 기능을 선택해주세요. 1.댓글 등록, 2.좋아요, 3. 수정, 4.삭제, 5.목록으로");

						int readCmd = Integer.parseInt(sc.nextLine());

						if (readCmd == 1) {
							Reply r = new Reply();

							System.out.println("댓글 내용을 입력해주세요:");
							String body = sc.nextLine();
							r.setParentId(target.getRegId());
							r.setBody(body);
							r.setNname(loginedMember.getMRegNN());

							RDao.insertReply(r);
							System.out.println("댓글이 등록되었습니다.");
							printArticle(target);

						} else if (readCmd == 2) {
							System.out.println("좋아요");

							if (!isLogin()) {
								continue;
							}

							Likeit like = new Likeit(target.getRegId(), loginedMember.getMRegNum());

							Likeit rst = LDao.getLikeByArticleIdAndMemberId(target.getRegId(),loginedMember.getMRegNum());
							LDao.insertLikeit(like);

							if (rst == null) {
								LDao.insertLikeit(like);
								System.out.println("좋아요가 등록 되었습니다.");
							} else {
								LDao.removeLikeit(rst);
								System.out.println("좋아요가 해제 되었습니다.");
							}
							
							printArticle(target);

						} else if (readCmd == 3) {
							// 수정 -> 로그인 후 사용 가능 -> 수정 후 상세보기 보여주기 printArticle(target)

							if (!isLogin() || isMyArticle(target)) {
								continue;
							}

							System.out.println("게시물의 제목을 입력해주세요.");
							String title = sc.nextLine();
							target.setTitle(title);

							System.out.println("게시물의 내용을 입력해주세요.");
							String body = sc.nextLine();
							target.setBody(body);
							System.out.println("게시물이 수정되었습니다.");
							System.out.println();

							printArticle(target);

						} else if (readCmd == 4) {
							// 삭제
							if (!isLogin()) {
								continue;
							}

							ADao.removeArticle(target);

						} else if (readCmd == 5) {
							break;
						}
					}
				}
			}
			// 검색기능
			if (cmd.equals("Article search")) {

				if (!isLogin()) {

					continue;

				} else {
					System.out.println("검색 항목을 선택해주세요. 1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자");
					int flag = Integer.parseInt(sc.nextLine());
					System.out.println("검색 키워드를 입력해주세요");
					String keyword = sc.nextLine();
					ArrayList<Article> searchedArticles;
					searchedArticles = ADao.getSearchedArticleByflag(flag, keyword);

					printArticles(searchedArticles);
				}
			}
			
			if (cmd.equals("Article sort")) {
				// 좋아요 수와 날짜로 게시물들이 정렬 되어야함
			}
			// =========================== Member ==============================

			// 회원가입 기능
			if (cmd.equals("Member signup")) {
				Member m = new Member();

				System.out.println("==== 회원 가입을 진행합니다 ====");

				System.out.println("아이디를 입력해주세요");
				String Id = sc.nextLine();
				m.setMRegId(Id);
				System.out.println("비밀번호를 입력해주세요");
				String Pw = sc.nextLine();
				m.setMRegPW(Pw);
				System.out.println("닉네임을 입력해주세요");
				String NN = sc.nextLine();
				m.setMRegNN(NN);

				MDao.insertMember(m);
				System.out.println("회원가입이 완료 되었습니다.");

			}
			// 로그인 기능
			if (cmd.equals("Member signin")) {

				System.out.println("==== 로그인 LOGIN ====");

				System.out.println("아이디를 입력해주세요");
				String Id = sc.nextLine();

				System.out.println("비밀번호를 입력해주세요");
				String Pw = sc.nextLine();

				Member member = MDao.getMemberByLoginIdAndPw(Id, Pw);
				if (member == null) {
					System.out.println("비밀번호가 틀렸거나 잘못된 회원정보입니다.");
				} else {
					loginedMember = member;
					System.out.println(loginedMember.getMRegNN() + "님 안녕하세요!");
				}

			}

			if (cmd.equals("Member logout")) {

				if (!isLogin()) {

					continue;

				} else {
					loginedMember = null;
					System.out.println("로그아웃 되었습니다.");
				}
			}
		} // while

	}

	// 게시판리스트출력 -> 기능 : list - 번호,제목,등록날짜,작성자,조회수
	private void printArticles(ArrayList<Article> articleList) {
		for (int i = 0; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			System.out.println("번호 : " + article.getRegId());
			System.out.println("제목 : " + article.getTitle());
			System.out.println("등록날짜 : " + article.getDate());
			Member regMember = MDao.getMemberById(article.getMid());
			System.out.println("작성자 : " + regMember.getMRegNN());
			System.out.println("조회수 : " + article.getViews());
			System.out.println("===================");
		}
	}

	// 게시판리스트출력 -> 기능 : read - 번호,제목,내용,등록날짜,작성자,조회수 + 댓글
	private void printArticle(Article target) {
		System.out.println("==== " + target.getRegId() + "====");
		System.out.println("번호 : " + target.getRegId());
		System.out.println("제목 : " + target.getTitle());
		System.out.println("내용 : " + target.getBody());
		System.out.println("등록날짜 : " + target.getDate());
		Member regMember = MDao.getMemberById(target.getMid());
		System.out.println("작성자 : " + regMember.getMRegNN());
		System.out.println("조회수 : " + target.getBody());

		int Likecnt = LDao.getLikeCount(target.getRegId());
		System.out.println("좋아요 : " + Likecnt);
		System.out.println("===============");
		System.out.println("================댓글==============");

		ArrayList<Reply> replies = RDao.getRepliesByParentId(target.getRegId());
		printReplies(replies);

	}

	// 댓글출력 -> 내용, 작성자 , 등록날짜
	private void printReplies(ArrayList<Reply> replyList) {
		for (int i = 0; i < replyList.size(); i++) {
			Reply reply = replyList.get(i);
			System.out.println("내용 : " + reply.getBody());
			System.out.println("등록날짜 : " + reply.getDate());
			System.out.println("===================");
		}
	}

	// 로그인 후 이용가능
	private boolean isLogin() {
		if (loginedMember == null) {
			System.out.println("로그인이 필요한 기능입니다.");
			return false;
		} else {
			return true;
		}
	}

	private boolean isMyArticle(Article article) {
		if (loginedMember.getMRegNum() != article.getMid()) {
			System.out.println("본인의 게시물만 수정/삭제 가능합니다.");
			return false;
		}
		return true;
	}
}
