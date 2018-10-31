package com.jifflenow.models;

import com.google.gson.annotations.SerializedName;

public class EventDetails {

    @SerializedName("location")
    private String location;

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("meeting_with")
    private String meetingWith;

    @SerializedName("event_uuid")
    private String eventUuid;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMeetingWith() {
        return meetingWith;
    }

    public void setMeetingWith(String meetingWith) {
        this.meetingWith = meetingWith;
    }

    public String getEventUuid() {
        return eventUuid;
    }

    public void setEventUuid(String eventUuid) {
        this.eventUuid = eventUuid;
    }
}
