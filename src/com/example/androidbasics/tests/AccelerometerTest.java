package com.example.androidbasics.tests;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerTest extends Activity implements SensorEventListener {
	StringBuilder builder = new StringBuilder();
	TextView      text_view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		text_view = new TextView(this);
		setContentView(text_view);
		
		SensorManager manager = (SensorManager)getSystemService(
								Context.SENSOR_SERVICE);
		if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0) {
			text_view.setText("No accelerometer installed.");
		}
		else {
			Sensor accelerometer = manager.getSensorList(
									Sensor.TYPE_ACCELEROMETER).get(0);
			if (!manager.registerListener(this, accelerometer,
					SensorManager.SENSOR_DELAY_GAME)) {
				text_view.setText("Couldn't register sensor listener.");
			}
		}
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		builder.setLength(0);
		builder.append("x: ");
		builder.append(event.values[0]);
		builder.append("\ny: ");
		builder.append(event.values[1]);
		builder.append("\nz: ");
		builder.append(event.values[2]);
		text_view.setText(builder.toString());
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Nothing to do here
	}
}