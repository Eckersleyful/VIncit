package main.webapp.java.sql.connector;

import com.google.gson.Gson;
import main.webapp.java.servlets.jsonobjects.SensorData;
import main.webapp.java.servlets.jsonobjects.SensorObject;

import java.sql.*;
import java.util.ArrayList;

public class QueryHandler {

    public String getSummary() throws SQLException{

        /*Returns all data of each sensor, temperature as average and count is the amount of temperature
        measurings
         */
        String query = "select SensorId as sensorId,COUNT(Temperature) as count," +
                " AVG(Temperature) as avgTemp FROM cubesensors_data GROUP BY sensorId;";
        try {
            //Open the SQLite connection through JDBC Driver
            Connection newConnection = new SQLiteConnector().getConnection();
            //Create a prepared statement with the query we wrote earlier
            PreparedStatement prepared = newConnection.prepareStatement(query);
            //Timeout in case DB is unresponsive or deadlocked
            prepared.setQueryTimeout(15);
            //Create a list for the sensor data objects (For Gson serializing)
            ArrayList<SensorData> sensorQueryResult = new ArrayList<>();
            //Read the results into resultSet
            ResultSet rs = prepared.executeQuery();
            //Iterate through the resultset and append all the data into the arraylist
            while (rs.next()) {

                sensorQueryResult.add(new SensorData(
                        rs.getString("sensorId"),
                        rs.getString("count"),
                        Double.toString(Math.round(((rs.getDouble("avgTemp") / 100.0)*100.0))/100.0)));
            }


            //Holds the array of sensors due to the required json form
            SensorObject allSensorData = new SensorObject(sensorQueryResult);
            //Create a JSON string based on all the data
            String sensorJson = new Gson().toJson(allSensorData);
            //Close the sql connection
            newConnection.close();
            //Return the JSON string
            return sensorJson;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public double getLatestTempById(String sensorId) throws SQLException{
        //Return the temperature from the latest measuring date, based on the sensor Id
        String query = "select Temperature from cubesensors_data WHERE sensorId = ? AND MeasurementTime IN (SELECT max(MeasurementTime)\n" +
                "from cubesensors_data WHERE sensorId = ?);";

        double latestTemperature = 0;
        try {
            //Open the SQLite connection through JDBC Driver
            Connection newConnection = new SQLiteConnector().getConnection();
            //Create a prepared statement with the query we wrote earlier
            PreparedStatement prepared = newConnection.prepareStatement(query);
            //Timeout in case DB is unresponsive or deadlocked
            prepared.setQueryTimeout(15);
            //As this is a parameterized query, set the missing values
            prepared.setString(1, sensorId);
            prepared.setString(2, sensorId);

            ResultSet rs = prepared.executeQuery();
            //Take the queried temperature from the resultSet and round it to two decimals
            while(rs.next()){
                latestTemperature = Math.round((rs.getDouble("Temperature")/100)*100);
                latestTemperature /= 100;
            }
            //Close the SQL connection and return the temperature
            newConnection.close();
            return latestTemperature;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return -1;
        }

    }

}
