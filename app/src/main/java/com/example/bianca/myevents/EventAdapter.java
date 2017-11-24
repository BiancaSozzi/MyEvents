package com.example.bianca.myevents;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by bianc on 19/11/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewholder> {

    List<Event> events;
    Context context;

    public EventAdapter(List<Event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @Override
    public EventViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new EventViewholder(inflater.inflate(R.layout.my_event_item,parent,false));
    }

    @Override
    public void onBindViewHolder(EventViewholder holder, int position) {
        final String name = events.get(position).getName();
        final String description = events.get(position).getDescription();
        final String image = events.get(position).getImage();
        final EventViewholder viewholder = holder;

        viewholder.name.setText(name);
        viewholder.description.setText(description);
        Picasso.with(context).load(image).placeholder(R.drawable.me_inner_logo).into(viewholder.image);

    }

    @Override
    public int getItemCount() {
        return events.size();
    }


    class EventViewholder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name, description;

        public EventViewholder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.event_image);
            name = itemView.findViewById(R.id.event_name);
            description = itemView.findViewById(R.id.event_description);

        }
    }
}


