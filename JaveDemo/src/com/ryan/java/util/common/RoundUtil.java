package com.ryan.java.util.common;

import java.io.PrintStream;

public class RoundUtil {
	public static float round(float value, int precision) {
		int multipler = 10;

		for (int i = 0; i < precision - 1; i++) {
			multipler *= multipler;
		}

		float roundedValue = (int) (value * multipler + 0.5D) / multipler;

		return roundedValue;
	}

	public static float round(double value, int precision) {
		float fvalue = (float) value;
		return round(fvalue, precision);
	}

	public static int round(float value) {
		int i = (int) (value + 0.5D);
		return i;
	}

	public static double roundDown(double value, int precision) {
		int multipler = 10;

		for (int i = 0; i < precision - 1; i++) {
			multipler *= multipler;
		}
		double v = (int) (value * multipler) / multipler;
		return v;
	}

	public static void main(String[] a) {
		System.out.println(roundDown(1.123D, 2));
		System.out.println(roundDown(555.0D, 2));
		System.out.println(roundDown(232323.98980000001D, 2));
		System.out.println(roundDown(-23.234100000000002D, 2));
		System.out.println(roundDown(-39.967700000000001D, 2));
		System.out.println(roundDown(-0.0258D, 2));
		System.out.println(roundDown(0.5678D, 2));
		System.out.println(roundDown(0.1234D, 2));
	}
}
