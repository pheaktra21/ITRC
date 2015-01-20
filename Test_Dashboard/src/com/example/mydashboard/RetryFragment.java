package com.example.mydashboard;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class RetryFragment extends Fragment{
	private Button btnRetry;
	private TextView lblMessage;

	//for controlling fragments
	private String[] labels;
	
	private final static String MESSAGE = "message";
	private final static String NUMBER_OF_FRAGMENTS = "size";
	private final static String LISTENER = "listener";
	
	private RetryFragment() {
		super();
	}
	
	static RetryFragment newInstance(String message, int number_of_fragments, OnClickListener clickListener){
		RetryFragment fragment = new RetryFragment();			
		Bundle bundle = new Bundle();
		bundle.putString(MESSAGE, message);
		bundle.putInt(NUMBER_OF_FRAGMENTS, number_of_fragments);
		bundle.putParcelable(LISTENER, new RetryFragment().new ParcelableObject(clickListener));
		
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.retry, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		labels = new String[getArguments().getInt(NUMBER_OF_FRAGMENTS)];
		
		lblMessage = (TextView)getView().findViewById(R.id.lblMessage);
		lblMessage.setText(getArguments().getString(MESSAGE));
		
		ParcelableObject po = (ParcelableObject)getArguments().getParcelable(LISTENER);
		btnRetry = (Button)getView().findViewById(R.id.btnRetry);
		btnRetry.setOnClickListener(po.getOnClickListener());
	}
	

	public void setMessage(String message){
		lblMessage.setText(message);
	}

	public String[] getLabels() {
		return labels;
	}

	public String getLabel(int index){
		return labels[index];
	}
	public void setLabel( int index, String label){
		labels[index] = label;
	}
	
	
	/**
	 * 
	 * Parcelable Class
	 *
	 */
	private class ParcelableObject implements Parcelable{
		OnClickListener listener;
		
		public ParcelableObject(OnClickListener listener){
			this.listener = listener;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			
		}
		
		public OnClickListener getOnClickListener(){
			return listener;
		}
		
	}

	
}
