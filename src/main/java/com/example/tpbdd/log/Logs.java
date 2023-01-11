package com.example.tpbdd.log;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;

import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "logs")
@Sharded(shardKey = {"userId"})
public class Logs {
    @Id
    private String id;
    private String userId;
    private String action;
    private String date;

    public Logs() {
        super();
    }

    public Logs(String userId, String action) {
        this.userId = userId;
        this.action = action;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.date = sdf.format(new Date());
    }

    public Logs(String userId, String action,String date) {
        this.userId = userId;
        this.action = action;
        //pas de vérification format date : flemme
        this.date = date;
    }

    public Logs(String userId, String action, Date date) {
        this.userId = userId;
        this.action = action;
        //pas de vérification du format date : flemme
        this.date = date.toString();
    }
    public Logs(String id, String userId, String action, String date) {
        this.id = id;
        this.userId = userId;
        this.action = action;
        //pas de vérification du format date : flemme
        this.date = date;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
