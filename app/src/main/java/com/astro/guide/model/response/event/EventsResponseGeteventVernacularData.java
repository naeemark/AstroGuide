package com.astro.guide.model.response.event;

public class EventsResponseGeteventVernacularData implements java.io.Serializable {
    private static final long serialVersionUID = -7758707422681044614L;
    private String actors;
    private String vernacularLongSynopsis;
    private String vernacularLanguage;
    private String directors;
    private String vernacularShortSynopsis;
    private String vernacularProgrammeTitle;
    private String producers;

    public String getActors() {
        return this.actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getVernacularLongSynopsis() {
        return this.vernacularLongSynopsis;
    }

    public void setVernacularLongSynopsis(String vernacularLongSynopsis) {
        this.vernacularLongSynopsis = vernacularLongSynopsis;
    }

    public String getVernacularLanguage() {
        return this.vernacularLanguage;
    }

    public void setVernacularLanguage(String vernacularLanguage) {
        this.vernacularLanguage = vernacularLanguage;
    }

    public String getDirectors() {
        return this.directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getVernacularShortSynopsis() {
        return this.vernacularShortSynopsis;
    }

    public void setVernacularShortSynopsis(String vernacularShortSynopsis) {
        this.vernacularShortSynopsis = vernacularShortSynopsis;
    }

    public String getVernacularProgrammeTitle() {
        return this.vernacularProgrammeTitle;
    }

    public void setVernacularProgrammeTitle(String vernacularProgrammeTitle) {
        this.vernacularProgrammeTitle = vernacularProgrammeTitle;
    }

    public String getProducers() {
        return this.producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }
}
