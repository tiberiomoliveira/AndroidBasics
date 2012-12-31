package com.example.androidbasics.tests;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class SurfaceViewTest extends Activity {
	FastRenderView render_view;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		render_view = new FastRenderView(this);
		setContentView(render_view);
	}
	
	protected void onResume() {
		super.onResume();
		render_view.resume();
	}
	
	protected void onPause() {
		super.onPause();
		render_view.pause();
	}
	
	class FastRenderView extends SurfaceView implements Runnable {
		Thread           render_thread = null;
		SurfaceHolder    holder;
		volatile boolean running = false;
		
		public FastRenderView(Context context) {
			super(context);
			holder = getHolder();
		}
		
		public void resume() {
			running = true;
			render_thread = new Thread(this);
			render_thread.start();;
		}
		
		public void run() {
			while (running) {
				if (!holder.getSurface().isValid())
					continue;
				
				Canvas canvas = holder.lockCanvas();
				canvas.drawRGB(255, 0, 0);
				holder.unlockCanvasAndPost(canvas);
			}
		}
		
		public void pause() {
			running = false;
			while (true) {
				try {
					render_thread.join();
					break;
				}
				catch (InterruptedException e) {
					// Retry
				}
			}
		}
	}
}