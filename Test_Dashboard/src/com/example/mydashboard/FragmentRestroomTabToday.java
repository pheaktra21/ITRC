/**
 * 
 */
package com.example.mydashboard;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import com.example.models.Monitoring_Daily;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.charts.ScatterChart.ScatterShape;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;
import com.github.mikephil.charting.utils.Legend.LegendForm;
import com.github.mikephil.charting.utils.Legend.LegendPosition;
import com.github.mikephil.charting.utils.XLabels.XLabelPosition;
import com.github.mikephil.charting.utils.YLabels.YLabelPosition;

import org.apache.http.NameValuePair;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentRestroomTabToday extends Fragment {
	boolean isLoad;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		isLoad = false;
		return inflater.inflate(R.layout.scatter_chart, container, false);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if(!isLoad){
			Log.i("onResume", "NOT loaded.");
			String pid = getActivity().getIntent().getStringExtra(Define.TAG_ID);
			Log.d("pid in Today", pid);
			
			new LoadData().execute(
					"sql_string", //param1 name 
					"SELECT * FROM monitoring_daily_r1 WHERE date_id=2011010109 AND patient_id=2", //param1 value
					"params_string", //param2 name
					Define.TAG_PATIENT_ID + "," + Define.TAG_LOCATION_ID + "," +
							Define.TAG_ORDER + "," + Define.TAG_DURATION); //param2 value
			
			//change isLoad flag to true
			//prevents when parent tab (activity) changed
			isLoad = true;
		}else
			Log.i("onResume", "already loaded.");

	}
		

class LoadData extends AsyncTask<String, String, String> {
	ProgressDialog mDialog;
	ArrayList<Monitoring_Daily> list;
	boolean isException;
	
	public LoadData(){					
		list = new ArrayList<Monitoring_Daily>();
		isException = false;
	}
	
	
    /**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("Loading...");
        mDialog.setIndeterminate(false);
        mDialog.setCancelable(false);
        mDialog.show();
    }

    /**
     * getting data from url
     * */
    @Override
    protected String doInBackground(String... args) {
        
    	// updating UI from Background Thread        
    	int success;
        try {     	
        	// Building Parameters
        	ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            
        	//Set parameters: FIRST is Column Name, SECOND is Column Value
        	//***Number of Parameters must be even
        	if(args.length>0){
        		for(int i=0; i<args.length/2; i++){
        			Log.i("Param " + (i+1), args[i*2]);
        			Log.i("Param " + (i+1), args[i*2 + 1]);
        			params.add(new BasicNameValuePair(args[i*2], args[(i*2) + 1]));
        		}
        		
	            // getting product details by making HTTP request
	            // Note that product details url will use GET request        	
	        	JSONObject json = JSONParser.makeHttpRequest
	        			(Define.URL_GET_FROM_QUERY, "GET", params);
	
	            // check your log for json response
	            Log.d("Data", json.toString());	
	            
	            // json success tag
	            success = json.getInt(Define.TAG_SUCCESS);
	            if (success == 1) {
	                // successfully received product details
	                JSONArray dataArray = json.getJSONArray(Define.TAG_OBJECT_ARRAY); // JSON Array                
	
	                // looping through All Products
	                for (int i = 0; i < dataArray.length(); i++) {
	                    JSONObject c = dataArray.getJSONObject(i);
	
	                    // Storing each json item in variable
	                    int patient_id = c.getInt(Define.TAG_PATIENT_ID);                    
	                    int location_id = c.getInt(Define.TAG_LOCATION_ID);
	                    int order = c.getInt(Define.TAG_ORDER);
	                    int duration = c.getInt(Define.TAG_DURATION);
	                    
	                    Monitoring_Daily obj = new Monitoring_Daily
	                    		(patient_id, location_id, order, duration);   
	                    list.add(obj);                                   
	                }                
	                
	            }else{	            	
	            	Log.e("Database", "Failed to load data");
	            	getActivity().finish();
	            }
        	}else{
        		Log.e("URL", "No Parameter");
        	}
        	
        } catch(ConnectException e){
        	isException = true;
        	String msg = getString(R.string.connection_exception) + 
        			"\n" + getString(R.string.check_internet_connection);
        	return msg;
        } 	
        catch(ConnectTimeoutException | SocketTimeoutException e){
        	isException = true;
        	String msg = getString(R.string.connection_timeout_exception) + 
        			"\n" + getString(R.string.server_might_be_down);
        	return msg;        
        } catch (Exception e) {
        	isException = true;
        	String msg = getString(R.string.server_might_be_down);
        	return msg;           
        }

        return null;
    }
    
    
    
    private void showErrorDialogFragment(String msg){
    	RetryFragment retryFragment = (RetryFragment) getFragmentManager().findFragmentByTag(Define.TAG_RETRY_FRAGMENT);    	
    	retryFragment.setMessage(msg); // set the message for the retry fragment
    	retryFragment.setLabel(0, Define.TAG_TODAY); // set the label to identify that this fragment is being replaced
    	
    	FragmentTransaction ft = getFragmentManager().beginTransaction();
    	ft.detach(getFragmentManager().findFragmentByTag(Define.TAG_TODAY)); //detach the current fragment   	
    	ft.show(retryFragment).commit(); // show the retry fragment
    }
    

    /**
     * After completing background task Dismiss the progress dialog
     * **/
    @Override
    protected void onPostExecute(String result) {
    	// dismiss the dialog after getting all products    	
        mDialog.dismiss(); 
        
        //check if there is error
    	if(isException){
        	showErrorDialogFragment(result);
        	return;
        }    	
    	  
        // draw graph
        drawGraph();
    }
    
    private void drawGraph(){
    	ScatterChart mChart;
    	ScatterData mData;
    	ScatterDataSet mDataSet1;
    	
    	mChart = (ScatterChart) getView().findViewById(R.id.chart);
		
		//Add data to ArrayList of Entry
		ArrayList<Entry> vals = new ArrayList<Entry>();
		
		for(int i=0; i<list.size(); i++){
			Entry e = new Entry(list.get(i).getDuration(), i);
			vals.add(e);
		}

				
		//Add ArrayList of Entry to DataSet
		mDataSet1 = new ScatterDataSet(vals, "Duration");
		//mDataSet1.setColors(new int[]{}, this);

				
		//Add DataSet to ArrayList of DataSet
		//and String Label to ArrayList of String
		ArrayList<ScatterDataSet> dataSets = new ArrayList<ScatterDataSet>();
		dataSets.add(mDataSet1);
				
		ArrayList<String> xVals = new ArrayList<String>();
		for(int i=0; i<list.size(); i++){
			xVals.add(String.valueOf(list.get(i).getOrder()));
		}
		
				
		//Add ArrayList of DataSet and ArrayList of String to (Chart) Data
		mData = new ScatterData(xVals, dataSets);
		mChart.setData(mData);

		//Styling Legend
		Legend mLegend = mChart.getLegend();
		mLegend.setFormSize(10f); //set size of the legend forms/shapes
		mLegend.setForm(LegendForm.CIRCLE); //set what type of form/shape should be used
		mLegend.setPosition(LegendPosition.BELOW_CHART_LEFT);
		mLegend.setXEntrySpace(5f); //set the space between the legend entries on the x-axis
		mLegend.setYEntrySpace(5f); //set the space between the legend entries on the y-axis
				
				
		//Styling Labels
		mChart.setDrawXLabels(true);
		mChart.setDrawYLabels(true);
				
		XLabels xl = mChart.getXLabels();
		xl.setPosition(XLabelPosition.BOTTOM); //set position
		xl.setTextSize(12f);
		xl.setSpaceBetweenLabels(3); //set how many character of space should be between the labels
				
		YLabels yl = mChart.getYLabels();
		yl.setPosition(YLabelPosition.BOTH_SIDED);
		yl.setTextSize(12f);
		yl.setLabelCount(8); //set how many label entries should be displayed		
			
		//Styling Scatter Shape
		mDataSet1.setScatterShape(ScatterShape.CIRCLE);
		mDataSet1.setScatterShapeSize(10f);		
				
		/**
		 * Set Animation
		 */
		mChart.animateY(3000); //animate vertical 3000 milliseconds
		//mChart.animateXY(3000, 3000); //animate horizontal and vertical 3000 milliseconds
		
		/**
		 * Other styling
		 */
		mChart.setDescription("Daily Using");
				
    }
    

}
	

}
