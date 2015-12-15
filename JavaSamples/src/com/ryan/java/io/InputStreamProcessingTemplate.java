package com.ryan.java.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * Java 异常处理模板
 * 使用subClass来写模板
 * @author Ryan
 *
 */
public abstract class InputStreamProcessingTemplate {


	public void process(String fileName){
		IOException processException = null;
		InputStream input = null;
		try {
			input= new FileInputStream(fileName);
			doProcess(input);
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
	
	public abstract void doProcess(InputStream input) throws IOException;

}
