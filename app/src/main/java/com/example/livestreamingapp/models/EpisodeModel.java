package com.example.livestreamingapp.models;

import com.google.gson.annotations.SerializedName;

public class EpisodeModel {
    @SerializedName("season")
    private int season;

    @SerializedName("episode")
    private String episode;

    @SerializedName("name")
    private String name;

    @SerializedName("air_date")
    private String airDate;

    public int getSeason() {
        return season;
    }

    public String getEpisode() {
        return episode;
    }

    public String getName() {
        return name;
    }

    public String getAirDate() {
        return airDate;
    }
}
