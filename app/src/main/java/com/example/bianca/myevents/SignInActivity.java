package com.example.bianca.myevents;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class SignInActivity extends AppCompatActivity {

    Button signInButton;
    ImageView goBack;
    TextInputLayout email, password;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInButton = findViewById(R.id.sign_in_button);
        goBack = findViewById(R.id.go_back);
        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);

        realm = Realm.getDefaultInstance();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailText = email.getEditText().getText().toString();
                String passwordText= password.getEditText().getText().toString();

                if(emailText.length()>0 && passwordText.length()>0){
                    RealmResults<User> user = realm.where(User.class).findAll()
                            .where().equalTo("email", emailText)
                            .and().equalTo("password", passwordText).findAll();
                    if(user.isEmpty()){
                        email.getEditText().setError("Might be wrong");
                        password.getEditText().setError("Might be wrong");
                    }else{
                        SharedPreferences preferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("isUserLoggedIn", true);
                        for(int i=0; i<user.size(); i++){
                            Log.i("email", user.get(i).getEmail());
                            Log.i("pass",user.get(i).getPassword());
                            Log.i("id",user.get(i).getId());
                            editor.putString("userId", user.get(i).getId());

                            if(user.get(i) != null){
                                Intent eventsListActivity = new Intent(getApplicationContext(), EventsListActivity.class);
                                startActivity(eventsListActivity);
                            }
                        }
                        editor.apply();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Email and password must be completed", Toast.LENGTH_LONG).show();
                }

            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
            }
        });
    }
}
