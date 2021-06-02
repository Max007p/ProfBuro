package com.org.profburo.network.responsesEntities.test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HasAnyCompletedTest {
    @SerializedName("has_completed_tests")
    @Expose
    private Boolean hasCompletedTests;

    public Boolean getHasCompletedTests() {
        return hasCompletedTests;
    }

    public void setHasCompletedTests(Boolean hasCompletedTests) {
        this.hasCompletedTests = hasCompletedTests;
    }

}
