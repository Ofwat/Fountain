package uk.gov.ofwat.fountain.api;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



public enum PopularityPeriod {
	WEEK,  		//last rolling seven days
	MONTH, 		//last rolling current month days	
	DAY,  		//current day 12am - 11:59pm
	YEAR,		//Last 365 approx. days (depending on the year)
	ALL_TIME;	//The all time most viewed - this is stored on the actual entities record as the actual view count is transient.   
	
	public static int calculateDaysInPastFromNow(PopularityPeriod period){
		Date currentDate = new Date();
		Calendar.getInstance().setTime(currentDate);
		Calendar mycal = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		int dayCount = 0;
		switch(period){
			case WEEK:
				dayCount = 7;
				return dayCount;
			case MONTH:
				dayCount = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
				return dayCount;
			case DAY:
				dayCount = 0;
				return dayCount;
			case YEAR:
				dayCount = mycal.getActualMaximum(Calendar.DAY_OF_YEAR);
				return dayCount;
			default:
				return 1;
		}

	}
	
}
