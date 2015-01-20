package com.example.mydashboard;

import com.tabandswipe.MySectionGenerator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Feature_2_Tab_2 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feature_2_tab_2);
		

		try{			
			ImageView[] images = {new ImageView(this),
					new ImageView(this),
					new ImageView(this),
					new ImageView(this),
					new ImageView(this)};
			images[0].setImageResource(R.drawable.livingroom_pose_faceleft);			
			images[1].setImageResource(R.drawable.livingroom_pose_faceright);			
			images[2].setImageResource(R.drawable.livingroom_pose_faceup);
			images[3].setImageResource(R.drawable.livingroom_pose_laydown);
			images[4].setImageResource(R.drawable.livingroom_pose_layup);			

			MySectionGenerator.setupSliderWithCircleIndicator(this,
				 R.id.pager, R.id.indicator, images);			

			/*
			MySectionGenerator.setupSwipe(getActivity(), R.id.pager,
					new String[]{"Swipe1","Swipe2","Swipe3"},
					new int[]{R.layout.activity_main, R.layout.feature_1, R.layout.feature_2});
			*/
		}catch(Exception e){
			Log.e("onActivityCreated", e.getMessage());
		}
	}
	
	

}
