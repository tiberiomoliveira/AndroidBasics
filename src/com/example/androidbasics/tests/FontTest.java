package com.example.androidbasics.tests;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class FontTest extends Activity {
	class RenderView extends View {
		Paint    paint;
		Typeface font;
		Rect     rect_bounds = new Rect();
		
		public RenderView(Context context) {
			super(context);
			
			paint = new Paint();
			font  = Typeface.createFromAsset(context.getAssets(),
											 "fonts/font.ttf");
		}
		
		protected void onDraw(Canvas canvas) {
			paint.setColor(Color.YELLOW);
			paint.setTypeface(font);
			paint.setTextSize(28);
			paint.setTextAlign(Paint.Align.CENTER);
			canvas.drawText("This is a fucking test!",
							canvas.getWidth() / 2,
							canvas.getHeight() / 4,
							paint);
			
			String text = "This is another fucking test! ";
			paint.setColor(Color.BLUE);
			paint.setTextSize(18);
			paint.setTextAlign(Paint.Align.LEFT);
			paint.getTextBounds(text, 0, text.length(), rect_bounds);
			canvas.drawText(text,
							canvas.getWidth() - rect_bounds.width(),
							canvas.getHeight() / 3,
							paint);
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