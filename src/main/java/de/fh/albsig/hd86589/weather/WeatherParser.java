package de.fh.albsig.hd86589.weather;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Weather Parser class and methods.
 * 
 * @author Johannes
 */
public class WeatherParser {

    private static Logger log = Logger.getLogger(WeatherParser.class);

    /**
     * Parse method, turns xml file input from yweather into Weather object.
     * 
     * @param file File object in xml
     * @return Weather
     */
    public Weather parse(File file) throws Exception {
        final Weather weather = new Weather();
        log.info("Creating XML Reader");
        final SAXReader xmlReader = createXmlReader();
        final Document doc = xmlReader.read(file);
        log.info("Parsing XML Response");
        weather.setCity(doc.valueOf("/query/results/channel/yweather:location/@city"));
        weather.setRegion(doc.valueOf("/query/results/channel/yweather:location/@region"));
        weather.setCountry(doc.valueOf("/query/results/channel/yweather:location/@country"));
        weather.setCondition(doc.valueOf("/query/results/channel/item/yweather:condition/@text"));
        weather.setTemp(doc.valueOf("/query/results/channel/item/yweather:condition/@temp"));
        weather.setChill(doc.valueOf("/query/results/channel/yweather:wind/@chill"));
        weather.setHumidity(doc.valueOf("/query/results/channel/yweather:atmosphere/@humidity"));
        return weather;
    }

    /**
     * help function for parse, returns a xml reader/interpreter.
     * 
     * @return SAXReader xml reader
     */
    private SAXReader createXmlReader() {
        final Map<String, String> uris = new HashMap<String, String>();
        uris.put("yweather", "http://xml.weather.yahoo.com/ns/rss/1.0");
        final DocumentFactory factory = new DocumentFactory();
        factory.setXPathNamespaceURIs(uris);
        final SAXReader xmlReader = new SAXReader();
        xmlReader.setDocumentFactory(factory);
        return xmlReader;
    }
}
