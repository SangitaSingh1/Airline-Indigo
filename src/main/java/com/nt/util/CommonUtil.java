package com.nt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	public static Date convertStringDateToDate(String dateString) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
			System.out.println("Converted Date: " + date);
		} catch (ParseException e) {
			System.out.println("Error parsing date: " + e.getMessage());
		}

		return date;

	}
}
