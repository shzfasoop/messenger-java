package com.kgaft.michatmessengerserver.Database.Entity.SecureInfo;

import javax.persistence.*;

@Entity
@Table(name = "UsersPasswords")
public class UsersPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long passwordId;
    private long userId;
    @Column(columnDefinition="TEXT")
    private String password;

    public UsersPassword(long userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public UsersPassword() {
    }

    public long getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(long passwordId) {
        this.passwordId = passwordId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
