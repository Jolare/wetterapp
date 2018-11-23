package de.fh.albsig.hd86589.weather;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Main program.
 * 
 * @author Johannes
 */
public class Main {
    private static Logger log = Logger.getLogger(Main.class);

    /**
     * Main method.
     * 
     * @param args arguments
     */
    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure(Main.class.getClassLoader().getResource("log4j.properties"));
        new Main().start();
    }

    /**
     * Start method, uses all other classes of the package.
     */
    public void start() throws Exception {
        final File file = new File("src/main/output.xml");
        final Scanner scanner = new Scanner(System.in, "utf-8");
        String read = "";
        System.out.print("Fuer welchen Ort soll das Wetter angezeigt werden? Format: Musterstadt, BW, DE\nEingabe: ");
        try {
            read = scanner.nextLine();
            scanner.close();
        } catch (final IllegalArgumentException | IllegalStateException exc) {
            log.error("Bitte geben Sie einen Ort an! Exception in Main::start: ", exc);
        }
        try {
            // Retrieve Data
            final InputStream dataIn = new WeatherRetriever().retrieve(read);
            FileUtils.copyInputStreamToFile(dataIn, file);
            if (dataIn != null) {
                dataIn.close();
            }
        } catch (final Exception eex) {
            log.error("Empfangen der Daten fehlgeschlagen! Exception in Main::start: ", eex);
        }
        final Weather weather = new WeatherParser().parse(file);
        final String formatted = new WeatherFormatter().format(weather);
        System.out.print(formatted);
        WeatherFormatter.formatXml(file);

    }
}
