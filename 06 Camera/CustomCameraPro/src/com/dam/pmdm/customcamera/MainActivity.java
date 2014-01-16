package com.dam.pmdm.customcamera;

import java.io.IOException;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements SurfaceHolder.Callback {
	Button btnSwitchCamara;
	SurfaceView surface;
	boolean isPreview = false;
	Camera camara;
	SurfaceHolder gestorSurface;
	int camaraToOpen = Camera.CameraInfo.CAMERA_FACING_BACK;
	private byte[] rgbbuffer = new byte[256 * 256];
	private int[] rgbints = new int[256 * 256];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Ocultar ActionBar
		getActionBar().hide();

		// Configuración Ventana
		getWindow().setFormat(PixelFormat.TRANSLUCENT);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		surface = (SurfaceView) findViewById(R.id.surfaceView);
		gestorSurface = surface.getHolder();
		gestorSurface.addCallback(this);

		btnSwitchCamara = (Button) findViewById(R.id.buttonSwitch);

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		camara.startPreview();

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		abrirCamara();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		camara.stopPreview();
		camara.release();
		camara = null;
	}

	public void switchCamera(View v) {
		if (camaraToOpen == Camera.CameraInfo.CAMERA_FACING_BACK) {
			camara.stopPreview();
			camara.release();
			camara = null;
			camaraToOpen = Camera.CameraInfo.CAMERA_FACING_FRONT;
			btnSwitchCamara.setText("Trasera");
		} else {
			camara.stopPreview();
			camara.release();
			camara = null;
			camaraToOpen = Camera.CameraInfo.CAMERA_FACING_BACK;
			btnSwitchCamara.setText("Frontal");
		}

		abrirCamara();
	}

	public void abrirCamara() {
		// Con el siguiente método obtengo el nº de cámaras que tiene el
		// dispositivo
		int numCameras = Camera.getNumberOfCameras();
		Camera.CameraInfo camInfo = new Camera.CameraInfo();


		if (numCameras == 1) {
			camara = Camera.open();
		} else {
			for (int idCam = 0; idCam < numCameras; idCam++) {
				// Obtengo la informacion de la camara actual
				Camera.getCameraInfo(idCam, camInfo);

				if (camInfo.facing == camaraToOpen) {
					camara = Camera.open(idCam);
				}

			}
		}

		try {

			// Configurar orientación de la cámara
			if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {

				camara.setDisplayOrientation(90);

			} else {

				camara.setDisplayOrientation(0);

			}

			camara.setPreviewDisplay(gestorSurface);
			camara.startPreview();

		} catch (IOException exception) {
			camara.release();
			camara = null;
		}

	}

}
