package com.jifflenow.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.jifflenow.data.local.EventDatabase;
import com.jifflenow.data.local.dao.EventsDao;
import com.jifflenow.models.EventDetails;
import com.jifflenow.models.Events;
import com.jifflenow.network.ApiClient;
import com.jifflenow.network.ApiInterface;
import com.jifflenow.util.GeneralUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JiffleRepository {

    private static JiffleRepository ourInstance = null;

    private EventsDao meventDao;


    private JiffleRepository(Context application) {
        EventDatabase db = EventDatabase.getDatabase(application);
        meventDao = db.eventDao();
    }

    public static JiffleRepository getInstance(final Context context) {
        if (ourInstance == null) {
            synchronized (JiffleRepository.class) {
                if (ourInstance == null) {
                    ourInstance = new JiffleRepository(context);
                }
            }
        }
        return ourInstance;
    }

    public LiveData<List<Events>> getEvents(Context applicationContext) {

        final MutableLiveData<List<Events>> data = new MutableLiveData<>();

        if (GeneralUtil.isNetworkConnected(applicationContext)) {
            Call<List<Events>> callEvents = ApiClient.getReftrofitClient().
                    create(ApiInterface.class).getEvents();


            callEvents.enqueue(new Callback<List<Events>>() {
                @Override
                public void onResponse(Call<List<Events>> call, Response<List<Events>> response) {
                    if (response.body() != null && response.code() == 200) {
                        Log.e("kjdk s", response.message());
                        data.setValue(response.body());
                        meventDao.saveMovies(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Events>> call, Throwable t) {
                    Log.e("kjdk s", call.toString());
                    data.setValue(null);
                }
            });
        }
        return data;
    }


    public LiveData<List<EventDetails>> getEventDetails(String eventName) {

        final MutableLiveData<List<EventDetails>> meventDetails = new MutableLiveData<>();

        Call<List<EventDetails>> eventDetails = ApiClient.getReftrofitClient().
                create(ApiInterface.class).getEventDetails(eventName);

        eventDetails.enqueue(new Callback<List<EventDetails>>() {
            @Override
            public void onResponse(Call<List<EventDetails>> call, Response<List<EventDetails>> response) {

                if (response.code() == 200 && response.body() != null) {
                    meventDetails.setValue(response.body());
                } else {
                    meventDetails.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<EventDetails>> call, Throwable t) {
                meventDetails.setValue(null);
            }
        });
        return meventDetails;
    }
}
