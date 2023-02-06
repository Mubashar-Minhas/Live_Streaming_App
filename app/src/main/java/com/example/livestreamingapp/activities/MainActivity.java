package com.example.livestreamingapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livestreamingapp.R;
import com.example.livestreamingapp.adapters.TVShowsAdapter;
import com.example.livestreamingapp.databinding.ActivityMainBinding;
import com.example.livestreamingapp.listeners.TVShowsListener;
import com.example.livestreamingapp.models.TVShowsModel;
import com.example.livestreamingapp.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class   MainActivity extends AppCompatActivity implements TVShowsListener {
    private MostPopularTVShowsViewModel viewModel;
    private ActivityMainBinding activityMainBinding;
    private List<TVShowsModel> showsModelList = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //setContentView(R.layout.activity_main);
        doInitialization();

    }

    private void doInitialization() {
        activityMainBinding.tvShowsRecyclerview.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter = new TVShowsAdapter(showsModelList,this);
        activityMainBinding.tvShowsRecyclerview.setAdapter(tvShowsAdapter);
        activityMainBinding.tvShowsRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.tvShowsRecyclerview.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePages) {
                        currentPage += 1;
                        getMostPopularTVShows();
                    }
                }
            }
        });
        getMostPopularTVShows();


    }

    private void getMostPopularTVShows() {
        toggleLoading();
        viewModel.getMostPopularTVShows(currentPage).observe(this, mostPopularTVShowsResponse -> {
            toggleLoading();
            if (mostPopularTVShowsResponse != null) {
                totalAvailablePages = mostPopularTVShowsResponse.getTotalPages();
                if (mostPopularTVShowsResponse.getTvShows() != null) {
                    int oldCount = showsModelList.size();
                    showsModelList.addAll(mostPopularTVShowsResponse.getTvShows());
                    tvShowsAdapter.notifyItemRangeInserted(oldCount, showsModelList.size());
                }

            }
        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            if (activityMainBinding.getIsLoading() != null && activityMainBinding.getIsLoading()) {
                activityMainBinding.setIsLoading(false);
            } else {
                activityMainBinding.setIsLoading(true);
            }

        } else {
            if (activityMainBinding.getIsLoadingMore() != null && activityMainBinding.getIsLoadingMore()) {
                activityMainBinding.setIsLoadingMore(false);
            } else {
                activityMainBinding.setIsLoadingMore(true);
            }
        }
    }

    @Override
    public void onTVShowClicked(TVShowsModel tvShowsModel) {
        Intent intent=new Intent(getApplicationContext(),TVShowDetailsActivity.class);
        intent.putExtra("id",tvShowsModel.getId());
        intent.putExtra("name",tvShowsModel.getName());
        intent.putExtra("startDate",tvShowsModel.getStartDate());
        intent.putExtra("country",tvShowsModel.getCountry());
        intent.putExtra("network",tvShowsModel.getNetwork());
        intent.putExtra("status",tvShowsModel.getStatus());
        startActivity(intent);
    }
}