package com.wdx.common.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class TimeUtil {
	
	/**
	 * string转时间
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str) {
		Date date = null;
		StringBuilder sb = new StringBuilder();
		if (str.contains("/")) {
			sb.append("yyyy/MM/dd");
		} else {
			sb.append("yyyy-MM-dd");
		}
		if (str.contains(":")) {
			sb.append(" HH:mm");
		}
		SimpleDateFormat format = new SimpleDateFormat(sb.toString(),
				Locale.CHINA);
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	
	public static String string2ChinaDateString(String str)
	{
		Date date=stringToDate(str);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日",
				Locale.CHINA);
		return format.format(date);
	}
	
	/** 
     * 获取当前日期是星期几 
     *
     * @return 当前日期是星期几 
     */ 
    public static String getWeekOfDate(String dstr) {
    	
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
    	Date dt =null;
		try {
			dt = sdf.parse(dstr);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        if(dt ==null)
        	dt =new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt); 

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) 
            w = 0; 

        return weekDays[w]; 
    } 
    
	/** 
     * 获取当前日期是星期几 

     * @return 当前日期是星期几 
     */ 
    public static String getWeekOfDate2(String dstr) {
    	
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
    	Date dt =null;
		try {
			dt = sdf.parse(dstr);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        if(dt ==null)
        	dt =new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt); 

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) 
            w = 0; 

        return weekDays[w]; 
    }
    
    /** 
     * 获取当前日期是星期几 
     * 
     * @param dstr 传入参数格式yyyy-MM-dd
     * @return 返回格式  MM-dd 周几
     */ 
    public static String getWeekOfDate3(String dstr) {
    	if(dstr==null)
    		return "";
    	String dateStr[]=dstr.split("-");
    	StringBuilder sb=new StringBuilder();
//    	if(dateStr[1]!=null&&dateStr[1].startsWith("0"))
//    		sb.append(dateStr[1].substring(1,2));
    	if(dateStr[1]!=null)
    		sb.append(dateStr[1]);
    	if(dateStr[2]!=null)
    		sb.append("-").append(dateStr[2]);
    	sb.append(" ");
    	sb.append(getWeekOfDate(dstr));
        return sb.toString(); 
    } 
	
	/** 
     * 获取当前时间是几月几号周几
     * @author 郑国强
     * @param date 传入时间date
     * @return 返回格式：yyyy-MM-dd 周几
     */ 
    public static String getWeekOfDate4(Date date) {
    	if(date==null)
    		return "";
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	String dataStr = sdf.format(date);
    	String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
        	w = 0;
        }
        return dataStr + " " + weekDays[w];
    }
    
    /** 
     * 获取当前时间是几月几号周几
     * @author 郑国强
     * @param date 传入时间date
     * @return 返回格式：MM-dd 周几
     */ 
    public static String getWeekOfDate5(Date date) {
    	String str = getWeekOfDate4(date);
        return str.substring(5, str.length());
    }

	//将时间戳转为字符串
	public static String getStrTime(String cc_time, String type){
		String re_strTime=null;
		SimpleDateFormat sdf=new SimpleDateFormat(type);
		long lcc_time= Long.valueOf(cc_time);
		re_strTime=sdf.format(new Date(lcc_time*1000L));
		return re_strTime;
	}
	
	//将时间戳转为字符串
	public static String getStrTimeWindows(String cc_time, String type){

		SimpleDateFormat sdf=new SimpleDateFormat(type);
		long lcc_time=0;
		try {
			lcc_time = Long.valueOf(cc_time);
		} catch (Exception e) {
		}
		return sdf.format(new Date(lcc_time));
	}
	
	/**
	 * 将小密蜂返回的时间字符串截取出日期跟时间
	 * @param timeStr 小密蜂返回的日期字符串
	 * @return 日期字符串
	 */
	public static String getDateByAirTime(String timeStr){
		
		StringBuffer timeSb =new StringBuffer();
		String str =timeStr;
		if(str.length() >8){
			timeSb.append(timeStr.substring(0,4));
			timeSb.append("-");
			timeSb.append(timeStr.substring(4,6));
			timeSb.append("-");
			timeSb.append(timeStr.substring(6,8));
			if(str.length()>12){
				timeSb.append(" ");
				timeSb.append(timeStr.substring(8,10));
				timeSb.append(":");
				timeSb.append(timeStr.substring(10,12));
			}
			return timeSb.toString();
		} else{
			return "";
		}
	}
	/**
	 * 将小密蜂返回的时间字符串截取出日期跟时间
	 * @param timeStr 小密蜂返回的日期字符串
	 * @return 日期字符串
	 */
	public static String getOnlyDateByAirTime(String timeStr){
		
		if(timeStr==null)
			return "";
		
		StringBuffer timeSb =new StringBuffer();
		String str =timeStr;
		if(timeStr.split("-").length>2)
			return timeStr.split(" ")[0];
		if(str.length() >=8){
			timeSb.append(timeStr.substring(0,4));
			timeSb.append("-");
			timeSb.append(timeStr.substring(4,6));
			timeSb.append("-");
			timeSb.append(timeStr.substring(6,8));
			return timeSb.toString();
		} else{
			return "";
		}
	}
	/**
	 * 将小密蜂返回的时间字符串截取出日期MM-dd
	 * @param timeStr 小密蜂返回的日期字符串
	 * @return 日期字符串
	 */
	public static String getOnlyDateMonthByAirTime(String timeStr){
		
		if(timeStr==null)
			return "";
		
		StringBuffer timeSb =new StringBuffer();
		String str =timeStr;
		if(timeStr.split("-").length>2)
			return timeStr.split(" ")[0];
		if(str.length() >=8){
			timeSb.append(timeStr.substring(4,6));
			timeSb.append("-");
			timeSb.append(timeStr.substring(6,8));
			return timeSb.toString();
		} else{
			return "";
		}
	}
	/**
	 * 将小密蜂返回的时间字符串截取出时间
	 * @param timeStr 小密蜂返回的时间字符串
	 * @return 时间字符串
	 */
	public static String getTimeByAirTime(String timeStr){
		
		StringBuffer timeSb =new StringBuffer();
		String str =timeStr;
		
		if(str.length() >12){
			timeSb.append(timeStr.substring(8,10));
			timeSb.append(":");
			timeSb.append(timeStr.substring(10,12));
			return timeSb.toString();
		} else{
			return "";
		}
		
	}
	
	public static String subDateByDateStr(String date){
		
		String dateStr =date;
		if(dateStr.length() >10){
			dateStr =dateStr.substring(0,10);
		}
		
		return dateStr;
	}
	
	/**
     * 获得指定日期的前一天
     * 
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     * 
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    /**
     * 得到当前时间的年月
    * @author 谢明峰
    * @Title: getCurYearAndMonth 
    * @Description: TODO
    * @param @return 
    * @return String 
    * @throws
     */
    public static String getCurYearAndMonth(){

    	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM");
    	Date curDate =new Date(System.currentTimeMillis());
    	return sdf.format(curDate);
    }
    
    /**
     * 得到当前时间的年月日
    * @author 谢明峰
    * @Title: getCurYearAndMonth 
    * @Description: TODO
    * @param @return 
    * @return String 
    * @throws
     */
    public static String getCurDate(){

    	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
    	Date curDate =new Date(System.currentTimeMillis());
    	return sdf.format(curDate);
    }
    
    /**获取两个时间间隔*/
    public static int compareTimeByMinute(long time1, long time2) {
        return (int)((time1 - time2) / (60 *1000));
        
   }
   
    /**
    * 获取两个日期之间的间隔天数
    * @return
    */
    public static int getGapCount(String startDate, String endDate) {
    	SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");

    	if(startDate!=null&&startDate.length()>12)
    		startDate=startDate.split(" ")[0];
    	if(endDate!=null&&endDate.length()>12)
    		endDate=endDate.split(" ")[0];
    	startDate=startDate.replace("-", "");
    	endDate=endDate.replace("-", "");
    	int count=0;

    	try {
			if(startDate.compareTo(endDate)<=0){
				count=getGapCount(format.parse(startDate),format.parse(endDate));
			}else{
				count=getGapCount(format.parse(endDate),format.parse(startDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	return count+1;
    }
    
    /**
    * 获取两个日期之间的间隔天数
    * @return
    */
    public static int getGapCount(Date startDate, Date endDate) {
    	
            Calendar fromCalendar = Calendar.getInstance();
            fromCalendar.setTime(startDate);  
            fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
            fromCalendar.set(Calendar.MINUTE, 0);
            fromCalendar.set(Calendar.SECOND, 0);
            fromCalendar.set(Calendar.MILLISECOND, 0);
      
            Calendar toCalendar = Calendar.getInstance();
            toCalendar.setTime(endDate);  
            toCalendar.set(Calendar.HOUR_OF_DAY, 0);
            toCalendar.set(Calendar.MINUTE, 0);
            toCalendar.set(Calendar.SECOND, 0);
            toCalendar.set(Calendar.MILLISECOND, 0);
      
            return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }
    /**
     * 获取两个日期之间的小时
     */
    public static String getTimeCount(String startDate, String endDate) {
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	String count=null;

    	try {
			if(startDate.compareTo(endDate)<=0){
				count=getTimeCount(format.parse(startDate),format.parse(endDate));
			}else{
				count=getTimeCount(format.parse(endDate),format.parse(startDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	return count;
    }
    /**
     * 获取两个日期之间的小时
     */
    public static String getTimeCount(Date nowDate, Date endDate){
    	long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        //计算差多少分钟
        long min=diff%nd%nh/nm;
        StringBuilder sb=new StringBuilder();
        if(day==0){
        	sb.append(hour).append("小时").append(min).append("分");
        }else{
        	sb.append(day).append("天").append(hour).append("小时").append(min).append("分");
        }
        return sb.toString();
    }

    
    /**
     * 判断二个时间差是否在几分钟之内,differ:相差几分钟
     */
	public static boolean isOutOfOneMinute(long nowDate, long preDate, long differ) {

		long diff = 0l;
		try {
//			diff =Long.parseLong(nowDate) -Long.parseLong(preDate);
			diff =nowDate -preDate;
			if(diff/60000.0 <differ)
				return true;
			else
				return false;
		} catch (Exception e) {
		}
		return false;
	}
	
    /**
     * 沟通列表时间规则
     */
	public static String getChatListTime(long timeLongStr){
		
		try {
			
			if(isOutOfOneMinute(System.currentTimeMillis(),timeLongStr,1L)){    //如果是1分钟内
				return "刚刚";
			} else if(isTodayByStr(timeLongStr)){   //如果是今天
				 return new SimpleDateFormat("HH:mm").format(timeLongStr);
			} else if(isThisYearByStr(timeLongStr)){    //如果是今年
				return new SimpleDateFormat("MM-dd HH:mm").format(timeLongStr);
			} else{    //如果是去年或者以前
				return new SimpleDateFormat("yyyy-MM-dd").format(timeLongStr);
			}
		} catch (Exception e) {
		}
		return "";
	}
	
    /**
     * 把long型转换成时间字符串，今天的时间不返回年月日
     */
	public static String getChatTime(long timeLongStr){
		
		try {
			
			if(isTodayByStr(timeLongStr)){   //如果是今天
				 return new SimpleDateFormat("HH:mm").format(timeLongStr);
			} else if(isThisYearByStr(timeLongStr)){    //如果是今年
				return new SimpleDateFormat("MM-dd HH:mm").format(timeLongStr);
			} else{    //如果是去年或者以前
				return new SimpleDateFormat("yyyy-MM-dd").format(timeLongStr);
			}
		} catch (Exception e) {
		}
		return "";
	}
	
    /**
     * 判断时间是不是今天(long型)
     */
	public static boolean isTodayByStr(long timeLongStr){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String d1 = sdf.format(new Date());//第一个时间
	    String d2 = sdf.format(new Date(timeLongStr));//第二个时间
	    
	    if(d1!=null && d1.equals(d2))
	    	return true;
	    else
	    	return false;
	}
	
    /**
     * 判断时间是不是今年(long型)
     */
	public static boolean isThisYearByStr(long timeLongStr){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	    String d1 = sdf.format(new Date());//第一个时间
	    String d2 = sdf.format(new Date(timeLongStr));//第二个时间
	    
	    if(d1!=null && d1.equals(d2))
	    	return true;
	    else
	    	return false;
	}
	
    /**
     * 获取当前系统时间制
     */
    public static int getSystemTimeFormat(Context context){
    	ContentResolver cv = context.getContentResolver();
        String strTimeFormat = android.provider.Settings.System.getString(cv,android.provider.Settings.System.TIME_12_24);
        if(strTimeFormat.equals("24"))
        {
            return 24;
        }else{
        	return 12;
        }
    }
    
    /**
     * date时间转string 包含时分秒
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
		return format.format(date);*/
    	return System.currentTimeMillis()+"";
	}

    /**
     * date时间转string 包含时分
     * @param date
     * @return
     */
    public static String dateToMiddleString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm",
				Locale.CHINA);
		return format.format(date);
	}
    
    /**
     *  date时间转string 不包含时分秒
     * @param date
     * @return
     */
	public static String dateToShortString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		return format.format(date);
	}
	
    /**
     *  date时间转string 不包含年时分秒
     * @param date
     * @return
     */
	public static String dateToMonthDayString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM月dd日", Locale.CHINA);
		return format.format(date);
	}

	 /**
     *  date时间转string 年月形式
     * @param date
     * @return
     */
	public static String dateToChinaNoYearShortString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM月dd日", Locale.CHINA);
		return format.format(date);
	}
	
	
	 /**
     *  date时间转string 月-日形式
     * @param date
     * @return
     */
	public static String dateToMonthShortString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM-dd", Locale.CHINA);
		return format.format(date);
	}
	
	
	/**
	 * 比较两个时间字符串的大小
	 * @param s1
	 * @param s2
	 * @param formatStr 时间的格式 不能为空 例如 yyyy-MM-dd
	 * @return
	 */
	public static int compar2TimeString(String s1, String s2, String formatStr)
	{
		SimpleDateFormat format = new SimpleDateFormat(formatStr, Locale.CHINA);
		try {
			return format.parse(s1).compareTo(format.parse(s2));
		} catch (ParseException e) {
		}
		return -3;
	}
	
	/**
	 * 字符串转时间 自定义格式
	 * @param s
	 * @param formatStr 时间的格式 不能为空 例如 yyyy-MM-dd
	 * @return
	 */
	public  static Date String2Date(String s, String formatStr)
	{
		if(s==null||s.equals(""))
			return null;
		
		SimpleDateFormat format = new SimpleDateFormat(formatStr, Locale.CHINA);
		try {
			return format.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static String date2StringFormat(Date date, String formatStr)
	{
		SimpleDateFormat format = new SimpleDateFormat(formatStr, Locale.CHINA);
		return  format.format(date);
	}
	
	//这种时间格式HH:mm的两个时间，求时间差
	public static String getTimeCountLongStr(String formTime, String toTime){
		long counth=0,countm=0,formh=0,toh=0,formm=0,tom=0;
		if(!TextUtils.isEmpty(formTime)&&!TextUtils.isEmpty(toTime)){
			try {
				String formhStr = formTime.split(":")[0];
				String tohStr = toTime.split(":")[0];
				String formmStr = formTime.split(":")[1];
				String tomStr = toTime.split(":")[1];
				formh = Long.parseLong(formhStr);
				toh = Long.parseLong(tohStr);
				formm = Long.parseLong(formmStr);
				tom = Long.parseLong(tomStr);
				if ((tom - formm >= 0)) {// 到达时间分钟大于等于出发时间的分钟
					countm = (tom - formm);
					if ((toh - formh) >= 0) {// 到达时间大于等于出发时间
						counth = (toh - formh);
					} else {
						counth = ((toh + 24) - formh);
					}
				} else {
					countm = ((tom + 60) - formm);
					if (((toh - 1) - formh) >= 0) {
						counth = ((toh - 1) - formh);
					} else {
						counth = ((toh + 23) - formh);
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return counth+"小时"+countm+"分钟";
	}
	
	//这种时间格式HH:mm的两个时间，求时间差，返回xhxm格式
	public static String getTimeCountLongStr2(String formTime, String toTime){
		long counth=0,countm=0,formh=0,toh=0,formm=0,tom=0;
		if(formTime!=null&&toTime!=null&&!"".equals(formTime)&&!"".equals(toTime)){
			String formhStr=formTime.split(":")[0];
			String tohStr=toTime.split(":")[0];
			String formmStr=formTime.split(":")[1];
			String tomStr=toTime.split(":")[1];
			try {
				formh= Long.parseLong(formhStr);
				toh= Long.parseLong(tohStr);
				formm= Long.parseLong(formmStr);
				tom= Long.parseLong(tomStr);
				if((tom-formm>=0)){//到达时间分钟大于等于出发时间的分钟
					countm=(tom-formm);     
					if((toh-formh)>=0){//到达时间大于等于出发时间
						counth=(toh-formh);
					}else{
						counth=((toh+24)-formh);
					}
				}else{
					countm=((tom+60)-formm);
					if(((toh-1)-formh)>=0){
						counth=((toh-1)-formh);
					}else{
						counth=((toh+23)-formh);
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return counth+"h"+countm+"m";
	}
	
	public static String getSysNowDateStr() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
	//处理预订租车时间，获取时分
	public static String getHourMinStr(String time){
		int index=time.indexOf(" ");
		if(index>0&&index<time.length()){
			String subStr=time.split(" ")[1];
			if(subStr!=null&&!"".equals(subStr)){
				String[] hourStr=subStr.split(":");
				return hourStr[0]+":"+hourStr[1];
			}
		}
		return time;
	}
	
	/**
	 * 获取当天的最早时间 例如2015-6-6 00:00:00
	 * @return
	 */
	public static Date getLastCurDate()
	{
		Date date=new Date();
		String str=date2StringFormat(date, "yyyy-MM-dd")+" 00:00:00";
		return String2Date(str, "yyyy-MM-dd HH:mm:ss");
		
	}
	
	/**
	 * 根据日历获取DayofWeek
	 * @param ca
	 * @return
	 */
	public  static String getWeekOfDayByCalendar(Calendar ca)
	{
		return getWeekofDayByNumber(ca.get(Calendar.DAY_OF_WEEK));
	}
	
	/**
	 * 根据Date获取DayofWeek
	 * @param date
	 * @return
	 */
	public static String getWeekofDayByDate(Date date)
	{
		Calendar ca= Calendar.getInstance();
		ca.setTime(date);
		return getWeekOfDayByCalendar(ca);
	}
	
	public static String getWeekofDayByNumber(int index) {
		
		String str="";
		switch (index) {
		case 1:
			str="周日";
			break;
		case 2:
			str="周一";
			break;
		case 3:
			str="周二";
			break;
		case 4:
			str="周三";
			break;
		case 5:
			str="周四";
			break;
		case 6:
			str="周五";
			break;
		case 7:
			str="周六";
			break;
		default:
			break;
		}
		
		return str;
	}
	
	public static String StringTimeFormat(String str, String formatStr){
		
		if(str==null)
			return "";
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat(formatStr);//设置日期格式
		try {
			return df.format(df1.parse(str));
		} catch (ParseException e) {
			return str;
		}
		
	}
	
	public static String subDateByString(String str){
		
		if(str!=null && !"".equals(str) && str.contains(" ") && str.length()>8){
			return str.split(" ")[0];
		}
		return str;
	}
	
	public static String subTimeByString(String str){
		
		if(str!=null && !"".equals(str) && str.contains(" ") && str.length()>8){
			return str.split(" ")[1];
		}
		return str;
	}
	
	
	public static int getCurrentYear()
	{
		return Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
	}
	
	public static int getCurrentMonth()
	{
		return Integer.valueOf(new SimpleDateFormat("MM").format(new Date()));
	}
	
	/**
	 * 获取年份
	 */
	public static int getYear(Date date) {
		return Integer.valueOf(new SimpleDateFormat("yyyy").format(date == null ? new Date() : date));
	}
	
	/**
	 * 获取月份
	 */
	public static int getMonth(Date date) {
		return Integer.valueOf(new SimpleDateFormat("MM").format(date == null ? new Date() : date));
	}
	
	/**
	 * 获取日子
	 */
	public static int getDay(Date date) {
		return Integer.valueOf(new SimpleDateFormat("dd").format(date == null ? new Date() : date));
	}
	
	//通过时间字符串获取年月 格式yyyy-MM-dd
	public static String getYearAndMonth(String date){
		if(date==null||"".equals(date)){
			return "";
		}
		String[] str=date.split("-");
		StringBuffer sb=new StringBuffer();
		sb.append(str[0]).append("-").append(str[1]);
		return sb.toString();
	}
	
	public static String longSecondsToString(String seconds){
		try {
			long ss= Long.valueOf(seconds);
			if(ss<60)
				return ss+"秒";
			else if(ss<60*60){
				long min=ss/60;
				long s=ss%60;
				return min+"分"+s+"秒";
			}else if(ss<60*60*24){
				long h=ss/(60*60);
				long min=ss%(60*60)/60;
				return h+"小时"+min+"分";
			}else {
				long day=ss/(60*60*24);
				long h=ss%(60*60*24)/60/60;
				return day+"天"+h+"小时";
			}
			
//			else if(ss<60*60*24*30){
//				long day=ss/(60*60*24);
//				long h=ss%(60*60*24)/60/60;
//				return day+"天"+h+"小时";
//			}
			
//			else if(ss<60*60*24*30*365){
//				long mounth=ss/(60*60*24*30);
//				long day=ss%(60*60*24*30)/(60*60*24);
//				return mounth+"月"+day+"天";
//			}else {
//				long year=ss/(60*60*24*30*365);
//				long mounth=ss%(60*60*24*30*365)/(60*60*24*30);
//				return year+"年"+mounth+"月";
//			}
		} catch (Exception e) {
			return "0";
		}
	}
	
	/**
	 * 获取当前月份有多少天
	 * @param year 年份
	 * @param month 月份
	 * @return 天数
	 */
	public static int hasDaysNumber(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);
		return calendar.getActualMaximum(Calendar.DATE);
	}
	
	/**
     * 获得指定日期的前一天
     *
     * @return
     * @throws Exception
     */
    public static Date getPrevDay(Date date) {
        if(date != null) {
    		long time = date.getTime();
    		time -= 24*60*60*1000;
    		date = new Date(time);
        }
        return date;
    }

    /**
     * 获得指定日期的后一天
     *
     * @return
     */
    public static Date getNextDay(Date date) {
    	if(date != null) {
    		long time = date.getTime();
    		time += 24*60*60*1000;
    		date = new Date(time);
        }
        return date;
    }
    /**
     * 获得指定日期的后n天，你可以为负，表示之前的日期
     * 

     * @return
     */
    public static Date getNextDay(Date date, int n) {
    	if(date != null) {
    		long time = date.getTime();
    		time += 24*60*60*1000*n;
    		date = new Date(time);
        }
        return date;
    }
    /**
     * 获得指定日期的后n天
     * 
     * @param 
     * @return
     */
    public static String getNextDay(String dateString, int n) {
    	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        Date date = null;
        String sdate="";
		try {
			date = sdf.parse(dateString);
			if(date != null) {
	    		long time = date.getTime();
	    		time += 24*60*60*1000*n;
	    		date = new Date(time);
	    		sdate=(new SimpleDateFormat("MM-dd")).format(date);
	        }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return sdate;
    }
    /**
     * 获得指定时间的后一段时间
     * 
     * @param 
     * @return
     */
    public static String getArriveDate(String dateString, String runString) {
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = null;
        String[] runSubString=runString.split(":");
        String sdate="";
		try {
			date = sdf.parse(dateString);
			if(date != null) {
	    		long time = date.getTime();
	    		time += Integer.parseInt(runSubString[0])*60*60*1000+ Integer.parseInt(runSubString[1])*60*1000;
	    		date = new Date(time);
	    		sdate=(new SimpleDateFormat("HH:mm")).format(date);
	        }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return sdate;
    }


	public static String getTimeDifferenceToTwo(Map<String, String> map,
                                                Map<String, String> map2) {
		 String TakeoffStr =TimeUtil.getDateByAirTime(map.get("landingtime"));
	     String LandingTimeStr =TimeUtil.getDateByAirTime(map2.get("takeofftime"));
		return TimeUtil.getTimeCount(TakeoffStr,LandingTimeStr);
	}
	/*
       * 将时间戳转换为时间
       */
	public static String stampToDate(String s){
		if(s!=null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

			return simpleDateFormat.format(new Date(Long.valueOf(s)));
		}else return "";

	}
}
