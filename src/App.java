import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class App {

	ReplyDao RDao = new ReplyDao();
	ArticleDao ADao = new ArticleDao();
	MemberDao MDao = new MemberDao();
	Member loginedMember = null;
	LikeitDao LDao = new LikeitDao();

	public void start() {

		Scanner sc = new Scanner(System.in);

		String[] Article = { "add : 게시물 추가", "list : 게시물 목록 조회", "read : 게시물 조회", "search : 검색", "sort : 정렬",
				"page : 페이지" };
		String[] Member = { "signup : 회원가입", "signin : 로그인", "findpass : 비밀번호 찾기", "findid : 아이디 찾기", "logout : 로그아웃",
				"myinfo : 나의 정보 확인 및 수정" };

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
				System.out.println();
				System.out.println("========= Article =========");
				for (int i = 0; i < Article.length; i++) {
					System.out.println((i + 1) + ". " + Article[i]);
				}
				System.out.println();
				System.out.println("========= Member =========");
				for (int i = 0; i < Member.length; i++) {
					System.out.println((i + 1) + ". " + Member[i]);
				}
			}
			// ========================================================= Article
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
				printArticles(articles, 1);

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

							Likeit rst = LDao.getLikeByArticleIdAndMemberId(target.getRegId(),
									loginedMember.getMRegNum());

							if (rst == null) {
								// 좋아요
								Likeit like = new Likeit(target.getRegId(), loginedMember.getMRegNum());
								LDao.insertLikeit(like);
								target.setLikeCnt(target.getLikeCnt() + 1);
								System.out.println("좋아요를 체크했습니다.");
							} else {
								// 좋아요 해제-삭제
								LDao.removeLikeit(rst);
								System.out.println("좋아요가 해제 되었습니다.");
								target.setLikeCnt(target.getLikeCnt() - 1);
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

					printArticles(searchedArticles, 1);
				}
			}
			// 1105 ~ 1106 - 정렬
			if (cmd.equals("Article sort")) {
				System.out.println("정렬 대상을 선택해주세요. : (like : 좋아요,  hit : 조회수)");
				String sortType = sc.nextLine();
				System.out.println("정렬 방법을 선택해주세요. : (asc : 오름차순,  desc : 내림차순)");
				String sortOrder = sc.nextLine();
				MycompArticle comp1 = new MycompArticle();
				comp1.sortOrder = sortOrder;
				comp1.sortType = sortType;

				// 조회수로 오름차순, 내림차순
				ArrayList<Article> article = ADao.getArticles();
				Collections.sort(article, comp1);
				printArticles(article, 1);

			}
			// 1106 - 페이징
			if (cmd.equals("Article page")) {

				ArrayList<Article> article = ADao.getArticles();

				String[] Page = { "prev : 이전 ", "next : 다음", "prevPage : 이전페이지", "nextPage : 다음페이지",
						"count : 페이지당 게시물 수", "go : 선택 ", "back : 뒤로가기" };
				System.out.println("원하시는 명령어를 입력해주세요.");
				System.out.println();		
				for (int i = 0; i < Page.length; i++) {
					System.out.println((i + 1) + ". " + Page[i]);
				}
				System.out.println();
				int currentpageNo = 1;
				int currentpageBlock = 1;
				int PageCntPerBlock = 5;
				
				printArticles(article, currentpageNo);


				while (true) {
					String Pagecmd = sc.nextLine();

					// 다음
					if (Pagecmd.equals("next")) {
						currentpageNo++;
						printArticles(article, currentpageNo);
					}
					// 뒤로가기
					else if (Pagecmd.equals("back")) {
						System.out.print("나가기");
						break;
					}
					// 이전
					else if (Pagecmd.equals("prev")) {
						currentpageNo--;
						printArticles(article, currentpageNo);
					}
					// 이전블럭
					else if (Pagecmd.equals("prevPage")) {
						currentpageNo = currentpageNo - PageCntPerBlock;
					}
					// 다음블럭
					else if (Pagecmd.equals("nextPage")) {
						currentpageNo = currentpageNo + PageCntPerBlock;
					}
					// 페이지당 게시물 수 - 페이지당 게시물이 입력한 수만큼 출력
					else if (Pagecmd.equals("count")) {
						System.out.print("원하시는 페이지 갯수를 선택해주세요 : ");
						int count = Integer.parseInt(sc.nextLine());
					}
					//선택
					else if (Pagecmd.equals("go")) {
						System.out.print("원하시는 페이지를 선택해주세요 : ");
						currentpageNo = Integer.parseInt(sc.nextLine());
					}
					printArticles(article, currentpageNo);
				}
			}
			// ========================================================= Member

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

	// ========================================================= 함수(재활용 -> 연산)

	// 게시판리스트출력 -> 기능 : list - 번호,제목,등록날짜,작성자,조회수
	private void printArticles(ArrayList<Article> articleList, int currentpageNo) {

		int totalCntOfItems = articleList.size(); // 전체 게시물 개수

		int startPageNo = 1; // 시작 페이지 번호
		int ItemsCntPage = 3; // 페이지당 출력 게시물 갯수
		int PageCntPerBlock = 5; // 한 페이지 블럭 당 페이지 갯수

		int endPageNo = (int) Math.ceil(((double) totalCntOfItems / ItemsCntPage)); // 마지막 페이지 번호(올림 매서드)

		// 현재 페이지가 시작페이지보다 작으면 안됨
		if (currentpageNo < startPageNo) {
			currentpageNo = startPageNo;
		}

		// 현재 페이지가 마지막페이지보다 크면 안됨
		if (currentpageNo > endPageNo) {
			currentpageNo = endPageNo;
		}

		int currentpageBlock = (int) Math.ceil(((double) currentpageNo / PageCntPerBlock)); // 현재 페이지 블럭
		int startPageNoInBlock = (currentpageBlock - 1) * PageCntPerBlock + 1; // 현재 페이지블록의 시작 페이지 번호
		int endPageNoInBlock = startPageNoInBlock + PageCntPerBlock - 1; // 현재 페이지블록의 마지막 페이지 번호

		// 페이지 번호가 마지막 페이지를 넘으면 안됨
		if (endPageNoInBlock > endPageNo) {
			endPageNoInBlock = endPageNo;
		}
		// 해당 페이지의 게시물 목록의 첫 인덱스
		int startIndex = (currentpageNo - 1) * ItemsCntPage;

		// 해당 페이지의 게시물 목록의 마지막 인덱스
		int endIndex = startIndex + ItemsCntPage;

		// 페이지의 마지막 인덱스가 저장소의 마지막 인덱스보다 크면 안됨
		if (endIndex > totalCntOfItems) {
			endIndex = totalCntOfItems;
		}
		
		for (int i = startIndex; i < endIndex; i++) {
			Article article = articleList.get(i);
			System.out.println("번호 : " + article.getRegId());
			System.out.println("제목 : " + article.getTitle());
//			System.out.println("등록날짜 : " + article.getDate());

//			Member regMember = MDao.getMemberById(article.getMid());
//			System.out.println("작성자 : " + regMember.getMRegNN());
			System.out.println("조회수 : " + article.getViews());
			System.out.println("좋아요 : " + article.getLikeCnt());
			System.out.println("===================");
		}

		for (int i = startPageNoInBlock; i <= endPageNoInBlock; i++) {
			if (i == currentpageNo) {
				System.out.print("[" + i + "] ");
			} else {
				System.out.print(i + " ");
			}
			System.out.print("");
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
		System.out.println("조회수 : " + target.getViews());
		System.out.println("좋아요 : " + target.getLikeCnt());
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

// =========================================================  정렬
// 좋아요 / 조회수 수 순서대로 오름차순,  내림차순
class MycompArticle implements Comparator<Article> {

	String sortOrder = "asc";
	String sortType = "hit";

	@Override
	public int compare(Article o1, Article o2) {
		int c1 = 0;
		int c2 = 0;

		if (sortOrder.equals("hit")) {
			c1 = o1.getViews();
			c2 = o2.getViews();
		} else if (sortOrder.equals("hit")) {
			c1 = o1.getLikeCnt();
			c2 = o2.getLikeCnt();
		}

		if (sortOrder.equals("asc")) {
			if (c1 > c2) {
				return 1; // 양수로 하는 게 확실
			}
			return -1;
		} else {
			if (c1 < c2) {
				return 1; // 양수로 하는 게 확실
			}
			return -1;
		}
	}
}