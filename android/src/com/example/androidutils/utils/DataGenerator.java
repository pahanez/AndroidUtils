package com.example.p37td8.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
	private static final Random mRandom = new Random();

	public static void main(String[] args) {
		List<CPUUsageItem> list = generateListOfMockProcesses(15);
		for(CPUUsageItem item:list)
			System.out.println(item);
	}

	private static String generateIntLimit(int limit) {
		return String.valueOf(mRandom.nextInt(limit));

	}

	private static String generateStringLimit(int length) {
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = (char) ('a' + mRandom.nextInt(25));
		}
		return new String(text);
	}

	private static String generatePackageName() {
		return generateStringLimit(2) + "." + generateStringLimit(6) + "." + generateStringLimit(4);
	}

	public static CPUUsageItem generateCPUUsageItem() {
		return new CPUUsageItem(generateIntLimit(9999), generateIntLimit(100), generatePackageName());
	}
	
	public static List<CPUUsageItem> generateListOfMockProcesses(int size){
		List<CPUUsageItem> tempList = new ArrayList<CPUUsageItem>();
		for (int i = 0; i < size; i++) {
			tempList.add(generateCPUUsageItem());
		}
		return tempList;
	}

	private static class CPUUsageItem {
		String mPid;
		String mCPU;
		String mName;

		public CPUUsageItem(String mPid, String mCPU, String mName) {
			this.mPid = mPid;
			this.mCPU = mCPU;
			this.mName = mName;
		}

		@Override
		public String toString() {
			return "mPid = " + mPid + " mCPU = " + mCPU + " mName = " + mName;
		}
	}

}
