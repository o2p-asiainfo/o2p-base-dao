/*
 * @(#)DateUitl.java        2013-3-1
 *
 * Copyright (c) 2013 asiainfo-linkage
 * All rights reserved.
 *
 */

package com.asiainfo.integration.o2p.basedao.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 类名称<br>
 * 这里是类的描述区域，概括出该的主要功能或者类的使用范围、注意事项等
 * <p>
 * @version 1.0
 * @author Administrator 2013-3-1
 * <hr>
 * 修改记录
 * <hr>
 * 1、修改人员:    修改时间:<br>       
 *    修改内容:
 * <hr>
 */

public final class DateUtil {
	
	private DateUtil(){}
	
	public static String getYear(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy"); 
		return formatter.format(date); 
	}
	
	public static String getYearMonth(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM"); 
		return formatter.format(date); 
	}
	
	public static String getYearMonthDay(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd"); 
		return formatter.format(date); 
	}
	
	public static String getYearMonthDayHour(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddkk"); 
		return formatter.format(date); 
	}
	
	public static String getYearMonthDayHourMinute(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddkkmm"); 
		return formatter.format(date); 
	}
	
	public static String getYearMonthDayHourMinuteSecond(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddkkmmss"); 
		return formatter.format(date);
	}
	
	/**
	 * 时间计算，加减天数
	 * @param days
	 * @return
	 */
	public static Date getDateByModifyDay(int days){
	    Calendar calendar = Calendar.getInstance();
	    int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day - days);
		return calendar.getTime();
	}
	
	
	public static String getLocalCurrentTime(){
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Date now = new Date();
		DateFormat dataFormat = DateFormat.getDateTimeInstance();
		return dataFormat.format(now);
	}
	
}