import org.junit.Test;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileReadTest {

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

    private void generateReport(WeatherRequest request) throws IOException {
        CurrentWeatherRepository currentWeatherRepository = new CurrentWeatherRepository();
        ForecastRepository forecastRepository = new ForecastRepository();

        CurrentWeatherReport currentWeatherReport = currentWeatherRepository.getCurrentWeather(request);
        ForecastReport forecastReport = forecastRepository.getForecast(request);

        FileReadWrite fileReadWrite = new FileReadWrite();
        fileReadWrite.writeToFile(currentWeatherReport, forecastReport);
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