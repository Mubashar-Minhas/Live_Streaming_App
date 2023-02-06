package com.example.livestreamingapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.livestreamingapp.models.TVShowDetailsResponse;
import com.example.livestreamingapp.repositories.MostPopularTVShowsRespository;
import com.example.livestreamingapp.repositories.TVShowDetailsRepository;

public class TVShowDetailsViewModel extends ViewModel {

    private TVShowDetailsRepository tvShowDetailsRepository;
    public TVShowDetailsViewModel() {
        tvShowDetailsRepository=new TVShowDetailsRepository();
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId) {
        return tvShowDetailsRepository.getTVShowDetails(tvShowId);
    }


}
