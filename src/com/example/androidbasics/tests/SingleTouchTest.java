package com.example.androidbasics.tests;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class SingleTouchTest extends Activity implements OnTouchListener {
	StringBuilder builder = new StringBuilder();
	TextView	  text_view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		text_view = new TextView(this);
		text_view.setText("Touch and drag (one finger only)!");
		text_view.setOnTouchListener(this);
		setContentView(text_view);
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		builder.setLength(0);
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			builder.append("down,\t");
			break;
		case MotionEvent.ACTION_MOVE:
			builder.append("move,\t");
			break;
		case MotionEvent.ACTION_CANCEL:
			builder.append("cancel,\t");
			break;
		case MotionEvent.ACTION_UP:
			builder.append("up,\t\t");
			break;
		}
		
		builder.append(event.getX());
		builder.append(", ");
		builder.append(event.getY());
		
		String text = builder.toString();
		Log.d("SingleTouchTest", text);
		text_view.setText(text);
		
		// Get 60 events per second
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
}