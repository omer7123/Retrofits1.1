package com.ripalay.retrofits11.data.remote;

import com.ripalay.retrofits11.data.models.Films;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilmApi {
    @GET("/films")
    Call<List<Films>> getFilms();

    @GET("/films/{id}")
    Call<Films> getFilm(
            @Path("id") String id
    );

}
