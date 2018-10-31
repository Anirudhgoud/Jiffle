package com.jifflenow.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.jifflenow.models.Events;

import java.util.List;

@Dao
public interface EventsDao {

    @Query("SELECT * FROM events")
    LiveData<List<Events>> loadEvents();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<Events> eventss);

}
