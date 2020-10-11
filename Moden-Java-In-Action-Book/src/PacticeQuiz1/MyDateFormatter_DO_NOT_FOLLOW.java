package PacticeQuiz1;

import java.text.SimpleDateFormat;
import java.util.Date;

// https://www.javatpoint.com/java-date-to-string
public class MyDateFormatter_DO_NOT_FOLLOW {

	private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<>();
	public SimpleDateFormat simpleDateFormat;

	public static String format(Date date) {
		SimpleDateFormat simpleDateFormat = getThreadLocalSimpleDateFormat();
		return simpleDateFormat.format(date);
	}

	MyDateFormatter_DO_NOT_FOLLOW() {
		simpleDateFormat = getThreadLocalSimpleDateFormat();
	}

	public static SimpleDateFormat getThreadLocalSimpleDateFormat() {
		SimpleDateFormat simpleDateFormat = simpleDateFormatThreadLocal.get();
		if (simpleDateFormat == null) {
			simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
			simpleDateFormatThreadLocal.set(simpleDateFormat);
		}
		return simpleDateFormat;
	}

	public static void main(String[] args) {

		// ThreadLocal tl = ThreadLocal.withInitial(() -> "Hello");
		Date d = new Date();
		//MyDateFormatter md = new MyDateFormatter();

		System.out.println(MyDateFormatter_DO_NOT_FOLLOW.format(d));

		printDateFormat(d, new DateFormatter());

		// String date = labmdaDateFormat(d, (Date date) -> date);
		// PredicateDateFormatting date =  (Date date) -> date);

	}

	public static void printDateFormat(Date date, DateFormatter df) {

		String output = df.dateFormatting(date);
		System.out.println(output);

	}

	interface PredicateDateFormatting {

		public String dateFormat(Date d);

	}

	public static String labmdaDateFormat(Date date, PredicateDateFormatting p) {

		String output = p.dateFormat(date);
		System.out.println(output);

		return output;
	}

}

interface PredicateDateFormat {

	public String dateFormatting(Date d);

}

class DateFormatter implements PredicateDateFormat {
	public static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<>();

	@Override
	public String dateFormatting(Date date) {
		SimpleDateFormat simpleDateFormat = MyDateFormatter_DO_NOT_FOLLOW.getThreadLocalSimpleDateFormat();
		return simpleDateFormat.format(date);

	}
}
