package com.example.bianca.myevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewEventsActivity extends AppCompatActivity {

    RecyclerView neweventsRecycleView;
    SearchView search;
    String searchparam="";
    String token = "XAQOUOUM7OXHPRHWSNFG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_events);

        search = findViewById(R.id.search);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchparam = search.getQuery().toString();
                getEvent();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        neweventsRecycleView = findViewById(R.id.new_events_list);
        neweventsRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        getEvent();

    }

    private void getEvent(){
      Gson gson = new GsonBuilder().create();

      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(EventsService.ENDPOINT)
              .addConverterFactory(GsonConverterFactory.create(gson))
              .build();

      EventsService eventsService = retrofit.create(EventsService.class);

      Call<EventResponse> call = eventsService.listEvents(token, searchparam);

      call.enqueue(new Callback<EventResponse>() {
          @Override
          public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
              NewEventAdapter adapter = new NewEventAdapter(response.body().events, NewEventsActivity.this);
              neweventsRecycleView.setAdapter(adapter);
          }

          @Override
          public void onFailure(Call<EventResponse> call, Throwable t) {
              Toast.makeText(NewEventsActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
          }
      });

    }
}
