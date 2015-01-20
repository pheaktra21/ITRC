package com.tabandswipe;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v13.app.FragmentStatePagerAdapter;

/**
 * 
 * @author Pheaktra P. Chhaya (Tim)
 * <p>A convenient and efficient tool for creating basic Tabs and Swipe Pages.</p>
 * 
 */
public class MySectionGenerator{	

	/**
	 * Setup Tabs on the given ActionBar instance. 
	 * 
	 * @param actionBar The ActionBar in which the Tabs on.
	 * @param sectionLabels The array of <i>String</i> indicated the label of each Tab
	 * @param tabListener The TabListener of the ActionBar.
	 */
	@SuppressWarnings("deprecation")
	public static void setupTabs(ActionBar actionBar, String[] sectionLabels, TabListener tabListener){		
		//Set NavigationMode for ActionBar
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		for (int i = 0; i<sectionLabels.length; i++) {
			actionBar.addTab(actionBar.newTab()		//create new tab
					.setText(sectionLabels[i])			//add title
					.setTabListener(tabListener));			//add listener			
		}			
	}
	
	/**
	 * Setup Swipe Pages by using the existed layouts.</br>
	 * The container layout of the Swipe Pages (layouts) must contain an object of ViewPager.
	 * 
	 * @param activity The activity containing the Swipe Pages.
	 * @param pagerID The ID of the ViewPager in the container layout file.
	 * @param sectionLabels The array of <i>String</i> indicated the label of each Swipe Page.
	 * @param sectionLayouts The array of <i>int</i> indicated the resources of the layouts of the Swipe Pages.
	 */
	public static void setupSwipe(Activity activity, int pagerID, String[] sectionLabels, int[] sectionLayouts){
		// Create the adapter that will return a fragment for each of the primary sections		
		SectionsPagerAdapter mSectionsPagerAdapter = new MySectionGenerator().new SectionsPagerAdapter(activity.getFragmentManager(), sectionLabels, sectionLayouts);
		if(mSectionsPagerAdapter.isValid){
			buildViewPager(activity, mSectionsPagerAdapter, pagerID);
		}
		else{
			Log.e("Data Validation", "Length of Labels and of Layout or Fragment are not equal.");
			activity.finish();
		}
		
	}
	
	/**
	 * Setup Swipe Pages by using the existed Fragments.</br>
	 * The container layout of the Swipe Pages (layouts) must contain an object of ViewPager.
	 * 
	 * @param activity The activity containing the Swipe Pages.
	 * @param pagerID The ID of the ViewPager in the container layout file.
	 * @param sectionLabels The array of <i>String</i> indicated the label of each Swipe Page.
	 * @param sectionFragments The array of <i>Fragment<i> indicated the layout of the Swipe Pages.
	 * 
	 */
	public static void setupSwipe(Activity activity, int pagerID, String[] sectionLabels, Fragment[] sectionFragments){
		// Create the adapter that will return a fragment for each of the primary sections
		SectionsPagerAdapter mSectionsPagerAdapter = new MySectionGenerator().new SectionsPagerAdapter(activity.getFragmentManager(), sectionLabels, sectionFragments);		
		if(mSectionsPagerAdapter.isValid){
			buildViewPager(activity, mSectionsPagerAdapter, pagerID);
		}
		else{
			Log.e("Data Validation", "Length of Labels and of Layout or Fragment are not equal.");
			activity.finish();
		}				
	}
	
	
	/**
	 * Setup Swipe Pages along with Tabs by using the existed layouts.</br>
	 * The container layout of the Swipe Pages (layouts) must contain an object of ViewPager.
	 * 
	 * @param activity The activity containing the Swipe Pages.
	 * @param pagerID The ID of the ViewPager in the container layout file.
	 * @param sectionLabels The array of <i>String</i> indicated the label of each Swipe Page.
	 * @param sectionLayouts The array of <i>int</i> indicated the resources of the layouts of the Swipe Pages.
	 *
	 */
	public static void setupSwipeAndTap(Activity activity, int pagerID, String[] sectionLabels, int[] sectionLayouts){		
		SectionsPagerAdapter mSectionsPagerAdapter = new MySectionGenerator().new SectionsPagerAdapter(activity.getFragmentManager(), sectionLabels, sectionLayouts);		
		if(mSectionsPagerAdapter.isValid){
			buildViewPagerWithTab(activity, mSectionsPagerAdapter, pagerID, sectionLabels);
		}
		else{
			Log.e("Data Validation", "Length of Labels and of Layout or Fragment are not equal.");
			activity.finish();
		}		
	}
	
	/**
	 * Setup Swipe Pages along with Tabs by using the existed Fragments.</br>
	 * The container layout of the Swipe Pages (layouts) must contain an object of ViewPager.
	 * 
	 * @param activity The activity containing the Swipe Pages.
	 * @param pagerID The ID of the ViewPager in the container layout file.
	 * @param sectionLabels The array of <i>String</i> indicated the label of each Swipe Page.
	 * @param sectionFragments The array of <i>Fragment<i> indicated the layout of the Swipe Pages.
	 * 
	 */
	public static void setupSwipeAndTap(Activity activity, int pagerID, String[] sectionLabels, Fragment[] sectionFragments){		
		SectionsPagerAdapter mSectionsPagerAdapter = new MySectionGenerator().new SectionsPagerAdapter(activity.getFragmentManager(), sectionLabels, sectionFragments);
		if(mSectionsPagerAdapter.isValid){
			buildViewPagerWithTab(activity, mSectionsPagerAdapter, pagerID, sectionLabels);
		}
		else{
			Log.e("Data Validation", "Length of Labels and of Layout or Fragment are not equal.");
			activity.finish();
		}		
	}
	
	
	/**
	 * Setup Swipe Views by using the existed layouts.</br>
	 * The container layout of the Swipe Views must contain an object of ViewPager,
	 * and an object of com.viewpagerindicator.PageIndicator adjacent to the object of ViewPager.
	 * 
	 * @param activity The activity containing the Swipe Pages.
	 * @param pagerID The ID of the ViewPager in the container layout file.
	 * @param indicatorID The ID of the PageIndicator in the container layout file.
	 * @param sectionLayouts The array of <i>int</i> indicated the resources of the layouts of the Swipe Views.
	 * 
	 */
	public static void setupSliderWithCircleIndicator(Activity activity, int pagerID, int indicatorID, int[] sectionLayouts){
		SectionsPagerAdapter mSectionsPagerAdapter = new MySectionGenerator().new SectionsPagerAdapter(activity.getFragmentManager(), sectionLayouts);		
		if(mSectionsPagerAdapter.isValid){
			buildViewPagerAndIndicator(activity, mSectionsPagerAdapter, pagerID, indicatorID);
		}
	}
	
	/**
	 * Setup Swipe Views by using the existed layouts.</br>
	 * The container layout of the Swipe Views must contain an object of ViewPager,
	 * and an object of com.viewpagerindicator.PageIndicator adjacent to the object of ViewPager.
	 * 
	 * @param activity The activity containing the Swipe Pages.
	 * @param pagerID The ID of the ViewPager in the container layout file.
	 * @param indicatorID The ID of the PageIndicator in the container layout file.
	 * @param views The array of <i>View</i> indicated the Views (layouts) of the Swipe Views.
	 * 
	 */
	public static void setupSliderWithCircleIndicator(Activity activity, int pagerID, int indicatorID, View[] views){
		SectionsPagerAdapter mSectionsPagerAdapter = new MySectionGenerator().new SectionsPagerAdapter(activity.getFragmentManager(), views);
		if(mSectionsPagerAdapter.isValid){
			buildViewPagerAndIndicator(activity, mSectionsPagerAdapter, pagerID, indicatorID);
		}
	}
	
	
	
	private static void buildViewPagerAndIndicator(Activity activity, SectionsPagerAdapter adapter,
			int pagerID, int indicatorID){
		ViewPager mViewPager = buildViewPager(activity, adapter, pagerID);
		if(mViewPager != null){
			PageIndicator mIndicator = (CirclePageIndicator) activity.findViewById(indicatorID);
			if(mIndicator != null){
				mIndicator.setViewPager(mViewPager);
			}else{
				Log.e("Indicator Error", "No Indicator with id: " + indicatorID);
				activity.finish();
			}
		}else{
			Log.e("ViewPager Error", "No ViewPager with id: " + pagerID);
			activity.finish();
		}
	}	
	
	
		
	
	private static ViewPager buildViewPager(Activity activity, SectionsPagerAdapter adapter, int pagerID){
		// Set up the ViewPager with the sections adapter.
		ViewPager mViewPager = (ViewPager) activity.findViewById(pagerID);
				
		if(mViewPager != null){
			mViewPager.setAdapter(adapter);		
			return mViewPager;
		}
		else{
			Log.e("setupSwipe", "No ViewPager with id: " + pagerID);
			activity.finish();			
		}
		return null;
	}
	
	
	
	@SuppressWarnings("deprecation")
	private static void buildViewPagerWithTab(Activity activity, SectionsPagerAdapter adapter, int pagerID, String[] sectionLabels){				
		final ActionBar actionBar = activity.getActionBar();
		final ViewPager mViewPager = buildViewPager(activity, adapter, pagerID);
		
		//Instantiate TabListener
		TabListener tl = new TabListener() {			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				//Change content when a Tab is selected
				try{
					mViewPager.setCurrentItem(tab.getPosition(), true);
				}catch(Exception e){
					Log.e("onTabSelected", e.getMessage());
				}				
			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
			}
		};
		
		setupTabs(actionBar, sectionLabels, tl);
		
		//Add event swiping to have Tab selected
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			@Override
			public void onPageSelected(int position){
				//Select a Tab when swiping
				if(actionBar != null){
					actionBar.setSelectedNavigationItem(position);
				}
			}
		});
	}
			
	
	
	
	/**
	 * 
	 * @author Tim
	 * 
	 *This class is being used with ViewPager that can be applied only
	 *to Swipe or SwipeWithTap. It CANNOT be applied to TabOnly.
	 *
	 *A FragmentStatePagerAdapter is the adapter for automatically
	 *constructing the Sections
	 *
	 */
	private class SectionsPagerAdapter extends FragmentStatePagerAdapter {
		private int[] sectionLayouts;
		private Fragment[] sectionFragments;
		private String[] sectionLabels;
		private View[] views;
		private int numberOfSections;
		private boolean isValid;
		
		public SectionsPagerAdapter(FragmentManager fm, String[] sectionLabels, int[] sectionLayouts) {
			super(fm);	
			setData(sectionLabels, sectionLayouts, null, null,
					sectionLabels.length, sectionLabels.length == sectionLayouts.length);
		}
		
		public SectionsPagerAdapter(FragmentManager fm, String[] sectionLabels, Fragment[] sectionFragments) {
			super(fm);
			setData(sectionLabels, null, sectionFragments, null,
					sectionLabels.length, sectionLabels.length == sectionFragments.length);			
		}
		
		public SectionsPagerAdapter(FragmentManager fm, int[] sectionLayouts) {
			super(fm);
			setData(null, sectionLayouts, null, null,
					sectionLayouts.length, true);
		}
		
		public SectionsPagerAdapter(FragmentManager fm, View[] views) {
			super(fm);
			setData(null, null, null, views, views.length, true);
		}		
		
		private void setData(String[] labels, int[] layouts, Fragment[] frags,
				View[] views, int size, boolean valid){
			this.sectionLabels = labels;
			this.sectionLayouts = layouts;
			this.sectionFragments = frags;
			this.views = views;
			this.numberOfSections = size;
			this.isValid = valid;
		}

//		Which fragment (item) should be changed
		@Override
		public Fragment getItem(int position) {
			Fragment f = null;
			if(sectionFragments!=null){
				/**
				 * If sectionFragments is not NULL, it must return
				 * a Fragment from sectionFragments
				 */
				f = sectionFragments[position];
			}
			else if(sectionLayouts!=null){
				/**
				 * If sectionFragment is NULL and sectionLayouts is not NULL,
				 * it must return a (static) Fragment from the custom Fragment class
				 * with the provided Layouts 
				 */
				f = Section.newInstance(sectionLayouts[position]);				
			}
			else if(views != null){
				f = Section.newInstance(views[position]);				
			}
			
			//Return an instance of Fragment with a specific layout
			return f;			
		}
//		Number of sections (tabs)
		@Override
		public int getCount() {
			return numberOfSections;
		}				
//		For strip description
		@Override
		public CharSequence getPageTitle(int position){
			if(sectionLabels != null)
				return sectionLabels[position];
			else
				return "";
		}				
	}
	
	
	
	/**
	 * 
	 * @author Tim
	 * 
	 * A Fragment class for instantiating when the FragmentArray is not provided.
	 * The child of Fragment must be public.
	 *
	 */
	public static class Section extends Fragment {		
		final static String LAYOUT = "layout";			
		final static String TYPE = "type";
		final static String VIEW = "view";
				
		
		/*@
		 * Every fragment must have an empty constructor, so it can be instantiated 
		 * when restoring its activity's state. It is strongly recommended that 
		 * subclasses do not have other constructors with parameters, 
		 * since these constructors will not be called when the fragment is 
		 * re-instantiated; instead, arguments can be supplied by the caller with 
		 * setArguments(Bundle) and later retrieved by the Fragment with getArguments().
		 */
		static Section newInstance(int layoutID) {
			Section fragment = new Section();			
			Bundle bundle = new Bundle();
			bundle.putInt(LAYOUT, layoutID);
			bundle.putString(TYPE, "");
			
			fragment.setArguments(bundle);
			return fragment;
		}
		
		static Section newInstance(View view) {
			Section fragment = new Section();			
			Bundle bundle = new Bundle();		
			bundle.putString(TYPE, VIEW);
			bundle.putParcelable(VIEW, new MySectionGenerator().new ViewParcelable(view));

			fragment.setArguments(bundle);
			return fragment;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState){			
			String type = getArguments().getString(TYPE);
			try{
				if(type.equals(VIEW)){			
					//Get ViewParcelable from Bundle
					ViewParcelable op = getArguments().getParcelable(VIEW);
					return op.mView;																
				}
				else{				
					//Get layoutID from Bundle
					int layoutID = getArguments().getInt(LAYOUT);
					return inflater.inflate(layoutID, container, false);
				}
			}catch(Exception e){
				Log.e("onCreateView", e.getMessage());
			}
				return null;

		}
		
	}
	
	private class ViewParcelable implements Parcelable{
		View mView;
		
		public ViewParcelable(View v){
			this.mView = v;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			
		}
		
	}





	

}
