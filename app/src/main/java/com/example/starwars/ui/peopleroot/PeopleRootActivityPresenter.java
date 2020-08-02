package com.example.starwars.ui.peopleroot;

import android.util.Log;

import com.example.starwars.entries.films.FilmsRoot;
import com.example.starwars.entries.people.PeopleRoot;
import com.example.starwars.entries.planets.Planets;
import com.example.starwars.entries.species.SpeciesRoot;
import com.example.starwars.entries.starships.StarshipsRoot;
import com.example.starwars.entries.vehicles.VehiclesRoot;
import com.example.starwars.service.APIConnection;
import com.example.starwars.service.RestAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PeopleRootActivityPresenter {

    private String TAG = PeopleRootActivity.class.getSimpleName();
    private APIConnection apiConnection;
    private RestAPI restAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<String> resultList = new ArrayList<>();
    private PeopleRootActivity peopleRootActivity;
    private boolean isLastPage = false;


    public PeopleRootActivityPresenter(PeopleRootActivity peopleRootActivity) {
        this.peopleRootActivity = peopleRootActivity;
    }

    public void getPeople(int page) {
        APIConnection apiConnection = APIConnection.getInstance();
        RestAPI restAPI = apiConnection.createGet();
        restAPI.getPeoplePage(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PeopleRoot>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(PeopleRoot peopleRoot) {
                        for (int i = 0; i < peopleRoot.getResults().size(); i++)
                            resultList.add(peopleRoot.getResults().get(i).getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        isLastPage = true;
                    }

                    @Override
                    public void onComplete() {
                        peopleRootActivity.setPeopleAdapter(resultList);
                    }
                });
    }

    public void getPlanetsList(int page) {
        APIConnection apiConnection = APIConnection.getInstance();
        RestAPI restAPI = apiConnection.createGet();

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
                        for (int i = 0; i < planets.getResults().size(); i++) {
                            resultList.add(planets.getResults().get(i).getName());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        isLastPage = true;
                    }

                    @Override
                    public void onComplete() {
                        peopleRootActivity.setPeopleAdapter(resultList);
                    }
                });
    }

    public void getFilms() {
        APIConnection apiConnection = APIConnection.getInstance();
        RestAPI restAPI = apiConnection.createGet();
        restAPI.getFilmsWithOutId()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<FilmsRoot>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(FilmsRoot films) {
                        for (int i = 0; i < films.getResults().size(); i++) {
                            resultList.add(films.getResults().get(i).getTitle());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        isLastPage = true;
                    }

                    @Override
                    public void onComplete() {
                        peopleRootActivity.setPeopleAdapter(resultList);

                    }
                });
    }

    public void getSpecies(int page) {
        APIConnection apiConnection = APIConnection.getInstance();
        RestAPI restAPI = apiConnection.createGet();
        restAPI.getSpecies(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SpeciesRoot>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onNext(SpeciesRoot speciesRoot) {
                        for (int i = 0; i < speciesRoot.getResults().size(); i++) {
                            resultList.add(speciesRoot.getResults().get(i).getName());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        isLastPage = true;
                    }

                    @Override
                    public void onComplete() {
                        peopleRootActivity.setPeopleAdapter(resultList);
                    }
                });
    }

    public void getVehicles(int page) {
        APIConnection apiConnection = APIConnection.getInstance();
        RestAPI restAPI = apiConnection.createGet();
        restAPI.getVehicles(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<VehiclesRoot>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onNext(VehiclesRoot vehiclesRoot) {
                        for (int i = 0; i < vehiclesRoot.getResults().size(); i++) {
                            resultList.add(vehiclesRoot.getResults().get(i).getName());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        isLastPage = true;
                    }

                    @Override
                    public void onComplete() {
                        peopleRootActivity.setPeopleAdapter(resultList);
                    }
                });
    }

    public void getStarships(int page) {
        APIConnection apiConnection = APIConnection.getInstance();
        RestAPI restAPI = apiConnection.createGet();
        restAPI.getSrtarsships(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<StarshipsRoot>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onNext(StarshipsRoot starshipsRoot) {
                        for (int i = 0; i < starshipsRoot.getResults().size(); i++) {
                            resultList.add(starshipsRoot.getResults().get(i).getName());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        isLastPage = true;
                    }

                    @Override
                    public void onComplete() {
                        peopleRootActivity.setPeopleAdapter(resultList);
                    }
                });
    }


    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public void compositeDispos() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}

