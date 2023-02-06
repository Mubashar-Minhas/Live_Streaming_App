package com.example.livestreamingapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.livestreamingapp.repositories.MostPopularTVShowsRespository;
import com.example.livestreamingapp.responses.TVShowsResponse;

public class MostPopularTVShowsViewModel extends ViewModel {
    private MostPopularTVShowsRespository mostPopularTVShowsRespository;

    public MostPopularTVShowsViewModel() {
     mostPopularTVShowsRespository=new MostPopularTVShowsRespository();
    }

    public LiveData<TVShowsResponse>getMostPopularTVShows(int page) {
     return  mostPopularTVShowsRespository.getMostPopularTVShows(page);
    }
}
