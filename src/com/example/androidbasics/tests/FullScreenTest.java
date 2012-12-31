package com.example.androidbasics.tests;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public class FullScreenTest extends MultiTouchTest {
	WakeLock wake_lock = null;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		PowerManager power_manager = (PowerManager)this.getSystemService(
										Context.POWER_SERVICE);
		wake_lock = power_manager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
											  "My lock");
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (wake_lock != null)
			wake_lock.acquire();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (wake_lock != null)
			wake_lock.release();
	}
}