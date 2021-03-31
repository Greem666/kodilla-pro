package com.kodillapro.weather.forecaster;

public class LazyWeatherForecast implements WeatherForecastInterface {
    private WeatherForecastInterface forecaster;
    private String forecast;

    @Override
    public String getWeather() throws InterruptedException {
        if (forecast == null) {
            this.manageForecasterInstance();
            this.updateForecast();
        }
        return forecast;
    }

    @Override
    public void refreshData() throws InterruptedException {
        this.manageForecasterInstance();
        forecaster.refreshData();
        this.updateForecast();

    }

    private void manageForecasterInstance() throws InterruptedException {
        if (forecast == null) {
            forecaster = new EagerWeatherForecast();
        }
    }

    private void updateForecast() throws InterruptedException {
        forecast = forecaster.getWeather();
    }
}
