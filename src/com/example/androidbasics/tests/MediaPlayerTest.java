package com.example.androidbasics.tests;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class MediaPlayerTest extends Activity {
	MediaPlayer media_player;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView text_view = new TextView(this);
		setContentView(text_view);
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		media_player = new MediaPlayer();
		
		try{
			AssetManager        asset_manager = getAssets();
			AssetFileDescriptor fd = asset_manager.openFd("sounds/music.ogg");
			
			media_player.setDataSource(fd.getFileDescriptor(),
									   fd.getStartOffset(),
									   fd.getLength());
			media_player.prepare();
			media_player.setLooping(true);
		}
		catch (IOException e) {
			text_view.setText("Couldn't load music file (" +
							  e.getLocalizedMessage() + ")");
			media_player = null;
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if (media_player != null) {
			media_player.start();
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		if (media_player != null) {
			media_player.pause();
			if (isFinishing()) {
				media_player.stop();
				media_player.release();
			}
		}
	}
}