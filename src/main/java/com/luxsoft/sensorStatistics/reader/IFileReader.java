package com.luxsoft.sensorStatistics.reader;

import java.io.IOException;

public interface IFileReader {
	
	void processFile(String filePath) throws IOException;

}
