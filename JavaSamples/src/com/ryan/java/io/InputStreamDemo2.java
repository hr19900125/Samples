package com.ryan.java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 使用read(byte[]) 读取
 * @author Ryan
 *
 */
public class InputStreamDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        InputStream inputStream = null;		
        try {
		    inputStream = new FileInputStream("src/com/ryan/java/io/input-text.txt");
			byte[] buf = new byte[50];
			int size = inputStream.read(buf);
			System.out.println("read size = " + size);
			System.out.println(new String(buf, 0, size));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        
	}

}
