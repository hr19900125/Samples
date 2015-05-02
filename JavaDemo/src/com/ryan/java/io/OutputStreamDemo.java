package com.ryan.java.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OutputStream output = null;
		try {
		    output = new FileOutputStream("src/com/ryan/java/io/output-text.txt");
		    String str = "Are you OK?";
		    output.write(str.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(output != null) {
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
