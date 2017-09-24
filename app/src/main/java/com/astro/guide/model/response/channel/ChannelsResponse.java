package com.astro.guide.model.response.channel;

import com.astro.guide.model.response.NetworkResponse;

public class ChannelsResponse extends NetworkResponse implements java.io.Serializable {

    private static final long serialVersionUID = 1076903251988909195L;
    private ChannelsResponseChannel[] channel;
    private ChannelsResponseChannel[] channels;
    private String responseMessage;
    private String responseCode;

    public ChannelsResponseChannel[] getChannel() {
        return this.channel;
    }

    public void setChannel(ChannelsResponseChannel[] channel) {
        this.channel = channel;
    }

    public ChannelsResponseChannel[] getChannels() {
        return this.channels;
    }

    public void setChannels(ChannelsResponseChannel[] channels) {
        this.channels = channels;
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
