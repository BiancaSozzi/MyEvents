package com.example.bianca.myevents;

import java.util.List;

/**
 * Created by bianc on 22/11/2017.
 */

public class EventResponse {

    public List<Event> events;
    public List<Logo> logo;

    public static class Event{
        public Name name;
        public Logo logo;
    }

    public static class Name{
        public String text;
    }

    public static class Logo{
        public String url;
    }
}
