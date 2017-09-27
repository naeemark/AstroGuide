package com.astro.guide.utils;

import com.astro.guide.constants.AppConstants;
import com.astro.guide.model.Channel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 23/9/2017
 */

public class SortUtils {

    /**
     * Sorts the given list according to provided sortOrder
     * @param channels
     * @param sortOrder
     * @return
     */
    public static List<Channel> sortList(List<Channel> channels, AppConstants.SortOrder sortOrder) {
        if(sortOrder.equals(AppConstants.SortOrder.SORT_BY_NAME)){
            return sortListByName(channels);
        }else {
            return sortListByNumber(channels);
        }
    }

    private static List<Channel> sortListByNumber(List<Channel> channels) {
        Collections.sort(channels, new Comparator<Channel>(){
            public int compare(Channel obj1, Channel obj2) {
                return obj1.getStbNumber().compareToIgnoreCase(obj2.getStbNumber()); // To compare string values
            }
        });
        return channels;
    }

    private static List<Channel> sortListByName(List<Channel> channels) {
        Collections.sort(channels, new Comparator<Channel>(){
            public int compare(Channel obj1, Channel obj2) {
                return obj1.getTitle().compareToIgnoreCase(obj2.getTitle()); // To compare string values
                // return Integer.valueOf(obj1.empId).compareTo(obj2.empId); // To compare integer values
            }
        });
        return channels;
    }
}
