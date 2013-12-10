package jodroid.livecam;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Authors :
 * - Tom Gibara (the original Socket Camera : http://www.tomgibara.com/android/camera-source)
 * - Neil Davies (modified version with updated Android classes : http://www.inter-fuser.com/2009/09/live-camera-preview-in-android-emulator.html)
 * - Joan Reynaud (forcePreviewSize, using the setParameters generate an exception so I had to add a specific method)
 */
public class SocketCamera {

	private static final String LOG_TAG = "SocketCamera";
	private static final int SOCKET_TIMEOUT = 1000;

	static private SocketCamera socketCamera;
	private CameraCapture capture;
	private Camera parametersCamera;
	private SurfaceHolder surfaceHolder;

	//Set the IP address of your pc here!! (be careful 127.0.0.1 means the emutor itself, not your localhost)
//	private final String address = "10.0.2.2"; // localhost onto which the emulator is running
//	private final String address = "192.168.1.35";
	private final String address = "10.0.2.2";
	private final int port = 8080;

	private final boolean preserveAspectRatio = true;
	private final Paint paint = new Paint();


	private int width = 240;
	private int height = 320;
	private Rect bounds = new Rect(0, 0, width, height);

	private SocketCamera() {
		//Just used so that we can pass Camera.Parameters in getters and setters
		parametersCamera = Camera.open();
	}

	static public SocketCamera open()
	{
		Log.d(LOG_TAG, "Creating Socket Camera");
		if (socketCamera == null) {
			socketCamera = new SocketCamera();
		}
		return socketCamera;
	}

	public void startPreview() {
		capture = new CameraCapture();
		capture.setCapturing(true);
		capture.start(); 
		Log.d(LOG_TAG, "Starting Socket Camera");

	}

	public void stopPreview(){
		capture.setCapturing(false);
		Log.d(LOG_TAG, "Stopping Socket Camera");
	}

	public void setPreviewDisplay(SurfaceHolder surfaceHolder) throws IOException {
		this.surfaceHolder = surfaceHolder;
	}

	public void setParameters(Camera.Parameters parameters) {
		//Bit of a hack so the interface looks like that of
		Log.d(LOG_TAG, "Setting Socket Camera parameters");
		parametersCamera.setParameters(parameters);
		Size size = parameters.getPreviewSize();
		bounds = new Rect(0, 0, size.width, size.height);
	}
	public Camera.Parameters getParameters() { 
		Log.d(LOG_TAG, "Getting Socket Camera parameters");
		return parametersCamera.getParameters(); 
	} 

	public void release() {
		Log.d(LOG_TAG, "Releasing Socket Camera parameters");
		stopPreview();
	} 
	
	public void forcePreviewSize(int w, int h) {
		bounds = new Rect(0, 0, w, h);
	}


	private class CameraCapture extends Thread  {

		private boolean capturing = false;

//		public boolean isCapturing() {
//			return capturing;
//		}

		public void setCapturing(boolean capturing) {
			this.capturing = capturing;
		}

		@Override
		public void run() {
			while (capturing) {
				Canvas c = null;
				try {
					c = surfaceHolder.lockCanvas(null);
					synchronized (surfaceHolder) {
						Socket socket = null;
						try {
							socket = new Socket();
							socket.bind(null);
							socket.setSoTimeout(SOCKET_TIMEOUT);
							socket.connect(new InetSocketAddress(address, port), SOCKET_TIMEOUT);

							//obtain the bitmap
							InputStream in = socket.getInputStream();
							Bitmap bitmap = BitmapFactory.decodeStream(in);

							//render it to canvas, scaling if necessary
							if (
									bounds.right == bitmap.getWidth() &&
									bounds.bottom == bitmap.getHeight()) {
								c.drawBitmap(bitmap, 0, 0, null);
							} else {
								Rect dest;
								if (preserveAspectRatio) {
									dest = new Rect(bounds);
									dest.bottom = bitmap.getHeight() * bounds.right / bitmap.getWidth();
									dest.offset(0, (bounds.bottom - dest.bottom)/2);
								} else {
									dest = bounds;
								}
								if (c != null)
								{ 
									c.drawBitmap(bitmap, null, dest, paint);
								}
							}

						} catch (RuntimeException e) {
							Log.d(LOG_TAG, "RE:"+e.getMessage());
							e.printStackTrace();

						} catch (IOException e) {
							Log.d(LOG_TAG, "IO"+e.getMessage());
							e.printStackTrace();
						} finally {
							try {
								socket.close();
							} catch (IOException e) {
								/* ignore */
							}
						}
					}
				} catch (Exception e) {
					Log.d(LOG_TAG, "E:"+e.getMessage());
					e.printStackTrace();
				} finally {

					// do this in a finally so that if an exception is thrown
					// during the above, we don't leave the Surface in an
					// inconsistent state
					if (c != null) {
						surfaceHolder.unlockCanvasAndPost(c);
					}
				}
			}
			Log.d(LOG_TAG, "Socket Camera capture stopped");
		}
	}

}