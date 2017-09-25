package com.astro.guide.model.response.event;

public class EventsResponseGetevent implements java.io.Serializable {
    private static final long serialVersionUID = -8413065193418738851L;
    private String eventID;
    private String channelStbNumber;
    private String directors;
    private String displayDateTimeUtc;
    private int contentId;
    private String episodeId;
    private int groupKey;
    private String programmeId;
    private String subGenre;
    private EventsResponseGeteventVernacularData[] vernacularData;
    private Object highlight;
    private String genre;
    private int channelId;
    private boolean live;
    private String channelHD;
    private String channelTitle;
    private String displayDateTime;
    private String siTrafficKey;
    private boolean premier;
    private String epgEventImage;
    private String certification;
    private String producers;
    private String shortSynopsis;
    private String displayDuration;
    private String actors;
    private boolean ottBlackout;
    private EventsResponseGeteventContentImage[] contentImage;
    private String programmeTitle;
    private Object longSynopsis;

    public String getEventID() {
        return this.eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getChannelStbNumber() {
        return this.channelStbNumber;
    }

    public void setChannelStbNumber(String channelStbNumber) {
        this.channelStbNumber = channelStbNumber;
    }

    public String getDirectors() {
        return this.directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getDisplayDateTimeUtc() {
        return this.displayDateTimeUtc;
    }

    public void setDisplayDateTimeUtc(String displayDateTimeUtc) {
        this.displayDateTimeUtc = displayDateTimeUtc;
    }

    public int getContentId() {
        return this.contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getEpisodeId() {
        return this.episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public int getGroupKey() {
        return this.groupKey;
    }

    public void setGroupKey(int groupKey) {
        this.groupKey = groupKey;
    }

    public String getProgrammeId() {
        return this.programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getSubGenre() {
        return this.subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

    public EventsResponseGeteventVernacularData[] getVernacularData() {
        return this.vernacularData;
    }

    public void setVernacularData(EventsResponseGeteventVernacularData[] vernacularData) {
        this.vernacularData = vernacularData;
    }

    public Object getHighlight() {
        return this.highlight;
    }

    public void setHighlight(Object highlight) {
        this.highlight = highlight;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getChannelId() {
        return this.channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public boolean getLive() {
        return this.live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public String getChannelHD() {
        return this.channelHD;
    }

    public void setChannelHD(String channelHD) {
        this.channelHD = channelHD;
    }

    public String getChannelTitle() {
        return this.channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getDisplayDateTime() {
        return this.displayDateTime;
    }

    public void setDisplayDateTime(String displayDateTime) {
        this.displayDateTime = displayDateTime;
    }

    public String getSiTrafficKey() {
        return this.siTrafficKey;
    }

    public void setSiTrafficKey(String siTrafficKey) {
        this.siTrafficKey = siTrafficKey;
    }

    public boolean getPremier() {
        return this.premier;
    }

    public void setPremier(boolean premier) {
        this.premier = premier;
    }

    public String getEpgEventImage() {
        return this.epgEventImage;
    }

    public void setEpgEventImage(String epgEventImage) {
        this.epgEventImage = epgEventImage;
    }

    public String getCertification() {
        return this.certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getProducers() {
        return this.producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getShortSynopsis() {
        return this.shortSynopsis;
    }

    public void setShortSynopsis(String shortSynopsis) {
        this.shortSynopsis = shortSynopsis;
    }

    public String getDisplayDuration() {
        return this.displayDuration;
    }

    public void setDisplayDuration(String displayDuration) {
        this.displayDuration = displayDuration;
    }

    public String getActors() {
        return this.actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public boolean getOttBlackout() {
        return this.ottBlackout;
    }

    public void setOttBlackout(boolean ottBlackout) {
        this.ottBlackout = ottBlackout;
    }

    public EventsResponseGeteventContentImage[] getContentImage() {
        return this.contentImage;
    }

    public void setContentImage(EventsResponseGeteventContentImage[] contentImage) {
        this.contentImage = contentImage;
    }

    public String getProgrammeTitle() {
        return this.programmeTitle;
    }

    public void setProgrammeTitle(String programmeTitle) {
        this.programmeTitle = programmeTitle;
    }

    public Object getLongSynopsis() {
        return this.longSynopsis;
    }

    public void setLongSynopsis(Object longSynopsis) {
        this.longSynopsis = longSynopsis;
    }
}
