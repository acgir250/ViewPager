package com.test.viewpager;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ShowSurfaceCamera extends SurfaceView implements
		SurfaceHolder.Callback {
	private SurfaceHolder holdme;
	private Camera camera;

	public ShowSurfaceCamera(Context context, Camera camera) {
		super(context);
		this.camera = camera;
		this.holdme = getHolder();
		holdme.addCallback(this);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			camera.setPreviewDisplay(holder);
			
		} catch (IOException e) {
			camera.release();
			e.printStackTrace();
		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
	}

}
