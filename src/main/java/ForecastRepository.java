import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

public class ForecastRepository {

	public ForecastReport getForecast(WeatherRequest request) {
		return getForecast(request.getForecastRequest());
	}

	private ForecastReport getForecast(String json) {
		Map<String, String> result = parseForecastRequest(json);

		List<Double> coord = new ArrayList<>();
		coord.add(Double.parseDouble(result.get("longitude")));
		coord.add(Double.parseDouble(result.get("latitude")));

		List<Double> forMax = new ArrayList<>();
		forMax.add(Double.parseDouble(result.get("day1Max")));
		forMax.add(Double.parseDouble(result.get("day2Max")));
		forMax.add(Double.parseDouble(result.get("day3Max")));

		List<Double> forMin = new ArrayList<>();
		forMin.add(Double.parseDouble(result.get("day1Min")));
		forMin.add(Double.parseDouble(result.get("day2Min")));
		forMin.add(Double.parseDouble(result.get("day3Min")));

		return new ForecastReport(
				result.get("city"),
				result.get("country"),
				coord,
				forMin,
				forMax);
	}

	public Map<String, String> parseForecastRequest(String json) {
		Map<String, String> result = new HashMap<>();

		try {
			JSONParser parser = new JSONParser();
			JSONObject mainObj = (JSONObject) parser.parse(json);

			JSONObject cityData = (JSONObject) mainObj.get("city");
			result.put("city", (String) cityData.get("name"));
			result.put("country", (String) cityData.get("country"));

			JSONObject coordinates = (JSONObject) cityData.get("coord");
			String longitude = String.valueOf(coordinates.get("lon"));
			String latitude = String.valueOf(coordinates.get("lat"));
			result.put("longitude", longitude);
			result.put("latitude", latitude);

			LocalDate currentDate = LocalDate.now();
			double day1Min, day1Max, day2Min, day2Max, day3Min, day3Max;
			day1Min = day2Min = day3Min = 100;
			day1Max = day2Max = day3Max = -100;

			JSONArray days = (JSONArray) mainObj.get("list");

			for (int i = 0; i < days.size(); i++) {
				JSONObject day = (JSONObject) days.get(i);
				String dateStr = (String) day.get("dt_txt");
				LocalDate date = LocalDate.parse(dateStr.substring(0, 10));

				JSONObject temperatureObj = (JSONObject) day.get("main");

				double min = Double.parseDouble(String.valueOf(temperatureObj.get("temp_min")));
				double max = Double.parseDouble(String.valueOf(temperatureObj.get("temp_max")));

				if (DAYS.between(currentDate, date) == 0) {
					continue;
				} else if (DAYS.between(currentDate, date) == 1) {
					if (min < day1Min) {
						day1Min = min;
					}
					if (max > day1Max) {
						day1Max = max;
					}
				} else if (DAYS.between(currentDate, date) == 2) {
					if (min < day2Min) {
						day2Min = min;
					}
					if (max > day2Max) {
						day2Max = max;
					}
				} else if (DAYS.between(currentDate, date) == 3) {
					if (min < day3Min) {
						day3Min = min;
					}
					if (max > day3Max) {
						day3Max = max;
					}
				} else {
					break;
				}
			}

			result.put("day1Min", String.valueOf(day1Min));
			result.put("day1Max", String.valueOf(day1Max));
			result.put("day2Min", String.valueOf(day2Min));
			result.put("day2Max", String.valueOf(day2Max));
			result.put("day3Min", String.valueOf(day3Min));
			result.put("day3Max", String.valueOf(day3Max));
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
