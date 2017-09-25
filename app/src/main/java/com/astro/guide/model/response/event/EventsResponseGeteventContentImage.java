package com.astro.guide.model.response.event;

public class EventsResponseGeteventContentImage implements java.io.Serializable {
    private static final long serialVersionUID = -6120818001418915365L;
    private String imageRole;
    private String imageURL;

    public String getImageRole() {
        return this.imageRole;
    }

    public void setImageRole(String imageRole) {
        this.imageRole = imageRole;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
