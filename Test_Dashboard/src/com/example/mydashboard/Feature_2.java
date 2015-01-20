package com.example.mydashboard;

import java.io.ObjectOutputStream.PutField;

import com.example.models.User;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class Feature_2 extends TabActivity{	
    	
	Fragment[] frags = new Fragment[5];
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);					
		setContentView(R.layout.tab_host);			
		
		String type = getIntent().getStringExtra(User.TYPE); 
		if(type==null){
			ActionBar actionBar = getActionBar();
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);			
		}
		
		
		try{
			/*
			String pid = getIntent().getStringExtra(Define.TAG_ID);
			if(pid == null || pid.isEmpty()){				                
                //Finish current activity and go back				
                finish();                
			}else{
				*//**
				* Evaluate the pid with data in database here, 
				* and then setupTab
				*//*
				setupTab(pid);
			}
*/			
			setupTab("2");
		}catch(Exception e){
			Log.e("Tab", e.getMessage());
		}
		
	
	}
	
	@Override
	public boolean onNavigateUp(){
		finish();
		return true;
	}
	
/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    
	    return true;
	}
	*/
	
	private void setupTab(String pid){
		String[] labels = {getString(R.string.partient_home),
				getString(R.string.partient_sleep),
				getString(R.string.partient_restroom),
				getString(R.string.partient_temperature),
				getString(R.string.partient_location),};		
		
		//Tab
		//MySectionGenerator.setupTabs(actionBar, labels, tl);
		
		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		
//		TabSpec tab1 = tabHost.newTabSpec("tab1");
		TabSpec tab2 = tabHost.newTabSpec("tab2");
		TabSpec tab3 = tabHost.newTabSpec("tab3");
		TabSpec tab4 = tabHost.newTabSpec("tab4");
		TabSpec tab5 = tabHost.newTabSpec("tab5");

//		tab1.setIndicator(labels[0]);			
		tab2.setIndicator(labels[1]);
		tab3.setIndicator(labels[2]);
		tab4.setIndicator(labels[3]);
		tab5.setIndicator(labels[4]);
		
//		Intent i1 = new Intent(this, Feature_2_Tab_1.class);
		Intent i2 = new Intent(this, Feature_2_Tab_2.class);
		Intent i3 = new Intent(this, Feature_2_Tab_3.class);
		Intent i4 = new Intent(this, Feature_2_Tab_4.class);
		Intent i5 = new Intent(this, Feature_2_Tab_5.class);
		
//		i1.putExtra(Define.TAG_ID, pid);
		i2.putExtra(Define.TAG_ID, pid);
		i3.putExtra(Define.TAG_ID, pid);
		i4.putExtra(Define.TAG_ID, pid);
		i5.putExtra(Define.TAG_ID, pid);
        
//		tab1.setContent(i1);
		tab2.setContent(i2);
		tab3.setContent(i3);
		tab4.setContent(i4);
		tab5.setContent(i5);
		
//		tabHost.addTab(tab1);
		tabHost.addTab(tab2);
		tabHost.addTab(tab3);
		tabHost.addTab(tab4);
		tabHost.addTab(tab5);
		
		/*
		//add icon to the tab
		tabHost.getTabWidget().getChildAt(0).
		setBackgroundResource(android.R.drawable.ic_menu_agenda);
		tabHost.getTabWidget().getChildAt(1).
		setBackgroundResource(android.R.drawable.ic_menu_camera);
		*/
	}
	
	


	

}
