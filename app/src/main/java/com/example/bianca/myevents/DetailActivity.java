package com.example.bianca.myevents;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmResults;

public class DetailActivity extends AppCompatActivity {

    public static final String IMAGE_URL = "Image";
    public static final String EVENT_NAME = "Name";
    public static final String EVENT_DESCRIPTION = "Description";
    public static final String EVENT_URL = "Url";
    public static boolean alreadySavedEvent = false;

    ImageView logo, saveIcon;
    TextView name, description, url, savetext;
    Realm realm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        logo = findViewById(R.id.event_logo);
        name = findViewById(R.id.event_name);
        description = findViewById(R.id.event_description);
        url = findViewById(R.id.event_url);
        saveIcon = findViewById(R.id.save_icon);
        savetext = findViewById(R.id.save_text);

        if(alreadySavedEvent){
            saveIcon.setVisibility(View.GONE);
            savetext.setVisibility(View.GONE);
        }

        realm = Realm.getDefaultInstance();

        final Intent i = getIntent();

        Picasso.with(this).load(i.getStringExtra(IMAGE_URL)).placeholder(R.drawable.me_inner_logo).into(logo);

        name.setText(i.getExtras().getString(EVENT_NAME));
        description.setText(i.getExtras().getString(EVENT_DESCRIPTION));
        url.setClickable(true);
        url.setMovementMethod(LinkMovementMethod.getInstance());
        url.setText(Html.fromHtml("<a href='"+i.getExtras().getString(EVENT_URL)+"'>"+getString(R.string.link_to_event_text)+"</a>"));

        saveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String image = i.getExtras().getString(IMAGE_URL);
                String eventname = name.getText().toString();
                String eventdescription = description.getText().toString();
                String eventurl = i.getExtras().getString(EVENT_URL);
                saveEvent(image, eventname,eventdescription,eventurl);
                Toast.makeText(getApplicationContext(), R.string.event_saved_toast_text,Toast.LENGTH_LONG).show();
            }
        });



    }

    private void saveEvent(final String image, final String eventname, final String eventdescription, final String eventurl){

      realm.executeTransaction(new Realm.Transaction() {
          @Override
          public void execute(Realm realm) {
              SharedPreferences preferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
              String userId = preferences.getString("userId", "");
              RealmResults<User> user =  realm.where(User.class).findAll().where().equalTo("id",userId).findAll();
              for(int i=0; i<user.size(); i++){
                  userId = user.get(i).getId();
              }
              Event event = new Event(image, eventname, eventdescription, eventurl, userId);
              realm.copyToRealm(event);
          }
      });
    }

}
