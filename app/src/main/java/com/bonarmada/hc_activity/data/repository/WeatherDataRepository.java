package com.bonarmada.hc_activity.data.repository;

import android.util.Log;

import com.bonarmada.hc_activity.data.api.ApiResponse;
import com.bonarmada.hc_activity.data.api.WeatherDataRemote;
import com.bonarmada.hc_activity.data.dao.WeatherDataDao;
import com.bonarmada.hc_activity.data.vo.WeatherData;

import java.awt.font.TextAttribute;

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
    public static final String TAG = WeatherDataRepository.class.getSimpleName();
    public static WeatherDataRemote remote;
    public static WeatherDataDao dao;

    @Inject
    public WeatherDataRepository(WeatherDataRemote remote, WeatherDataDao dao){
        this.remote = remote;
        this.dao = dao;
    }

    public static void getWeatherData(int cityId){
        remote.getWeatherById(cityId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<WeatherData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Response<WeatherData> weatherDataResponse) {
                        if (weatherDataResponse.code() == 200){
                            Log.e(TAG, weatherDataResponse.body().toString());
                            dao.save(weatherDataResponse.body());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }
                });
    }

    public static void getMultipleWeatherData(){
        // TODO: dynamic id input, etc.
        remote.getGroupWeather("3067696,5391959,2643743").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<ApiResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "OnSub");

                    }

                    @Override
                    public void onSuccess(Response<ApiResponse> apiResponseResponse) {
                        if (apiResponseResponse.code() == 200){
                            Log.e(TAG, apiResponseResponse.body().getList().size()+"");
                            dao.save(apiResponseResponse.body().getList());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }
                });
    }
}
