package com.luxsoft.sensorStatistics.measurements;

public interface IMeasurements {
	 void incrementFilesProcessed() ;
	 void incrementFailedCounter();
	 void incrementMeasurentsCounter();
	int getMeasurments();
	int getFailedMeasurments();
	int getFilesProcessed();

}
