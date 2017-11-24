package com.example.bianca.myevents;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bianc on 19/11/2017.
 */

public class Event extends RealmObject {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String image;
    private String name;
    private String description;
    private String url;
    private String userId;

    public Event(){

    }

    public Event(String image, String name, String description, String url, String userId) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.url = url;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
