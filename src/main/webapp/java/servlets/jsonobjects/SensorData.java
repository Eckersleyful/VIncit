package main.webapp.java.servlets.jsonobjects;

import com.google.gson.annotations.SerializedName;

public class SensorData {
    @SerializedName("sensorId")
    private String sensorName;
    private String count;
    private String avgTemp;

    public SensorData(String sensorName, String count, String avgTemp) {
        this.sensorName = sensorName;
        this.count = count;
        this.avgTemp = avgTemp;
    }

    public String getAvgTemp() {
        return avgTemp;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setAvgTemp(String avgTemp) {
        this.avgTemp = avgTemp;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }


}
