import java.util.ArrayList;
import java.util.Scanner;

public class Test3 {
	public static void main(String[] args) {
		// 0 = 현재 페이지 1 = 현재의 이전페이지 2 = 현재의 다음페이지
		ArrayList<Article> articles = new ArrayList<>();

		for (int i = 1; i <= 50; i++) {
			Article a1 = new Article();
			a1.setRegId(i);
			a1.setTitle("제목" + i);
			a1.setBody("내용" + i);

			articles.add(a1);
		}

		Scanner sc = new Scanner(System.in);

		int totalCntOfItems = articles.size(); // 전체 게시물 개수

		System.out.println("입력");
		int currentpageNo = sc.nextInt();
		System.out.println(currentpageNo);

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

		// 페이지별 게시물 출력
		int cmd = sc.nextInt();

//		for (int i = startIndex; i < endIndex; i++) {
//			System.out.println("======================================");
//			System.out.println("번호 : " + articles.get(i).getRegId());
//			System.out.println("제목 : " + articles.get(i).getTitle());
//			System.out.println("내용 : " + articles.get(i).getBody());
//			System.out.println("======================================");
//		}
//
//		// 페이지 블록 출력
//		for (int i = startPageNoInBlock; i <= endPageNoInBlock; i++) {
//			if (i == currentpageNo) {
//				System.out.print("[" + i + "] ");
//			} else {
//				System.out.print(i + " ");
//			}
//		}

	}
}
