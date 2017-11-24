package com.example.bianca.myevents;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class EventsListActivity extends AppCompatActivity {

    RecyclerView eventsRecyclerView;
    FloatingActionButton addButton;
    ImageView signOut;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);

        addButton = findViewById(R.id.floating_add_button);
        signOut = findViewById(R.id.sign_out);


        eventsRecyclerView = findViewById(R.id.events_list);
        eventsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        realm = Realm.getDefaultInstance();

       SharedPreferences preferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);

       String userid = preferences.getString("userId","");

       RealmResults<Event> events = realm.where(Event.class).findAll().where().equalTo("userId", userid).findAll();

        EventAdapter adapter = new EventAdapter(events, this);
       eventsRecyclerView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newEventsActivity = new Intent(getApplicationContext(),NewEventsActivity.class);
                startActivity(newEventsActivity);
                finish();
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isUserLoggedIn", false);
                editor.apply();
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }
        });

    }

}
