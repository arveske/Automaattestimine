import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        input: while (true) {
            System.out.print("Read cities from input.txt (tex) or input them yourself (inp)? ");
            switch (sc.nextLine()) {
                case "tex":
                    try {
                        new FileRead().readFromFile();
                        System.out.println("Weather file added\n");
                    } catch (Exception e) {
                        System.out.println("Wrong command\n");
                    }
                    break input;
                case "inp":
                    userInput();
                    break input;
                default:
                    System.out.println("Wrong command");
            }
        }
    }

    private static void userInput() {
        String city;

        while (true) {
            System.out.println("Input \"quit\" for quit");
            System.out.print("City: ");
            city = sc.nextLine();
            if (city.equals("quit")) {
                break;
            }

            try {
                WeatherRequest request = new WeatherRequest(city, null, "metric");

                CurrentWeatherRepository currentWeatherRepository = new CurrentWeatherRepository();
                ForecastRepository forecastRepository = new ForecastRepository();

                CurrentWeatherReport currentWeatherReport = currentWeatherRepository.getCurrentWeather(request);
                ForecastReport forecastReport = forecastRepository.getForecast(request);

                FileWriter writer = new FileWriter(currentWeatherReport, forecastReport);
                writer.writeToFile();
                System.out.println("Weather file added\n");
            } catch (Exception e) {
                System.out.println("Wrong name\n");
            }
        }
    }
}