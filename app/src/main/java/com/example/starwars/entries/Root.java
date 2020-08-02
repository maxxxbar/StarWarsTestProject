package com.example.starwars.entries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Root {

    @SerializedName("people")
    @Expose
    private String people;
    @SerializedName("planets")
    @Expose
    private String planets;
    @SerializedName("films")
    @Expose
    private String films;
    @SerializedName("species")
    @Expose
    private String species;
    @SerializedName("vehicles")
    @Expose
    private String vehicles;
    @SerializedName("starships")
    @Expose
    private String starships;

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getPlanets() {
        return planets;
    }

    public void setPlanets(String planets) {
        this.planets = planets;
    }

    public String getFilms() {
        return films;
    }

    public void setFilms(String films) {
        this.films = films;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getVehicles() {
        return vehicles;
    }

    public void setVehicles(String vehicles) {
        this.vehicles = vehicles;
    }

    public String getStarships() {
        return starships;
    }

    public void setStarships(String starships) {
        this.starships = starships;
    }



}