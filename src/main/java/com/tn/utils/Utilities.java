package com.tn.utils;

import java.util.Date;

public class Utilities {
	
	public static String emailDateTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "seleniumpanda" + timeStamp + "@gmail.com";
	}
	
	public static final int IMPLICIT_WAIT_TIME = 100;
	public static final int PAGE_lOAD_TIMEOUT = 100;
	public static final int SCRIPT_TIMEOUT = 1000;
	
	

}
