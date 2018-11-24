package de.fh.albsig.hd86589.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.File;

@TestInstance(Lifecycle.PER_CLASS)
public class WeatherParserTest {
    private final File testFile = new File("src/test/resources/testWeather.xml");

    @Test
    public void test() throws Exception {
        final WeatherParser parserTest = new WeatherParser();
        final Weather testWeather = parserTest.parse(testFile);
        assertEquals("Stuttgart", testWeather.getCity());
        assertEquals(" BW", testWeather.getRegion());
        assertEquals("Germany", testWeather.getCountry());
        assertEquals("6", testWeather.getTemp());
        assertEquals("Sunny", testWeather.getCondition());
        assertEquals("72", testWeather.getHumidity());
        assertEquals("45", testWeather.getChill());
    }
}
