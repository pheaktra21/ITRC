package com.example.mydashboard;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

public class Feature_1_Tab_1 extends Activity{
	Button[][] buttons = new Button[6][3];
	Handler handler =  new Handler();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feature_1_tab_1);
		
		try{
			//Get data from Intent
			String floor_id = getIntent().getStringExtra(Define.TAG_ID);
			int count = 0;
			
			GridLayout ll = (GridLayout)findViewById(R.id.linear);
			for(int i=0; i<6; i++)
				for(int j=0; j<3; j++){
					buttons[i][j] = new Button(this);
					buttons[i][j].setText(floor_id + String.format("%02d", ++count)); //101, 102, 103, ...
					
					buttons[i][j].setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							Log.i("OnClickListener", ((Button)v).getText().toString());							
						}
					});
					
					ll.addView(buttons[i][j]);
				}
		
			Button btnViewPatientList = (Button)findViewById(R.id.btnViewPatientList);
			btnViewPatientList.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					startActivity(new Intent(Feature_1_Tab_1.this,
							AllPatients.class));
				}
			});
		}catch(Exception e){
			Log.e("onCreate", e.getMessage());
		}
		

		try{
			handler.postDelayed(new Runnable() {			
				@Override
				public void run() {
					Log.i("runnable", "running");
					String msg = "Patient in room " + buttons[1][1].getText().toString() +
							" fell down at " + new SimpleDateFormat("HH:mm:ss").format(new Date());
					alert(buttons[1][1], msg);
				}
			}, 4000);
		}catch(Exception e){
			Log.e("thread", e.getMessage());
		}
		
	}
	
	
	 @Override
	  public void onBackPressed() {
		 //Call TabActivity (Parent) onBackPressed()
		 this.getParent().onBackPressed();   
	  }
	 
	 private class MyAlert extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... params) {
			runOnUiThread(new Runnable() {
                public void run() {
                    
                	try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //wait 5 seconds
                    
                }
            });
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			Log.d("onPost", "after sleep");
		}
		 
	 }
	 
	 private void alert(final Button btn, String msg){ 		 
		 int floor_id = Integer.valueOf(getIntent().getStringExtra(Define.TAG_ID))-1;		 
		 final View tab = ((TabActivity)getParent()).getTabWidget().getChildAt(floor_id);
		 
		 final Drawable viewTab = tab.getBackground(); //orignal view of Tab
		 final Drawable viewButton = btn.getBackground(); //original view of Button
		 
		 btn.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));		 
		 tab.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
		 
		 AlertDialog.Builder builder = new AlertDialog.Builder(Feature_1_Tab_1.this);
		 builder.setTitle("Emergency!!!");
		 builder.setIcon(android.R.drawable.ic_dialog_alert);
		 builder.setCancelable(false);
		 builder.setMessage(msg);
		 builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {											
							btn.setBackground(viewButton);
							tab.setBackground(viewTab);
							
							startActivity(new Intent(Feature_1_Tab_1.this, PatientFalling.class));
						}
					});
		
		 builder.create().show();
	 }
	

	
}
