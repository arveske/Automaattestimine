package main.weather;

import main.weather.currentweather.CurrentWeatherReport;
import main.weather.currentweather.CurrentWeatherRepository;
import main.weather.forecast.ForecastReport;
import main.weather.forecast.ForecastRepository;

import java.io.*;

public class InputReader {

	private CurrentWeatherReport currentWeatherReport;
	private ForecastReport forecastReport;

	public void readFromFile() throws IOException {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"))) {
			String line;
			String city;
			String country = null;

			while ((line = in.readLine()) != null) {
				String[] request = line.trim().split("|");
				city = request[0];

				if (request.length > 1) {
					country = request[1];
				}

				getWeatherAndForecast(city, country);

				FileWriter writer = new FileWriter(currentWeatherReport, forecastReport);
				writer.writeToFile();
			}
		}
	}

	private void getWeatherAndForecast(String city, String country) {
		WeatherRequest request = new WeatherRequest(city, country, "metric");

		CurrentWeatherRepository currentWeatherRepository = new CurrentWeatherRepository();
		ForecastRepository forecastRepository = new ForecastRepository();

		currentWeatherReport = currentWeatherRepository.getCurrentWeather(request);
		forecastReport = forecastRepository.getForecast(request);
	}
}
