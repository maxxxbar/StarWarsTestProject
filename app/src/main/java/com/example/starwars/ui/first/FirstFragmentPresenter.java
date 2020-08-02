package com.example.starwars.ui.first;

import android.util.Log;

import com.example.starwars.entries.planets.Planets;
import com.example.starwars.entries.planets.Result;
import com.example.starwars.service.APIConnection;
import com.example.starwars.service.RestAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FirstFragmentPresenter {

    private String TAG = FirstFragmentPresenter.class.getSimpleName();
    private APIConnection apiConnection;
    private RestAPI restAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<Result> resultList = new ArrayList<>();


    private boolean isLastPage = false;
    private FirstFragment firstFragment;

    public FirstFragmentPresenter(FirstFragment firstFragment) {
        this.firstFragment = firstFragment;
    }


    public void getPlanetsList(int page) {
        apiConnection = APIConnection.getInstance();
        restAPI = apiConnection.createGet();
        restAPI
                .getPlanetsResult(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Planets>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Planets planets) {
                        Result resultItem;
                        for (int i = 0; i < planets.getResults().size(); i++) {
                            resultItem = planets.getResults().get(i);
                            resultList.add(resultItem);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        isLastPage = true;
                        Log.d(TAG, "onError isLastPage: " + isLastPage);
                    }

                    @Override
                    public void onComplete() {
                        firstFragment.setPlanetsAdapter(resultList);
                    }
                });
    }



    public Result getPlanetItem(int id) {
        return resultList.get(id);
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void compositeDispos() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }


    }
}
