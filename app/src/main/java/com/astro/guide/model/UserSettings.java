package com.astro.guide.model;

import com.astro.guide.constants.AppConstants;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 23/9/2017
 */
@Singleton
public class UserSettings {

    private String userName;

    private String userEmail;

    private int sortOrder = AppConstants.SortOrder.SORT_BY_NAME.ordinal();

    @Inject
    public UserSettings() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return "UserSettings{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", sortOrder=" + sortOrder +
                '}';
    }
}
