package com.luxsoft.sensorStatistics.reader;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.luxsoft.sensorStatistics.measurements.MeasurementCalculator;
import com.luxsoft.sensorStatistics.processor.SensorDataProcessor;


public class CSVFileReaderTest {
	
	@Test
	public void testForEmptyFile() {
		IFileReader fileReader = new CSVFileReader(MeasurementCalculator.getMeasurementsCalculator(),new SensorDataProcessor());
		try {
			fileReader.processFile("");
			
		} catch (IOException e) {
			Assert.assertTrue("Exception occured during file processing",true);
		}
	}
	
	@Test
	public void testForCorrectFile() {
		IFileReader fileReader = new CSVFileReader(MeasurementCalculator.getMeasurementsCalculator(),new SensorDataProcessor());
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("leader-1.csv").getFile());
			fileReader.processFile(file.getPath());
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue("Exception should not occur as correct directory provided for processing",false);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.assertTrue("Exception should not occur as correct directory provided for processing",false);
		}
	}

}
