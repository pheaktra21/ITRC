/**
 * @author Jerry
 *
 */
package com.example.mydashboard;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.chart.BarChart.Type;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.model.XYValueSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.app.Activity;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class FragmentRestroomTabMonthly extends Fragment {
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
		
		String[] titles = new String[] { "2007", "2008" };
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 5230, 7300, 9240, 10540, 7900, 9200, 12030,
				11200, 9500, 10500, 11600, 13500 });
		values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200,
				22030, 21200, 19500, 15500, 12600, 14000 });
		int[] colors = new int[] { Color.CYAN, Color.BLUE };
		XYMultipleSeriesRenderer renderer = ChartHelper.buildBarRenderer(colors);
		renderer.setOrientation(Orientation.VERTICAL);
		ChartHelper.setChartSettings(renderer, "Monthly sales in the last 2 years",
				"Month", "Units sold", 0.5, 12.5, 0, 24000, Color.GRAY, Color.LTGRAY);
		renderer.setXLabels(1);
		renderer.setYLabels(10);
		renderer.addXTextLabel(1, "Jan");
		renderer.addXTextLabel(3, "Mar");
		renderer.addXTextLabel(5, "May");
		renderer.addXTextLabel(7, "Jul");
		renderer.addXTextLabel(10, "Oct");
		renderer.addXTextLabel(12, "Dec");
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer seriesRenderer = renderer
					.getSeriesRendererAt(i);
			seriesRenderer.setDisplayChartValues(true);
		}
		
		if (mChartView == null) {
			FrameLayout layout = (FrameLayout) getView().findViewById(R.id.frag);
			mChartView=ChartFactory.getBarChartView(getActivity(), ChartHelper.buildBarDataset(titles, values), renderer, Type.DEFAULT);
			layout.addView(mChartView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		}else {
			mChartView.repaint();
		}
		
	}
}
