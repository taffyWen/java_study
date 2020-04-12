package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormateUtil {

	
	public static void main(String[] args) {
		
		System.out.println(dateToString(new Date()));
	}
	
	public static String dateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}
	
}
