package de.fh.albsig.hd86589.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class WeatherTest {

    private Weather weather;
    private Weather testWeather;

    @BeforeAll
    public void init() throws Exception {
        testWeather = mock(Weather.class);
        when(testWeather.getCity()).thenReturn("Stuttgart");
        when(testWeather.getRegion()).thenReturn("BW");
        when(testWeather.getCountry()).thenReturn("DE");
        when(testWeather.getCondition()).thenReturn("Sunny");
        when(testWeather.getTemp()).thenReturn("3");
        when(testWeather.getChill()).thenReturn("27");
        when(testWeather.getHumidity()).thenReturn("72");
    }

    @BeforeEach
    public void create() throws Exception {
        weather = new Weather();
    }

    @Test
    public void testTemp() throws Exception {
        weather.setChill("27");
        weather.setTemp("3");
        assertEquals(testWeather.getTemp(), weather.getTemp());
        assertEquals(testWeather.getChill(), weather.getChill());
    }

    @Test
    public void testLocation() throws Exception {
        weather.setCity("Stuttgart");
        weather.setRegion("BW");
        weather.setCountry("DE");
        assertEquals(testWeather.getCity(), weather.getCity());
        assertEquals(testWeather.getRegion(), weather.getRegion());
        assertEquals(testWeather.getCountry(), weather.getCountry());
    }

    @AfterEach
    public void intClean() throws Exception {
        weather = null;
    }

    @AfterAll
    public void clena() throws Exception {
        testWeather = null;
    }
}
