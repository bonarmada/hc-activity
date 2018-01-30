package com.bonarmada.hc_activity.data.dao;

import java.util.Collection;
import java.util.List;

import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by bonbonme on 1/30/2018.
 */

public interface Dao<T> {

    RealmLiveData get(RealmResults realmResults);

    List<T> get();

    T get(Integer id);

    Integer getId();

    RealmModel save(T item);

    void save(Collection<T> items);

    void clear();

    void delete(Integer id);

    void delete(Collection<T> items);

    Integer count();
}


