package main.webapp.java.servlets;

import main.webapp.java.sql.connector.QueryHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SummaryAPI extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException
    {
        //Open the output stream for the response
        PrintWriter out = response.getWriter();
        String sensorJson = null;
        try {
            //Get the summary JSON from the queryhandler class
            sensorJson = new QueryHandler().getSummary();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Set response content and encoding
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        //Send the json and close the stream
        out.print(sensorJson);
        out.flush();
    }

}
