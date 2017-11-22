package com.example.bianca.myevents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bianc on 19/11/2017.
 */

public class Event {

    private int image;
    private String name;
    private String description;

    public Event(int image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public static List<Event> getFakeEvents(){
        List<Event> fakeEvents = new ArrayList<>();

        Event event1 = new Event(R.mipmap.ic_launcher, "Arjona en Concierto", "Arjona en Arena Maipú mendoza...fafdas");
        Event event2 = new Event(R.drawable.me_inner_logo, "Las Pastillas del Abuelo", "Una vez más vuelve a mendoza el grupo que jasdjsakldsa");

        fakeEvents.add(event1);
        fakeEvents.add(event2);
        fakeEvents.add(event1);
        fakeEvents.add(event2);
        fakeEvents.add(event1);
        fakeEvents.add(event2);
        fakeEvents.add(event1);
        fakeEvents.add(event2);
        fakeEvents.add(event1);
        fakeEvents.add(event2);
        fakeEvents.add(event1);
        fakeEvents.add(event2);
        fakeEvents.add(event1);
        fakeEvents.add(event2);

        return fakeEvents;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
