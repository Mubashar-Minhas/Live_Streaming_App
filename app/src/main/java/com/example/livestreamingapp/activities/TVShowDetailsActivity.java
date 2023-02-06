package com.example.livestreamingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.livestreamingapp.R;
import com.example.livestreamingapp.adapters.ImageSliderAdapter;
import com.example.livestreamingapp.databinding.ActivityTvshowDetailsBinding;
import com.example.livestreamingapp.repositories.TVShowDetailsRepository;
import com.example.livestreamingapp.viewmodels.TVShowDetailsViewModel;

public class TVShowDetailsActivity extends AppCompatActivity {
    private ActivityTvshowDetailsBinding activityTvshowDetailsBinding;
    private TVShowDetailsViewModel tvShowDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTvshowDetailsBinding= DataBindingUtil.setContentView(this,
                R.layout.activity_tvshow_details);
       // setContentView(R.layout.activity_tvshow_details);
        doInitialization();
    }

    private void doInitialization() {
        tvShowDetailsViewModel=new ViewModelProvider(this).get(TVShowDetailsViewModel.class);
        getTVShowDetails();
    }
    private void getTVShowDetails(){
        activityTvshowDetailsBinding.setIsLoading(true);
        String tvShowId=String.valueOf(getIntent().getIntExtra("id",-1));
        tvShowDetailsViewModel.getTVShowDetails(tvShowId).observe(this,tvShowDetailsResponse -> {
            activityTvshowDetailsBinding.setIsLoading(false);
           /* Toast.makeText(getApplicationContext(),tvShowDetailsResponse.getTvShowDetails().getUrl(),
                    Toast.LENGTH_SHORT).show();*/
            if(tvShowDetailsResponse!=null)
            if(tvShowDetailsResponse.getTvShowDetails()!=null){
                if(tvShowDetailsResponse.getTvShowDetails().getPictures()!=null){
                 loadImageSlider(tvShowDetailsResponse.getTvShowDetails().getPictures());
                }
            }
        });
    }

    private void loadImageSlider(String[] sliderImages){
        activityTvshowDetailsBinding.sliderViewPager.setOffscreenPageLimit(1);
        activityTvshowDetailsBinding.sliderViewPager.setAdapter(new ImageSliderAdapter(sliderImages));
        activityTvshowDetailsBinding.sliderViewPager.setVisibility(View.VISIBLE);
        activityTvshowDetailsBinding.viewFadingEdge.setVisibility(View.VISIBLE);
    }
}