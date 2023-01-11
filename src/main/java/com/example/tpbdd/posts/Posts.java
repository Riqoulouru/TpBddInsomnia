package com.example.tpbdd.posts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "posts")
@Sharded(shardKey = {"userId"})
public class Posts {

    @Id
    private String id;
    private String userId;
    private String text;
    private String date;
    public Posts() {
        super();
    }

    public Posts(String id, String userId, String text, String date) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.date = date;
    }

    public Posts(String userId, String text) {
        this.userId = userId;
        this.text = text;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.date = sdf.format(new Date());
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

