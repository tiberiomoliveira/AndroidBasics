package com.example.androidbasics.tests;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MultiTouchTest extends Activity implements OnTouchListener {
	StringBuilder builder = new StringBuilder();
	TextView      text_view;
	float[]       x = new float[10];
	float[]       y = new float[10];
	int[]         index = new int[10];
	boolean[]     touched = new boolean[10];
	
	private void updateTextView() {
		builder.setLength(0);
		
		for (int i = 0; i < 10; ++i) {
			builder.append("t=");
			builder.append(touched[i]);
			builder.append(",\t id=");
			builder.append(i);
			builder.append(",\t index=");
			builder.append(index[i]);
			builder.append(",\t x=");
			builder.append(x[i]);
			builder.append(",\t y=");
			builder.append(y[i]);
			builder.append("\n");
		}
		
		text_view.setText(builder.toString());
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		text_view = new TextView(this);
		text_view.setText("Touch and drag (multiple fingers supported)!");
		text_view.setOnTouchListener(this);
		setContentView(text_view);
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		int action        = event.getAction() & MotionEvent.ACTION_MASK;
		int pointer_index = (event.getAction() &
							 MotionEvent.ACTION_POINTER_INDEX_MASK) >>
							 MotionEvent.ACTION_POINTER_INDEX_SHIFT;
		int pointer_id    = event.getPointerId(pointer_index);
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			touched[pointer_id] = true;
			index[pointer_id]   = pointer_index;
			x[pointer_id]       = (int)event.getX(pointer_index);
			y[pointer_id]       = (int)event.getY(pointer_index);
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_CANCEL:
			touched[pointer_id] = false;
			index[pointer_id]   = pointer_index;
			x[pointer_id]       = (int)event.getX(pointer_index);
			y[pointer_id]       = (int)event.getY(pointer_index);
			break;
		case MotionEvent.ACTION_MOVE:
			int pointer_count = event.getPointerCount();
			
			for (int i = 0; i < pointer_count; ++i) {
				pointer_index = i;
				pointer_id = event.getPointerId(pointer_index);
				x[pointer_id]       = (int)event.getX(pointer_index);
				y[pointer_id]       = (int)event.getY(pointer_index);
			}
			break;
		}
		
		updateTextView();
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