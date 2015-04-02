package com.test.viewpager;

import java.util.Calendar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class CalenderView extends Activity {
	private Calendar calenderInstance = null;
	private int CurYear = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calender_view);
		try {

			// Setting the current Year in the calender

			// calenderInstance = Calendar.getInstance();
			//
			// calenderInstance.set(Calendar.YEAR, CurYear);// Set year to
			// calendar
			// calenderInstance.set(Calendar.MONTH, 0); // Set January as
			// default
			// // month to
			// // start filling calendar
			// calenderInstance.set(Calendar.DATE, 1);

			// Container to fill the month in the calneder
			YearAdapter yearAdapter = new YearAdapter();
			GridView root_year_view = (GridView) findViewById(R.id.yearCalendar);
			root_year_view.setAdapter(yearAdapter);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class YearAdapter extends BaseAdapter {

		@Override
		public int getCount() {

			return Constants.monthNames.length;
		}

		@Override
		public Object getItem(int position) {

			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MonthViewHolder monthViewHolder = null;
			if (convertView == null) {

				// inflating month values one by one

				LayoutInflater layoutInflater = (LayoutInflater) getLayoutInflater();
				convertView = layoutInflater.inflate(
						R.layout.yearview_calender, null);

				monthViewHolder = new MonthViewHolder();

				monthViewHolder.monthName = (TextView) convertView
						.findViewById(R.id.monthName);

				monthViewHolder.monthCalendar = (ExpandableHeightGridView) convertView
						.findViewById(R.id.monthCalendar);
//				monthViewHolder.calenderAdapter = new MonthAdapter(
//						CalenderView.this, position);

				monthViewHolder.monthCalendar
						.setAdapter(monthViewHolder.calenderAdapter);
				monthViewHolder.monthCalendar.setExpanded(true);

				Log.i("MonthNames", Constants.monthNames[position]);

				monthViewHolder.monthName
						.setText(Constants.monthNames[position]);
				monthViewHolder.calenderAdapter.setPosition(position);
				monthViewHolder.calenderAdapter.notifyDataSetChanged();

				final int curMonth = monthViewHolder.calenderAdapter
						.getPosition();

				/*
				 * Handling the click events of the view in the UI Calling the
				 * child view (month view)
				 */
				monthViewHolder.monthCalendar
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int pos, long arg3) {
								// Starting the month child activity using
								// intent.
								Toast.makeText(getBaseContext(),"All this folks!!!!!", Toast.LENGTH_LONG).show();
							}
						});

			} else {
				monthViewHolder = (MonthViewHolder) convertView.getTag();
			}
			return convertView;
		}

	}

	private static class MonthViewHolder {
		TextView monthName;
		ExpandableHeightGridView monthCalendar;
		MonthAdapter calenderAdapter;
	}
}
