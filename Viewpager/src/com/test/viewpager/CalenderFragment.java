package com.test.viewpager;

import java.util.Calendar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CalenderFragment extends android.support.v4.app.Fragment implements OnClickListener {
	private View view;
	private Calendar cal;
	int CURR_YEAR = 0;
	int curryear = 0;
	int curYear;
	int curMonth;
	private ImageView btnleft;
	private ImageView btnright;
	String curCalendar;
	YearAdapter yearAdapter = null;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LayoutInflater infaltor = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = infaltor.inflate(R.layout.activity_calender_view, null);
		btnleft  =(ImageView) view.findViewById(R.id.btnBack);
		btnright  =(ImageView) view.findViewById(R.id.btnNext);
		btnleft.setOnClickListener(this);
		btnright.setOnClickListener(this);
		YearAdapter yearAdapter = new YearAdapter();
		GridView root_year_view = (GridView) view
				.findViewById(R.id.yearCalendar);
		root_year_view.setAdapter(yearAdapter);

		Log.i("Getting year", String.valueOf(cal.get(Calendar.YEAR)));

		Toast.makeText(getActivity(),
				"gettting Year::::" + String.valueOf(cal.get(Calendar.YEAR)),
				Toast.LENGTH_SHORT).show();
		//yearAdapter.notifyDataSetChanged();
		
		return view;
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.btnBack:
			
			break;
        case R.id.btnNext:
			break;	
		}
		
		
	}
	class YearAdapter extends BaseAdapter {

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

			try {
				if (convertView == null) {

					// inflating month values one by one
					monthViewHolder = new MonthViewHolder();
					LayoutInflater layoutInflater = (LayoutInflater) getActivity()
							.getLayoutInflater();
					convertView = layoutInflater.inflate(
							R.layout.yearview_calender, null);

					monthViewHolder.monthName = (TextView) convertView
							.findViewById(R.id.monthName);

					monthViewHolder.monthCalendar = (ExpandableHeightGridView) convertView
							.findViewById(R.id.monthCalendar);
					monthViewHolder.monthAdapter = new MonthAdapter(
							getActivity(), position, cal);
					monthViewHolder.monthCalendar
							.setAdapter(monthViewHolder.monthAdapter);
					monthViewHolder.monthCalendar.setExpanded(true);
					monthViewHolder.monthAdapter.notifyDataSetChanged();
					Log.i("MonthNames", Constants.monthNames[position]);
					convertView.setTag(monthViewHolder);

				} else {
					monthViewHolder = (MonthViewHolder) convertView.getTag();
				}

				monthViewHolder.monthName
						.setText(Constants.monthNames[position]);

				monthViewHolder.monthAdapter.setPosition(position);

				monthViewHolder.monthAdapter.notifyDataSetChanged();

				final int curMonth = monthViewHolder.monthAdapter.getPosition();

				monthViewHolder.monthCalendar
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int pos, long arg3) {
								// Starting the month child activity using
								// intent.

								Intent i = new Intent(getActivity(),
										MonthActivity.class);
								i.putExtra("CURRYEAR", cal.get(Calendar.YEAR));
								i.putExtra("MONTH", curMonth);
								startActivity(i);
							}
						});

			} catch (Exception e) {
				e.printStackTrace();
			}
			return convertView;
		}

	}

	private static class MonthViewHolder {
		TextView monthName;
		ExpandableHeightGridView monthCalendar;
		MonthAdapter monthAdapter;
	}

	

}
