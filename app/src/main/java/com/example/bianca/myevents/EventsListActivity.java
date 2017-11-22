package com.example.bianca.myevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridLayout;

import java.util.List;

public class EventsListActivity extends AppCompatActivity {

    RecyclerView eventsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);

        eventsRecyclerView = findViewById(R.id.events_list);
        eventsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<Event> events = Event.getFakeEvents();

        EventAdapter adapter = new EventAdapter(events, this);
        eventsRecyclerView.setAdapter(adapter);
    }
}
