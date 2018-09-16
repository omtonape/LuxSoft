package com.luxsoft.sensorStatistics.measurements;

public class MeasurementCalculator implements IMeasurements{
	private int measurments = 0;
	private int failedMeasurments = 0;
	private int filesProcessed = 0;
	
	private MeasurementCalculator() {
	}

	@Override
	public int getMeasurments() {
		return measurments;
	}
	@Override
	public int getFailedMeasurments() {
		return failedMeasurments;
	}
	@Override
	public int getFilesProcessed() {
		return filesProcessed;
	}
	
	@Override
	public void incrementFilesProcessed() {
		filesProcessed++;
	}
	@Override
	public void incrementFailedCounter() {
		failedMeasurments++;
	}
	@Override
	public void incrementMeasurentsCounter() {
		measurments++;
	}
	
	public static final MeasurementCalculator getMeasurementsCalculator() {
		return new MeasurementCalculator();
	}
}
