package com.example.bianca.myevents;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bianc on 22/11/2017.
 */

public class NewEventAdapter extends RecyclerView.Adapter<NewEventAdapter.NewEventViewHolder> {
    List<EventResponse.Event> eventList;
    Context context;

    public NewEventAdapter(List<EventResponse.Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @Override
    public NewEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new NewEventViewHolder(inflater.inflate(R.layout.new_event_item,parent,false));
    }

    @Override
    public void onBindViewHolder(NewEventViewHolder holder, int position) {
        final String event = eventList.get(position).name.text;
        final String eventImageURL = eventList.get(position).logo.url;

        final NewEventViewHolder viewHolder = holder;

        Picasso.with(context).load(eventImageURL).placeholder(R.drawable.me_inner_logo).into(viewHolder.eventImage);

        viewHolder.eventName.setText(event);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class NewEventViewHolder extends RecyclerView.ViewHolder{
        TextView eventName;
        ImageView eventImage;
        public NewEventViewHolder (View itemView) {
            super(itemView);

            eventName=itemView.findViewById(R.id.event_name);
            eventImage=itemView.findViewById(R.id.event_image);

        }

    }



}
