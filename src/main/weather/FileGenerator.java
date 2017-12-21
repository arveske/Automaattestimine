package main.weather;

import main.weather.currentweather.CurrentWeatherReport;
import main.weather.currentweather.CurrentWeatherRepository;
import main.weather.forecast.ForecastReport;
import main.weather.forecast.ForecastRepository;

import java.io.*;

public class FileGenerator {

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
				writeToFile(currentWeatherReport, forecastReport);
			}
		}
	}

	public void writeToFile(CurrentWeatherReport currentWeatherReport, ForecastReport forecastReport) throws IOException {
		try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(currentWeatherReport.getCity() + ".txt"), "UTF-8"))) {
			out.write("City / Country code: " + currentWeatherReport.getCity());
			if (currentWeatherReport.getCountryCode() != null) {
				out.write("/ " + currentWeatherReport.getCountryCode());
				out.newLine();
			}
			out.write("Coordinates (x/y): " + currentWeatherReport.getCoord1() + ", " + currentWeatherReport.getCoord2());
			out.newLine();
			out.write("Current temperature: " + currentWeatherReport.getCurrentTemp());
			out.newLine();
			out.write("Forecast: ");
			out.newLine();
			out.write("Day 1: " + forecastReport.getDay1Max() + "; " +forecastReport.getDay1Min());
			out.newLine();
			out.write("Day 2: " + forecastReport.getDay2Max() + "; " +forecastReport.getDay2Min());
			out.newLine();
			out.write("Day 3: " + forecastReport.getDay3Max() + "; " +forecastReport.getDay3Min());
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
