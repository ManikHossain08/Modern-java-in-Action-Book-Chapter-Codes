package PacticeQuiz1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

// https://objectcomputing.com/resources/publications/sett/february-2013-java-8-project-lambda
public class SimpleDateFormat_LE {

	public static void main(String[] args) {
		
		String pattern = "dd-MMMM-yyyy";
		
		// use an instance of SimpleDateFormat to convert a Data to a String
	    SimpleDateFormat fmt = new SimpleDateFormat(pattern);
	    
	    // SOLUTION # 01
	    Function<Date, String> strToDate1 = d -> fmt.format(d); // Lambda expression
	    System.out.println("Using Function<Date, String>, Today Date1: " + BuiltInFunction_getDate(new Date(), strToDate1));
	    
	    // SOLUTION # 02
	    Function<Date, String> strToDate2 = fmt::format; // method reference
	    System.out.println("Using Function<Date, String>, Today Date2: " + BuiltInFunction_getDate(new Date(), strToDate2));
	    
	    // SOLUTION # 03, my developed code using my own interface
	    iDateFormatter interfaceImpl = (Date d) -> new SimpleDateFormat(pattern).format(d);
	    System.out.println("My developed code, Today Date3: " + interfaceImpl.getStringDate(new Date()));
	    getDate(new Date(), interfaceImpl); // GOOD ONE
	    
	    // SOLUTION # 04, my developed code using my own interface
	    getDate(new Date(), (Date d) -> new SimpleDateFormat(pattern).format(d)); // GOOD ONE
	    
	    
	    // this solution used different class and static method which return string date from separate java file.
	    iDateFormatter idate2 = (Date d) ->  MyDateFormatter_DO_NOT_FOLLOW.format(d);
	    getDate(new Date(), idate2);
	    
	    // SOLUTION # 05 USING LAMBDA BODY (A BLOCK OF STATEMENT)
	    iDateFormatter lambdaBody = (Date d) -> {
	    	String p = "dd-MMMM-yyyy";
	    	SimpleDateFormat sdf = new SimpleDateFormat(p);
		    return sdf.format(d);
	    };
	    
	    Date bDay = null;
	    try {
			 bDay = new SimpleDateFormat("dd-MM-yyyy").parse("10-01-1989");
		} catch (ParseException e) {

			e.printStackTrace();
		}  
	    System.out.print("From Lambda body Date: "+lambdaBody.getStringDate(bDay));
	    
	    
	    // *** SOLUTION 3 & 4 ARE MORE ACCURATE ONE ***
		
	}
	
	
	private static String BuiltInFunction_getDate(Date date, Function<Date, String> strToDate) {
		return strToDate.apply(date);
		
	}


	private static void getDate(Date input, iDateFormatter code) {
		String strDate = code.getStringDate(input);
		System.out.println("Today Date using lambda: " +strDate);
		
	}
	
	
	interface iDateFormatter {
		public String getStringDate(Date d);
	}
	

}
