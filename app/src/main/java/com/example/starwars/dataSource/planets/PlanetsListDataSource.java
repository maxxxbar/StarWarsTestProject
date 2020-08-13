package com.example.starwars.dataSource.planets;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.starwars.databinding.FragmentFirstBinding;
import com.example.starwars.entries.planets.Planets;
import com.example.starwars.entries.planets.Result;
import com.example.starwars.service.LoadingStatus;
import com.example.starwars.service.RestAPI;
import com.example.starwars.ui.first.FirstFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PlanetsListDataSource extends PageKeyedDataSource<Integer, Result> {

    private final String TAG = getClass().getSimpleName();
    private RestAPI restAPI;

    public PlanetsListDataSource(RestAPI restAPI) {
        this.restAPI = restAPI;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Result> callback) {
        List<Result> resultList = new ArrayList<>();
        restAPI.getPlanetsResult(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Planets>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Planets planets) {
                        resultList.addAll(planets.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        callback.onResult(resultList, null, 2);
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
        List<Result> resultList = new ArrayList<>();
        restAPI.getPlanetsResult(params.key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Planets>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Planets planets) {
                        resultList.addAll(planets.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        callback.onResult(resultList, params.key + 1);
                    }
                });
    }

}

