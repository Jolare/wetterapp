package de.fh.albsig.hd86589.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.File;

@TestInstance(Lifecycle.PER_CLASS)
public class WeatherFormatterTest {
    private Weather weather;
    private final File file = new File("src/test/resources/testWeather.xml");
    String output;

    @BeforeAll
    public void construct() throws Exception {
        final WeatherParser parser = new WeatherParser();
        weather = parser.parse(file);
        output = "output.vm";
    }

    @Test
    public void testFormat() throws Exception {
        final WeatherFormatter wf = new WeatherFormatter();
        final String vOutput = wf.format(weather, output);
        assertNotNull(vOutput);
        assertEquals(vOutput,
                "*********************************\r\n" + "Current Weather Conditions for:\r\n"
                        + "Stuttgart,  BW, Germany\r\n" + "Temperature: 6\r\n" + "Condition: Sunny\r\n"
                        + "Humidity: 72\r\n" + "Wind Chill: 45\r\n" + "*********************************\r\n");
    }
}
