import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileReadWriteTest {

    private String tallinnCurrentWeatherResponse;
    private String tallinnForecastResponse;
    private String tartuCurrentWeatherResponse;
    private String tartuForecastResponse;
    private String londonCurrentWeatherResponse;
    private String londonForecastResponse;

    private BufferedReader in;
    private WeatherRequest tallinnRequest;
    private WeatherRequest tartuRequest;
    private WeatherRequest londonRequest;

    @Before
    public void setup() throws IOException {
        in = mock(BufferedReader.class);
        when(in.readLine()).thenReturn("Tallinn", "Tartu", "London", null);

        tallinnRequest = mock(WeatherRequest.class);
        tartuRequest = mock(WeatherRequest.class);
        londonRequest = mock(WeatherRequest.class);

        readJSONFromFile();

        when(tallinnRequest.getCurrentWeatherRequest()).thenReturn(tallinnCurrentWeatherResponse);
        when(tallinnRequest.getForecastRequest()).thenReturn(tallinnForecastResponse);
        when(tartuRequest.getCurrentWeatherRequest()).thenReturn(tartuCurrentWeatherResponse);
        when(tartuRequest.getForecastRequest()).thenReturn(tartuForecastResponse);
        when(londonRequest.getCurrentWeatherRequest()).thenReturn(londonCurrentWeatherResponse);
        when(londonRequest.getForecastRequest()).thenReturn(londonForecastResponse);
    }

    @Test
    public void testReadInput() throws IOException {
        File input = new File("input.txt");
        assertTrue(input.exists());

        String line;
        List<String> values = new ArrayList<>();
        while ((line = in.readLine()) != null) {
            values.add(line);
        }

        assertEquals(3, values.size());
        assertEquals("Tallinn", values.get(0));
        assertEquals("Tartu", values.get(1));
        assertEquals("London", values.get(2));
    }

    @Test
    public void testTallinnReportFileWrite() throws IOException {
        generateReport(tallinnRequest);

        File result = new File("Tallinn.txt");
        assertTrue(result.exists());

        String[] values = readFromFile(result);

        assertEquals(6, values.length);
        assertEquals("Tallinn, EE", values[0]);
        assertEquals("24.75, 59.44", values[1]);
        assertEquals("5.1; 4.02", values[2]);
        assertEquals("4.34; 1.15", values[3]);
        assertEquals("3.17; 0.73", values[4]);
        assertEquals("-3.0", values[5]);
    }

    @Test
    public void testTartuReportFileWrite() throws IOException {
        generateReport(tartuRequest);

        File result = new File("Tartu.txt");
        assertTrue(result.exists());

        String[] values = readFromFile(result);

        assertEquals(6, values.length);
        assertEquals("Tartu, EE", values[0]);
        assertEquals("26.72, 58.37", values[1]);
        assertEquals("2.71; 0.53", values[2]);
        assertEquals("2.18; -0.43", values[3]);
        assertEquals("2.6; -1.12", values[4]);
        assertEquals("-3.0", values[5]);
    }

    @Test
    public void testLondonReportFileWrite() throws IOException {
        generateReport(londonRequest);

        File result = new File("London.txt");
        assertTrue(result.exists());

        String[] values = readFromFile(result);

        assertEquals(6, values.length);
        assertEquals("London, GB", values[0]);
        assertEquals("-0.13, 51.51", values[1]);
        assertEquals("5.06; 3.05", values[2]);
        assertEquals("4.54; 2.51", values[3]);
        assertEquals("5.49; -1.68", values[4]);
        assertEquals("9.45", values[5]);
    }

    private void generateReport(WeatherRequest request) throws IOException {
        CurrentWeatherRepository currentWeatherRepository = new CurrentWeatherRepository();
        ForecastRepository forecastRepository = new ForecastRepository();

        CurrentWeatherReport currentWeatherReport = currentWeatherRepository.getCurrentWeather(request);
        ForecastReport forecastReport = forecastRepository.getForecast(request);
    }

    private void readJSONFromFile() throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("json.txt"), "UTF-8"))) {
            tallinnCurrentWeatherResponse = in.readLine();
            tallinnForecastResponse = in.readLine();
            tartuCurrentWeatherResponse = in.readLine();
            tartuForecastResponse = in.readLine();
            londonCurrentWeatherResponse = in.readLine();
            londonForecastResponse = in.readLine();
        }
    }

    private String[] readFromFile(File file) throws IOException {
        List<String> values = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            while ((line = in.readLine()) != null) {
                values.add(line.split(":")[1].trim());
            }
        }
        return values.toArray(new String[values.size()]);
    }
}