package com.jifflenow.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jifflenow.R;
import com.jifflenow.models.Events;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventHolder> {

    private View.OnClickListener mEventClickListener = null;
    private List<Events> mEventList = null;

    public EventsAdapter(View.OnClickListener onClickListener, List<Events> events) {
        this.mEventClickListener = onClickListener;
        this.mEventList = events;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_list_item, parent, false);
        return new EventHolder(view, mEventClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder eventHolder, int i) {
        Events events = mEventList.get(i);
        eventHolder.tvEventName.setText(events.getEventSystemName());
        eventHolder.tvLocation.setText(events.getLocation());
        eventHolder.rlEvent.setTag(events);
    }

    @Override
    public int getItemCount() {
        return mEventList == null ? 0 : mEventList.size();
    }

    class EventHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvEventName)
        TextView tvEventName;

        @BindView(R.id.tvLocation)
        TextView tvLocation;

        @BindView(R.id.rlEvent)
        RelativeLayout rlEvent;


        public EventHolder(@NonNull View itemView, View.OnClickListener mEventClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            rlEvent.setOnClickListener(mEventClickListener);
        }
    }
}
