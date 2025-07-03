package com.data.user.api.output;

import jakarta.validation.Payload;

import java.util.UUID;

public class UserInfoDetails implements Payload {

    private String id;
    private long created;
    private long modified;
    private long last_login;
    private String token;
    private boolean isactice;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getModified() {
        return modified;
    }

    public void setModified(long modified) {
        this.modified = modified;
    }

    public long getLast_login() {
        return last_login;
    }

    public void setLast_login(long last_login) {
        this.last_login = last_login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isIsactice() {
        return isactice;
    }

    public void setIsactice(boolean isactice) {
        this.isactice = isactice;
    }
}
