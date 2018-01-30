package com.bonarmada.hc_activity.data.repository;

import android.util.Log;

import com.bonarmada.hc_activity.data.api.ApiResponse;
import com.bonarmada.hc_activity.data.api.WeatherDataRemote;
import com.bonarmada.hc_activity.data.dao.WeatherDataDao;
import com.bonarmada.hc_activity.data.vo.WeatherData;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by bonbonme on 1/30/2018.
 */

public class WeatherDataRepository {
    public static WeatherDataRemote remote;
    public static WeatherDataDao dao;

    @Inject
    public WeatherDataRepository(WeatherDataRemote remote, WeatherDataDao dao){
        this.remote = remote;
        this.dao = dao;
    }

    public static void getWeatherData(){
        remote.getWeatherById(2643743).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<WeatherData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Response<WeatherData> weatherDataResponse) {
                        if (weatherDataResponse.code() == 200){
                            dao.save(weatherDataResponse.body());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public static void getMultipleWeatherData(){
        remote.getGroupWeather("3067696,5391959,2643743").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<ApiResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Response<ApiResponse> apiResponseResponse) {
                        if (apiResponseResponse.code() == 200){
                            dao.save(apiResponseResponse.body().getList());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
