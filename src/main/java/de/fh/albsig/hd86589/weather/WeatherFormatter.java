package de.fh.albsig.hd86589.weather;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Weather Formatter class and methods.
 * 
 * @author Johannes
 */
public class WeatherFormatter {
    private static Logger log = Logger.getLogger(WeatherFormatter.class);

    /**
     * Format method, formats weather object into velocity output.
     * 
     * @param weather Weather object
     * @return String formatted weather object
     */
    public String format(Weather weather) throws Exception {
        log.info("Formatting Weather Data");
        final Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("output.vm"));
        final VelocityContext context = new VelocityContext();
        context.put("weather", weather);
        final StringWriter writer = new StringWriter();
        Velocity.evaluate(context, writer, "", reader);
        final String stringWeather = writer.toString();
        try {
            writer.flush();
            writer.close();
        } catch (final IOException eex) {
            log.error("Writer konnte nicht geschlossen werden! Exception in WeatherFormatter::format: ", eex);
        }
        return stringWeather;
    }

    /**
     * FormatXML method, properly formats xml response of server.
     * 
     * @param file File in xml
     */
    public static final void formatXml(File file) throws Exception {
        Document doc = null;
        try {
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final FileInputStream fis = new FileInputStream(file);
            doc = db.parse(fis);
            fis.close();
        } catch (SAXException | IOException | ParserConfigurationException eex) {
            log.error("XML Konvertierung fehlgeschlagen! Exception in WeatherFormatter::formatXml: ", eex);
        }
        final Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        if (file == new File("findbugsXml.xml")) {
            final Writer out = new FileWriter(new File("findbugs_formatted.xml"));
            tf.transform(new DOMSource(doc), new StreamResult(out));
            try {
                if (out != null) {
                    out.close();
                }
            } catch (final IOException exc) {
                log.error("Writer konnte nicht geschlossen werden! Exception in WeatherFormatter::formatXml: ", exc);
            }

        } else {
            final Writer out = new FileWriter(new File("src/main/formatted.xml"));
            tf.transform(new DOMSource(doc), new StreamResult(out));
            try {
                if (out != null) {
                    out.close();
                }
            } catch (final IllegalStateException exc) {
                log.error("Writer konnte nicht geschlossen werden! Exception in WeatherFormatter::formatXml: ", exc);
            }
        }

    }
}
