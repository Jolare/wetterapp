package de.fh.albsig.hd86589.weather;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;

public class Main {

    public static void main(String[] args) throws Exception {
        // Configure Log4J
        PropertyConfigurator.configure(Main.class.getClassLoader().getResource("log4j.properties"));

        // Start the program
        new Main().start();
    }

    public void start() throws Exception {
        final File file = new File("src/main/output.xml");
        try {
            // Retrieve Data
            final InputStream dataIn = new WeatherRetriever().retrieve();
            FileUtils.copyInputStreamToFile(dataIn, file);
            if (dataIn != null)
                dataIn.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

//        final byte[] buffer = new byte[dataIn.available()];
//        dataIn.read(buffer);
//        os = new FileOutputStream(targetFile);
//        int bytesRead;
//        while ((bytesRead = dataIn.read(buffer)) != -1) {
//            os.write(buffer, 0, bytesRead);
//        }
        // dataIn = new WeatherRetriever().retrieve();
        // Parse Data
        final Weather weather = new WeatherParser().parse(file);
        // Format (Print) Data
        final String formatted = new WeatherFormatter().format(weather);
        System.out.print(formatted);

    }
}