package com.test.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DialogFrag extends DialogFragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		LayoutInflater layoutInflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.customdialog_month, null);
	
		return view;

	}

}
