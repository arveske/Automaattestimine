import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class CurrentWeatherRepositoryTest {
	
	private final String Tallinn = "Tallinn";
	private final String EE = "EE";
	private final String metric = "metric";
	
	@Test
	void testIfWeatherRepositoryRespCityEqualsReqCity() {
		try {
			WeatherRequest request = new WeatherRequest(Tallinn, EE, metric);
			CurrentWeatherRepository repository = new CurrentWeatherRepository();
			
			CurrentWeatherReport report = repository.getCurrentWeather(request);
			
			assertEquals(report.getCity(), request.getCity());
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	void testIfForecastRepositoryRespCityEqualsReqCity() {
		try {
			WeatherRequest request = new WeatherRequest(Tallinn, EE, metric);
			ForecastRepository repository = new ForecastRepository();
			
			ForecastReport report = repository.getForecast(request);
			
			assertEquals(report.getCity(), request.getCity());
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	void testIfWeatherRepositoryRespCountryEqualsReqCountry() {
		try {
			WeatherRequest request = new WeatherRequest(Tallinn, EE, metric);
			CurrentWeatherRepository repository = new CurrentWeatherRepository();
			
			CurrentWeatherReport report = repository.getCurrentWeather(request);
			
			assertEquals(report.getCountryCode(), request.getCountry());
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	void testIfForecastRepositoryRespCountryEqualsReqCountry() {
		try {
			WeatherRequest request = new WeatherRequest(Tallinn, EE, metric);
			ForecastRepository repository = new ForecastRepository();
			
			ForecastReport report = repository.getForecast(request);
			
			assertEquals(report.getCountry(), request.getCountry());
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	void testCurrentWeatherURLBuild() {
		String rightURL = "http://api.openweathermap.org/data/2.5/weather?q=Tallinn,EE&units=metric&appid=48fef17a00a3a871675bcde9bca4b3d5";
		WeatherRequest request = new WeatherRequest(Tallinn, EE, metric);
		assertEquals(rightURL, request.buildURL(true));
	}
	
	@Test
	void testForecastURLBuild() {
		String rightURL = "http://api.openweathermap.org/data/2.5/forecast?q=Tallinn,EE&units=metric&appid=48fef17a00a3a871675bcde9bca4b3d5";
		WeatherRequest request = new WeatherRequest(Tallinn, EE, metric);
		assertEquals(rightURL, request.buildURL(false));
	}
	
	@Test
	void testURLBuildImperialUnits() {
		String rightURL = "http://api.openweathermap.org/data/2.5/weather?q=Tallinn,EE&units=imperial&appid=48fef17a00a3a871675bcde9bca4b3d5";
		WeatherRequest request = new WeatherRequest(Tallinn, EE, "imperial");
		assertEquals(rightURL, request.buildURL(true));
	}
	
	@Test
	void testCurrentWeatherResponseHasCoordinates() {
		try {
			WeatherRequest request = new WeatherRequest(Tallinn, EE, metric);
			CurrentWeatherRepository repository = new CurrentWeatherRepository();
			
			Map<String, String> report = repository.parseCurrentWeatherRequest(request.getCurrentWeatherRequest());
			
			assertTrue(report.containsKey("longitude"));
			assertTrue(report.containsKey("latitude"));
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	void testIfForecastResponseHasCoordinates() {
		try {
			WeatherRequest request = new WeatherRequest(Tallinn, EE, metric);
			ForecastRepository repository = new ForecastRepository();
			
			Map<String, String> report = repository.parseForecastRequest(request.getForecastRequest());
			
			assertTrue(report.containsKey("longitude"));
			assertTrue(report.containsKey("latitude"));
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	void testCurrentWeatherResponseHasTemperature() {
		try {
			WeatherRequest request = new WeatherRequest(Tallinn, EE, metric);
			CurrentWeatherRepository repository = new CurrentWeatherRepository();
			
			Map<String, String> report = repository.parseCurrentWeatherRequest(request.getCurrentWeatherRequest());
			
			assertTrue(report.containsKey("currentTemp"));
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	void testForecastResponseHasAllDays() {
		try {
			WeatherRequest request = new WeatherRequest(Tallinn, EE, metric);
			ForecastRepository repository = new ForecastRepository();
			
			Map<String, String> report = repository.parseForecastRequest(request.getForecastRequest());
			
			assertTrue(report.containsKey("day1Min"));
			assertTrue(report.containsKey("day1Max"));
			assertTrue(report.containsKey("day2Min"));
			assertTrue(report.containsKey("day2Max"));
			assertTrue(report.containsKey("day3Min"));
			assertTrue(report.containsKey("day3Max"));
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	void testTallinnLongitude() {
		double rightLongitude = 24.75;
		try {
			WeatherRequest request = new WeatherRequest(Tallinn, EE, metric);
			CurrentWeatherRepository repository = new CurrentWeatherRepository();
			
			CurrentWeatherReport report = repository.getCurrentWeather(request);
			
			assertEquals(rightLongitude, report.getCoord1());
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	void testTallinnLatitude() {
		double rightLatitude = 59.44;
		try {
			WeatherRequest request = new WeatherRequest(Tallinn, EE, metric);
			CurrentWeatherRepository repository = new CurrentWeatherRepository();
			
			CurrentWeatherReport report = repository.getCurrentWeather(request);
			
			assertEquals(rightLatitude, report.getCoord2());
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	void testLondonLongitude() {
		double rightLongitude = -0.13;
		try {
			WeatherRequest request = new WeatherRequest("London", "GB", metric);
			CurrentWeatherRepository repository = new CurrentWeatherRepository();
			
			CurrentWeatherReport report = repository.getCurrentWeather(request);
			
			assertEquals(rightLongitude, report.getCoord1());
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	void testLondonLatitude() {
		double rightLatitude = 51.51;
		try {
			WeatherRequest request = new WeatherRequest("London", "GB", metric);
			CurrentWeatherRepository repository = new CurrentWeatherRepository();
			
			CurrentWeatherReport report = repository.getCurrentWeather(request);
			
			assertEquals(rightLatitude, report.getCoord2());
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
}