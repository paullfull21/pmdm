package com.miguelcr.maps;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements
		OnMarkerClickListener, OnMarkerDragListener, OnMapClickListener {

	private GoogleMap map;
	static final LatLng SALESIANOS_TRIANA = new LatLng(37.380346, -6.007743);
	private Marker marker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Configuraci—n del Mapa
		map = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.animateCamera(CameraUpdateFactory.zoomTo(15f));

		// A–adimos un marcador al mapa
		addMarker();

		map.setOnMarkerClickListener(this);
		map.setOnMarkerDragListener(this);
		
		// Evento clic sobre el mapa
		map.setOnMapClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	@Override
	public boolean onMarkerClick(Marker marker) {
		marker.showInfoWindow();
		return true;
	}

	@Override
	public void onMarkerDrag(Marker marker) {

	}

	@Override
	public void onMarkerDragEnd(Marker marker) {
		// Actualizo la informaci—n del marcador
		marker.setSnippet(getCity(marker.getPosition()));
		marker.showInfoWindow();

	}

	@Override
	public void onMarkerDragStart(Marker marker) {

	}

	public void addMarker() {

		marker = map.addMarker(new MarkerOptions()
				.position(SALESIANOS_TRIANA)
				.title("Marcador")
				.snippet(getCity(SALESIANOS_TRIANA))
				.draggable(true)
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher)));
	}


	public String getCity(LatLng posicion) {
		Geocoder geocoder;
		List<Address> addresses;
		String city = "";
		geocoder = new Geocoder(this, Locale.getDefault());
		try {
			addresses = geocoder.getFromLocation(posicion.latitude,
					posicion.longitude, 1);

			city = addresses.get(0).getAddressLine(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return city;
	}

	@Override
	public void onMapClick(LatLng posicionMapa) {
		marker.setPosition(posicionMapa);
		
		String nuevaCiudad = getCity(posicionMapa);
		marker.setSnippet(nuevaCiudad);
		
		marker.showInfoWindow();
		
		
	}
}
