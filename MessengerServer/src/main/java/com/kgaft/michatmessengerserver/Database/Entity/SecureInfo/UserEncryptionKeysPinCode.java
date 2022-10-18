package com.kgaft.michatmessengerserver.Database.Entity.SecureInfo;

import com.kgaft.michatmessengerserver.Database.Entity.User;

import javax.persistence.*;

@Entity
@Table(name = "UsersEncryptionKeysPinCodes")
public class UserEncryptionKeysPinCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pinCodeId;
    private byte[] pinCode;
    @OneToOne
    private User user;

    public UserEncryptionKeysPinCode(long pinCodeId, byte[] pinCode, User user) {
        this.pinCodeId = pinCodeId;
        this.pinCode = pinCode;
        this.user = user;
    }


    public UserEncryptionKeysPinCode() {
    }

    public long getPinCodeId() {
        return pinCodeId;
    }

    public void setPinCodeId(long pinCodeId) {
        this.pinCodeId = pinCodeId;
    }

    public byte[] getPinCode() {
        return pinCode;
    }

    public void setPinCode(byte[] pinCode) {
        this.pinCode = pinCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
