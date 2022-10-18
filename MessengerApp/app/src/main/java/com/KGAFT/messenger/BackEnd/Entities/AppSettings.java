package com.KGAFT.messenger.BackEnd.Entities;

import com.KGAFT.messenger.BackEnd.DataBase.Annotations.Column;
import com.KGAFT.messenger.BackEnd.DataBase.Annotations.Id;
import com.KGAFT.messenger.BackEnd.DataBase.Annotations.Table;

@Table
public class AppSettings {
    @Id
    @Column
    private Integer id;
    @Column
    private String serverHttpConnectionAddress;
    @Column
    private String serverRpcConnectionAddress;
    @Column
    private String callRpcConnection;
    @Column
    private String appToken = "";
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private Long currentUser;
    @Column
    private byte[] pinCodeForEncryptionKeys;

    public AppSettings(Integer id, String serverHttpConnectionAddress) {
        this.id = id;
        this.serverHttpConnectionAddress = serverHttpConnectionAddress;
    }

    public AppSettings() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServerHttpConnectionAddress() {
        return serverHttpConnectionAddress;
    }

    public void setServerHttpConnectionAddress(String serverHttpConnectionAddress) {
        this.serverHttpConnectionAddress = serverHttpConnectionAddress;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public Long getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Long currentUserId) {
        this.currentUser = currentUserId;
    }

    public byte[] getPinCodeForEncryptionKeys() {
        return pinCodeForEncryptionKeys;
    }

    public void setPinCodeForEncryptionKeys(byte[] pinCodeForEncryptionKeys) {
        this.pinCodeForEncryptionKeys = pinCodeForEncryptionKeys;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServerRpcConnectionAddress() {
        return serverRpcConnectionAddress;
    }

    public void setServerRpcConnectionAddress(String serverRpcConnectionAddress) {
        this.serverRpcConnectionAddress = serverRpcConnectionAddress;
    }

    public String getCallRpcConnection() {
        return callRpcConnection;
    }

    public void setCallRpcConnection(String callRpcConnection) {
        this.callRpcConnection = callRpcConnection;
    }
}
