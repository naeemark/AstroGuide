package com.astro.guide.model.response.event;

import com.astro.guide.model.response.NetworkResponse;

public class EventsResponse extends NetworkResponse implements java.io.Serializable {
    private static final long serialVersionUID = 1448075971325125723L;
    private EventsResponseGetevent[] getevent;
    private String responseMessage;
    private String responseCode;

    public EventsResponseGetevent[] getGetevent() {
        return this.getevent;
    }

    public void setGetevent(EventsResponseGetevent[] getevent) {
        this.getevent = getevent;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}
