package com.baiwang.banktax.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期,时间相关工具类
 * 
 * @author hewm
 * @version 1.0
 */
public class DateUtils {

	private static final long ONE_DAY = 86400000l;
	public static SimpleDateFormat d = new SimpleDateFormat("dd");
	public static SimpleDateFormat YM = new SimpleDateFormat("yyyyMM");
	public static SimpleDateFormat YMD = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat Y_M = new SimpleDateFormat("yyyy-MM");
	public static SimpleDateFormat Md = new SimpleDateFormat("MM-dd");
	public static SimpleDateFormat yMd = new SimpleDateFormat("yy-MM-dd");
	public static SimpleDateFormat YMd = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat YMd_cn = new SimpleDateFormat("yyyy年MM月dd日");
	public static SimpleDateFormat YMdhm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static SimpleDateFormat yMdhm = new SimpleDateFormat("yy-MM-dd HH:mm");
	public static SimpleDateFormat YMdhm_cn = new SimpleDateFormat("yyyy年MM月dd HH:mm");
	public static SimpleDateFormat YMdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat yMdhms = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	public static SimpleDateFormat YMdhmsS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static SimpleDateFormat YMdhmsS_cn = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss.SSS",Locale.CHINA);
	public static SimpleDateFormat YMdhmsS_en = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss.SSS",Locale.ENGLISH);
	public static SimpleDateFormat YMdhms_noSpli =new SimpleDateFormat("yyyyMMddHHmmss");

	private static SimpleDateFormat[] sdfs = { YMdhms, yMdhms, YMdhm, yMdhm,
			YMd, yMd, YMd_cn, };
	
	private static void log(String str){
		
	}
	
	public static Date strToDate(String str) {
		if (str != null)
			str = str.trim();
		else
			return null;
		Date date = null;
		for (int i = 0; i < sdfs.length; i++) 
			try {
				date = sdfs[i].parse(str);
				return date;
			} catch (ParseException e) {
			}
		return date;
	}
	
	public static String getDate(SimpleDateFormat sdf) {
		return sdf.format(new Date());
	}
	public static String getLocalDate() {
		return YMdhmsS_en.format(new Date());
	}
	public static Date strToDate(String str,String format) {
		Date date = null;
		if (str != null) {			
			try {
				SimpleDateFormat df = new SimpleDateFormat(format);
				date = df.parse(str);
			} catch (Exception e) {
				log("DateParse or DateFormat Error!");
				//log.error("DateParse Error!");
			}
		}
		return date;
	}
	
	public static Date strToDate(String str,SimpleDateFormat sdf) {
		Date date = null;
		if (str != null) {			
			try {
				date = sdf.parse(str);
			} catch (Exception e) {
				log("DateParse or DateFormat Error!");
				//log.error("DateParse Error!");
			}
		}
		return date;
	}

	public static String dateToStr(Date date,String format) {
		String str = null;
		if (date != null)
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);		 
			str = df.format(date);
		} catch (Exception e) {
			log("DateFormat Error!");
			//log.error("DateParse Error!");
		}
		return str;
	}
	
	public static String dateToStr(Date date,SimpleDateFormat df) {
		String str = null;
		if (date != null)
		try {	 
			str = df.format(date);
		} catch (Exception e) {
			log("DateFormat Error!");
			//log.error("DateParse Error!");
		}
		return str;
	}
	/**
	 * 
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date add(Date date, double i){
	    date = new Date(date.getTime() + ((long)(i * ONE_DAY)));	    
	    return date;
	}
	
	public static java.sql.Timestamp dec(java.sql.Timestamp date, int i){
	    long re = date.getTime() -( i * ONE_DAY);	    
	    return new java.sql.Timestamp(re);
	}
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date add(Date date){
	    return add(date,1);
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date sub(Date date){
	    return add(date,-1);
	}	
	public static int getMonthDayNum(String str){
		int re = 0;
		if (str != null)
			try {
				int month = Integer.parseInt(str.substring(5));				
				month = month==12? 1: (month+1);				
				str = str.substring(0,5)+month+ "-01";					
				Date date = YMd.parse(str);				
				date = sub(date);
				str = dateToStr(date,YMd);
				str = str.substring(8);
				re= Integer.parseInt(str);
			} catch (Exception e) {
				log("DateParse or DateFormat Error!");
				//log.error("DateParse Error!");
			}
	
		return re;
	}
	

	/**
	 * 判断年份是否为闰年
	 * @param year 年份
	 * @return TRUE 闰年 false：不是闰年
	 */
	public static boolean isLeapYear(int year){
		if((year%4==0&&year%100!=0)||year%400==0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断当前月有几天
	 * @param year 年份
	 * @param month 月份
	 * @return
	 */
	public static int calculate(int year, int month)  
    {  
        boolean yearleap = isLeapYear(year);  
        int day;  
		if (yearleap && month == 2)
			day = 29;
		else if (!yearleap && month == 2)
			day = 28;

		else if (month == 4 || month == 6 || month == 9 || month == 11)
			day = 30;
		else
			day = 31;
		return day;
    }  
	
	
//	public static void main(String args[]){
//		System.out.println("===============================");
//
//	}
	
///////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////END//////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
}
