package com.example.tpbdd.messages;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;

import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "messages")
@Sharded(shardKey = {"from"})
public class Messages {

    @Id
    private String id;
    private String from;
    private String to;
    private String message;
    private String date;

    public Messages() {
        super();
    }

    public Messages(String id, String from, String to, String message, String date) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.date = date;
    }

    public Messages(String fromUser, String toUser, String tchat, String date) {
        this.from = fromUser;
        this.to = toUser;
        this.message = tchat;
        this.date = date;
    }

    public Messages(String fromUser, String toUser, String tchat, Date date) {
        this.from = fromUser;
        this.to = toUser;
        this.message = tchat;
        this.date = date.toString();
    }

    public Messages(String fromUser, String toUser, String tchat) {
        this.from = fromUser;
        this.to = toUser;
        this.message = tchat;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.date = sdf.format(new Date());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
