package com.example.androidbasics.tests;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class SoundPoolTest extends Activity implements OnTouchListener {
	SoundPool sound_pool;
	int       explosion_id = -1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView text_view = new TextView(this);
		text_view.setOnTouchListener(this);
		setContentView(text_view);
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		sound_pool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
		
		try {
			AssetManager asset_manager = getAssets();
			AssetFileDescriptor fd = asset_manager.openFd("sounds/explosion.ogg");
			
			explosion_id = sound_pool.load(fd, 1);
		}
		catch (IOException e) {
			text_view.setText("Couldn't load sound effect from asset (" +
								e.getMessage() + ")");
		}
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (explosion_id != -1) {
				sound_pool.play(explosion_id, 1.0f, 1.0f, 0, 0, 1.0f);
			}
		}
		
		return true;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (isFinishing()) {
			sound_pool.unload(explosion_id);
			sound_pool.release();
		}
	}
}