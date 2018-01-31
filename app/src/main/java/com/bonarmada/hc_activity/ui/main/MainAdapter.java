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

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bonbonme on 1/31/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    public interface AdapterListener {
        void onItemClick(int position, int id);
    }

    private static Context context;
    private List<WeatherData> weatherDataList;
    private AdapterListener listener;


    public MainAdapter(Context context, List<WeatherData> weatherDataList, AdapterListener listener) {
        this.weatherDataList = weatherDataList;
        this.context = context;
        this.listener = listener;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final WeatherData weatherData = weatherDataList.get(position);

        DecimalFormat numberFormat = new DecimalFormat("#.0");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position, weatherData.getId());
            }
        });
        holder.tvCityName.setText(weatherData.getName());
        holder.tvHumidity.setText(weatherData.getMain().getHumidity().toString());
        holder.tvPressure.setText(weatherData.getMain().getPressure().toString());
        holder.tvTemperature.setText(numberFormat.format(weatherData.getMain().getTemp()).toString());
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
        @BindView(R.id.tvCityName)
        AppCompatTextView tvCityName;

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