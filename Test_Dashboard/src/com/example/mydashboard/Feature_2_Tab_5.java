package com.example.mydashboard;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Feature_2_Tab_5 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_container);
		
		ImageView img = new ImageView(this);
		img.setImageResource(R.drawable.location);
		
		FrameLayout layout = (FrameLayout) findViewById(R.id.frag);
		layout.addView(img);		
		
	}
}
