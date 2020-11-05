import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Test {
	public static void main(String[] args) {

		ArrayList<String> StringList = new ArrayList<String>();
		ArrayList<Integer> intList = new ArrayList<Integer>();

		StringList.add("A");
		StringList.add("B");
		StringList.add("C");
		StringList.add("D");
		StringList.add("E");
		StringList.add("F");
		StringList.add("AD");

		Collections.sort(StringList);

		System.out.println(StringList);

		for (int i = 1; i < 11; i++) {
			intList.add(i);
		}

		Collections.sort(intList);
		System.out.println(intList);

		Collections.sort(intList, new AscendingInteger());
		Collections.sort(StringList, new AscendingString());

	}
} 
class AscendingString implements Comparator<String> {
	// Override
	public int compare(String a, String b) {
		return b.compareTo(a);
	}
}

class AscendingInteger implements Comparator<Integer> {
	// Override
	public int compare(Integer a, Integer b) {
		return b.compareTo(a);
	}
}
