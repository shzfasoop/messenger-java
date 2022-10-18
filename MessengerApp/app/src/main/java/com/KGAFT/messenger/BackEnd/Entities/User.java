package com.KGAFT.messenger.BackEnd.Entities;

import com.KGAFT.messenger.BackEnd.DataBase.Annotations.Column;
import com.KGAFT.messenger.BackEnd.DataBase.Annotations.Id;
import com.KGAFT.messenger.BackEnd.DataBase.Annotations.Table;

@Table
public class User {
    @Id
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String login;
    @Column
    private Long userIconId;
    @Column
    private Long lastActive;
    @Column
    private String number;

    public User(Long id, String name, String login, Long lastActive) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.lastActive = lastActive;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getUserIconId() {
        return userIconId;
    }

    public void setUserIconId(Long userIconId) {
        this.userIconId = userIconId;
    }

    public Long getLastActive() {
        return lastActive;
    }

    public void setLastActive(Long lastActive) {
        this.lastActive = lastActive;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
