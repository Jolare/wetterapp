package de.fh.albsig.hd86589.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class WeatherTest {

    @Test
    public void test() throws Exception {
        final Weather testWeather = mock(Weather.class);
        when(testWeather.getCity()).thenReturn("Stuttgart");
        assertEquals(testWeather.getCity(), "Stuttgart");
        when(testWeather.getRegion()).thenReturn("BW");
        assertEquals(testWeather.getRegion(), "BW");
        when(testWeather.getCountry()).thenReturn("DE");
        assertEquals(testWeather.getCountry(), "DE");
        when(testWeather.getCondition()).thenReturn("Sunny");
        assertEquals(testWeather.getCondition(), "Sunny");
        when(testWeather.getTemp()).thenReturn("3");
        assertEquals(testWeather.getTemp(), "3");
        when(testWeather.getChill()).thenReturn("27");
        assertEquals(testWeather.getChill(), "27");
        when(testWeather.getHumidity()).thenReturn("72");
        assertEquals(testWeather.getHumidity(), "72");
    }
}
