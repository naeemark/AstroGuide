package com.astro.guide.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 23/9/2017
 */

public class Channel implements Serializable {

    private int id;
    private String title;
    private String stbNumber;
    private String description;
    private String logoUrl;
    private boolean isFavourite;

    private List<Event> events = new ArrayList<>();


    public Channel(int id, String title, String stbNumber, String description) {
        this.id = id;
        this.title = title;
        this.stbNumber = stbNumber;
        this.description = description;
    }

    public Channel(int id, String title, String stbNumber, String description, String logoUrl, boolean isFavourite) {
        this.id = id;
        this.title = title;
        this.stbNumber = stbNumber;
        this.description = description;
        this.logoUrl = logoUrl;
        this.isFavourite = isFavourite;
    }

    public Channel(int id, String title, String stbNumber, String description, String logoUrl, boolean isFavourite, List<Event> events) {
        this.id = id;
        this.title = title;
        this.stbNumber = stbNumber;
        this.description = description;
        this.logoUrl = logoUrl;
        this.isFavourite = isFavourite;
        this.events = events;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStbNumber() {
        return stbNumber;
    }

    public void setStbNumber(String stbNumber) {
        this.stbNumber = stbNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", stbNumber='" + stbNumber + '\'' +
                ", description='" + description + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", isFavourite=" + isFavourite +
                ", events=" + events +
                '}';
    }
}
