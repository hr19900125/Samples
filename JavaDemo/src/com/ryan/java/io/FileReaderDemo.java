package com.ryan.java.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class FileReaderDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String path = IOPath.INPUT_TEXT;
		
		Reader r = null;
		
		try {
			r = new FileReader(path);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
