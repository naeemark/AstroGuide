package com.astro.guide.utils;

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
    private static final int HOURS_BEHIND = 2;
    private static final int HOURS_AHEAD = 1;

    /**
     * Provides an array of Two Strings for start and end time to be sent as param
     * @return
     */
    public static String[] getTimeRequestParams() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_API_REQUEST_PARAM);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, -(HOURS_BEHIND));
        String startTime = sdf.format(now.getTime());

        now.add(Calendar.HOUR, HOURS_BEHIND + HOURS_AHEAD);
        String endTime = sdf.format(now.getTime());

        return new String[]{startTime, endTime};
    }


    /**
     * Determines server timezone id on the base of difference b/w two timestamps of same time
     * @param timeStringUtc UTC time for the event
     * @param timeString Server/API time for the event
     * @param sdf SimpleDateFormat
     * @return
     */
    public static String getServerTimeZoneId(String timeStringUtc, String timeString, SimpleDateFormat sdf) {
        try {
            Date dateUtc = sdf.parse(timeStringUtc);
            Date dateLocal = sdf.parse(timeString);

            int diffHours = dateLocal.getHours() - dateUtc.getHours();
            int diffMinutes = dateLocal.getMinutes() - dateUtc.getMinutes();

            Date tempDate = new Date();
            tempDate.setHours(diffHours);
            tempDate.setMinutes(diffMinutes);

            String paramId = "GMT" + ((dateUtc.getTime() > dateLocal.getTime()) ? "-" : "+") + new SimpleDateFormat("HH:mm").format(tempDate);

            return TimeZone.getTimeZone(paramId).getDisplayName();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return TimeZone.getDefault().getDisplayName();
    }

    /**
     * Checks if the event is not expired
     * @param event
     * @return
     * @throws ParseException
     */
    public static boolean isEventAlive(Event event) throws ParseException {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");

        TimeZone aDefault = TimeZone.getDefault();

        TimeZone.setDefault(TimeZone.getTimeZone(DateTimeUtils.getServerTimeZoneId(event.getDisplayDateTimeUtc(), event.getDisplayDateTime(), sdfDate)));
        sdfDate.setTimeZone(TimeZone.getDefault());
        parser.setTimeZone(TimeZone.getDefault());
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();


        Date startDate = sdfDate.parse(event.getDisplayDateTime());

        calendar.setTime(startDate);

        Date showTime = parser.parse(event.getDisplayDuration());

        calendar.add(Calendar.HOUR_OF_DAY, showTime.getHours());
        calendar.add(Calendar.MINUTE, showTime.getMinutes());
        calendar.add(Calendar.SECOND, showTime.getSeconds());

        Date endDate = calendar.getTime();
        TimeZone.setDefault(aDefault);
        return date.after(startDate) && date.before(endDate);
    }


    /**
     * Formates timestamp tp shos in list item
     * @param event
     * @return
     * @throws ParseException
     */
    public static String getFormatedDateTime(Event event) throws ParseException {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

        sdfDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parse = sdfDate.parse(event.getDisplayDateTimeUtc());

        return sdf.format(parse);
    }

    /**
     * Finds current event from the list
     * @param events Events from a Channel
     * @return Current Event
     */
    public static Event getCurrentEvent(List<Event> events) {
        for (Event event : events) {
            try {
                if (isEventAlive(event)) {
                    return event;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

