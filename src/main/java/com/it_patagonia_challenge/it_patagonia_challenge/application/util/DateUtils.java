package com.it_patagonia_challenge.it_patagonia_challenge.application.util;

import java.time.LocalDate;
import java.util.Map;


public class DateUtils {

	public static LocalDate getDateThirtyDaysBeforeNow() {
		return LocalDate.now().minusDays(30);
	}

}
