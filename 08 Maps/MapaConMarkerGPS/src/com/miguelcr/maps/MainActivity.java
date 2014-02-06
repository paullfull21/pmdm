package com.miguelcr.maps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements LocationListener {

	GoogleMap mapa;
	LocationManager locationManager;
	ProgressDialog pd;
	Marker marcadorPosicion = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mapa = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		pd = ProgressDialog
				.show(this, "Localizaci�n", "Esperando localizaci�n");

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// Recordatorio: en este m�todo indicamos la frecuencia con la
		// que obtenemos una nueva posici�n.
		// En este caso cada 1segundo y que me haya desplazado 1 metro
		// obtendr� una nueva localizaci�n
		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 2000, 20, this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_type_map_normal:
			mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		case R.id.action_type_map_hybrid:
			mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			break;
		case R.id.action_type_map_satellite:
			mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			break;
		case R.id.action_type_map_terrain:
			mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLocationChanged(Location posicion) {
		LatLng latitudLongitud = new LatLng(posicion.getLatitude(),
				posicion.getLongitude());

		// Hago desaparecer del interfaz de usuario el ProgressDialog
		pd.dismiss();

		if (marcadorPosicion == null) {
			// 
			marcadorPosicion = mapa.addMarker(new MarkerOptions()
					.position(latitudLongitud)
					.title("Mi ubicaci�n")
					.snippet(
							"lat:" + posicion.getLatitude() + ",lon:"
									+ posicion.getLongitude())
					.draggable(true)
					// .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.ic_launcher)));
			
			mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(latitudLongitud,
					13));
		} else {
			marcadorPosicion.setPosition(latitudLongitud);
			
			mapa.animateCamera(CameraUpdateFactory.newLatLng(latitudLongitud));
		}
		
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		Intent intent = new Intent(
				android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(intent);

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}