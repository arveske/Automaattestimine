package main.weather.forecast;

import java.util.List;

public class ForecastReport {
	
	private String city;
	private String countryCode;
	private List<Double> coord;
	private List<Double> forMin;
	private List<Double> forMax;
	
		ForecastReport(String city,
				   String countryCode,
					   List<Double> coord,
					   List<Double> forMin,
					   List<Double> forMax) {
		this.city = city;
		this.countryCode = countryCode;
		this.coord = coord;
		this.forMax = forMax;
		this.forMin = forMin;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getCountry() {
		return countryCode;
	}
	
	public double getCoord1() {
		return coord.get(0);
	}
	
	public double getCoord2() {
		return coord.get(1);
	}
	
	public double getDay1Min() {
		return forMin.get(0);
	}
	
	public double getDay1Max() {
		return forMax.get(0);
	}
	
	public double getDay2Min() {
		return forMin.get(1);
	}
	
	public double getDay2Max() {
		return forMax.get(1);
	}
	
	public double getDay3Min() {
		return forMin.get(2);
	}
	
	public double getDay3Max() {
		return forMax.get(2);
	}
	
	@Override
	public String toString() {
		String startLine = "Forecast report.\n" +
				"City: " + city + "\n";
		if (countryCode != null) {
			startLine += "Country code: " + countryCode + "\n";
		}
		return startLine +
				"Coordinates: " + coord.get(0) + ", " + coord.get(1) + "\n" +
				"Forecast:\n" +
				"\tDay 1: " + forMin.get(0) + "; " + forMax.get(0) + "\n" +
				"\tDay 2: " + forMin.get(1) + "; " + forMax.get(1) + "\n" +
				"\tDay 3: " + forMin.get(2) + "; " + forMax.get(2);
	}
}
