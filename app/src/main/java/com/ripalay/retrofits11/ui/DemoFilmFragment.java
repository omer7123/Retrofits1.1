package com.ripalay.retrofits11.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.ripalay.retrofits11.App;
import com.ripalay.retrofits11.R;
import com.ripalay.retrofits11.data.models.Films;
import com.ripalay.retrofits11.databinding.FragmentDemoFilmBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DemoFilmFragment extends Fragment {
    private FragmentDemoFilmBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDemoFilmBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id;
        id = getArguments().getString("id");


        App.api.getFilm(id).enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                if (response.isSuccessful()) {
                    Films film = response.body();
                    binding.progressBar.setVisibility(View.GONE);
                    Glide.with(requireActivity()).load(film.getImage()).into(binding.avaFilm);
                    binding.titleFilm.setText(film.getTitle());
                    binding.describeFilm.setText(film.getDescription());
                    binding.directorFilm.setText(film.getDirector());
                    binding.titleOrigFilm.setText(film.getOriginalTitle());
                    binding.releaseFilm.setText(film.getReleaseDate());
                }
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
                Log.e("olol", t.getLocalizedMessage());
            }
        });
    }

}