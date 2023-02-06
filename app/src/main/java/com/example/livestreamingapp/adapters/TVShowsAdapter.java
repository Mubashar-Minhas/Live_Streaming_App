package com.example.livestreamingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livestreamingapp.R;
import com.example.livestreamingapp.databinding.ItemContainerTvShowBinding;
import com.example.livestreamingapp.listeners.TVShowsListener;
import com.example.livestreamingapp.models.TVShowsModel;

import java.util.List;

public class TVShowsAdapter extends RecyclerView.Adapter<TVShowsAdapter.TVSHowViewHolder> {

    private List<TVShowsModel> tvShowsModelList;
    private LayoutInflater layoutInflater;
    private TVShowsListener tvShowsListener;

    public TVShowsAdapter(List<TVShowsModel> tvShowsModelList,TVShowsListener tvShowsListener) {
        this.tvShowsListener=tvShowsListener;
        this.tvShowsModelList = tvShowsModelList;
    }

    @NonNull
    @Override
    public TVSHowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerTvShowBinding tvShowBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_container_tv_show
                , parent, false);

        return new TVSHowViewHolder(tvShowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TVSHowViewHolder holder, int position) {
        holder.bindTVShow(tvShowsModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return tvShowsModelList.size();
    }

     class TVSHowViewHolder extends RecyclerView.ViewHolder {
        ItemContainerTvShowBinding itemContainerTvShowBinding;

        public TVSHowViewHolder(ItemContainerTvShowBinding itemContainerTvShowBinding) {
            super(itemContainerTvShowBinding.getRoot());
            this.itemContainerTvShowBinding = itemContainerTvShowBinding;
        }

        public void bindTVShow(TVShowsModel tvShowsModel) {
            itemContainerTvShowBinding.setTvShow(tvShowsModel);
            itemContainerTvShowBinding.executePendingBindings();
            itemContainerTvShowBinding.getRoot().setOnClickListener(v -> tvShowsListener.onTVShowClicked(tvShowsModel));
        }
    }
}
