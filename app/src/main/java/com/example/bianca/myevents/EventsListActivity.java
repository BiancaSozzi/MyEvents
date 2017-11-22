package com.example.bianca.myevents;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;

import java.util.List;

public class EventsListActivity extends AppCompatActivity {

    RecyclerView eventsRecyclerView;
    FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);

        addButton = findViewById(R.id.floating_add_button);

        eventsRecyclerView = findViewById(R.id.events_list);
        eventsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<Event> events = Event.getFakeEvents();

        EventAdapter adapter = new EventAdapter(events, this);
        eventsRecyclerView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newEventsActivity = new Intent(getApplicationContext(),NewEventsActivity.class);
                startActivity(newEventsActivity);
            }
        });
    }
}
