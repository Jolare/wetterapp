package de.fh.albsig.hd86589.weather;

import org.apache.commons.lang3.Validate;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Weather Retriever class and methods.
 * 
 * @author Johannes
 */
public class WeatherRetriever {
    private static Logger log = Logger.getLogger(WeatherRetriever.class);

    /**
     * Retrieve method, gets yweather xml data based on entered string from command
     * line.
     * 
     * @param string user-defined location for yweather
     * @return InputStream
     */
    public InputStream retrieve(String string) throws Exception {
        Validate.notBlank(string);
        log.info("Retrieving Weather Data");
        String request;
        request = String.format("select * from weather.forecast where u = 'c' and woeid in (select"
                + " woeid from geo.places where text = '%s')", string);
        final URIBuilder uri = new URIBuilder("https://query.yahooapis.com/v1/public/yql");
        uri.addParameter("format", "xml");
        uri.addParameter("q", request);
        URLConnection conn = null;
        InputStream is = null;
        try {
            final URL url = uri.build().toURL();
            conn = url.openConnection();
            is = conn.getInputStream();
        } catch (final Exception eex) {
            log.error(eex.getMessage(), eex);
        }

        return is;
    }
}
