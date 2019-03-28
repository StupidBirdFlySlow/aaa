package design;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TimeTest {
	
	@Test
	public void timeTest(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		String format = sdf.format(calendar.getTime());
		System.out.println(format);
		calendar.set(Calendar.DAY_OF_WEEK, 7);
		String format2 = sdf.format(calendar.getTime());
		System.out.println(format2);
	}
	@Test
	public void timeTest2(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int maximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, maximum);
		
		String format = sdf.format(calendar.getTime());
		System.out.println(format);
		int minmum = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, minmum);
		String format2 = sdf.format(calendar.getTime());
		System.out.println(format2);
	}
}
