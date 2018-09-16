package com.luxsoft.sensorStatistics.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.luxsoft.sensorStatistics.model.Sensor;

public class SensorDataProcessor implements IDataProcessor {

	private List<Sensor> sensors = new ArrayList<>();
	
	@Override
	public Sensor processData(String str) {
		String[] items = str.split(",");
		String sensorId = items[0];
		for (Sensor sensor : sensors) {
			if (sensorId.equalsIgnoreCase(sensor.getSensorId())) {
				sensor.setCurrentTemp(Double.parseDouble(items[1]));
				//sensor.updateSensorData();
				return sensor;
			}
		}
		Sensor sensor = new Sensor(sensorId);
		sensor.setCurrentTemp(Double.parseDouble(items[1]));
		//sensor.updateSensorData();
		sensors.add(sensor);
		return sensor;
	}
	@Override
	public List<Sensor> getSensors(){
	 Collections.sort(this.sensors);
		return this.sensors;
	}
	
	@Override
	public void printData() {
		List<Sensor> sortedSensors = getSensors();
		System.out.println("Sensors with highest avg humidity: ");
		System.out.println("sensor-id,min,avg,max");
		for(Sensor sensor : sortedSensors) {
			System.out.println(sensor);
		}
	}

}
