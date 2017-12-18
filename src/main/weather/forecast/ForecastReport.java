package main.weather.forecast;

public class ForecastReport {
	
	private String city;
	private String countryCode;
	private double coord1;
	private double coord2;
	private double day1Min;
	private double day1Max;
	private double day2Min;
	private double day2Max;
	private double day3Min;
	private double day3Max;
	
		ForecastReport(String city,
				   String countryCode,
				   double coord1,
				   double coord2,
				   double day1Min,
				   double day1Max,
				   double day2Min,
				   double day2Max,
				   double day3Min,
				   double day3Max) {
		this.city = city;
		this.countryCode = countryCode;
		this.coord1 = coord1;
		this.coord2 = coord2;
		this.day1Min = day1Min;
		this.day1Max = day1Max;
		this.day2Min = day2Min;
		this.day2Max = day2Max;
		this.day3Min = day3Min;
		this.day3Max = day3Max;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getCountry() {
		return countryCode;
	}
	
	public double getCoord1() {
		return coord1;
	}
	
	public double getCoord2() {
		return coord2;
	}
	
	public double getDay1Min() {
		return day1Min;
	}
	
	public double getDay1Max() {
		return day1Max;
	}
	
	public double getDay2Min() {
		return day2Min;
	}
	
	public double getDay2Max() {
		return day2Max;
	}
	
	public double getDay3Min() {
		return day3Min;
	}
	
	public double getDay3Max() {
		return day3Max;
	}
	
	@Override
	public String toString() {
		String startLine = "Forecast report.\n" +
				"City: " + city + "\n";
		if (countryCode != null) {
			startLine += "Country code: " + countryCode + "\n";
		}
		return startLine +
				"Coordinates: " + coord1 + ", " + coord2 + "\n" +
				"Forecast:\n" +
				"\tDay 1: " + day1Min + "; " + day1Max + "\n" +
				"\tDay 2: " + day2Min + "; " + day2Max + "\n" +
				"\tDay 3: " + day3Min + "; " + day3Max;
	}
}
