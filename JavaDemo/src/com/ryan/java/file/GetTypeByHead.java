package com.ryan.java.file;

import java.io.FileInputStream;

/**
 * 
 * @author Ryan
 *
 */
public class GetTypeByHead {

	public static String getFileType(String filePath) {
		System.out.println(getFileHeader(filePath));
//		System.out.println();
		return null;
	}
	
	public static String getFileHeader(String filePath) {
		FileInputStream is = null;
		String value = null;
		try {
			is = new FileInputStream(filePath);
			byte[] b = new byte[4];
			is.read(b, 0, b.length);
			value = byteToHexString(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
				   is.close();
				} catch (Exception e) {
					
				}
			}
		}
		return value;
	}
	
	private static String byteToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if(src == null || src.length <= 0) {
			return null;
		}
		
		String hv;
		for (int i = 0;i < src.length; i ++) {
			hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
		    if(hv.length() < 2) {
		    	builder.append(0);
		    }
		    builder.append(hv);
		}
		System.out.println(builder.toString());
		return builder.toString();
	}
	
	public static void main(String[] args) {
		getFileType("src/com/ryan/java/file/1-activity.png");
	}
}
