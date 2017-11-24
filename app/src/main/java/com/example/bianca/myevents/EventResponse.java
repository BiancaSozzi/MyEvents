package com.example.bianca.myevents;

import java.util.List;

/**
 * Created by bianc on 22/11/2017.
 */

public class EventResponse {

    public List<Event> events;

    public static class Event{
        public Name name;
        public Logo logo;
        public Description description;
        public String url;
    }

    public static class Name{
        public String text;
    }

    public static class Logo{
        public String url;
    }

    public static class Description{
        public String text;
    }
}
