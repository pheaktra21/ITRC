package com.example.mydashboard;


import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import org.apache.http.NameValuePair;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;


public class AllPatients extends ListActivity {
	//Adapter
	SimpleAdapter adapter;	
    //ListAdapter needs ArrayList of Map
	ArrayList<HashMap<String, String>> patientsList;
	//Determining if the data is already loaded
	boolean isLoad;
    //Determining if there is exception occurs
	boolean isException;

	private ProgressDialog pDialog;
	
	
	@Override
	public boolean onNavigateUp(){
		finish();
		return true;
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_list);
		
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        

		EditText txtSearch = (EditText) findViewById(R.id.txtSearch);
		txtSearch.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//adapter.getFilter().filter(s);
				//apter.filter(s.toString());
				adapter.getFilter().filter(s);
				Log.i("onTextChange", s.toString());
			}			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {		
			}	
			@Override
			public void afterTextChanged(Editable s) {			
			}
		});

		ListView lv = (ListView) findViewById(android.R.id.list);
		//on selecting single item
		//launching Edit Product Screen
	    lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
                // getting values from selected ListItem
                String pid = ((TextView) view.findViewById(R.id.lblID)).getText().toString();
                
                // Starting new intent
                Intent in = new Intent(getApplicationContext(), Feature_2.class);
                // sending pid to next activity
                in.putExtra(Define.TAG_ID, pid);                
                
                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
			}
        });
		
	    
		Button btnViewPatientList = (Button)findViewById(R.id.btnViewAllFloors);		
		btnViewPatientList.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {				
				startActivity(new Intent(AllPatients.this,
						Feature_1_A.class));
			}
		});
		
		
		//Hashmap for ListView
		patientsList = new ArrayList<HashMap<String, String>>();
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if(!isLoad){
			Log.i("onResume", "NOT loaded.");			
			new LoadAllPatients().execute(
					"sql_string", //param1 name 
					"SELECT * FROM patient", //param1 value
					"params_string", //param2 name
					Define.TAG_ID + "," + Define.TAG_NAME + "," +
							Define.TAG_AGE); //param2 value
			
			//change isLoad flag to true
			//prevents when parent tab (activity) changed
			isLoad = true;
		}else
			Log.i("onResume", "already loaded.");

	}
	
	
	
	/*
	//Response from the next activity that itself started
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == 100){
			//if result code 100 is received, it means user edited/deleted product.
			//So, reload this screen again
			Log.i("onActivityResult: ", "Code 100");
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}
	}
	*/

	/**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllPatients extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();            
            pDialog.show();
        }
 
        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
        	 JSONObject json = null;
        	 
        	//Set parameters: FIRST is Column Name, SECOND is Column Value
        	//***Number of Parameters must be even
        	ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        	if(args.length>0){
        		for(int i=0; i<args.length/2; i++){
        			Log.i("Param " + (i+1), args[i*2]);
        			Log.i("Param " + (i+1), args[i*2 + 1]);
        			params.add(new BasicNameValuePair(args[i*2], args[(i*2) + 1]));
        		}
        		
	            // getting JSON string from URL
	           
				try {
					json = JSONParser.makeHttpRequest(
							Define.URL_GET_FROM_QUERY, "GET", params);
					
					 // Checking for SUCCESS TAG
	                int success = json.getInt(Define.TAG_SUCCESS);	
	                if (success == 1) {
	                	// successfully received product details
		                JSONArray dataArray = json.getJSONArray(Define.TAG_OBJECT_ARRAY); // JSON Array 

	                    // looping through All Items
	                    for (int i = 0; i < dataArray.length(); i++) {
	                        JSONObject c = dataArray.getJSONObject(i);
	                        
	                        // Storing each json item in variable
		                    String id = c.getString(Define.TAG_ID);                    
		                    String name = c.getString(Define.TAG_NAME);
		                    String age = c.getString(Define.TAG_AGE);
	                        
	                        
	                        // creating new HashMap
	                        HashMap<String, String> map = new HashMap<String, String>();                       
	                        // adding each child node to HashMap key => value
	                        map.put(Define.TAG_ID, id);
	                        map.put(Define.TAG_NAME, name);
	                        map.put(Define.TAG_AGE, age);
	 
	                        // adding HashList to ArrayList
	                        patientsList.add(map);  
	                    }
	                } else {
	                    /*
	                	// no products found
	                    // Launch Add New product Activity
	                    Intent i = new Intent(getApplicationContext(),
	                            NewProductActivity.class);
	                   
	                    // Closing all previous activities
	                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                    startActivity(i);
	                    */
	                }
				} catch(ConnectException e){
		        	isException = true;
		        	String msg = getString(R.string.connection_exception) + 
		        			"\n" + getString(R.string.check_internet_connection);
		        	return msg;
		        } catch(ConnectTimeoutException | SocketTimeoutException e){
		        	isException = true;
		        	String msg = getString(R.string.connection_timeout_exception) + 
		        			"\n" + getString(R.string.server_might_be_down);
		        	return msg;        
		        } catch(NullPointerException e){
		        	isException = true;
		        	return e.getCause().toString();
		        }
				catch (Exception e) {
		        	isException = true;
		        	//String msg = getString(R.string.server_might_be_down);
		        	return e.toString();           
		        }
	 
        	}
        	
 
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String result) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            showRetry(result);
            
            
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    
                	/**
                     * Updating parsed JSON data into ListView
                     * */
                	
                    adapter = new SimpleAdapter(
                            AllPatients.this, patientsList,
                            R.layout.list_item, 
                            new String[] {Define.TAG_ID, Define.TAG_NAME, Define.TAG_AGE},
                            new int[] { R.id.lblID, R.id.lblName, R.id.lblAge});                    
                    
                    // updating listview
                    setListAdapter(adapter);
                    
                }
            });
 
        }
        
        private void showRetry(String msg){
        	if(isException){
            	try{
            		OnClickListener l = new OnClickListener() {				
        				@Override
        				public void onClick(View v) {
        					AllPatients.this.finish();
        					startActivity(new Intent(getApplicationContext(), AllPatients.class));        					
        				}
        			};
        			RetryFragment frag = RetryFragment.newInstance(msg, 1, l);			

        			//Change layout view
        			AllPatients.this.getFragmentManager().beginTransaction()
        				.replace(android.R.id.content, frag).commit();
        			
            	}catch(Exception e){
            		Log.e("retry", e.getMessage());
            	}
            }        
        }
 
    }
}
