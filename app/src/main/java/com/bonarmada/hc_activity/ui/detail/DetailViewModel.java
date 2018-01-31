package com.bonarmada.hc_activity.ui.detail;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.bonarmada.hc_activity.data.dao.RealmLiveData;
import com.bonarmada.hc_activity.data.repository.WeatherDataRepository;
import com.bonarmada.hc_activity.data.vo.WeatherData;
import com.bonarmada.hc_activity.ui.main.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * Created by bonbonme on 1/30/2018.
 */

public class DetailViewModel extends ViewModel implements MainPresenter {

    private int weatherDataId;
    public LiveData<WeatherData> weatherData;
    public MutableLiveData<Boolean> networkProcessIndicator = new MutableLiveData<>();

    WeatherDataRepository repository;

    @Inject
    public DetailViewModel(WeatherDataRepository repository) {
        this.repository = repository;
        subscribeToWeatherData();
    }


    private void subscribeToWeatherData() {
        RealmLiveData<WeatherData> liveWeatherData = this.repository.dao.getAsLiveData();

        weatherData = Transformations.map(liveWeatherData, new Function<RealmResults<WeatherData>, WeatherData>() {
            @Override
            public WeatherData apply(RealmResults<WeatherData> input) {
                onKillEvent();
                return repository.dao.get(weatherDataId);
            }
        });
    }


    public void getWeatherData() {
        this.onDispatchEvent();
        repository.getWeatherData(this.weatherDataId);
    }

    public int getWeatherDataId() {
        return weatherDataId;
    }

    public void setWeatherDataId(int weatherDataId) {
        this.weatherDataId = weatherDataId;
    }

    public void setWeatherData(LiveData<WeatherData> weatherData) {
        this.weatherData = weatherData;
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
