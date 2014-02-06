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
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.miguelcr.maps.R;

public class MainActivity extends FragmentActivity {
	
	private GoogleMap mMap;
	static final LatLng SALESIANOS_TRIANA = new LatLng(37.380346, -6.007743);
	static final LatLng CALLE_SAN_JACINTO = new LatLng(37.380346, -6.007743);


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//configuraci�n del mapa
		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		mMap.animateCamera(CameraUpdateFactory.zoomTo(13f));
		
		Geocoder geocoder;
		List<Address> addresses;
		String city = "";
		geocoder = new Geocoder(this, Locale.getDefault());
		try {
			addresses = geocoder.getFromLocation(SALESIANOS_TRIANA.latitude, SALESIANOS_TRIANA.longitude, 1);
			city = addresses.get(0).getAddressLine(0);
			//String calle = addresses.get(0).getAddressLine(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mMap.addMarker(new MarkerOptions()
        .position(SALESIANOS_TRIANA)
        .title("Salesianos Triana")
        .snippet(city)
        .draggable(true)
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
		
		mMap.addMarker(new MarkerOptions()
        .position(CALLE_SAN_JACINTO)
        .title("Calle San Jacinto")
        .snippet(city)
        .draggable(true)
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
		
		// Asocio a los InfoWindow del Mapa el Adaptador personalizado que he creado.
		mMap.setInfoWindowAdapter(new UserInfoWindowAdapter(getLayoutInflater()));
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}