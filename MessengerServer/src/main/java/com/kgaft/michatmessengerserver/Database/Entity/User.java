package com.kgaft.michatmessengerserver.Database.Entity;

import javax.persistence.*;

@Entity
@Table(name = "UsersInfo")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String login;

    private String number = "";
    private long userIconId;
    private long lastActive;

    public User(String name, String login) {
        this.name = name;
        this.login = login;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getLastActive() {
        return lastActive;
    }

    public void setLastActive(long lastActive) {
        this.lastActive = lastActive;
    }

    public long getUserIconId() {
        return userIconId;
    }

    public void setUserIconId(long userIconId) {
        this.userIconId = userIconId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        return ((User)obj).getId()==this.id;
    }
}
