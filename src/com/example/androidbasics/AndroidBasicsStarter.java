package com.example.androidbasics;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AndroidBasicsStarter extends ListActivity {
	
	String tests[] = { "LifeCycleTest",
					   "SingleTouchTest",
					   "MultiTouchTest",
					   "KeyTest",
					   "AccelerometerTest",
					   "AssetsTest",
					   "ExternalStorageTest",
					   "SoundPoolTest",
					   "MediaPlayerTest",
					   "FullScreenTest",
					   "RenderViewTest",
					   "ShapeTest",
					   "BitmapTest",
					   "FontTest",
					   "SurfaceViewTest",
					   "SwipeTest",
					   "TouchAndSwipeTest"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(
        		new ArrayAdapter<String>(
        				this,
        				android.R.layout.simple_list_item_1,
        				tests));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_android_basics_starter,
        						  menu);
        return true;
    }
    
    @Override
    protected void onListItemClick(ListView list,
    							   View view,
    							   int position,
    							   long id) {
    	super.onListItemClick(list, view, position, id);
    	String testName = tests[position];
    	try {
    		Class<?> clazz = Class.forName("com.example.androidbasics.tests."
    									+ testName);
    		Intent intent = new Intent(this, clazz);
    		startActivity(intent);
    	}
    	catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }
}
