package com.example.starwars.ui.first;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.starwars.dataSource.planets.PlanetsListDataSource;
import com.example.starwars.dataSource.planets.PlanetsListDataSourceFactory;
import com.example.starwars.entries.planets.Result;
import com.example.starwars.service.APIConnection;
import com.example.starwars.service.LoadingStatus;
import com.example.starwars.service.RestAPI;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FirstFragmentViewModel extends AndroidViewModel {

    private APIConnection connection;
    private RestAPI api;
    private Executor executor;
    private LiveData<PlanetsListDataSource> dataSourceLiveData;
    private LiveData<PagedList<Result>> pagedListLiveData;
    private PlanetsListDataSourceFactory planetsListDataSourceFactory;
    private MutableLiveData<Boolean> isLoading;

    public FirstFragmentViewModel(@NonNull Application application) {
        super(application);
        connection = APIConnection.getInstance();
        api = connection.createGet();
        planetsListDataSourceFactory = new PlanetsListDataSourceFactory(application, api);
        dataSourceLiveData = planetsListDataSourceFactory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(2)
                .build();

        executor = Executors.newCachedThreadPool();

        pagedListLiveData = new LivePagedListBuilder<>(planetsListDataSourceFactory, config)
                .setFetchExecutor(executor)
                .build();
        isLoading = new MutableLiveData<>();
    }

    public LiveData<PagedList<Result>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading.postValue(isLoading);
    }
}
