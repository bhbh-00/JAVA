import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	// 등록날짜
	public static String getCurrenDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();

		String t1 = format1.format(time);

		return t1;
	}

}
