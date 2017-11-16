package test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import db.SqliteDataSeed;
import ui.Util;

public class Test {
	public static void main(String[] args) throws Exception {
		Test test = new Test();
		test.testDatesIntv();
			
		
	
	}
	public void testDatesIntv() {
		List<Date> dates = Util.getDaysBetweenDates(Date.valueOf("2017-2-24"),Date.valueOf("2017-5-1"));
		for(Date d : dates) {
			System.out.println(d.toString());
		}
	}
	
}
