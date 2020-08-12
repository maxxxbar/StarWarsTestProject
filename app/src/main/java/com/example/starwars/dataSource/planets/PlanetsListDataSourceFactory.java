package com.example.starwars.dataSource.planets;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.starwars.entries.planets.Result;
import com.example.starwars.service.RestAPI;

public class PlanetsListDataSourceFactory extends DataSource.Factory<Integer, Result> {
    private PlanetsListDataSource dataSource;
    private MutableLiveData<PlanetsListDataSource> mutableLiveData;
    private RestAPI restAPI;
    private Application application;

    public PlanetsListDataSourceFactory(RestAPI restAPI) {
        mutableLiveData = new MutableLiveData<>();
        this.restAPI = restAPI;
    }

    @NonNull
    @Override
    public DataSource<Integer, Result> create() {
        dataSource = new PlanetsListDataSource(restAPI);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<PlanetsListDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

}
