package com.luxsoft.sensorStatistics.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.luxsoft.sensorStatistics.measurements.IMeasurements;
import com.luxsoft.sensorStatistics.model.Sensor;
import com.luxsoft.sensorStatistics.processor.IDataProcessor;

public class CSVFileReader implements IFileReader {

	private IMeasurements measurements;
	private IDataProcessor dataProcessor;
	
	public CSVFileReader(IMeasurements measurements, IDataProcessor dataProcessor){
		this.measurements = measurements;
		this.dataProcessor = dataProcessor;
	}


	@Override
	public void processFile(String filePath) throws IOException {
		File file = new File(filePath);
		measurements.incrementFilesProcessed();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			br.lines().skip(1).map(line -> dataProcessor.processData(line)).forEach(sensor -> updateMeasurements(sensor));
		} catch (IOException e) {
			throw e;
		}
	}
	
	private void updateMeasurements(Sensor sensor) {
		measurements.incrementMeasurentsCounter();
		if(Double.isNaN(sensor.getCurrentTemp())) {
			measurements.incrementFailedCounter();
		}
	}

}
