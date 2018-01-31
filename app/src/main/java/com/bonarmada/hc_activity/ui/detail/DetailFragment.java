package com.bonarmada.hc_activity.ui.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bonarmada.hc_activity.App;
import com.bonarmada.hc_activity.R;
import com.bonarmada.hc_activity.data.vo.Weather;
import com.bonarmada.hc_activity.data.vo.WeatherData;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bonbonme on 1/30/2018.
 */

public class DetailFragment extends Fragment{

    @BindView(R.id.empty_state)
    LinearLayout emptyStateContainer;

    @BindView(R.id.content_frame)
    LinearLayout contentFrame;

    @BindView(R.id.top_container)
    RelativeLayout topContainer;

    @BindView(R.id.ivIcon)
    AppCompatImageView ivIcon;

    @BindView(R.id.tvCaption)
    AppCompatTextView tvCaption;
    @BindView(R.id.tvDescription)
    AppCompatTextView tvDescription;

    @BindView(R.id.tvHumidity)
    AppCompatTextView tvHumidity;

    @BindView(R.id.tvPressure)
    AppCompatTextView tvPressure;

    @BindView(R.id.tvTemperature)
    AppCompatTextView tvTemperature;

    @BindView(R.id.tvWind)
    AppCompatTextView tvWind;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private static final String OWM_ICON_PATH = "http://openweathermap.org/img/w/";
    private int cityId;
    private WeatherData weatherData;
    private DetailViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        ((App) getActivity().getApplication()).getComponent().inject(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle == null) {
            emptyStateContainer.setVisibility(View.VISIBLE);
            contentFrame.setVisibility(View.GONE);
        } else {
            cityId = bundle.getInt("cityId");
            subscribeToViewModel();
        }
    }

    private void subscribeToViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel.class);
        viewModel.setWeatherDataId(cityId);
        viewModel.weatherData.observe(this, new Observer<WeatherData>() {
            @Override
            public void onChanged(@Nullable WeatherData temp) {
                if (temp!= null){
                    weatherData = temp;
                    //update ui
                    updateUi();
                }
            }
        });

        viewModel.getWeatherData();
    }

    private void updateUi() {
        if (weatherData == null)
            return;

        Weather weather = weatherData.getWeather().get(0);

        Picasso.with(getActivity()).load(OWM_ICON_PATH + weather.getIcon() + ".png").fit().into(ivIcon);
        getActivity().setTitle(weatherData.getName());
        tvDescription.setText(weatherData.getWeather().get(0).getMain());
        tvCaption.setText(weatherData.getWeather().get(0).getDescription());

        tvHumidity.setText(weatherData.getMain().getHumidity().toString());
        tvPressure.setText(weatherData.getMain().getPressure().toString());
        tvTemperature.setText(weatherData.getMain().getTemp().toString());
        tvWind.setText(weatherData.getWind().getSpeed().toString());
    }

    public void onListItemSelected(int id) {
        cityId = id;
        contentFrame.setVisibility(View.VISIBLE);
        emptyStateContainer.setVisibility(View.GONE);

        subscribeToViewModel();
    }
}
