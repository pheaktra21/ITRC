package com.example.mydashboard;

import java.util.ArrayList;
import java.util.HashMap;

public class Define {
	
	//URL node names
	public final static String SERVER_NAME = "http://210.115.187.254/ITRC_DB";
	
	public final static String  URL_GET_MONITORING_DAILY_OF_A_PATIENT = SERVER_NAME + "/get_monitoring_daily_of_a_patient.php";
	public final static String  URL_GET_FROM_QUERY = SERVER_NAME + "/get_from_query.php";

	
	
	//JSON node names
	public static final String TAG_SUCCESS = "success";	
	public static final String TAG_OBJECT_ARRAY = "object_array";
	
	//Undefined Category
	public static final String TAG_ID = "id";
	public static final String TAG_NAME = "name";
	public static final String TAG_AGE = "age";
	
	//Patients
	public static final String TAG_PATIENT_ID = "patient_id";
	
	
	//Monitoring_Daily
	public static final String TAG_IDX = "idx";	
	public static final String TAG_LOCATION_ID = "location_id";
	public static final String TAG_DATE_ID = "date_id";
	public static final String TAG_ORDER = "order";
	public static final String TAG_PLACE_FREQUENCY = "place_frequency";
	public static final String TAG_DURATION = "duration";
	
	
	//Fragment
	public static final String TAG_RETRY_FRAGMENT = "retry_fragment";
	public static final String TAG_TODAY = "today";
	public static final String TAG_WEEKLY = "weekly";
	public static final String TAG_MONTHLY = "monthly";
	
	
	//Static Map of String
	static ArrayList<String> PATIENT_LIST = new ArrayList<>();
	static{
		PATIENT_LIST.add("Jackson");
		PATIENT_LIST.add("Joey");
		PATIENT_LIST.add("Johnny");
	}
}
