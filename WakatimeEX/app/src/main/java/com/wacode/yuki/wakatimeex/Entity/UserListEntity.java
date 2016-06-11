package com.wacode.yuki.wakatimeex.Entity;

/**
 * Created by Riberd on 2016/06/06.
 */
public class UserListEntity {
    private int id;
    private String name;
    private String url;
    private boolean isValidCheck;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isValidCheck() {
        return isValidCheck;
    }

    public void setValidCheck(boolean validCheck) {
        isValidCheck = validCheck;
    }
}
