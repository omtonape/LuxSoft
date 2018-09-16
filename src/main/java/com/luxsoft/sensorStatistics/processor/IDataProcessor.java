package com.luxsoft.sensorStatistics.processor;

import java.util.List;

import com.luxsoft.sensorStatistics.model.Sensor;

public interface IDataProcessor {

	Sensor processData(String str) ;

	List<Sensor> getSensors();

	void printData();
}
