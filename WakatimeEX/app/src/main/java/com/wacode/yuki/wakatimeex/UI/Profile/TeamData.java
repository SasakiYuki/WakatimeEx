package com.wacode.yuki.wakatimeex.UI.Profile;

import android.graphics.Bitmap;

/**
 * Created by Riberd on 2016/05/30.
 */
public class TeamData {
    private Bitmap icon;
    private String name;
    private int status;

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
