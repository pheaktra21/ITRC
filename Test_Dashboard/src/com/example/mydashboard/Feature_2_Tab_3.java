package com.example.mydashboard;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Feature_2_Tab_3 extends Activity{

	Fragment[] frags = new Fragment[4];	
	int buttonID; 
	
	OnClickListener l = new OnClickListener() {
		@Override
		public void onClick(View v) {				
			FragmentManager fm = getFragmentManager();
			FragmentTransaction ft3 = fm.beginTransaction();
			RetryFragment retryFragment = (RetryFragment) fm.findFragmentByTag("retry_fragment");
			
			int index = 0;			
			ft3.hide(frags[3]); //hide the retry_fragment on the current fragment
			
			switch(buttonID){			
			case R.id.btnToday:
				Log.d("day", "day");
				index = 0;				
				break;
			case R.id.btnWeek:				
				Log.d("week", "week");
				index = 1;					
				break;
			case R.id.btnMonth:		
				Log.d("month", "month");
				index = 2;
				break;
			}
			
			ft3.attach(frags[index]); //Use attach() instead of show(), because we need to refresh
			showFragment(ft3, frags, index); //in case it doesn't show because of changing button
			retryFragment.setLabel(index, null); //set label
			ft3.commit();						
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.feature_2_tab_3);
		
		try{
			frags[0] = new FragmentRestroomTabToday();
			frags[1] = new FragmentRestroomTabWeekly();
			frags[2] = new FragmentRestroomTabMonthly();
			frags[3] = RetryFragment.newInstance("", 3, l);
			
			//add all fragments (views) to the activity
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.add(R.id.frag, frags[0], Define.TAG_TODAY);
			ft.add(R.id.frag, frags[1], Define.TAG_WEEKLY);
			ft.add(R.id.frag, frags[2], Define.TAG_MONTHLY);
			ft.add(R.id.frag, frags[3], Define.TAG_RETRY_FRAGMENT);
			ft.commit();

			//set default button clicked (first view)
			buttonID = R.id.btnToday;
			Button btnDefault = (Button)findViewById(buttonID);			
			doClick(btnDefault);
			
		}catch(Exception e){
			Log.e("onCreate", e.getMessage());
		}
		
	}
	
	public void doClick(View v){		
		buttonID = v.getId();
		Button clickedButton = (Button)findViewById(buttonID);		
		Button[] allButtons = {(Button)findViewById(R.id.btnToday),
				(Button)findViewById(R.id.btnWeek),
				(Button)findViewById(R.id.btnMonth)};	
		
		//Change TextColor
		for(int i=0; i<allButtons.length; i++){
			allButtons[i].setTextColor(getResources().getColor(android.R.color.black));			
		}		
		clickedButton.setTextColor(getResources().getColor(android.R.color.holo_blue_light));
		
		//Change Content; Must use different Transaction instance	
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft2 = fm.beginTransaction();
		RetryFragment retryFragment = (RetryFragment) fm.findFragmentByTag("retry_fragment");
		String[] tag = retryFragment!=null?retryFragment.getLabels():new String[]{"","",""};
		
		switch(buttonID){
		case R.id.btnToday:
			Log.d("btnDay", String.valueOf(retryFragment!=null) + tag[0]);			
			showFragment(ft2, frags, tag[0]==Define.TAG_TODAY?3:0);									
			break;
		case R.id.btnWeek:
			Log.d("btnWeek", String.valueOf(retryFragment!=null) + tag[1]);
			showFragment(ft2, frags, tag[1]==Define.TAG_WEEKLY?3:1);					
			break;
		case R.id.btnMonth:
			Log.d("btnMonth", String.valueOf(retryFragment!=null) + tag[2]);
			showFragment(ft2, frags, tag[2]==Define.TAG_MONTHLY?3:2);							
			break;		
		}
		ft2.commit();
				
	}
	
	/**
	 * Show the already added Fragment without having it refreshed
	 * 
	 * @param ft
	 * @param frags
	 * @param index
	 */
	private void showFragment(FragmentTransaction ft, Fragment[] frags, int index){
		try{
			for(int i=0; i<frags.length; i++){
				ft.hide(frags[i]);
			}			
			ft.show(frags[index]);
		}catch(Exception e){
			Log.e("show", e.getMessage());
		}

	}
	
	
}
