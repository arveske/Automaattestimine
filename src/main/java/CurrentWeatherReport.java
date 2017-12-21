import java.util.List;

public class CurrentWeatherReport {

	private List<Double> coord;
	private double currentTemp;
	private String city;
	private String countryCode;

	CurrentWeatherReport(List<Double> coord,
						 double currentTemp,
						 String city,
						 String countryCode) {
		this.coord = coord;
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
				"Coordinates: " + coord.get(0) + ", " + coord.get(1) + "\n" +
				"Current temperature: " + currentTemp;
	}

	public double getCoord1() {
		return coord.get(0);
	}

	public double getCoord2() {
		return coord.get(1);
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
