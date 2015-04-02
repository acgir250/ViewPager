package com.test.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

public class CutomPagerAdapter extends PagerAdapter {
	private Context mcontext = null;
	private LayoutInflater layoutInflater = null;

	public CutomPagerAdapter(Context context) {
		mcontext = context;
		layoutInflater = (LayoutInflater) mcontext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		return super.instantiateItem(container, position);
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		super.destroyItem(container, position, object);
	}
}
