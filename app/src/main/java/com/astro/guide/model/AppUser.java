package com.astro.guide.model;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 23/9/2017
 */
@Singleton
public class AppUser {

    private String name;

    private String email;

    private int sortOrder;

    private boolean hideFavouriteButton;

    private Set<Integer> favouritesIds = new HashSet<>();

    @Inject
    public AppUser(String name, String email, int sortOrder) {
        this.name = name;
        this.email = email;
        this.sortOrder = sortOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isHideFavouriteButton() {
        return hideFavouriteButton;
    }

    public void setHideFavouriteButton(boolean hideFavouriteButton) {
        this.hideFavouriteButton = hideFavouriteButton;
    }

    public Set<Integer> getFavouritesIds() {
        return favouritesIds;
    }

    public void setFavouritesIds(Set<Integer> favouritesIds) {
        this.favouritesIds = favouritesIds;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sortOrder=" + sortOrder +
                ", hideFavouriteButton=" + hideFavouriteButton +
                ", favouritesIds=" + favouritesIds +
                '}';
    }
}
