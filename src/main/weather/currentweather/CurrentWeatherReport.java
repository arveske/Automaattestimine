package main.weather.currentweather;

public class CurrentWeatherReport {

	private double coord1;
	private double coord2;
	private double currentTemp;
	private String city;
	private String countryCode;
	
	CurrentWeatherReport(double coord1,
						 double coord2,
						 double currentTemp,
						 String city,
						 String countryCode) {
		this.coord1 = coord1;
		this.coord2 = coord2;
		this.currentTemp = currentTemp;
		this.city = city;
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		String startLine = "Current weather:\n" +
				"City: " + city + "\n";
		if (countryCode != null) {
			startLine += "Country code: " + countryCode + "\n";
		}
		return startLine +
				"Coordinates: " + coord1 + ", " + coord2 + "\n" +
				"Current temperature: " + currentTemp;
	}

	public double getCoord1() {
		return coord1;
	}

	public double getCoord2() {
		return coord2;
	}

	public double getCurrentTemp() {
		return currentTemp;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
}
