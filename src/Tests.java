import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class Tests {
    private WeatherAPI weather;
    ArrayList ks = new ArrayList();

    @Before
    public void init() { weather = new WeatherAPI(); }

    @Test
    public void getTemperatureNow() {
        assertEquals(0, weather.getTemperatureNow());
    }

    @Test
    public void get3DaysHighestAndLowest() {
        assertTrue(weather.get3DaysHighestAndLowest() == ks);
    }

    @Test
    public void getGEO() {
        assertTrue(weather.getGEO() == null);
    }
}
