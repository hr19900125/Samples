package com.ryan.java.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * 通过接口的方式来写模板
 * @author Ryan
 *
 */
public class InputStreamProcessingTemplate2 {

	public void process(String fileName, InputStreamProcessor processor) {
		IOException processException = null;
		InputStream input = null;
		try {
			input= new FileInputStream(fileName);
			processor.process(input);
		} catch(IOException e) {
			processException = e;
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					if (processException != null) {
						throw new MyException(processException, e, "Error message ..." + fileName);
					} else {
						throw new MyException(e, "Error closing InputStream for file " + fileName);
					}
				} 
			}
			
			if(processException != null) {
				throw new MyException(processException, "Error processing InputStream for file " + fileName);
			}
		}
	}

}
