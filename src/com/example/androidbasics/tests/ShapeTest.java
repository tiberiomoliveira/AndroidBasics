package com.example.androidbasics.tests;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ShapeTest extends Activity {
	class RenderView extends View {
		Paint paint;
		
		public RenderView(Context context) {
			super(context);
			paint = new Paint();
		}
		
		protected void onDraw(Canvas canvas) {
			canvas.drawRGB(255, 255, 255);
			paint.setColor(Color.RED);
			canvas.drawLine(0, 0,
							canvas.getWidth() - 1,
							canvas.getHeight() - 1,
							paint);
			
			paint.setStyle(Style.STROKE);
			paint.setColor(0xff00ff00);
			canvas.drawCircle(canvas.getWidth() / 2,
							  canvas.getHeight() / 2,
							  40, paint);
			
			paint.setStyle(Style.FILL);
			paint.setColor(0x770000ff);
			canvas.drawRect(canvas.getWidth() / 4,
							canvas.getHeight() / 4,
							(canvas.getWidth() / 4) + 100,
							(canvas.getHeight() / 4) + 50,
							paint);
			
			paint.setStyle(Style.FILL_AND_STROKE);
			paint.setColor(0x55ff0000);
			canvas.drawRect(3 * (canvas.getWidth() / 4),
							3 * (canvas.getHeight() / 4),
							3 * (canvas.getWidth() / 4) + 50,
							3 * (canvas.getHeight() / 4) + 25,
							paint);
			
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