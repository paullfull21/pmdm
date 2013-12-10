package jodroid.livecam;

import java.io.IOException;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Authors :
 * - Tom Gibara (the original Preview class : http://www.tomgibara.com/android/camera-source)
 * - Neil Davies (modified version with updated Android classes : http://www.inter-fuser.com/2009/09/live-camera-preview-in-android-emulator.html)
 * - Joan Reynaud (the forcePreviewSize call) 
 */
class Preview extends SurfaceView implements SurfaceHolder.Callback {
	
	private static final String TAG = "Preview";
	
	SurfaceHolder mHolder;
	//Camera mCamera;
	SocketCamera mCamera;

	Preview(Context context) {
		super(context);

		// Install a SurfaceHolder.Callback so we get notified when the
		// underlying surface is created and destroyed.
		mHolder = getHolder();
		mHolder.addCallback(this);
		//mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		Log.d(TAG, "Surface created.");
		// The Surface has been created, acquire the camera and tell it where
		// to draw.
		//mCamera = Camera.open();
		mCamera = SocketCamera.open();
		try {
			mCamera.setPreviewDisplay(holder);
		} catch (IOException exception) {
			mCamera.release();
			mCamera = null;
			// TODO: add more exception handling logic here
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		if (mCamera != null) {
			mCamera.forcePreviewSize(width, height);
			mCamera.startPreview();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface destroyed.");
		if (mCamera != null) {
			mCamera.release();
		}
	}
}