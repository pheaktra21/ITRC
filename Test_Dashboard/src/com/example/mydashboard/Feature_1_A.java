package com.example.mydashboard;

import com.tabandswipe.MySectionGenerator;

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
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;


public class Feature_1_A extends TabActivity{
	
	Fragment[] frags = new Fragment[3]; //3 floors
	private long back_pressed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);					
		setContentView(R.layout.tab_host);
		/*	
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		*/
		try{
			setupTab();
		}catch(Exception e){
			Log.e("Tab", e.getMessage());
		}
			
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    
	    return true;
	}
	/*
	@Override
	public boolean onNavigateUp(){
		Intent i = getIntent();
		if(i.getBooleanExtra("main", false)){
			//Abruptly kills the Application
			android.os.Process.killProcess(android.os.Process.myPid());
		}else{
			finish();
		}		
		
		return true;
	}
	*/
	@Override
	public void onBackPressed(){		
		//2 second		
	    if (back_pressed + 2000 > System.currentTimeMillis()){
	        super.onBackPressed();
	    }
	    else{
	        Toast.makeText(getBaseContext(),
	                "Press once again to exit!", Toast.LENGTH_SHORT)
	                .show();
	    }
	    back_pressed = System.currentTimeMillis();
	}
	
	
	private void setupTab(){
		String[] labels = {getString(R.string.floor_1),
				getString(R.string.floor_2),
				getString(R.string.floor_3)};		
		
		//Tab				
		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		
		TabSpec tab1 = tabHost.newTabSpec("tab1");
		TabSpec tab2 = tabHost.newTabSpec("tab2");
		TabSpec tab3 = tabHost.newTabSpec("tab3");
		
		tab1.setIndicator(labels[0]);			
		tab2.setIndicator(labels[1]);
		tab3.setIndicator(labels[2]);
		
		Intent i1 = new Intent(this, Feature_1_Tab_1.class);
		i1.putExtra(Define.TAG_ID, "1");
		Intent i2 = new Intent(this, Feature_1_Tab_1.class);
		i2.putExtra(Define.TAG_ID, "2");
		Intent i3 = new Intent(this, Feature_1_Tab_1.class);
		i3.putExtra(Define.TAG_ID, "3");
		
		tab1.setContent(i1);
		tab2.setContent(i2);
		tab3.setContent(i3);
		
		tabHost.addTab(tab1);
		tabHost.addTab(tab2);
		tabHost.addTab(tab3);		

	}
	

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    if(item.getItemId()==R.id.action_setting){
	    	startActivity(new Intent(this, SettingsActivity.class));
	    }
		
		return super.onOptionsItemSelected(item);
	}
	
}
