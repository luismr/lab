/**
 * 
 */
package notifications.data.domain.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Frequency of Subscription Notifications
 * 
 * @author luismr
 *
 */
public enum Frequency {

	IMMEDIATE, DAILY, WEEKLY, MONTHLY;

	public Date calculateNextDelivery() {
		return calculateNextDelivery(this);
	}
	
	/**
	 * Calculate next delivery
	 * 
	 * IMMEDIATE: in 5 minutes
	 * DAILY: next 08:00 am
	 * WEEKLY: next monday 07:00
	 * MONTHLY: next month 1st 06:00
	 * 
	 * @param frequency
	 * @return
	 */
	protected Date calculateNextDelivery(final Frequency frequency) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());

		if (DAILY.equals(frequency)) {
			if (calendar.get(Calendar.HOUR_OF_DAY) > 8 && calendar.get(Calendar.MINUTE) >= 0) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}

			calendar.set(Calendar.HOUR_OF_DAY, 8);
			calendar.set(Calendar.MINUTE, 00);
		} else if (WEEKLY.equals(frequency)) {
			if ((calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
					|| (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY 
							&& calendar.get(Calendar.HOUR_OF_DAY) >= 7 ) 
							&& calendar.get(Calendar.MINUTE) >= 0) {
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				calendar.add(Calendar.WEEK_OF_YEAR, 1);
			}
			
			calendar.set(Calendar.HOUR_OF_DAY, 7);
			calendar.set(Calendar.MINUTE, 00);
		} else if (MONTHLY.equals(frequency)) {
			if ((calendar.get(Calendar.DAY_OF_MONTH) > 1)
					|| (calendar.get(Calendar.DAY_OF_MONTH) == 1 
							&& calendar.get(Calendar.HOUR_OF_DAY) >= 6) 
							&& calendar.get(Calendar.MINUTE) >= 0) {
				calendar.add(Calendar.MONTH, 1);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
			}
			
			calendar.set(Calendar.HOUR_OF_DAY, 6);
			calendar.set(Calendar.MINUTE, 00);			
		} else {
			calendar.add(Calendar.MINUTE, 5);
		}
		
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}

}
