import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Test2 {
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Num> nlist = new ArrayList<Num>();

		list.add(4);
		list.add(1);
		list.add(2);
		list.add(5);
		list.add(3);

		nlist.add(new Num(4, 5));
		nlist.add(new Num(1, 1));
		nlist.add(new Num(2, 4));
		nlist.add(new Num(5, 3));
		nlist.add(new Num(3, 2));
		
		printList(list);
		Collections.sort(list);
		Collections.sort(list, Collections.reverseOrder());
		printList(list);
		
		Comparator1 comp = new Comparator1();
		Collections.sort(nlist, comp);
		
		for (int i = 0; i < nlist.size(); i++) {
			System.out.println(nlist.get(i).n1);
		}
		
	}
	

	public static void printList(ArrayList<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
class Comparator1 implements Comparator<Num> {

	@Override
	public int compare(Num o1, Num o2) {
		if(o1.n1 > o2.n1) { // 왼쪽 값이 더 크면 자리를 바꿔라 -> 오름차순 <-> 오른쪽 값이 더 클때 자리를 바꿔라 -> 내림차순
			
			return 1; // 0, 1 바꿔라
		}
		return -1; // -1 바꾸지마라 
	}
	//양수(왼쪽이 뒤로), 음수(오른쪽이 뒤로), 0리턴
	//왼쪽이 더 > 바꿔라는 오름차순 오른쪽이 더 < 내림차순
}

class Num {

	int n1;
	int n2;

	Num(int a, int b) {
		n1 = a;
		n2 = b;
	}
}


