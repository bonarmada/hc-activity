package com.bonarmada.hc_activity.ui.main;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bonarmada.hc_activity.R;
import com.bonarmada.hc_activity.data.vo.WeatherData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bonbonme on 1/31/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private static Context context;
    private List<WeatherData> weatherDataList;
//    private AdapterListener listener;


    public MainAdapter(Context context, List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
        this.context = context;
    }

    public void refresh(List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
        this.notifyDataSetChanged();
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_city, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WeatherData weatherData = weatherDataList.get(position);

        holder.tvHumidity.setText(weatherData.getMain().getHumidity().toString());
        holder.tvPressure.setText(weatherData.getMain().getPressure().toString());
        holder.tvTemperature.setText(weatherData.getMain().getTemp().toString());
        holder.tvWind.setText(weatherData.getWind().getSpeed().toString());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Bind ui objects here
        @BindView(R.id.tvHumidity)
        AppCompatTextView tvHumidity;
        @BindView(R.id.tvPressure)
        AppCompatTextView tvPressure;
        @BindView(R.id.tvTemperature)
        AppCompatTextView tvTemperature;
        @BindView(R.id.tvWind)
        AppCompatTextView tvWind;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemCount() {
        return this.weatherDataList.size();
    }
}