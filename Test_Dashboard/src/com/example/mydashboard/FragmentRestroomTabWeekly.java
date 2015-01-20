/**
 * 
 */
package com.example.mydashboard;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.BubbleChart;
import org.achartengine.chart.CubicLineChart;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.model.XYValueSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.*;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * @author Jerry
 *
 */
public class FragmentRestroomTabWeekly extends Fragment {

	private GraphicalView mChartView;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_container, container, false);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		try{
			drawGraph();
		}catch(Exception e){
			Log.d("draw", e.getMessage());
		}
		
	}
	
	private void drawGraph(){
		
		String[] titles = new String[] { "Crete Air Temperature", "Skiathos Air Temperature" };
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		}
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4,
				26.1, 23.6, 20.3, 17.2, 13.9 });
		values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });
		int[] colors = new int[] { Color.GREEN, Color.rgb(200, 150, 0) };
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE,
				PointStyle.DIAMOND };
		XYMultipleSeriesRenderer renderer = ChartHelper.buildRenderer(colors, styles);
		renderer.setPointSize(5.5f);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = (XYSeriesRenderer) renderer
					.getSeriesRendererAt(i);
			r.setLineWidth(5);
			r.setFillPoints(true);
		}
		ChartHelper.setChartSettings(renderer, "Weather data", "Month", "Temperature", 0.5,
				12.5, 0, 40, Color.LTGRAY, Color.LTGRAY);
		
		renderer.setXLabels(12);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setZoomButtonsVisible(true);
		renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
		renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });
		
		XYValueSeries sunSeries = new XYValueSeries("Sunshine hours");
		sunSeries.add(1f, 35, 4.3);
		sunSeries.add(2f, 35, 4.9);
		sunSeries.add(3f, 35, 5.9);
		sunSeries.add(4f, 35, 8.8);
		sunSeries.add(5f, 35, 10.8);
		sunSeries.add(6f, 35, 11.9);
		sunSeries.add(7f, 35, 13.6);
		sunSeries.add(8f, 35, 12.8);
		sunSeries.add(9f, 35, 11.4);
		sunSeries.add(10f, 35, 9.5);
		sunSeries.add(11f, 35, 7.5);
		sunSeries.add(12f, 35, 5.5);
		XYSeriesRenderer lightRenderer = new XYSeriesRenderer();
		lightRenderer.setColor(Color.YELLOW);
		
		XYSeries waterSeries = new XYSeries("Water Temperature");
		waterSeries.add(1, 16);
		waterSeries.add(2, 15);
		waterSeries.add(3, 16);
		waterSeries.add(4, 17);
		waterSeries.add(5, 20);
		waterSeries.add(6, 23);
		waterSeries.add(7, 25);
		waterSeries.add(8, 25.5);
		waterSeries.add(9, 26.5);
		waterSeries.add(10, 24);
		waterSeries.add(11, 22);
		waterSeries.add(12, 18);
		renderer.setBarSpacing(0.5);
		XYSeriesRenderer waterRenderer = new XYSeriesRenderer();
		waterRenderer.setColor(Color.argb(250, 0, 210, 250));
		
		XYMultipleSeriesDataset dataset = ChartHelper.buildDataset(titles, x, values);
		dataset.addSeries(0, sunSeries);
		dataset.addSeries(0, waterSeries);
		renderer.addSeriesRenderer(0, lightRenderer);
		renderer.addSeriesRenderer(0, waterRenderer);
		waterRenderer.setDisplayChartValues(true);
		waterRenderer.setChartValuesTextSize(10);
		
		String[] types = new String[] { BarChart.TYPE, BubbleChart.TYPE,
				LineChart.TYPE, CubicLineChart.TYPE };
		
		if (mChartView == null) {
			FrameLayout layout = (FrameLayout) getView().findViewById(R.id.frag);
			mChartView=ChartFactory.getCombinedXYChartView(getActivity(), dataset, renderer, types);
			layout.addView(mChartView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		}else {
			mChartView.repaint();
		}
	}
}
