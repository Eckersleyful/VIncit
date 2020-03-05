package main.webapp.java.servlets;
import main.webapp.java.sql.connector.QueryHandler;
import main.webapp.java.webscrapers.WttrScraper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DifferenceAPI extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException
    {

        //Open the stream for the response
        PrintWriter out = response.getWriter();

        try {

            //Get the sensorId from the url and strip the leading slash
            String sensorId = request.getPathInfo().replaceAll("/", "");

            //Get the temperature of the latest date from the DB and the other from the website
            double latestTemperatureFromSensor = new QueryHandler().getLatestTempById(sensorId);
            double latestTemperatureFromHelsinki = new WttrScraper().getTemperatureFromSite();

            double difference = latestTemperatureFromHelsinki - latestTemperatureFromSensor;
            //Create the json
            String json = "{\"difference\" : " + difference +"}";
            //Set response information
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
            //Print the response json to the output stream
            out.print(json);


        } catch (Exception e) {
            e.printStackTrace();
        }
        //Close the stream so the request can succesfully go through
        out.flush();
    }

}