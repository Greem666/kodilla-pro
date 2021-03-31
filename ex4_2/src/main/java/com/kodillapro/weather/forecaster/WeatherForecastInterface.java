package com.kodillapro.weather.forecaster;

public interface WeatherForecastInterface {
    String getWeather() throws InterruptedException;
    void refreshData() throws InterruptedException;
}
