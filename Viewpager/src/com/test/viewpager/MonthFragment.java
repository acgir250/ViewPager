package com.test.viewpager;

import java.util.Calendar;

import com.test.viewpager.CalenderFragment.YearAdapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MonthFragment extends Fragment implements OnItemClickListener {

	private View view;
	private Calendar cal;
	private TextView txtCurrMonth;
	int weekDay;
	int totDays;
	int curYear;
	int curMonth;
	GridView dataGrid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		cal = Calendar.getInstance();

	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		try {
			LayoutInflater infaltor = (LayoutInflater) getActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = infaltor.inflate(R.layout.month_fragment, null);
			txtCurrMonth = (TextView) view.findViewById(R.id.txtCurrentMonth);

			Bundle bundle = getArguments();
			int curryear = (Integer) bundle.get("curYear");
			int currmonth = (Integer) bundle.get("curMonth");

			cal.set(Calendar.YEAR, curryear);
			cal.set(Calendar.MONTH, currmonth);
			cal.set(Calendar.DATE, 1);

			weekDay = cal.get(Calendar.DAY_OF_WEEK);
			totDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			dataGrid = (GridView) view.findViewById(R.id.monthGrid_month);
			DateAdapter dateAdapter = new DateAdapter();
			dataGrid.setAdapter(dateAdapter);
			dateAdapter.notifyDataSetChanged();
			txtCurrMonth.setText(Constants.monthNames[currmonth]);
			dataGrid.setOnItemClickListener(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		try {
			/// Opening a dialog box of current Details of content...
			DialogFrag dg = new DialogFrag();
			dg.show(getFragmentManager(),"Tag for this");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class DateAdapter extends BaseAdapter {
		int lenghtofgrid = 49;

		@Override
		public int getCount() {
			return lenghtofgrid;
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
			// inflating the view and fill the dates based on the months...
			final DateViewHolder dateViewHolder;
			try {
				if (convertView == null) {
					LayoutInflater vi = (LayoutInflater) getActivity()
							.getLayoutInflater();
					convertView = vi.inflate(R.layout.month_sub_single_grid,
							null);
					dateViewHolder = new DateViewHolder();
					dateViewHolder.weekName = (TextView) convertView
							.findViewById(R.id.weekName);
					dateViewHolder.date = (TextView) convertView
							.findViewById(R.id.month_date);
					dateViewHolder.eventView = (RelativeLayout) convertView
							.findViewById(R.id.eventView);
					convertView.setTag(dateViewHolder);
				} else {
					dateViewHolder = (DateViewHolder) convertView.getTag();
				}

				dateViewHolder.weekName.setVisibility(View.VISIBLE);
				dateViewHolder.eventView.setBackgroundColor(Color.WHITE);

				switch (position) {
				case 0:
					dateViewHolder.weekName.setText("SUN");
					dateViewHolder.eventView.setVisibility(View.GONE);
					break;
				case 1:
					dateViewHolder.weekName.setText("MON");
					dateViewHolder.eventView.setVisibility(View.GONE);
					break;
				case 2:
					dateViewHolder.weekName.setText("TUE");
					dateViewHolder.eventView.setVisibility(View.GONE);
					break;
				case 3:
					dateViewHolder.weekName.setText("WED");
					dateViewHolder.eventView.setVisibility(View.GONE);
					break;
				case 4:
					dateViewHolder.weekName.setText("THU");
					dateViewHolder.eventView.setVisibility(View.GONE);
					break;
				case 5:
					dateViewHolder.weekName.setText("FRI");
					dateViewHolder.eventView.setVisibility(View.GONE);
					break;
				case 6:
					dateViewHolder.weekName.setText("SAT");
					dateViewHolder.eventView.setVisibility(View.GONE);
					break;

				default:
					dateViewHolder.weekName.setVisibility(View.GONE);
					dateViewHolder.eventView.setVisibility(View.VISIBLE);
					fillDates(dateViewHolder, position);
					break;

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return convertView;
		}

		private void fillDates(DateViewHolder dateViewHolder, int pos)
				throws Exception {

			if ((pos - 7) + 1 < weekDay) {

				dateViewHolder.date.setText("");

			} else if (((pos - 7) + 1 - weekDay) >= totDays) {

				dateViewHolder.date.setText("");

			} else {

				dateViewHolder.date.setText(""
						+ ((pos - 7) + 1 - (weekDay - 1))); // Set dates
			}
		}
	}

	private class DateViewHolder {
		TextView weekName;
		TextView date;
		RelativeLayout eventView;
	}
}
