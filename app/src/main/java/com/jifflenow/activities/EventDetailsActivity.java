package com.jifflenow.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.jifflenow.R;
import com.jifflenow.adapters.EventdetailsAdapter;
import com.jifflenow.models.EventDetails;
import com.jifflenow.viewmodels.EventDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDetailsActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.progressBar)
    View progressBar;

    @BindView(R.id.rlNoData)
    RelativeLayout rlNoData;


    private EventDetailsViewModel mEventDetailsViewModel;
    private EventdetailsAdapter mEventsAdapter;

    private List<EventDetails> mEventDetailsList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        mEventsAdapter = new EventdetailsAdapter(mEventDetailsList);
        mRecyclerView.setAdapter(mEventsAdapter);
    }

    private void initNSubscribeViewModel() {

        Intent intent = getIntent();
        String eventName = null;

        if (intent != null) {
            eventName = intent.getStringExtra("event_name");
        }


        mEventDetailsViewModel = ViewModelProviders.of(this).get(EventDetailsViewModel.class);

        progressBar.setVisibility(View.VISIBLE);

        mEventDetailsViewModel.getEventDetails(eventName).observe(this, new Observer<List<EventDetails>>() {
            @Override
            public void onChanged(@Nullable List<EventDetails> eventDetails) {
                if (eventDetails != null && !eventDetails.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    rlNoData.setVisibility(View.GONE);

                    mEventDetailsList.addAll(eventDetails);
                    mEventsAdapter.notifyDataSetChanged();
                } else {
                    progressBar.setVisibility(View.GONE);
                    rlNoData.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
