import java.io.*;

public class FileRead {

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
