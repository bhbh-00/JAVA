import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Json {
	
	public static void main(String[] args) {

		/*
		 * [] = ArrayList {} = JSONObject
		 */

		String json_str = "[0" + "{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";

		// JSON ArrayList로 사용 -> 배열
		JSONArray array = (JSONArray) JSONValue.parse(json_str);
//		System.out.println(array.get(1)); // 출력
		// JSON Object -> 맵
		JSONObject obj = (JSONObject) array.get(1);
//		System.out.println(obj.get("1")); // 출력
//		System.out.println("Field \" 1\""); // 출력

		JSONObject obj2 = new JSONObject();
		String jsonText;

		obj2.put("id", new Integer(1));
		obj2.put("title", "안녕하세요");
		jsonText = obj2.toString();

		System.out.println("Encode a JSON Object - to String");
		System.out.print(jsonText);

		JSONObject ojoj = (JSONObject) JSONValue.parse(jsonText);
		String s1 = (String) ojoj.get("name");
		long l1 = (long) ojoj.get("num");
		Double d1 = (Double) ojoj.get("balance");
		boolean b1 = (boolean) ojoj.get("is_vip");
		
		System.out.println(s1);
		System.out.println(l1);
		System.out.println(d1);
		System.out.println(b1);

//	      StringWriter out = new StringWriter();
//	      obj2.writeJSONString(out);       
//	      jsonText = out.toString();

//	      System.out.println("\nEncode a JSON Object - Streaming");       
//	      System.out.print(jsonText);

//		try {
//			// 파일 객체 생성
//			File file = new File("C:\\Users\\world\\Desktop\\javaprogramming\\FileIO\\Sample.txt");
//			// 입력 스트림 생성
//			FileReader filereader = new FileReader(file);
//			int singleCh = 0;
//			while ((singleCh = filereader.read()) != -1) {
//				System.out.print((char) singleCh);
//			}
//			filereader.close();
//		} catch (FileNotFoundException e) {
//			// TODO: handle exception
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//
//		try {
//			// 파일 객체 생성
//			File file = new File("C:\\Users\\world\\Desktop\\javaprogramming\\FileIO\\Sample.txt");
//			// 입력 스트림 생성
//			FileReader filereader = new FileReader(file);
//			// 입력 버퍼 생성
//			BufferedReader bufReader = new BufferedReader(filereader);
//			String line = "";
//			while ((line = bufReader.readLine()) != null) {
//				System.out.println(line);
//			}
//			// .readLine()은 끝에 개행문자를 읽지 않는다.
//			bufReader.close();
//		} catch (FileNotFoundException e) {
//			// TODO: handle exception
//		} catch (IOException e) {
//			System.out.println(e);
//		}

		// XML
		// JSON 라이브러리 - https://code.google.com/archive/p/json-simple/downloads
		// JSON 해석 -> Decoding Examples
		// JSON 쓰기 -> Encoding Examples

	}
}
