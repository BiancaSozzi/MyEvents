package com.example.bianca.myevents;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;

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
    public void onBindViewHolder(EventViewholder holder, final int position) {
        final Event event = events.get(position);
        final String name = events.get(position).getName();
        final String description = events.get(position).getDescription();
        final String image = events.get(position).getImage();
        final String url = events.get(position).getUrl();
        final EventViewholder viewholder = holder;

        viewholder.name.setText(name);
        viewholder.description.setText(description);
        Picasso.with(context).load(image).placeholder(R.drawable.me_inner_logo).into(viewholder.image);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        //Para borrar solamente llamamos este metodo
                        event.deleteFromRealm();

                      notifyItemRemoved(viewholder.getAdapterPosition());
                    }
                });

            }
        });

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailActivity = new Intent(context, DetailActivity.class);

                detailActivity.putExtra(DetailActivity.EVENT_NAME,name);
                detailActivity.putExtra(DetailActivity.IMAGE_URL,image);
                detailActivity.putExtra(DetailActivity.EVENT_DESCRIPTION,description);
                detailActivity.putExtra(DetailActivity.EVENT_URL,url);
                DetailActivity.alreadySavedEvent = true;

                context.startActivity(detailActivity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }


    class EventViewholder extends RecyclerView.ViewHolder{

        ImageView image, delete;
        TextView name, description;
        RelativeLayout container;

        public EventViewholder(View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.my_event_container);
            image = itemView.findViewById(R.id.event_image);
            name = itemView.findViewById(R.id.event_name);
            description = itemView.findViewById(R.id.event_description);
            delete = itemView.findViewById(R.id.delete_event);

        }
    }
}


