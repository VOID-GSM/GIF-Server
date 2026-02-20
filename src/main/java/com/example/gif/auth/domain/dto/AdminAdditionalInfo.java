package com.example.gif.auth.domain.dto;

import lombok.Getter;

@Getter
public class AdminAdditionalInfo {
    private String username;
    private boolean master;

    public void setUsername(String username) {
        this.username = username;
    }
    public void setMaster(boolean master) {
        this.master = master;
    }
}
