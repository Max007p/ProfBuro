package com.org.profburo.network.responsesEntities.test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestResults {
    @SerializedName("specialName")
    @Expose
    private String specialName;
    @SerializedName("balls")
    @Expose
    private Integer balls;
    @SerializedName("maxBalls")
    @Expose
    private Integer maxBalls;

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    public Integer getBalls() {
        return balls;
    }

    public void setBalls(Integer balls) {
        this.balls = balls;
    }

    public Integer getMaxBalls() {
        return maxBalls;
    }

    public void setMaxBalls(Integer maxBalls) {
        this.maxBalls = maxBalls;
    }
}
