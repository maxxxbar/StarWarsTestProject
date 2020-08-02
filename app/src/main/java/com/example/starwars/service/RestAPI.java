package com.example.starwars.service;

import com.example.starwars.entries.Root;
import com.example.starwars.entries.films.Films;
import com.example.starwars.entries.films.FilmsRoot;
import com.example.starwars.entries.people.People;
import com.example.starwars.entries.people.PeopleRoot;
import com.example.starwars.entries.planets.Planets;
import com.example.starwars.entries.species.SpeciesRoot;
import com.example.starwars.entries.starships.StarshipsRoot;
import com.example.starwars.entries.vehicles.VehiclesRoot;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestAPI {

    @GET("planets/")
    Observable<Planets> getPlanetsResult(@Query("page") int page);

    @GET("people/{id}/")
    Observable<People> getPeople(@Path("id") int id);

    @GET("people/")
    Observable<PeopleRoot> getPeoplePage(@Query("page") int page);

    @GET("films/{id}/")
    Observable<Films> getFilms(@Path("id") int id);

    @GET("films/")
    Observable<FilmsRoot> getFilmsWithOutId();

    @GET("species/")
    Observable<SpeciesRoot> getSpecies(@Query("page") int page);

    @GET("vehicles/")
    Observable<VehiclesRoot> getVehicles(@Query("page") int page);

    @GET("starships/")
    Observable<StarshipsRoot> getSrtarsships(@Query("page") int page);

    @GET("/api/")
    Observable<Root> getRoot();


}
