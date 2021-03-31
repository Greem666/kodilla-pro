package com.kodillapro.weather;

import com.kodillapro.weather.forecaster.EagerWeatherForecast;
import com.kodillapro.weather.forecaster.LazyWeatherForecast;
import com.kodillapro.weather.forecaster.WeatherForecastInterface;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class WeatherApp {

    public static void main(String[] args)
            throws InterruptedException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        WeatherApp.forecastWeatherAFewTimes(EagerWeatherForecast.class, "eager");
        WeatherApp.forecastWeatherAFewTimes(LazyWeatherForecast.class, "lazy");
    }

    private static void forecastWeatherAFewTimes(
            Class<?> forecasterDescriptor, String forecasterType)
            throws InterruptedException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {

        long start = System.currentTimeMillis();

        WeatherForecastInterface forecaster =
                (WeatherForecastInterface) forecasterDescriptor.getConstructor(new Class[]{}).newInstance();

        for (int n = 0; n < 5; n++) {
            System.out.println(forecaster.getWeather());

            boolean refreshNeeded = new Random().nextInt(100) < 20;
            if (refreshNeeded) {
                forecaster.refreshData();
            }
        }

        long end = System.currentTimeMillis();
        System.out.printf("Program execution time for %s forecaster: " + (end - start) + "[ms]\n\n", forecasterType);
    }
}
