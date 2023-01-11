package com.example.tpbdd.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;

import java.util.List;

@Document(collection = "users")
@Sharded(shardKey = { "username" })
public class Users {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;
    private String password;
    private String name;
    private String email;
    private List<String> friends;
    private String profile_picture;
    private String description;

    public Users() {
        super();
    }

    public Users(String id, String username, String password, String name, String email, List<String> friends, String profile_picture, String description) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.friends = friends;
        this.profile_picture = profile_picture;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addFriend(String friend) {
        this.friends.add(friend);
    }

    public void removeFriend(String friend) {
        this.friends.remove(friend);
    }


}
