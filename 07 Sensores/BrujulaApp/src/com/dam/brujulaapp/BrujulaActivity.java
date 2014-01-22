package com.dam.brujulaapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class BrujulaActivity extends Activity implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mSensorMagnetico, mSensorAcelerometro;
	private TextView readingAzimuth, readingPitch, readingRoll;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_brujula);

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mSensorMagnetico = mSensorManager
				.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		mSensorAcelerometro = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		readingAzimuth = (TextView) findViewById(R.id.azimuth);
		readingPitch = (TextView) findViewById(R.id.pitch);
		readingRoll = (TextView) findViewById(R.id.roll);
	}

	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mSensorAcelerometro,
				SensorManager.SENSOR_DELAY_NORMAL);
		mSensorManager.registerListener(this, mSensorMagnetico,
				SensorManager.SENSOR_DELAY_NORMAL);

	}

	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] matrixR = new float[9];
		float[] matrixI = new float[9];

		float[] valuesAcelerometro = new float[3];
		float[] valuesMagnetico = new float[3];
		float[] valuesOrientacion = new float[3];

		switch (event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			// Log.e("Accelerometer", "Accelerometer Changed");
			valuesAcelerometro = event.values.clone();
			break;

		case Sensor.TYPE_MAGNETIC_FIELD:
			// Log.e("Magnetif", "Magnetif Field Changed");
			valuesMagnetico = event.values.clone();
			break;
		}

		if (valuesAcelerometro != null && valuesMagnetico != null) {
			Log.e("Entra", "Changed");

		SensorManager.getRotationMatrix(matrixR, null, valuesAcelerometro, valuesMagnetico);

		SensorManager.getOrientation(matrixR, valuesOrientacion);

		 Log.e("0", String.valueOf(valuesOrientacion[0]));
		 Log.e("1", String.valueOf(valuesOrientacion[1]));
		 Log.e("2", String.valueOf(valuesOrientacion[2]));
		
		double azimuth = Math.toDegrees(valuesOrientacion[0]);
		double pitch = Math.toDegrees(valuesOrientacion[1]);
		double roll = Math.toDegrees(valuesOrientacion[2]);

		readingAzimuth.setText("Azimuth: " + String.valueOf(azimuth));
		readingPitch.setText("Pitch: " + String.valueOf(pitch));
		readingRoll.setText("Roll: " + String.valueOf(roll));
		

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_brujula, menu);
		return true;
	}
}
