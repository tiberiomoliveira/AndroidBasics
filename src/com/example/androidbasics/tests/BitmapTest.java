package com.example.androidbasics.tests;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BitmapTest extends Activity {
	class RenderView extends View {
		Bitmap bob888;
		Bitmap bob565;
		Bitmap bob4444;
		Rect   rec_dst = new Rect();
		
		public RenderView(Context context) {
			super(context);
			
			try {
				AssetManager asset_manager = context.getAssets();
				InputStream  input_stream  = asset_manager.open("pics/bobrgb888.png");
				BitmapFactory.Options options = new BitmapFactory.Options();
				
				bob888 = BitmapFactory.decodeStream(input_stream);
				Log.d("BitmapText", "bobrgb888.png format(888): " + bob888.getConfig());
				
				options.inPreferredConfig = Bitmap.Config.RGB_565;
				bob565 = BitmapFactory.decodeStream(input_stream, null, options);
				input_stream.close();
				Log.d("BitmapText", "bobargb8888.png format(565): " + bob565.getConfig());
				
				input_stream = asset_manager.open("pics/bobargb8888.png");
				options.inPreferredConfig = Bitmap.Config.ARGB_4444;
				bob4444 = BitmapFactory.decodeStream(input_stream, null, options);
				input_stream.close();
				Log.d("BitmapText", "bobargb8888.png format: " + bob4444.getConfig());
			}
			catch (IOException e) {
				Log.d("BitmapText", "Couldn't open images: " + e.getMessage());
				bob888.recycle();
				bob565.recycle();
				bob4444.recycle();
			}
			finally {
				// We should really close our input streams here.
			}
		}
		
		protected void onDraw(Canvas canvas) {
			rec_dst.set(canvas.getWidth() / 5,
						canvas.getHeight() / 5,
						(canvas.getWidth() / 5) + 300,
						(canvas.getHeight() / 5) + 300);
			canvas.drawBitmap(bob888, null, rec_dst, null);
			canvas.drawBitmap(bob565,
							  (canvas.getWidth() / 5) + 25,
							  (canvas.getHeight() / 5) + 300, null);
			canvas.drawBitmap(bob4444,
							  (canvas.getWidth() / 5) + 150,
							  (canvas.getHeight() / 5) + 150, null);
			invalidate();
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(new RenderView(this));
	}
}