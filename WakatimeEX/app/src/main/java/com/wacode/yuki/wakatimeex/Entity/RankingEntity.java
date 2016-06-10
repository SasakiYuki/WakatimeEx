package com.wacode.yuki.wakatimeex.Entity;

import android.graphics.drawable.ShapeDrawable;

/**
 * Created by Riberd on 2016/06/02.
 */
public class RankingEntity {
    private int rank;
    private String name;
    private String time;
    private int userId;
    private ShapeDrawable background;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ShapeDrawable getBackground() {
        return background;
    }

    public void setBackground(ShapeDrawable background) {
        this.background = background;
    }
}
