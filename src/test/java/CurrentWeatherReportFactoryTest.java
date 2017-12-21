import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrentWeatherReportFactoryTest {
	
	private static final String TALLINN_RESPONSE = "{\"coord\":{\"lon\":24.75,\"lat\":59.44},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":1,\"pressure\":1008,\"humidity\":80,\"temp_min\":1,\"temp_max\":1},\"visibility\":10000,\"wind\":{\"speed\":5.1,\"deg\":150},\"clouds\":{\"all\":90},\"dt\":1511904000,\"sys\":{\"type\":1,\"id\":5014,\"message\":0.0029,\"country\":\"EE\",\"sunrise\":1511851645,\"sunset\":1511875845},\"id\":588409,\"name\":\"Tallinn\",\"cod\":200}";
	private static final String TARTU_RESPONSE = "{\"coord\":{\"lon\":26.73,\"lat\":58.38},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":-0.05,\"pressure\":1017.2,\"humidity\":88,\"temp_min\":-0.05,\"temp_max\":-0.05,\"sea_level\":1025.68,\"grnd_level\":1017.2},\"wind\":{\"speed\":6.96,\"deg\":170.502},\"clouds\":{\"all\":92},\"dt\":1511909072,\"sys\":{\"message\":0.0036,\"country\":\"EE\",\"sunrise\":1511850704,\"sunset\":1511875836},\"id\":588335,\"name\":\"Tartu\",\"cod\":200}";
	private static final String LONDON_RESPONSE = "{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":3.74,\"pressure\":1008,\"humidity\":70,\"temp_min\":2,\"temp_max\":6},\"visibility\":10000,\"wind\":{\"speed\":4.1,\"deg\":280},\"clouds\":{\"all\":90},\"dt\":1511904000,\"sys\":{\"type\":1,\"id\":5091,\"message\":0.0132,\"country\":\"GB\",\"sunrise\":1511854867,\"sunset\":1511884576},\"id\":2643743,\"name\":\"London\",\"cod\":200}";
	
	private static CurrentWeatherReport tallinnReport;
	private static CurrentWeatherReport tartuReport;
	private static CurrentWeatherReport londonReport;
	
	@BeforeAll
	static void initialize() {
		CurrentWeatherRepository repository = new CurrentWeatherRepository();
		tallinnReport = repository.getCurrentWeather(TALLINN_RESPONSE);
		tartuReport = repository.getCurrentWeather(TARTU_RESPONSE);
		londonReport = repository.getCurrentWeather(LONDON_RESPONSE);
	}
	
	@Test
	void tallinnReportCityNameTest() {
		assertEquals("Tallinn", tallinnReport.getCity());
	}
	
	@Test
	void tartuReportCityNameTest() {
		assertEquals("Tartu", tartuReport.getCity());
	}
	
	@Test
	void londonReportCityNameTest() {
		assertEquals("London", londonReport.getCity());
	}
	
	@Test
	void tallinnReportCountryNameTest() {
		assertEquals("EE", tallinnReport.getCountryCode());
	}
	
	@Test
	void tartuReportCountryNameTest() {
		assertEquals("EE", tartuReport.getCountryCode());
	}
	
	@Test
	void londonReportCountryNameTest() {
		assertEquals("GB", londonReport.getCountryCode());
	}
	
	@Test
	void tallinnReportLongitudeTest() {
		assertEquals(24.75, tallinnReport.getCoord1());
	}
	
	@Test
	void tartuReportLongitudeTest() {
		assertEquals(26.73, tartuReport.getCoord1());
	}
	
	@Test
	void londonReportLongitudeTest() {
		assertEquals(-0.13, londonReport.getCoord1());
	}
	
	@Test
	void tallinnReportLatitudeTest() {
		assertEquals(59.44, tallinnReport.getCoord2());
	}
	
	@Test
	void tartuReportLatitudeTest() {
		assertEquals(58.38, tartuReport.getCoord2());
	}
	
	@Test
	void londonReportLatitudeTest() {
		assertEquals(51.51, londonReport.getCoord2());
	}
	
	@Test
	void tallinnReportCurrentTempTest() {
		assertEquals(1, tallinnReport.getCurrentTemp());
	}
	
	@Test
	void tartuReportCurrentTempTest() {
		assertEquals(-0.05, tartuReport.getCurrentTemp());
	}
	
	@Test
	void londonReportCurrentTempTest() {
		assertEquals(3.74, londonReport.getCurrentTemp());
	}
}