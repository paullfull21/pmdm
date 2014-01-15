package com.dam.sensoresexamples;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SensoresActivity extends Activity implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mAccelerometer, mSensorLight;
	TextView x, y, z;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensores);

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		x = (TextView) findViewById(R.id.coordX);
		y = (TextView) findViewById(R.id.coordY);
		z = (TextView) findViewById(R.id.coordZ);
	}

	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
		mSensorManager.registerListener(this, mSensorLight,
				SensorManager.SENSOR_DELAY_NORMAL);

	}

	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	/*
	 * El metodo onAccuracyChanged() es llamado cuando la precision del sensor
	 * cambia, mientras que el metodo onSensorChanged() se invoca cuando los
	 * valores medidos por el sensor cambian.
	 */
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		switch (sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			// x.setText("X: " + accuracy);
			// y.setText("Y: " + accuracy);
			// z.setText("Z: " + accuracy);
			break;
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

			switch (event.sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				x.setText("X: " + event.values[0]); // coordenada X
				y.setText("Y: " + event.values[1]); // coordenada Y
				z.setText("Z: " + event.values[2]); // coordenada Z

				// Muestro los valores por el Logcat
				Log.i("ACELEROMETRO", "" + event.values[0] + ","
						+ event.values[1] + "," + event.values[2]);
				break;
			case Sensor.TYPE_LIGHT:
				
				// Tendriamos que implementar el tratamiento del cambios de valores
				//
				break;
			}

	}
}
