package com.bonarmada.hc_activity.data.api;

import com.bonarmada.hc_activity.data.vo.WeatherData;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by bonbonme on 1/30/2018.
 */

public class ApiResponse {
    private int cnt;
    private List<WeatherData> list;

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherData> getList() {
        return list;
    }

    public void setList(List<WeatherData> list) {
        this.list = list;
    }
}
