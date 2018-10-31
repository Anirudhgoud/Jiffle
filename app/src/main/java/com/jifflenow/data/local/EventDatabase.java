package com.jifflenow.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.jifflenow.data.local.dao.EventsDao;
import com.jifflenow.data.local.entity.EventEntity;
import com.jifflenow.models.Events;

@Database(entities = {Events.class}, version = 1)
public abstract class EventDatabase extends RoomDatabase {

    public abstract EventsDao eventDao();

    private static volatile EventDatabase INSTANCE;

    public static EventDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EventDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EventDatabase.class, "event_database").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
