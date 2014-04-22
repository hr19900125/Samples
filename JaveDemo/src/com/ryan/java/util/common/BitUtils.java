package com.ryan.java.util.common;

public class BitUtils {
	public static boolean getBooleanValue(int num, int position) {
		int v = pow(position) ^ 0xFFFFFFFF;
		return (v & num) != num;
	}

	public static final int pow(int x) {
		int back = 1;
		for (int i = 0; i < x; i++) {
			back *= 2;
		}
		return back;
	}
}
