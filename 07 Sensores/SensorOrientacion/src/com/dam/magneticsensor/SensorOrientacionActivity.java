package com.dam.magneticsensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class SensorOrientacionActivity extends Activity implements SensorEventListener {

	SensorManager mSensorManager;
	Sensor mMagnetico;
	Sensor mAcelerometro;
	TextView z;
	float[] valuesGravity, valuesGeomagnetic;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_magnetic);

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mMagnetico = mSensorManager
				.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		mAcelerometro = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		z = (TextView) findViewById(R.id.valueZ);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mMagnetico,
				SensorManager.SENSOR_DELAY_FASTEST);
		mSensorManager.registerListener(this, mAcelerometro,
				SensorManager.SENSOR_DELAY_FASTEST);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_magnetic, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] valuesOrientacion = new float[3];
		float[] matrixR = new float[9];
		float[] matrixI = new float[9];

		switch (event.sensor.getType()) {
		case Sensor.TYPE_MAGNETIC_FIELD:
			Log.i("Sensor magnetic", "X: " + event.values[0]);
			Log.i("Sensor magnetic", "Y: " + event.values[1]);
			Log.i("Sensor magnetic", "Z: " + event.values[2]);
			
			// Realizo una copia (clonaci�n) de los valores de las coordenadas del sensor magn�tico
			this.valuesGeomagnetic = event.values.clone();
			break;
		case Sensor.TYPE_ACCELEROMETER:
			Log.i("Sensor acelerometro", "X: " + event.values[0]);
			Log.i("Sensor acelerometro", "Y: " + event.values[1]);
			Log.i("Sensor acelerometro", "Z: " + event.values[2]);

			// Realizo una copia (clonaci�n) de los valores de las coordenadas del aceler�metro
			this.valuesGravity = event.values.clone();
			break;
		}

		if (valuesGeomagnetic != null && valuesGravity != null) {
			
			// Obtengo la matriz de Rotaci�n (matrixR) a partir de los valores del aceler�metro
			// y del sensor magn�tico
			boolean b = SensorManager.getRotationMatrix(matrixR, matrixI,
					valuesGravity, valuesGeomagnetic);

			if (b) {
				SensorManager.getOrientation(matrixR,valuesOrientacion);
				
				// En valuesOrientacion tengo las coordenadas de orientaci�n (X,Y,Z consultar
				// la documentaci�n del m�todo getOrientation() para saber en qu� posici�n est�
				// la coordenada Z).
				
				// Atenci�n!!!
				// Los valores de orientaci�n est�n indicados en RADIANES, pero a nosotros nos
				// interesa obtenerlos en grados.
				
				// Una vez tengamos la coordenada Z en grados s�lo nos quedar� saber en funci�n
				// de los grados en qu� coordenada cardinal me encuentro.
				
			}

		}

	}

}
