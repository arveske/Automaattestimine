package main.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherRequest {
	
	private final String APIkey = "5ad86dcb343b3b28131d5d8cf488d0ad";
	private String city;
	private String country;
	private String units;
	
	WeatherRequest(String city, String country, String units) {
		this.city = city;
		this.country = country;
		this.units = units;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getUnits() {
		return units;
	}
	
	public String getCurrentWeatherRequest() {
		String url = buildURL(true);
		return getRequestResponse(url);
	}
	
	public String getForecastRequest() {
		String url = buildURL(false);
		return getRequestResponse(url);
	}
	
	public String buildURL(boolean requestCurrentWeather) {
		String url = "http://api.openweathermap.org/data/2.5/";
		if (requestCurrentWeather) {
			url += "weather?q=" + city;
		} else {
			url += "forecast?q=" + city;
		}
		if (country != null) {
			url += "," + country;
		}
		url += "&units=" + units;
		url += "&appid=" + APIkey;
		return url;
	}
	
	private String getRequestResponse(String urlAsString) {
		String jsonResponse = "";
		
		try {
			URL url = new URL(urlAsString);
			URLConnection connection = url.openConnection();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				jsonResponse += line;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return jsonResponse;
	}
}
