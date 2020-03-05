package main.webapp.java.webscrapers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WttrScraper {

    public double getTemperatureFromSite(){
        try {

            //Fetch the site document through Jsoup
            Document siteDoc = Jsoup.connect("http://wttr.in/Helsinki").get();
            //Get the span with certain class
            Elements temperatureElement = siteDoc.getElementsByClass("ef049");

            //We know its the first element so we just take the first index and parse it into a double
            return (Double.parseDouble(temperatureElement.get(0).text()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
