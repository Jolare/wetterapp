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
public class WeatherFormatterTest {
    private Weather weather;
    private final File file = new File("src/test/resources/testWeather.xml");
    private WeatherParser parser;
    private WeatherFormatter wf;
    private WeatherFormatter mockedWF;
    private String output;

    @BeforeAll
    public void construct() throws Exception {
        mockedWF = mock(WeatherFormatter.class);
        output = "output.vm";
    }

    @BeforeEach
    public void create() throws Exception {
        weather = new Weather();
        parser = new WeatherParser();
        wf = new WeatherFormatter();
    }

    @Test
    public void testFormat() throws Exception {
        weather = parser.parse(file);
        when(mockedWF.format(weather, output))
                .thenReturn("*********************************\r\n" + "Current Weather Conditions for:\r\n"
                        + "Stuttgart,  BW, Germany\r\n" + "Temperature: 6\r\n" + "Condition: Sunny\r\n"
                        + "Humidity: 72\r\n" + "Wind Chill: 45\r\n" + "*********************************\r\n");
        final String vOutput = wf.format(weather, output);
        final String mOutput = mockedWF.format(weather, output);
        assertEquals(vOutput, mOutput);
    }

    @AfterEach
    public void intClean() throws Exception {
        weather = null;
        parser = null;
        wf = null;
    }

    @AfterAll
    public void clean() throws Exception {
        mockedWF = null;
    }
}
