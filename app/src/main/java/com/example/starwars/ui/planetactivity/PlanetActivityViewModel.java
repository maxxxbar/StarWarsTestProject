package com.example.starwars.ui.planetactivity;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.starwars.R;
import com.example.starwars.entries.films.Films;
import com.example.starwars.entries.people.People;
import com.example.starwars.entries.planets.Result;
import com.example.starwars.service.APIConnection;
import com.example.starwars.service.RestAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PlanetActivityViewModel extends AndroidViewModel {

    private String TAG = getClass().getSimpleName();
    private StringBuilder stringBuilderResidents;
    private StringBuilder stringBuilderFilms;
    private MutableLiveData<Result> resultMutableLiveData;
    private Result result;

    public PlanetActivityViewModel(@NonNull Application application) {
        super(application);
        resultMutableLiveData = new MutableLiveData<>();
        stringBuilderResidents = new StringBuilder();
        stringBuilderFilms = new StringBuilder();
    }

    public LiveData<Result> getResultMutableLiveData() {
        updateData();
        return resultMutableLiveData;
    }

    public void setResultMutableLiveData(MutableLiveData<Result> resultMutableLiveData) {
        this.resultMutableLiveData = resultMutableLiveData;
    }

    private void updateData() {
            result = resultMutableLiveData.getValue();
            if (result != null && result.getFilms().size() > 0) {
                for (int i = 0; i < result.getFilms().size(); i++) {
                    String films = result.getFilms().get(i).trim();
                    int filmsID = Integer.parseInt(films.substring(27, 28));
                    getFilms(filmsID);
                }
            } else if (result != null) {
                result.setFilms(new ArrayList<>(new ArrayList<>(Collections.singleton(getApplication().getResources().getString(R.string.no_information_msg)))));
                resultMutableLiveData.postValue(result);
            }
            if (result != null && result.getResidents().size() > 0) {
                for (int i = 0; i < result.getResidents().size(); i++) {
                    String resident = result.getResidents().get(i).trim();
                    if (resident.length() == 31) {
                        int residentID = Integer.parseInt(result.getResidents().get(i).substring(28, 30));
                        getResidents(residentID);
                    } else if (resident.length() == 30) {
                        int residentID = Integer.parseInt(result.getResidents().get(i).substring(28, 29));
                        getResidents(residentID);
                    }
                }
            } else if (result != null) {
                result.setResidents(new ArrayList<>(Collections.singleton(getApplication().getResources().getString(R.string.no_information_msg))));
                resultMutableLiveData.postValue(result);
            }
    }

    public void getFilms(int id) {
        APIConnection apiConnection = APIConnection.getInstance();
        RestAPI restAPI = apiConnection.createGet();
        restAPI.getFilms(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Films>() {
                    @Override
                    public void onSubscribe(Disposable d) {
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
                        List<String> films = new ArrayList<>();
                        films.add(String.valueOf(stringBuilderFilms));
                        result.setFilms(films);
                        resultMutableLiveData.postValue(result);
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
                        List<String> residents = new ArrayList<>();
                        residents.add(String.valueOf(stringBuilderResidents));
                        result.setResidents(residents);
                        resultMutableLiveData.postValue(result);
                    }
                });
    }
}
