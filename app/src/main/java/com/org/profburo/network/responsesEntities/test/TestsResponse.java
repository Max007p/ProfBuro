package com.org.profburo.network.responsesEntities.test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestsResponse {
    @SerializedName("idTest")
    @Expose
    private Integer idTest;
    @SerializedName("titleTest")
    @Expose
    private String titleTest;
    @SerializedName("descriptionTest")
    @Expose
    private String descriptionTest;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("resultId")
    @Expose
    private Integer resultId;

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public Integer getIdTest() {
        return idTest;
    }

    public void setIdTest(Integer idTest) {
        this.idTest = idTest;
    }

    public String getTitleTest() {
        return titleTest;
    }

    public void setTitleTest(String titleTest) {
        this.titleTest = titleTest;
    }

    public String getDescriptionTest() {
        return descriptionTest;
    }

    public void setDescriptionTest(String descriptionTest) {
        this.descriptionTest = descriptionTest;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
