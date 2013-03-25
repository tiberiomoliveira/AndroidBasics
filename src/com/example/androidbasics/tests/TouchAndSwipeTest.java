package com.example.androidbasics.tests;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class TouchAndSwipeTest extends Activity implements OnTouchListener {
	private GestureDetector gesture_detector;
	private StringBuilder   txt_builder = new StringBuilder();
	private TextView        text_view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		text_view = new TextView(this);
		text_view.setText("Touch or swipe (one finger only)!");
		text_view.setOnTouchListener(this);
		setContentView(text_view);
		
		gesture_detector = new GestureDetector(getBaseContext(),
											   new SwipeGestureDetector());
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		boolean ret = gesture_detector.onTouchEvent(event);
		ret = ret || handle_touch(event);
		
		// Get 60 events per second
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
    private class SwipeGestureDetector extends SimpleOnGestureListener {
		@SuppressWarnings("deprecation")
		@Override
    	public boolean onFling(MotionEvent e1,
							   MotionEvent e2,
							   float       vel_x,
							   float       vel_y) {
			txt_builder.setLength(0);
    		try {
    			Display display = getWindowManager().getDefaultDisplay();
    			
    			if (Math.abs(e1.getRawY() - e2.getRawY()) > (display.getWidth() * 0.1)) {
					txt_builder.append("swipe " +
							(e1.getRawY() < e2.getRawY() ? "down,\t" : "up,\t"));
    			}
    				
    			if (Math.abs(e1.getRawX() - e2.getRawX()) > (display.getHeight() * 0.1)) {
					txt_builder.append("swipe " +
							(e1.getRawX() < e2.getRawX() ? "right,\t" : "left,\t"));
    			}
    			
    		}
    		catch (Exception e) {
    			Log.e("TouchAndSwipeTest", "Error on Gesture: " + e.getMessage());
    		}
    		
			txt_builder.append(e1.getX());
			txt_builder.append(", ");
			txt_builder.append(e1.getY());
			txt_builder.append(" - ");
			txt_builder.append(e2.getX());
			txt_builder.append(", ");
			txt_builder.append(e2.getY());
		
			String text = txt_builder.toString();
			Log.d("TouchAndSwipeTest", text);
			text_view.setText(text);
		
    		return true;
    	}
    }
    
	public boolean handle_touch(MotionEvent event) {
		txt_builder.setLength(0);
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			txt_builder.append("touch down,\t");
			break;
		case MotionEvent.ACTION_MOVE:
			txt_builder.append("touch move,\t");
			break;
		case MotionEvent.ACTION_CANCEL:
			txt_builder.append("touch cancel,\t");
			break;
		case MotionEvent.ACTION_UP:
			txt_builder.append("touch up,\t\t");
			break;
		}
		
		txt_builder.append(event.getX());
		txt_builder.append(", ");
		txt_builder.append(event.getY());
		
		String text = txt_builder.toString();
		Log.d("TouchAndSwipeTest", text);
		text_view.setText(text);
		
		return true;
	}
}