package com.test.viewpager;

import java.util.Calendar;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.GridView;
import android.widget.Toast;

public class MonthActivity extends FragmentActivity {
	GridView monthgrid;
	Calendar cal;
	Intent intent;
	int weekDay;
	int totDays;
	int curYear;
	int curMonth;
	int oldposition = 0;
	final int NUMBER_OF_PAGES = 12;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity_month);
			// Inflating a month fragment in current activity...

			Calendar cal = Calendar.getInstance();
			intent = getIntent();
			curYear = intent.getIntExtra("CURRYEAR", 0);
			curMonth = intent.getIntExtra("MONTH", -1);

			// txtcurrmonth.setText(Constants.monthNames[curMonth]);
			//
			// Toast.makeText(getBaseContext(),
			// String.valueOf(curYear),Toast.LENGTH_LONG).show();
			// Toast.makeText(getBaseContext(),
			// String.valueOf(curMonth),Toast.LENGTH_LONG).show();
			//
			// cal.set(Calendar.YEAR,curYear);
			// cal.set(Calendar.MONTH,curMonth);
			// cal.set(Calendar.DATE,1);
			//
			// weekDay = cal.get(Calendar.DAY_OF_WEEK);
			// totDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			// monthgrid = (GridView) findViewById(R.id.monthGrid);
			// dateAdapter = new DateAdapter();
			// monthgrid.setAdapter(dateAdapter);

			final ViewPager viewPager = (ViewPager) findViewById(R.id.month_pager);
			final MonthPagerAdapter monthPagerAdapter = new MonthPagerAdapter(
					getSupportFragmentManager());
			viewPager.setAdapter(monthPagerAdapter);
			viewPager.setCurrentItem(curMonth);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class MonthPagerAdapter extends FragmentPagerAdapter {

		public MonthPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			MonthFragment monthFragment = new MonthFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("curYear", curYear);
			bundle.putInt("curMonth", curMonth);

			switch (position) {
			case 0:
				bundle.putInt("curMonth", 0);
				break;
			case 1:
				bundle.putInt("curMonth", 1);
				break;
			case 2:
				bundle.putInt("curMonth", 2);
				break;
			case 3:
				bundle.putInt("curMonth", 3);
				break;
			case 4:
				bundle.putInt("curMonth", 4);
				break;
			case 5:
				bundle.putInt("curMonth", 5);
				break;
			case 6:
				bundle.putInt("curMonth", 6);
				break;
			case 7:
				bundle.putInt("curMonth", 7);
				break;
			case 8:
				bundle.putInt("curMonth", 8);
				break;
			case 9:
				bundle.putInt("curMonth", 9);
				break;
			case 10:
				bundle.putInt("curMonth", 10);
				break;
			case 11:
				bundle.putInt("curMonth", 11);
				break;
			}
			monthFragment.setArguments(bundle);
			fragment = monthFragment;
			return fragment;
		}

		@Override
		public int getCount() {
			return NUMBER_OF_PAGES;
		}
	}
}
