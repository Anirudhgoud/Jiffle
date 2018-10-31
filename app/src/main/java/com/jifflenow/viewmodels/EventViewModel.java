package com.jifflenow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.jifflenow.data.JiffleRepository;
import com.jifflenow.models.Events;

import java.util.List;

public class EventViewModel extends AndroidViewModel {

    private JiffleRepository mJiffleRepo;
    private LiveData<List<Events>> mListEvents;


    public EventViewModel(@NonNull Application application) {
        super(application);
        mJiffleRepo = JiffleRepository.getInstance(application.getApplicationContext());
        mListEvents = mJiffleRepo.getEvents(application.getApplicationContext());
    }

    public LiveData<List<Events>> getEvents() {
        return mListEvents;
    }
}
