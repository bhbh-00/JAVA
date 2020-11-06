import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		// 첫번째 과제 - 페이지 5개 출력하기
		Scanner sc = new Scanner(System.in);

		int totalCntOfItems = 20;	// 전체 게시물 개수	
		
		int currentpageNo = sc.nextInt(); 
		
		int startPageNo = 1; // 시작 페이지 번호
		int ItemsCntPage = 3; // 페이지당 출력 게시물 갯수
		int PageCntPerBlock = 5; // 한 페이지 블럭 당 페이지 갯수
		int endPageNo = (int)Math.ceil(((double)totalCntOfItems / ItemsCntPage)); //마지막 페이지 번호(반올림 매서드)
		int currentpageBlock = (int)Math.ceil(((double)currentpageNo / PageCntPerBlock)) ; // 현재 페이지 블럭
		int startPageNoInBlock = (currentpageBlock - 1 ) * PageCntPerBlock + 1;
		int endPageNoInBlock = startPageNoInBlock + PageCntPerBlock - 1;
//		System.out.print(startPageNoInBlock);
//		System.out.print(endPageNoInBlock);
		
		for(int i = startPageNoInBlock; i <= endPageNoInBlock; i++) {			
			
			// 두번째 과제 - 현재 페이지 표현하기
			if(i == currentpageNo) {
				System.out.print("[" + i + "] ");
			} else {
				System.out.print(i + " ");
			}
		}
		
		
		// 세번째 과제 - 현재 페이지가 속한 페이지 블럭 출력하기
		/* 네번째 과제 - 시작페이지와 마지막 페이지(13 이라고 가정)를 이용해
		 * 이전/다음 페이지 블록의 유무를 알려주고 현재 페이지가 시작페이지와 마지막 페이지를 못넘어가게 하기*/ 
		//다섯번째 과제 -  페이지와 게시물 출력 연동하기
		
	}
} 

