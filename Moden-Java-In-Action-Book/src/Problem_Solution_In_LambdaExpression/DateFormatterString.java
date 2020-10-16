package Problem_Solution_In_LambdaExpression;

import java.text.SimpleDateFormat;
import java.util.Date;

// FOR MORE DEPTH KNOWLEDGE PLEASE STUDY THIS ARTICLE
// https://javarevisited.blogspot.com/2014/02/10-example-of-lambda-expressions-in-java8.html
// https://objectcomputing.com/resources/publications/sett/february-2013-java-8-project-lambda

public class DateFormatterString {
	public static void main(String[] args) {
		
		String pattern = "dd-MMMM-yyyy";
		getDate(new Date(), (Date d) -> new SimpleDateFormat(pattern).format(d));

	}

	private static void getDate(Date input, iDateFormatter code) {
		String strDate = code.getStringDate(input);
		System.out.println("Today Date using lambda: " + strDate);
	}

}

interface iDateFormatter {
	public String getStringDate(Date d);
}

