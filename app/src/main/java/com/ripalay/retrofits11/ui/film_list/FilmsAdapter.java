package com.ripalay.retrofits11.ui.film_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ripalay.retrofits11.data.models.Films;
import com.ripalay.retrofits11.databinding.ItemFilmBinding;

import java.util.ArrayList;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.ViewHolder> {

    private List<Films> list = new ArrayList<>();
    private onClick onClickItem;

    public void setOnClickItem(FilmsAdapter.onClick onClick) {
        this.onClickItem = onClick;
    }

    public Films getItem(int position){
        return list.get(position);
    }

    public void setList(List<Films> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmBinding binding = ItemFilmBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemFilmBinding binding;

        public ViewHolder(@NonNull ItemFilmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem.onItemClick(getAdapterPosition());
                }
            });
        }

        public void onBind(Films films) {
            binding.filmName.setText(films.getTitle());
            binding.filmDirector.setText(films.getDirector());
        }
    }

    public interface onClick {
        void onItemClick(int position);
    }
}
