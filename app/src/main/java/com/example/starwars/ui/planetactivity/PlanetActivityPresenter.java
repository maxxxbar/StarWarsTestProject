package com.example.starwars.ui.planetactivity;

import android.util.Log;

import com.example.starwars.entries.films.Films;
import com.example.starwars.entries.people.People;
import com.example.starwars.service.APIConnection;
import com.example.starwars.service.RestAPI;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PlanetActivityPresenter {

    private PlanetActivity planetActivity;
    private String TAG = PlanetActivityPresenter.class.getSimpleName();
    private APIConnection apiConnection;
    private RestAPI restAPI;
    private StringBuilder stringBuilderResidents = new StringBuilder();
    private StringBuilder stringBuilderFilms = new StringBuilder();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PlanetActivityPresenter(PlanetActivity planetActivity) {
        this.planetActivity = planetActivity;
    }

    public void getFilms(int id) {
        apiConnection = APIConnection.getInstance();
        restAPI = apiConnection.createGet();

        restAPI.getFilms(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Films>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Films films) {
                        stringBuilderFilms.append(films.getTitle()).append("\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        planetActivity.setTextViewFilms(stringBuilderFilms);

                    }
                });
    }

    public void getResidents(int id) {
        APIConnection apiConnection = APIConnection.getInstance();
        RestAPI restAPI = apiConnection.createGet();
        restAPI.getPeople(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<People>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(People people) {
                        stringBuilderResidents.append(people.getName()).append("\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        planetActivity.setTextViewResidents(stringBuilderResidents);
                    }
                });
    }

    public void compositeDispos() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
