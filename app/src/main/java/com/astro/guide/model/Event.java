package com.astro.guide.model;

import java.io.Serializable;

public class Event implements Serializable {

    private String eventID;
    private String displayDateTimeUtc;
    private String displayDateTime;
    private String displayDuration;
    private int contentId;
    private String shortSynopsis;
    private String actors;
    private String epgEventImage;
    private String programmeTitle;

    public Event(String eventID, String displayDateTimeUtc, String displayDateTime, String displayDuration, int contentId, String shortSynopsis, String actors, String epgEventImage, String programmeTitle) {
        this.eventID = eventID;
        this.displayDateTimeUtc = displayDateTimeUtc;
        this.displayDateTime = displayDateTime;
        this.displayDuration = displayDuration;
        this.contentId = contentId;
        this.shortSynopsis = shortSynopsis;
        this.actors = actors;
        this.epgEventImage = epgEventImage;
        this.programmeTitle = programmeTitle;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getDisplayDateTimeUtc() {
        return displayDateTimeUtc;
    }

    public void setDisplayDateTimeUtc(String displayDateTimeUtc) {
        this.displayDateTimeUtc = displayDateTimeUtc;
    }

    public String getDisplayDateTime() {
        return displayDateTime;
    }

    public void setDisplayDateTime(String displayDateTime) {
        this.displayDateTime = displayDateTime;
    }

    public String getDisplayDuration() {
        return displayDuration;
    }

    public void setDisplayDuration(String displayDuration) {
        this.displayDuration = displayDuration;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getShortSynopsis() {
        return shortSynopsis;
    }

    public void setShortSynopsis(String shortSynopsis) {
        this.shortSynopsis = shortSynopsis;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getEpgEventImage() {
        return epgEventImage;
    }

    public void setEpgEventImage(String epgEventImage) {
        this.epgEventImage = epgEventImage;
    }

    public String getProgrammeTitle() {
        return programmeTitle;
    }

    public void setProgrammeTitle(String programmeTitle) {
        this.programmeTitle = programmeTitle;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventID='" + eventID + '\'' +
                ", programmeTitle='" + programmeTitle + '\'' +
                ", displayDateTimeUtc='" + displayDateTimeUtc + '\'' +
                ", displayDateTime='" + displayDateTime + '\'' +
                ", displayDuration='" + displayDuration + '\'' +
                ", contentId=" + contentId +
                ", shortSynopsis='" + shortSynopsis + '\'' +
                ", actors='" + actors + '\'' +
                ", epgEventImage='" + epgEventImage + '\'' +
                '}';
    }
}
