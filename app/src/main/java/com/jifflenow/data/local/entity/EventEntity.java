package com.jifflenow.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "events")
public class EventEntity {

    @SerializedName("event_system_name")
    private String eventSystemName;

    @SerializedName("location")
    private String location;

    @PrimaryKey
    @SerializedName("uuid")
    @NonNull private String uuid;

    public String getEventSystemName() {
        return eventSystemName;
    }

    public void setEventSystemName(String eventSystemName) {
        this.eventSystemName = eventSystemName;
    }

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


}
