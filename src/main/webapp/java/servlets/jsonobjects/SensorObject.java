package main.webapp.java.servlets.jsonobjects;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SensorObject{


    @SerializedName("sensors")
    private ArrayList<SensorData> listOfSensors;

    public SensorObject(ArrayList<SensorData> listOfSensors){
        setListOfSensors(listOfSensors);
    }

    public void setListOfSensors(ArrayList<SensorData> listOfSensors) {
        if(listOfSensors != null){
            this.listOfSensors = listOfSensors;
        }

    }
}
