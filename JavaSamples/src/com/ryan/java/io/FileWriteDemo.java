package com.ryan.java.io;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriteDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String path = IOPath.OUTPUT_TEXT;
		
		FileWriter w = null;
		try {
			w = new FileWriter(path);
			w.write("I am a boy!");
			w.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(w != null) {
				try {
					w.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
