import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//좋아요를 입력하면 +1
		// 작성자가 기억
		
		System.out.println("좋아요를 입력해주세요.");
		Scanner sc = new Scanner(System.in);
		
		int good_num = 0; // 이걸을 어디에 둘까
		
		int cmd = sc.nextInt();
		
		if(cmd == 2 ) {
			good_num = good_num +1 ;
			System.out.println("좋아요" + good_num);
		} 
		if(cmd == 0 ) { 
			good_num = good_num - 1 ;
			System.out.println("좋아요" + good_num);
		}
	}
	
	// 객체를 만들어서 사용 1.id 2.좋아요 3.좋아요를 한 회원
	// 어떤 게시물의 누구의 좋아요인지 -> 부모ID
	// 좋아요의 Dao도 만들어줌. public void insert 만들어줌 / 현재날짜 만들기 / 
	// App으로 돌아가서 1. 좋아요를 
}
