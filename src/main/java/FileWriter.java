import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileWriter {
    private CurrentWeatherReport currentWeatherReport;
    private ForecastReport forecastReport;

    FileWriter(CurrentWeatherReport currentWeatherReport,
               ForecastReport forecastReport) {
        this.currentWeatherReport = currentWeatherReport;
        this.forecastReport = forecastReport;
    }

    public void writeToFile() throws IOException {
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(currentWeatherReport.getCity() + ".txt"), "UTF-8"))) {
            out.write("City / Country code: " + currentWeatherReport.getCity());
            if (currentWeatherReport.getCountryCode() != null) {
                out.write("/ " + currentWeatherReport.getCountryCode());
                out.newLine();
            }
            out.write("Coordinates (x/y): " + currentWeatherReport.getCoord1() + ", " + currentWeatherReport.getCoord2());
            out.newLine();
            out.write("Current temperature: " + currentWeatherReport.getCurrentTemp());
            out.newLine();
            out.write("Forecast: ");
            out.newLine();
            out.write("Day 1: " + forecastReport.getDay1Max() + "; " +forecastReport.getDay1Min());
            out.newLine();
            out.write("Day 2: " + forecastReport.getDay2Max() + "; " +forecastReport.getDay2Min());
            out.newLine();
            out.write("Day 3: " + forecastReport.getDay3Max() + "; " +forecastReport.getDay3Min());
        }
    }
}
