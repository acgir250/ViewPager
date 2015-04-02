package com.test.viewpager;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private Calendar cal;
	private ImageView btnleft;
	private ImageView btnright;
	int curryear;
	private YearAdapter yearAdapter;
	private TextView yearname;

	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customdialog_month);
		try {
			
			Button btncamera = (Button)findViewById(R.id.camerabutton);
			
			btncamera.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try
					{
						startActivity(new Intent(getBaseContext(),CameraApi.class));
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
			});
//			cal = Calendar.getInstance(Locale.getDefault());
//			curryear = cal.get(Calendar.YEAR);
//			initcomponents();

			// viewPager = (ViewPager) findViewById(R.id.pager);
			// cal = Calendar.getInstance(Locale.getDefault());
			// CURR_YEAR = cal.get(Calendar.YEAR);
			// curryear = cal.get(Calendar.YEAR);
			// cal.set(Calendar.YEAR, CURR_YEAR);
			//
			//
			// myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
			// viewPager.setAdapter(myPagerAdapter);
			// viewPager.setCurrentItem(10);

		} catch (Exception e) {
			Log.i("error in activity", e.toString());
		}
	}

	@Override
	public void onBackPressed() {
		finish();
	}

	private void initcomponents() {
		try {

			yearname = (TextView) findViewById(R.id.txtCalendarYear);
			btnleft = (ImageView) findViewById(R.id.btnBack);
			btnright = (ImageView) findViewById(R.id.btnNext);
			// filling the calender
			GridView view = (GridView) findViewById(R.id.yearCalendar);
			yearAdapter = new YearAdapter();
			view.setAdapter(yearAdapter);
			yearname.setText(String.valueOf(curryear));
			btnleft.setOnClickListener(this);
			btnright.setOnClickListener(this);
			// curryear--;
			// cal.set(Calendar.YEAR, curryear);
			// cal.set(Calendar.MONTH, 0);
			// cal.set(Calendar.DATE, 1);
			// yearname.setText(String.valueOf(cal.get(Calendar.YEAR)));
			// yearAdapter.notifyDataSetChanged();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnBack:
			curryear--;
			cal.set(Calendar.YEAR, curryear);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.DATE, 1);
			yearname.setText(String.valueOf(cal.get(Calendar.YEAR)));
			yearAdapter.notifyDataSetChanged();
			break;
		case R.id.btnNext:
			curryear++;
			cal.set(Calendar.YEAR, curryear);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.DATE, 1);
			yearname.setText(String.valueOf(cal.get(Calendar.YEAR)));
			yearAdapter.notifyDataSetChanged();
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
					LayoutInflater layoutInflater = (LayoutInflater) getLayoutInflater();
					convertView = layoutInflater.inflate(
							R.layout.yearview_calender, null);

					monthViewHolder.monthName = (TextView) convertView
							.findViewById(R.id.monthName);

					monthViewHolder.monthCalendar = (ExpandableHeightGridView) convertView
							.findViewById(R.id.monthCalendar);
					monthViewHolder.monthAdapter = new MonthAdapter(
							getBaseContext(), position, cal);
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
								Intent i = new Intent(MainActivity.this,
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

// private class MyPagerAdapter extends FragmentPagerAdapter {
//
// public MyPagerAdapter(FragmentManager fm) {
// super(fm);
//
// }
//
// @Override
// public Fragment getItem(int position) {
// // Paging from one to another year by swiping the finger.
// Fragment fragment = null;
// try {
//
//
// } catch (Exception e) {
// e.printStackTrace();
// }
// return fragment;
// }
//
// @Override
// public int getCount() {
// return NUMBER_OF_PAGES;
// }
//
// }

