package de.fh.albsig.hd86589.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet.
 * 
 * @author Johannes
 */
public class WeatherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(WeatherServlet.class);

    /**
     * Constructor.
     * 
     * @see HttpServlet#HttpServlet()
     */
    public WeatherServlet() {
        super();
    }

    /**
     * Servlet method.
     * 
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String select = "Albstadt, BW, DE";
        if (!StringUtils.isEmpty(request.getParameter("city"))) {
            select = request.getParameter("city");
        }
        final PrintWriter writer = response.getWriter();
        try {
            writer.println(WeatherWeb.start(select));
            writer.flush();
            writer.close();
        } catch (final Exception exc) {
            log.error("Fehler in Servlet, konnte Daten nicht senden: " + exc.getMessage(), exc);
        }
    }
}
