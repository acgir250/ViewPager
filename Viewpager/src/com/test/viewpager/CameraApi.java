package com.test.viewpager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraApi extends Activity {
	private Camera cameraobject;
	private ImageView imageview;
	private ShowSurfaceCamera showSurfaceCamera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_api);
		imageview = (ImageView) findViewById(R.id.imageView1);
		cameraobject = isCameraAvailable();
		showSurfaceCamera = new ShowSurfaceCamera(this, cameraobject);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(showSurfaceCamera);
	}

	public void snapIt(View view) {
		cameraobject.takePicture(null, null, capturedIt);
	}

	public static Camera isCameraAvailable() {
		Camera object = null;
		try {
			object = Camera.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	private PictureCallback capturedIt = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {

			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			if (bitmap == null) {
				Toast.makeText(getApplicationContext(), "not taken",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "taken",
						Toast.LENGTH_SHORT).show();
			}
			imageview.setImageBitmap(bitmap);
			cameraobject.release();
		}
	};
}
