package com.example.androidutils.utils;

import java.io.IOException;
import java.io.InputStream;

import android.util.Log;

public abstract class ThrottlingInputStream extends InputStream {

	private static final String TAG = "ThrIS";
	
	private int inputSpeed = 1024; // bytes per second
	
	private int bytesRead = 0;
	
	private int bytesReadTotal = 0;
	
	public ThrottlingInputStream(byte[] buf, int inputSpeed) {
		super();
		this.inputSpeed = inputSpeed;
	}
	
	@Override
	public int read(byte[] b) throws IOException {
		return read(b, 0, inputSpeed);
	}

	@Override
	public int read(byte[] b, int offset, int length) throws IOException {
		// Check if we should wait before sending next msg fragment.
		if (bytesRead > inputSpeed) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bytesRead = 0;
			Log.d(TAG, "zeroing bytes read");
		}
		
		// Select proper msg size.
		int size = inputSpeed;
		int read = 0;
		if (inputSpeed > length) {
			size = length;
		}
		
		// Read it and adjust the bytes read numbers.
		read = super.read(b, offset, size);
		bytesRead += read;
		bytesReadTotal += read;
		Log.d(TAG, String.format("read %d bytes", read));
			
		return read;
	}
	
}
