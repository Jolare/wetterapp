package de.fh.albsig.hd86589.weather;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;

public class WeatherRetriever {
    private static Logger log = Logger.getLogger(WeatherRetriever.class);

    public InputStream retrieve() throws Exception {
        log.info("Retrieving Weather Data");
        final String request = "select * from weather.forecast where u = 'c' and woeid in (select"
                + " woeid from geo.places where text = 'Albstadt, BW, DE')";
        final URIBuilder uri = new URIBuilder("https://query.yahooapis.com/v1/public/yql");
        uri.addParameter("format", "xml");
        uri.addParameter("q", request);
        final URL url = uri.build().toURL();
        final URLConnection conn = url.openConnection();

        return conn.getInputStream();
    }
}