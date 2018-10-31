package com.jifflenow.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.jifflenow.R;
import com.jifflenow.adapters.EventsAdapter;
import com.jifflenow.models.Events;
import com.jifflenow.viewmodels.EventViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.progressBar)
    View progressBar;

    private EventViewModel mEventViewModel;
    private EventsAdapter mEventsAdapter;

    private List<Events> mEventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUi();
    }

    private void initUi() {
        initRecyclerView();
        initNSubscribeViewModel();
    }

    private void initRecyclerView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mEventsAdapter = new EventsAdapter(this, mEventList);
        mRecyclerView.setAdapter(mEventsAdapter);
    }

    private void initNSubscribeViewModel() {
        mEventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        progressBar.setVisibility(View.VISIBLE);
        mEventViewModel.getEvents().observe(this, new Observer<List<Events>>() {
            @Override
            public void onChanged(@Nullable List<Events> events) {
                if (events != null && !events.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    mEventList.addAll(events);
                    mEventsAdapter.notifyDataSetChanged();
                }else{
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rlEvent:
                if (v.getTag() instanceof Events) {
                    Events events = (Events) v.getTag();
                    String eventName = events.getEventSystemName();
                    if (!TextUtils.isEmpty(eventName)) {
                        getEventDetails(eventName);
                    }
                }
                break;
        }
    }

    private void getEventDetails(String eventName) {
        Intent intent = new Intent(this, EventDetailsActivity.class);
        intent.putExtra("event_name", eventName);
        startActivity(intent);
    }
}
