package com.example.models;

public class Monitoring_Daily {
	
	/*public static final String IDX = "idx";
	public static final String PATIENT_ID = "patient_id";
	public static final String LOCATION_ID = "location_id";
	public static final String DATE_ID = "date_id";
	public static final String ORDER = "order";
	public static final String FREQUENCY = "frequency";
	public static final String STAY = "stay";
	*/
	private int idx;
	private int patient_id;
	private int location_id;
	private int date_id;
	private int order;
	private int place_frequency;
	private int duration;
	
	public Monitoring_Daily() {
		this(0, 0, 0, 0, 0, 0, 0);
	}
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public int getDate_id() {
		return date_id;
	}

	public void setDate_id(int date_id) {
		this.date_id = date_id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}


	public Monitoring_Daily(int patient_id, int location_id, int order, int duration){
		this(0, patient_id, location_id, 0, order, 0, duration);
	}
	
	public Monitoring_Daily(int idx, int patient_id, int location_id, 
			int date_id, int order, int frequency, int duration){
		this.idx = idx;
		this.patient_id = patient_id;
		this.location_id = location_id;
		this.date_id = date_id;
		this.order = order;
		this.place_frequency = frequency;
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getPlace_frequency() {
		return place_frequency;
	}

	public void setPlace_frequency(int place_frequency) {
		this.place_frequency = place_frequency;
	}

}
