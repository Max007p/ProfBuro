package com.org.profburo.network.responsesEntities.test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestInsertResponse {
    @SerializedName("inserted_results")
    @Expose
    private Boolean areResultsInserted;

    public Boolean getAreResultsInserted() {
        return areResultsInserted;
    }

    public void setAreResultsInserted(Boolean areResultsInserted) {
        this.areResultsInserted = areResultsInserted;
    }
}
