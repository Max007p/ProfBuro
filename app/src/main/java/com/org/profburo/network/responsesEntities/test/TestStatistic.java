package com.org.profburo.network.responsesEntities.test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestStatistic {
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("regionName")
    @Expose
    private String regionName;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
