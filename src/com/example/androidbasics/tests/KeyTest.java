package com.example.androidbasics.tests;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.TextView;

public class KeyTest extends Activity implements OnKeyListener {
	StringBuilder builder = new StringBuilder();
	TextView      text_view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		text_view = new TextView(this);
		text_view.setText("Press keys (if you have some)!");
		text_view.setOnKeyListener(this);
		text_view.setFocusableInTouchMode(true);
		text_view.requestFocus();
		setContentView(text_view);
	}
	
	@Override
	public boolean onKey(View view, int key_code, KeyEvent event) {
		builder.setLength(0);
		
		switch (event.getAction()) {
		case KeyEvent.ACTION_DOWN:
			builder.append("down, ");
			break;
		case KeyEvent.ACTION_UP:
			builder.append("up, ");
			break;
		}
		
		builder.append(event.getKeyCode());
		builder.append(", ");
		builder.append((char)event.getUnicodeChar());
		String text = builder.toString();
		Log.d("KeyTest", text);
		text_view.setText(text);
		
		return (event.getKeyCode() == KeyEvent.KEYCODE_BACK) ? false : true;
	}
}