package com.bonarmada.hc_activity.ui.main;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.bonarmada.hc_activity.data.dao.RealmLiveData;
import com.bonarmada.hc_activity.data.repository.WeatherDataRepository;
import com.bonarmada.hc_activity.data.vo.WeatherData;

import java.util.List;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * Created by bonbonme on 1/30/2018.
 */

public class MainViewModel extends ViewModel implements MainPresenter {

    public LiveData<List<WeatherData>> weatherDataList;
    public MutableLiveData<Boolean> networkProcessIndicator = new MutableLiveData<>();

    WeatherDataRepository repository;

    @Inject
    public MainViewModel(WeatherDataRepository repository) {
        this.repository = repository;

        subscribeToWeatherData();
    }


    private void subscribeToWeatherData() {
        RealmLiveData<WeatherData> liveWeatherData = this.repository.dao.getAsLiveData();

        weatherDataList = Transformations.map(liveWeatherData, new Function<RealmResults<WeatherData>, List<WeatherData>>() {
            @Override
            public List<WeatherData> apply(RealmResults<WeatherData> input) {
                onKillEvent();
                return repository.dao.get();
            }
        });
    }


    public void getMultipleWeatherData() {
        this.onDispatchEvent();
        repository.getMultipleWeatherData();
    }

    @Override
    public void onDispatchEvent() {
        networkProcessIndicator.setValue(true);
    }

    @Override
    public void onKillEvent() {
        networkProcessIndicator.setValue(false);
    }
}
