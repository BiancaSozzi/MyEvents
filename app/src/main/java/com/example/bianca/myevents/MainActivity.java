package com.example.bianca.myevents;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Button signInButton, signUpButton;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        SharedPreferences preferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        boolean isUserLoggedIn = preferences.getBoolean("isUserLoggedIn", false);

        if(isUserLoggedIn){
            Intent eventslistactivity = new Intent(getApplicationContext(), EventsListActivity.class);
            startActivity(eventslistactivity);
            finish();
        }

        signInButton = findViewById(R.id.sign_in_button);
        signUpButton = findViewById(R.id.sign_up_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginActivity = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(loginActivity);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpActivity = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(signUpActivity);
            }
        });

        RealmResults<User> users = realm.where(User.class).findAll();
        for(int i=0; i< users.size(); i++){

            Log.i("email",users.get(i).getEmail());
            Log.i("username",users.get(i).getUserName());
            Log.i("password",users.get(i).getPassword());

        }
    }
}
