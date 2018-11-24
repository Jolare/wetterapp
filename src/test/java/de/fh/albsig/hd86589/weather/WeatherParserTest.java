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

import java.io.File;

@TestInstance(Lifecycle.PER_CLASS)
public class WeatherParserTest {
    private final File testFile = new File("src/test/resources/testWeather.xml");
    private Weather weather;
    private WeatherParser parserTest;

    @BeforeAll
    public void init() throws Exception {
        weather = mock(Weather.class);
        when(weather.getCity()).thenReturn("Stuttgart");
        when(weather.getRegion()).thenReturn(" BW");
        when(weather.getCountry()).thenReturn("Germany");
        when(weather.getCondition()).thenReturn("Sunny");
        when(weather.getTemp()).thenReturn("6");
        when(weather.getChill()).thenReturn("45");
        when(weather.getHumidity()).thenReturn("72");
    }

    @BeforeEach
    public void create() throws Exception {
        parserTest = new WeatherParser();
    }

    @Test
    public void test() throws Exception {
        final Weather testWeather = parserTest.parse(testFile);
        assertEquals(weather.getCity(), testWeather.getCity());
        assertEquals(weather.getRegion(), testWeather.getRegion());
        assertEquals(weather.getCountry(), testWeather.getCountry());
        assertEquals(weather.getTemp(), testWeather.getTemp());
        assertEquals(weather.getCondition(), testWeather.getCondition());
        assertEquals(weather.getHumidity(), testWeather.getHumidity());
        assertEquals(weather.getChill(), testWeather.getChill());
    }

    @AfterEach
    public void intClean() throws Exception {
        parserTest = null;
    }

    @AfterAll
    public void clean() throws Exception {
        weather = null;
    }
}
