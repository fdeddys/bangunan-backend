package com.ddabadi.dto;

/**
 * Created by deddy on 5/27/16.
 */
public class UserAuthDto {

    private String userName;
    private String passkey;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasskey() {
        return passkey;
    }

    public void setPasskey(String passkey) {
        this.passkey = passkey;
    }
}
