package com.astro.guide.utils;

import android.util.Log;

import com.astro.guide.model.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 25/9/2017
 */

public class DateTimeUtils {

    private static final String FORMAT_API_REQUEST_PARAM = "yyyy-MM-dd HH:mm";
    private static final String FORMAT_API_RESPONSE_DISPLAY_TIME = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String FORMAT_LIST_ITEM = "hh:mm a";

    public static String getTime(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_API_RESPONSE_DISPLAY_TIME);
        try {
            Date d = sdf.parse(dateTime);
            sdf.applyPattern(FORMAT_LIST_ITEM);
            dateTime = sdf.format(d);
        } catch (ParseException ex) {
            ex.printStackTrace();
        } finally {
            return dateTime;
        }
    }

    public static String[] getTimeRequestParams() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_API_REQUEST_PARAM);

        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, -6);
        String startTime =  sdf.format(now.getTime());

        now.add(Calendar.HOUR, 7);
        String endTime =  sdf.format(now.getTime());

        return new String[]{startTime, endTime};
    }


    public static String getServerTimeZoneId(String timeStringUtc, String timeString, SimpleDateFormat sdf) {
        try {
            Date dateUtc = sdf.parse(timeStringUtc);
            Date dateLocal = sdf.parse(timeString);

            int diffHours = dateLocal.getHours() - dateUtc.getHours();
            int diffMinutes = dateLocal.getMinutes() - dateUtc.getMinutes();

            Date tempDate = new Date();
            tempDate.setHours(diffHours);
            tempDate.setMinutes(diffMinutes);

            String paramId = "GMT"+((dateUtc.getTime() > dateLocal.getTime()) ? "-" : "+") + new SimpleDateFormat("HH:mm").format(tempDate);

            return TimeZone.getTimeZone(paramId).getDisplayName();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return TimeZone.getDefault().getDisplayName();
    }

    public static boolean isEventAlive(Event event) throws ParseException {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        TimeZone aDefault = TimeZone.getDefault();

        TimeZone.setDefault(TimeZone.getTimeZone(DateTimeUtils.getServerTimeZoneId(event.getDisplayDateTimeUtc(), event.getDisplayDateTime(), sdfDate)));
        Calendar c = Calendar.getInstance();
        Date date = Calendar.getInstance().getTime();

//        TimeZone.setDefault(aDefault);


        Date startDate = sdfDate.parse(event.getDisplayDateTime());

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");

        c.setTime(startDate);

        Date showTime = parser.parse(event.getDisplayDuration());

        c.add(Calendar.HOUR_OF_DAY, showTime.getHours());
        c.add(Calendar.MINUTE, showTime.getMinutes());
        c.add(Calendar.SECOND, showTime.getSeconds());

        Date endDate = c.getTime();


        Log.e("DIFFX", "D:"+TimeZone.getDefault().getDisplayName());

        return date.after(startDate) && date.before(endDate);
    }

    public static String getFormatedDateTime(Event event) throws ParseException {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");


        sdfDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parse = sdfDate.parse(event.getDisplayDateTimeUtc());


        Calendar c = Calendar.getInstance(TimeZone.getDefault());

        sdf.setTimeZone(TimeZone.getTimeZone(DateTimeUtils.getServerTimeZoneId(event.getDisplayDateTimeUtc(), event.getDisplayDateTime(), sdfDate)));

        TimeZone tz = TimeZone.getDefault();

        SimpleDateFormat destFormat = new SimpleDateFormat("hh:mm a");
        destFormat.setTimeZone(tz);

        return destFormat.format(parse);
    }

    public static String convertDateToTime(Event event) throws ParseException {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");


        sdfDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parse = sdfDate.parse(event.getDisplayDateTimeUtc());


        Calendar c = Calendar.getInstance(TimeZone.getDefault());


        TimeZone tz = TimeZone.getTimeZone("GMT+5");

        SimpleDateFormat destFormat = new SimpleDateFormat("hh:mm a");
        destFormat.setTimeZone(tz);

        return destFormat.format(parse);
    }



    public static Event getCurrentEvent(List<Event> events) {
        for (Event event : events) {
            try {
                if(isEventAlive(event)){
                    return event;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

