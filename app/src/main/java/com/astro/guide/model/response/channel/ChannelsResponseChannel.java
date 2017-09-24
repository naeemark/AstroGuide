package com.astro.guide.model.response.channel;

public class ChannelsResponseChannel implements java.io.Serializable {
    private static final long serialVersionUID = -3391264678251873440L;
    private Object channelStartDate;
    private Object channelEndDate;
    private Object channelColor1;
    private String channelStbNumber;
    private Object channelColor2;
    private ChannelsResponseChannelLinearOttMapping[] linearOttMapping;
    private String channelLanguage;
    private Object channelColor3;
    private String siChannelId;
    private String channelCategory;
    private int hdSimulcastChannel;
    private ChannelsResponseChannelChannelExtRef[] channelExtRef;
    private String channelDescription;
    private int channelId;
    private String channelTitle;
    private boolean channelHD;

    public Object getChannelStartDate() {
        return this.channelStartDate;
    }

    public void setChannelStartDate(Object channelStartDate) {
        this.channelStartDate = channelStartDate;
    }

    public Object getChannelEndDate() {
        return this.channelEndDate;
    }

    public void setChannelEndDate(Object channelEndDate) {
        this.channelEndDate = channelEndDate;
    }

    public Object getChannelColor1() {
        return this.channelColor1;
    }

    public void setChannelColor1(Object channelColor1) {
        this.channelColor1 = channelColor1;
    }

    public String getChannelStbNumber() {
        return this.channelStbNumber;
    }

    public void setChannelStbNumber(String channelStbNumber) {
        this.channelStbNumber = channelStbNumber;
    }

    public Object getChannelColor2() {
        return this.channelColor2;
    }

    public void setChannelColor2(Object channelColor2) {
        this.channelColor2 = channelColor2;
    }

    public ChannelsResponseChannelLinearOttMapping[] getLinearOttMapping() {
        return this.linearOttMapping;
    }

    public void setLinearOttMapping(ChannelsResponseChannelLinearOttMapping[] linearOttMapping) {
        this.linearOttMapping = linearOttMapping;
    }

    public String getChannelLanguage() {
        return this.channelLanguage;
    }

    public void setChannelLanguage(String channelLanguage) {
        this.channelLanguage = channelLanguage;
    }

    public Object getChannelColor3() {
        return this.channelColor3;
    }

    public void setChannelColor3(Object channelColor3) {
        this.channelColor3 = channelColor3;
    }

    public String getSiChannelId() {
        return this.siChannelId;
    }

    public void setSiChannelId(String siChannelId) {
        this.siChannelId = siChannelId;
    }

    public String getChannelCategory() {
        return this.channelCategory;
    }

    public void setChannelCategory(String channelCategory) {
        this.channelCategory = channelCategory;
    }

    public int getHdSimulcastChannel() {
        return this.hdSimulcastChannel;
    }

    public void setHdSimulcastChannel(int hdSimulcastChannel) {
        this.hdSimulcastChannel = hdSimulcastChannel;
    }

    public ChannelsResponseChannelChannelExtRef[] getChannelExtRef() {
        return this.channelExtRef;
    }

    public void setChannelExtRef(ChannelsResponseChannelChannelExtRef[] channelExtRef) {
        this.channelExtRef = channelExtRef;
    }

    public String getChannelDescription() {
        return this.channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public int getChannelId() {
        return this.channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return this.channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public boolean getChannelHD() {
        return this.channelHD;
    }

    public void setChannelHD(boolean channelHD) {
        this.channelHD = channelHD;
    }
}
