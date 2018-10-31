package com.jifflenow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.jifflenow.data.JiffleRepository;
import com.jifflenow.models.EventDetails;

import java.util.List;

public class EventDetailsViewModel extends AndroidViewModel {

    private JiffleRepository mJiffleRepo;
    private LiveData<List<EventDetails>> mEventDetails;

    public EventDetailsViewModel(@NonNull Application application) {
        super(application);
        mJiffleRepo = JiffleRepository.getInstance(application);
    }

    public LiveData<List<EventDetails>> getEventDetails(String eventName) {
        if (mEventDetails == null) {
            this.mEventDetails = mJiffleRepo.getEventDetails(eventName);
        }
        return mEventDetails;
    }

    public LiveData<List<EventDetails>> getEventDetails() {
        return mEventDetails;
    }

}
