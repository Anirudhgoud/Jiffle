package com.jifflenow.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jifflenow.R;
import com.jifflenow.models.EventDetails;
import com.jifflenow.models.Events;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventdetailsAdapter extends RecyclerView.Adapter<EventdetailsAdapter.DetailsHolder> {

    private List<EventDetails> mEventDetailsList = null;


    public EventdetailsAdapter(List<EventDetails> mEventDetailsList) {
        this.mEventDetailsList = mEventDetailsList;
    }

    @Override
    public EventdetailsAdapter.DetailsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.layout_list_item, viewGroup, false);
        return new DetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventdetailsAdapter.DetailsHolder detailsHolder, int i) {

        EventDetails eventDetails = mEventDetailsList.get(i);
        detailsHolder.tvEventName.setText(eventDetails.getMeetingWith());
        detailsHolder.tvLocation.setText(eventDetails.getLocation());
    }

    @Override
    public int getItemCount() {
        return mEventDetailsList==null?0:mEventDetailsList.size();
    }


    class DetailsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvEventName)
        TextView tvEventName;

        @BindView(R.id.tvLocation)
        TextView tvLocation;

        @BindView(R.id.rlEvent)
        RelativeLayout rlEvent;


        public DetailsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
