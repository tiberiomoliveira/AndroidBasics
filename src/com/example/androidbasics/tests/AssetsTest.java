package com.example.androidbasics.tests;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class AssetsTest extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView text_view = new TextView(this);
		text_view.setVerticalFadingEdgeEnabled(true);
		text_view.setMovementMethod(new ScrollingMovementMethod());
		setContentView(text_view);
		
		AssetManager asset_manager = getAssets();
		InputStream  input_stream  = null;
		
		try {
			input_stream = asset_manager.open("texts/text_file.txt");
			String text = getTextFile(input_stream);
			text_view.setText(text);
		}
		catch (IOException e) {
			text_view.setText("Couldn't load file.\n");
		}
		finally {
			if (input_stream != null) {
				try {
					input_stream.close();
				}
				catch (IOException e) {
					text_view.setText("Couldn't close file.\n");
				}
			}
		}
	}
	
	public String getTextFile(InputStream input_stream) throws IOException {
		ByteArrayOutputStream byte_stream = new ByteArrayOutputStream();
		byte[]                buffer      = new byte[4096];
		int                   len         = 0;
		int                   offset      = 0;
		
		while ((len = input_stream.read(buffer)) > 0) {
			byte_stream.write(buffer, offset, len);
			offset += len;
		}
		
		return new String(byte_stream.toByteArray(), "UTF8");
	}
}