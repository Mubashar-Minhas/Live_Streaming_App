package com.example.livestreamingapp.responses;

import com.example.livestreamingapp.models.TVShowsModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShowsResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("pages")
    private int totalPages;

    @SerializedName("tv_shows")
    private List<TVShowsModel> tvShows;

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<TVShowsModel> getTvShows() {
        return tvShows;
    }
}
