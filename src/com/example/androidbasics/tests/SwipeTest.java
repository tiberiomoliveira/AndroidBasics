package com.example.androidbasics.tests;

import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class SwipeTest extends Activity {
	private GestureDetector gesture_detector;
	private ImageView       image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		image_view = new ImageView(this);
		setContentView(image_view);
        gesture_detector = new GestureDetector(getBaseContext(), new SwipeGestureDetector());
    }

    @Override
    public boolean onTouchEvent(MotionEvent motion_event) {
    	if (gesture_detector.onTouchEvent(motion_event))
    		return true;
    	return super.onTouchEvent(motion_event);
    }
    
    private class SwipeGestureDetector extends SimpleOnGestureListener {
		Bitmap bm;
    	@SuppressWarnings("deprecation")
		@Override
    	public boolean onFling(MotionEvent e1,
							   MotionEvent e2,
							   float velocity_x,
							   float velocity_y) {
    		try {
    			Display      display       = getWindowManager().getDefaultDisplay();
				AssetManager asset_manager = getAssets();
				InputStream  is            = null;
    			
    			if (Math.abs(e1.getRawY() - e2.getRawY()) > (display.getWidth() * 0.1)) {
    				is = asset_manager.open((e1.getRawY() < e2.getRawY()) ?
    											"pics/down.png":
    											"pics/up.png");
					bm = BitmapFactory.decodeStream(is);
					image_view.setImageBitmap(bm);
    			}
    				
    			if (Math.abs(e1.getRawX() - e2.getRawX()) > (display.getHeight() * 0.1)) {
    				is = asset_manager.open((e1.getRawX() < e2.getRawX()) ?
    											"pics/right.png":
    											"pics/left.png");
					bm = BitmapFactory.decodeStream(is);
					image_view.setImageBitmap(bm);
    			}
				is.close();
    			
    		}
    		catch (Exception e) {
    			Log.e("SwipeTest", "Error on Gesture: " + e.getMessage());
    		}
    		return false;
    	}
    }
}