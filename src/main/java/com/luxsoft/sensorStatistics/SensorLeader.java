package com.luxsoft.sensorStatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.luxsoft.sensorStatistics.measurements.IMeasurements;
import com.luxsoft.sensorStatistics.measurements.MeasurementCalculator;
import com.luxsoft.sensorStatistics.processor.IDataProcessor;
import com.luxsoft.sensorStatistics.processor.SensorDataProcessor;
import com.luxsoft.sensorStatistics.reader.CSVFileReader;
import com.luxsoft.sensorStatistics.reader.IFileReader;

public class SensorLeader {

	public static void main(String[] args) {
		System.out.println("Please enter directory:");
		String inputDirectory = args[0];
		System.out.println("Input Directory is : "+inputDirectory);
		
		IMeasurements measurements = MeasurementCalculator.getMeasurementsCalculator();
		IDataProcessor dataProcessor = new SensorDataProcessor();
		IFileReader fileReader = new CSVFileReader(measurements, dataProcessor);
		
		try(Stream<Path> paths = Files.walk(Paths.get(inputDirectory))){
			paths.filter(Files :: isRegularFile)
				 .map(Path :: toString)
				 .forEach(path -> {
					 try {
						fileReader.processFile(path);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 });
				 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Number of  processed files: "+measurements.getFilesProcessed());
		System.out.println("Number of processed measurements: "+measurements.getMeasurments());
		System.out.println("Number of failed Measurements: "+measurements.getFailedMeasurments());
		dataProcessor.printData();
	}	
}
