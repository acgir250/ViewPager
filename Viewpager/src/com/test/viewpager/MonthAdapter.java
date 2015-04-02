package com.test.viewpager;

import java.util.Calendar;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MonthAdapter extends BaseAdapter {
	private Context context = null;
	private int resource = 0;
	private Calendar cal;
	// handling for highlighting the current date.
	// Calendar currCal = Calendar.getInstance();
	//
	// int currentYear = currCal.get(Calendar.YEAR);
	// int currentMonth = currCal.get(Calendar.MONTH);
	// int currentDate = currCal.get(Calendar.DATE);

	int weekDay;
	int totDays;

	int monthNumber;

	int lengthOfGrid = 43;

	public MonthAdapter(Context context, int pos, Calendar cal) {
		this.context = context;
		this.resource = pos;
		this.cal = cal;
	}

	public int getPosition() {
		return monthNumber;
	}

	public void setPosition(int position) {
		this.monthNumber = position;
	}

	@Override
	public int getCount() {
		return lengthOfGrid;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DateViewHolder dateViewHolder = null;
		try {
			
			cal.set(Calendar.MONTH, monthNumber);
			weekDay = cal.get(Calendar.DAY_OF_WEEK);
			totDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			if (convertView == null) {
				LayoutInflater inflate = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflate.inflate(R.layout.single_grid, null);
				dateViewHolder = new DateViewHolder();
				convertView.setTag(dateViewHolder);
				dateViewHolder.date = (TextView) convertView
						.findViewById(R.id.date);
				// final int i = Integer
				// .parseInt(("#faf4e6").replace("#", ""), 16);
				//
				// dateViewHolder.date.setBackgroundColor(i);
			} else {
				dateViewHolder = (DateViewHolder) convertView.getTag();
			}

			switch (position) {
			case 0:
				dateViewHolder.date.setText("S");
				break;
			case 1:
				dateViewHolder.date.setText("M");
				break;
			case 2:
				dateViewHolder.date.setText("T");
				break;
			case 3:
				dateViewHolder.date.setText("W");
				break;
			case 4:
				dateViewHolder.date.setText("T");
				break;
			case 5:
				dateViewHolder.date.setText("F");
				break;
			case 6:
				dateViewHolder.date.setText("S");
				break;

			default:

				fillDates(dateViewHolder, position, monthNumber);

				break;

			}
		} catch (Exception e) {
			Log.e("Error in the monthview Dates", e.toString());
		}
		return convertView;

	}

	private void fillDates(DateViewHolder dateViewHolder, int pos,
			int currentmonth) {

		if ((pos - 7) + 1 < weekDay) {
			// Blank dates before this month
			dateViewHolder.date.setText("");

		} else if (((pos - 7) + 1 - weekDay) >= totDays) {
			// Blank dates after this month
			dateViewHolder.date.setText("");
		} else {
			// Set dates
			int date = ((pos - 7) + 1 - (weekDay - 1));
			dateViewHolder.date.setText("" + date);

			String cMonth = Integer.toString((monthNumber + 1));
			if (cMonth.length() == 1) {
				cMonth = "0" + cMonth;
			}
			// Other dates handling from database goes here.
		}

	}

	class DateViewHolder {
		TextView date;
	}

}
