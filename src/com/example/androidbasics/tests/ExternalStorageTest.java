package com.example.androidbasics.tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ExternalStorageTest extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView text_view = new TextView(this);
		text_view.setVerticalFadingEdgeEnabled(true);
		text_view.setMovementMethod(new ScrollingMovementMethod());
		setContentView(text_view);
		
		String state = Environment.getExternalStorageState();
		if (!state.equals(Environment.MEDIA_MOUNTED)) {
			text_view.setText("No external storage mounted.");
		}
		else {
			File external_dir = Environment.getExternalStorageDirectory();
			File text_file    = new File(external_dir.getAbsolutePath() +
										File.separator + "text.txt");
			
			try {
				write_text_file(text_file, "Test text file for android.");
				String str_text = read_text_file(text_file);
				text_view.setText(str_text);
				if (!text_file.delete()) {
					text_view.setText("Couldn't remove temporary file.");
				}
			}
			catch (IOException e) {
				text_view.setText("Something went wrong: " + e.getMessage());
			}
		}
	}
	
	private void write_text_file(File file, String text) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		writer.write(text);
		writer.close();
	}
	
	private String read_text_file(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder  text   = new StringBuilder();
		String         line;
		
		while ((line = reader.readLine()) != null) {
			text.append(line);
			text.append('\n');
		}
		
		reader.close();
		
		return text.toString();
	}
}