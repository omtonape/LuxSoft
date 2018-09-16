package com.luxsoft.sensorStatistics.model;

import java.io.Serializable;

public class Sensor implements Serializable,Comparable<Sensor> {

	private static final long serialVersionUID = 7291774841978220984L;
	
	private String sensorId;
	private double currentTemp;
	private double min = Double.NaN;
	private double max = Double.NaN;
	private double avg = Double.NaN;
	private int counter = 0;
	private int totalTemp = 0;
	public Sensor(String sensorId) {
		this.sensorId = sensorId;
	}
	public String getSensorId() {
		return sensorId;
	}
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
	public void calculateAvg() {
		if(Double.isNaN(currentTemp)) {
			return;
		}
		counter++;
		totalTemp += currentTemp;
		avg = totalTemp / counter;
	}
	public void updateSensorData() {
		if(Double.isNaN(currentTemp)) {
			return;
		}
		if(Double.isNaN(max) || currentTemp > max) {
			max = currentTemp;
			if((Double.isNaN(min))) {
				min = max;
			}
		}
		if(Double.isNaN(min) || currentTemp < min) {
			min = currentTemp;
		}
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Sensor)) {
			return false;
		}
		Sensor s = (Sensor)obj;
		if(s.getSensorId() == null) {
			return false;
		}
		return this.sensorId.equals(s.sensorId);
	}
	
	@Override
	public int hashCode() {
		return this.getSensorId().hashCode();
	}
	@Override
	public String toString() {
		Double min = this.getMin();
		Double avg = this.getAvg();
		Double max = this.getMax();
		StringBuilder sb = new StringBuilder();
				sb.append(this.getSensorId()).append(",").append(min.isNaN() ? min : min.intValue()).append(",");
				sb.append(avg.isNaN() ? avg : avg.intValue()).append(",").append(max.isNaN() ? max : max.intValue());
		return sb.toString();
	}
	@Override
	public int compareTo(Sensor o) {
		if(this.getAvg() > o.getAvg()) {
			return -1;
		}
		if(this.getAvg() == o.getAvg()) {
			return 0;
		}
		return 1;
	}
	public double getCurrentTemp() {
		return currentTemp;
	}
	public void setCurrentTemp(double currentTemp) {
		this.currentTemp = currentTemp;
		updateSensorData();
		calculateAvg();
	}
	public double getMin() {
		return min;
	}
	public double getMax() {
		return max;
	}
	public double getAvg() {
		return avg;
	}

}
