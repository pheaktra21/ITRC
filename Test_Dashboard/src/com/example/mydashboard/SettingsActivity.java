package com.example.mydashboard;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.Button;
import android.widget.ListView;

public class SettingsActivity extends PreferenceActivity{

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        addPreferencesFromResource(R.xml.preferences);
	        try{
	        	Button btnOK = new Button(this);
		        btnOK.setText("OK");
		        btnOK.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT
		        		, LayoutParams.WRAP_CONTENT));
		        
		        ListView lv = getListView();
		        lv.addFooterView(btnOK);
		        
		        btnOK.setOnClickListener(new OnClickListener() {					
					@Override
					public void onClick(View v) {
						finish();						
					}
				});
	        }catch(Exception e){
	        	Log.e("dkf", e.getMessage());
	        }
	        
	    }
}
