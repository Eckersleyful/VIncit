package main.webapp.java.test;

import main.webapp.java.sql.connector.QueryHandler;
import main.webapp.java.webscrapers.WttrScraper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Testing {

    public static void main(String[] args) throws SQLException {
        String [] allSensors = new String[]{
                "000D6F0003141E14",
                "000D6F0003E4EC19",
                "000D6F0004476483",
                "000D6F0004491FF6",
                "000D6F000449280A",
                "000D6F000449336B"
        };


        /*
        QueryHandler query = new QueryHandler();
        double resultTemperature = query.getLatestTempById("000D6F0003141E14");
        if(resultTemperature == -1){
            System.out.println("Parameterized sensor query test failed");
        }
        else{
            System.out.println(resultTemperature);
        }


        String resultJson = query.getSummary();
        if(resultJson.equals("") || resultJson == null){
            System.out.println("Non-Parameterized sensor query test failed");
        }
        else{
            System.out.println(resultJson);
        }

        WttrScraper testScraper = new WttrScraper();
        System.out.println("Latest temperature is " + testScraper.getTemperatureFromSite());

        for(String sensor : allSensors){
            System.out.println(query.getLatestTempById(sensor));
        }*/
    }

}
