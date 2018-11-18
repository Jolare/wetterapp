package de.fh.albsig.hd86589.weather;

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

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class WeatherFormatter {
    private static Logger log = Logger.getLogger(WeatherFormatter.class);

    public String format(Weather weather) throws Exception {
        log.info("Formatting Weather Data");
        final Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("output.vm"));
        final VelocityContext context = new VelocityContext();
        context.put("weather", weather);
        final StringWriter writer = new StringWriter();
        Velocity.evaluate(context, writer, "", reader);
        return writer.toString();
    }

    public static final void formatXML(File file) throws Exception {
        Document doc = null;
        try {
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            final DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new FileInputStream(file));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        final Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        final Writer out = new FileWriter(new File("src/main/formatted.xml"));
        tf.transform(new DOMSource(doc), new StreamResult(out));
    }
}
