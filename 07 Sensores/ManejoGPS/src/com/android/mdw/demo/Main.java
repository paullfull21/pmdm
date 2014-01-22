package com.android.mdw.demo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class Main extends Activity implements LocationListener {

	private TextView textGps = null;
	private ProgressDialog pd = null;
	LocationManager locationManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textGps = (TextView) findViewById(R.id.textGps);
		pd = ProgressDialog.show(this, "Localizaci�n",
				"Esperando localizaci�n GPS");

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// Proveedor de localizaci�n: antena GPS
		// Frecuencia de actualizaci�n de posici�n: 6000ms = 6segundos
		// Distancia recorrida desde la �ltima actualizaci�n : como m�nimo 50 metros

		// Cada 6 segundos y siempre que nos hayamos desplazado una distancia de
		// 50 metros desde
		// la �ltima localizaci�n, la localizaci�n se actualiza. Esto permite
		// establecer 2 condiciones
		// para evitar que est� continuamente actualiz�ndose y se consuman
		// recursos innecesariamente.
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				6000, 20, this);

	}

	/* IMPLEMENTAMOS LOS M�TODOS DE LA INTERFAZ LocationListener */

	// Este m�todo es invocado por requestLocationUpdates, cada vez que se cumplen
	// las 2 condiciones: tiempo entre consultas y distancia recorrida desde la �ltima posici�n conocida.
		
	@Override
	public void onLocationChanged(Location location) {
		
		// Modifico el contenido del TextView del layout con las coordenadas nuevas.
		
		textGps.setText("lat,lon: " + String.valueOf(location.getLatitude())
				+ "," + String.valueOf(location.getLongitude()));

		// Hago desaparecer del interfaz de usuario el ProgressDialog
		pd.dismiss();

	}

	// Se invoca cada vez que se desactiva un proveedor de Localizaci�n: GPS, Wifi,...
	
	@Override
	public void onProviderDisabled(String provider) {
		// Con el siguiente intent, invocamos a la p�gina de configuraci�n en la que el usuario
		// puede activar el GPS u otro modo de localizaci�n.
		Intent intent = new Intent(
				android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(intent);
	}
	
	// Se invoca cada vez que se activa un proveedor de Localizaci�n: GPS, Wifi,...	

	@Override
	public void onProviderEnabled(String provider) {
//		String proveedorActual = LocationManager.NETWORK_PROVIDER;
//		if(provider.equals(LocationManager.GPS_PROVIDER) && proveedorActual.equals(LocationManager.NETWORK_PROVIDER)) {
//			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//				6000, 20, this);
//		}
	}
	
	// Se invoca cuando cambia el estado del proveedor de Localizaci�n
	// Existen 3 estados:
	// AVAILABLE: disponible
	// OUT_OF_SERVICE: servicio no disponible
	// TEMPORARILY_UNAVAILABLE: temporalmente (se espera que se restablezca en breve) no disponible.

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}

}