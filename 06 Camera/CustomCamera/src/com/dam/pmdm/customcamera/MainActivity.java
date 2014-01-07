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
import android.widget.Button;

public class MainActivity extends Activity implements SurfaceHolder.Callback {
	Button btnCamara;
	SurfaceView surface;
	boolean isPreview = false;
	Camera camara;
	SurfaceHolder gestorSurface;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Configuraci�n Ventana
		getWindow().setFormat(PixelFormat.TRANSLUCENT);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		surface = (SurfaceView) findViewById(R.id.surfaceView);
		gestorSurface = surface.getHolder();
		gestorSurface.addCallback(this);

	}


	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		camara.startPreview();

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// Al no pasarle ning�n par�metro al m�todo open(), se abrir� la c�mara
		// por defecto
		camara = Camera.open();

		try {
			
			// Configurar orientaci�n de la c�mara
			if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {

				camara.setDisplayOrientation(90);

			} else {

				camara.setDisplayOrientation(0);

			}

			camara.setPreviewDisplay(gestorSurface);

		} catch (IOException exception) {
			camara.release();
			camara = null;
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		camara.stopPreview();
		camara.release();
		camara = null;

	}


}
