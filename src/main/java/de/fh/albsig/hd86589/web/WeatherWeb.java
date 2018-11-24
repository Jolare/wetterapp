package de.fh.albsig.hd86589.web;

import de.fh.albsig.hd86589.weather.Weather;
import de.fh.albsig.hd86589.weather.WeatherFormatter;
import de.fh.albsig.hd86589.weather.WeatherParser;
import de.fh.albsig.hd86589.weather.WeatherRetriever;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.InputStream;

/**
 * Weather Formatter class and methods.
 * 
 * @author Johannes
 */
public class WeatherWeb {
    private static Logger log = Logger.getLogger(WeatherFormatter.class);

    /**
     * Start method.
     */
    public static String start(String request) throws Exception {
        final String output = "web.vm";
        final File file = new File("src/main/output.xml");
        try {
            // Retrieve Data
            final InputStream dataIn = new WeatherRetriever().retrieve(request);
            FileUtils.copyInputStreamToFile(dataIn, file);
            if (dataIn != null) {
                dataIn.close();
            }
        } catch (final Exception eex) {
            log.error(eex.getMessage(), eex);
        }
        final Weather weather = new WeatherParser().parse(file);
        final String formatted = new WeatherFormatter().format(weather, output);
        System.out.print(formatted);
        WeatherFormatter.formatXml(file);
        return formatted;
    }
}
