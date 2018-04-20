package com.timer.utils;

import java.util.Timer;

public class GlobalVariates{
	public static int hour;
	public static int minute;
	public static int second;
	
	public static Timer timer=new Timer();
	public static Timer getTimer() {
		return timer=new Timer();
	}
}
