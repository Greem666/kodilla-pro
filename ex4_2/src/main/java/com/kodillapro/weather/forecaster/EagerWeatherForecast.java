package com.kodillapro.weather.forecaster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EagerWeatherForecast implements WeatherForecastInterface {

    private String weather;

    public EagerWeatherForecast() throws InterruptedException {
        this.refreshData();
    }

    @Override
    public String getWeather() {
        return weather;
    }

    @Override
    public void refreshData() throws InterruptedException {
        Thread.sleep(5000);
        updateWeather();
    }

    private void updateWeather() {
        List<String> options = Arrays.asList("Cloudy", "Sunny", "Rainy");
        int newWeatherIdx = new Random().nextInt(options.size());
        weather = "Weather is " + options.get(newWeatherIdx) + ".";
    }
}
