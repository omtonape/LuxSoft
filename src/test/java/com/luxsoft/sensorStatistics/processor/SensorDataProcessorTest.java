package com.luxsoft.sensorStatistics.processor;

import org.junit.jupiter.api.Test;

import com.luxsoft.sensorStatistics.model.Sensor;

import junit.framework.Assert;

public class SensorDataProcessorTest {
	
	@Test
	public void testForCorrectInput() {
		String str = "s1,80";
		IDataProcessor dataProcessor = new SensorDataProcessor();
		Sensor sensor = dataProcessor.processData(str);
		Assert.assertTrue("Min Temp should be 80", sensor.getMin() == 80);
		Assert.assertTrue("Max Temp should be 80", sensor.getMax() == 80);
		Assert.assertTrue("Avg Temp should be 80", sensor.getAvg() == 80);
	}
	@Test
	public void testForMultipleInput() {
		String str = "s1,80";
		String str1 = "s1,30";
		String str2 = "s1,40";
		IDataProcessor dataProcessor = new SensorDataProcessor();
		Sensor sensor = dataProcessor.processData(str);
		Sensor s1 = dataProcessor.processData(str1);
		Sensor s2 = dataProcessor.processData(str2);
		Assert.assertTrue("Min Temp should be 30", s2.getMin() == 30);
		Assert.assertTrue("Max Temp should be 80", s2.getMax() == 80);
		System.out.println("avg is : "+s2.getAvg());
		Assert.assertTrue("Avg Temp should be 50", s2.getAvg() == 50);
	}
	@Test
	public void testForTwoInput() {
		String str = "s1,80";
		String str1 = "s1,30";
		IDataProcessor dataProcessor = new SensorDataProcessor();
		Sensor sensor = dataProcessor.processData(str);
		Sensor s1 = dataProcessor.processData(str1);
		Assert.assertTrue("Min Temp should be 30", s1.getMin() == 30);
		Assert.assertTrue("Max Temp should be 80", s1.getMax() == 80);
		System.out.println("Avg temp is : "+s1.getAvg());
		Assert.assertTrue("Avg Temp should be 55", s1.getAvg() == 55);
	}
	@Test
	public void testForMultiInputWithNan() {
		String str = "s1,80";
		String str1 = "s1,30";
		String str2 = "s2,40";
		String str3 = "s1,NaN";
		IDataProcessor dataProcessor = new SensorDataProcessor();
		Sensor sensor = dataProcessor.processData(str);
		Sensor s1 = dataProcessor.processData(str1);
		Sensor s2 = dataProcessor.processData(str2);
		Sensor s3 = dataProcessor.processData(str3);
		Assert.assertTrue("s1 Min Temp should be 30", s3.getMin() == 30);
		Assert.assertTrue("s1 Max Temp should be 80", s3.getMax() == 80);
		System.out.println("s1 Avg temp is : "+s3.getAvg());
		Assert.assertTrue("s1 Avg Temp should be 55", s3.getAvg() == 55);
		Assert.assertTrue("S2's min should be 40", s2.getMin() == 40);
		Assert.assertTrue("S2's max should be 40", s2.getMax() == 40);
		Assert.assertTrue("S2's avg should be 40", s2.getMax() == 40);
	}
	@Test
	public void testForNanInput() {
		String str = "s1,NaN";
		IDataProcessor dataProcessor = new SensorDataProcessor();
		Sensor sensor = dataProcessor.processData(str);
		Assert.assertTrue("Min Temp should be NaN", Double.isNaN(sensor.getMin()));
		Assert.assertTrue("Max Temp should be NaN", Double.isNaN(sensor.getMax()));
		Assert.assertTrue("Avg Temp should be NaN", Double.isNaN(sensor.getAvg()));
	}

}
