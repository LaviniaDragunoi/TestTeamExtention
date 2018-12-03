package com.example.user.testteamextention.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResponse {

    @SerializedName("results")
    @Expose
    private List<ItemObject> results;
    @SerializedName("total_results")
    @Expose
    private int totalResults;

    public List<ItemObject> getResults() {
        return results;
    }

    public void setResults(List<ItemObject> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
