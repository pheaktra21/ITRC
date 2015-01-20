package com.example.mydashboard;

import com.example.models.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);			
		
	}
	
	public void doLoginClick(View v){
		int id = v.getId();
		switch(id){
		case R.id.btnClose:
			android.os.Process.killProcess(android.os.Process.myPid());
			break;
		case R.id.btnLogin:
			EditText txtUsername = (EditText)findViewById(R.id.txtUsername);
			EditText txtPassword = (EditText)findViewById(R.id.txtPassword);
			
			if(txtUsername.getText().toString().equals(User.TYPE_NURSE) &&
					txtPassword.getText().toString().equals("1")){				
				Intent i = new Intent(this, Feature_1_A.class);
				i.putExtra(User.TYPE, User.TYPE_NURSE);							
				
				finish(); //finish login activity
				startActivity(i);		
			}else if(txtUsername.getText().toString().equals(User.TYPE_MONITOR) &&
					txtPassword.getText().toString().equals("1")){				
				Intent i = new Intent(this, Feature_1_A.class);
				i.putExtra(User.TYPE, User.TYPE_MONITOR);							
				
				finish(); //finish login activity
				startActivity(i);				
			}else if(txtUsername.getText().toString().equals(User.TYPE_PATIENT) &&
					txtPassword.getText().toString().equals("1")){				
				Intent i = new Intent(this, Feature_2.class);
				i.putExtra(User.TYPE, User.TYPE_PATIENT);							
				
				finish(); //finish login activity
				startActivity(i);				
			}
			else{
				Toast.makeText(this, "Username or password is incorrect!",
						Toast.LENGTH_LONG).show();
				txtUsername.requestFocus();
			}
		}
	}
	
	/*
	public void doFeatureClick(View v){
		Intent i = null;
		int id = v.getId();
		
		switch(id){
		case R.id.btnFeature1:
			i = new Intent(this, Feature_1_A.class);
			break;
		case R.id.btnFeature2:
			i = new Intent(this, Feature_2.class);
			break;
		default:
			this.finish();
			i = getIntent();
			break;
		}
		
		startActivity(i);
	}
	*/
	
	
	/*
	 * Show action menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);	    
	    return true;
	}
	

	
	@Override
	public void onResume(){
		super.onResume();
/*
		try{
			Handler handler = new Handler();
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					for(int i=0; i<5; i++){
						Log.i("in thread", "running");
						try{
							Thread.sleep(1000);
						}catch(InterruptedException e){
							Log.e("Sleep()", e.getMessage());
						}
					}
				}
			});
			
		}catch(Exception e){
			Log.e("thread", e.getMessage());
		}*/
	}
		
}
