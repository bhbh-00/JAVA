import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		// 첫번째 과제 - 페이지 5개 출력하기
		// 두번째 과제 - 현재 페이지 표현하기
		// 세번째 과제 - 현재 페이지가 속한 페이지 블럭 출력하기
		/*
		 * 네번째 과제 - 시작페이지와 마지막 페이지(13 이라고 가정)를 이용해 이전/다음 페이지 블록의 유무를 알려주고 현재 페이지가 시작페이지와
		 * 마지막 페이지를 못넘어가게 하기
		 */
		// 다섯번째 과제 - 페이지와 게시물 출력 연동하기

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

		int startPageNo = 1; // 시작 페이지 번호
		int ItemsCntPage = 3; // 페이지당 출력 게시물 갯수
		int PageCntPerBlock = 5; // 한 페이지 블럭 당 페이지 갯수

		int endPageNo = (int) Math.ceil(((double) totalCntOfItems / ItemsCntPage)); // 마지막 페이지 번호(올림 매서드)
		int currentpageBlock = (int) Math.ceil(((double) currentpageNo / PageCntPerBlock)); // 현재 페이지 블럭

		int startPageNoInBlock = (currentpageBlock - 1) * PageCntPerBlock + 1; // 현재 페이지블록의 시작 페이지 번호
		int endPageNoInBlock = startPageNoInBlock + PageCntPerBlock - 1; // 현재 페이지블록의 마지막 페이지 번호
//		System.out.print(startPageNoInBlock);
//		System.out.print(endPageNoInBlock);

		// 현재 페이지가 시작페이지보다 작으면 안됨
		if (startPageNoInBlock < startPageNo) {
			startPageNoInBlock = startPageNo;
		}

		// 현재 페이지가 마지막페이지보다 크면 안됨
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
		for (int i = startIndex; i < endIndex; i++) {
			System.out.println("======================================");
			System.out.println("번호 : " + articles.get(i).getRegId());
			System.out.println("제목 : " + articles.get(i).getTitle());
			System.out.println("내용 : " + articles.get(i).getBody());
			System.out.println("======================================");
		}
		
		// 페이지 블록 출력
		for (int i = startPageNoInBlock; i <= endPageNoInBlock; i++) {

			if (i == currentpageNo) {
				System.out.print("[" + i + "] ");
			} else {
				System.out.print(i + " ");
			}
		}

	}
}
