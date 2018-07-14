package com.xmg.pss.util;

import java.util.Calendar;
import java.util.Date;

//日期处理帮助类
public class DateUtil {

    /**
     * 获取到一天的最开始时间,即时分秒都为0
     * @param currentDate
     * @return
     */
    public static Date getBeginDate(Date currentDate) {
        //获取当前日期的Calendar
        Calendar c=Calendar.getInstance();
        //设置时间
        c.setTime(currentDate);
        //设置时分秒
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        return c.getTime();
    }
    /**
     * 获取到一天的最后一秒时间 23:59:59
     * @param currentDate
     * @return
     */
    public static Date getEndDate(Date currentDate) {
        //获取当前日期的Calendar
        Calendar c=Calendar.getInstance();
        //设置时间
        c.setTime(currentDate);
        //设置时分秒
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        return c.getTime();
    }


}
