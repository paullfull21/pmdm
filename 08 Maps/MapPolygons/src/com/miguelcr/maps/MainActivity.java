package com.miguelcr.maps;

import java.util.List;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.miguelcr.maps.R;

public class MainActivity extends FragmentActivity {
	
	private GoogleMap mMap;
	static final LatLng CIRCUITO_JEREZ = new LatLng(37.35, -122.0);


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		
		
		mMap.animateCamera(CameraUpdateFactory.zoomTo(15f));
		//mMap.animateCamera(CameraUpdateFactory.scrollBy(10, 0));
		
		mMap.addMarker(new MarkerOptions()
        .position(CIRCUITO_JEREZ)
        .title("Circuito de Jerez")
        .snippet("PMDM")
        .draggable(true)
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		
		PolygonOptions poligonoOptions = new PolygonOptions()
        .add(new LatLng(36.712742,-6.03128))
        .add(new LatLng(36.712312,-6.031258))
        .add(new LatLng(36.711814,-6.03188))
        .add(new LatLng(36.711452,-6.032073))
        .add(new LatLng(36.710059,-6.03188))
        .add(new LatLng(36.70956,-6.031408))
        .add(new LatLng(36.709337,-6.030807))
        .add(new LatLng(36.709044,-6.027782))
        .add(new LatLng(36.708545,-6.027246))
        .add(new LatLng(36.70796,-6.027288))
        .add(new LatLng(36.707513,-6.027696))
        .add(new LatLng(36.704124,-6.032943))
        .add(new LatLng(36.704184,-6.033361))
        .add(new LatLng(36.704606,-6.03334))
        .add(new LatLng(36.706171,-6.032406))
        .add(new LatLng(36.706842,-6.032588))
        .add(new LatLng(36.70821,-6.033919))
        .add(new LatLng(36.708373,-6.034756))
        .add(new LatLng(36.707978,-6.03555))
        .add(new LatLng(36.706128,-6.036397))
        .add(new LatLng(36.706016,-6.036955))
        .add(new LatLng(36.70661,-6.037931))
        .add(new LatLng(36.709001,-6.03659))
        .add(new LatLng(36.709173,-6.034123))
        .add(new LatLng(36.70704,-6.031913))
        .add(new LatLng(36.706963,-6.031387))
        .add(new LatLng(36.707444,-6.031301))
        .add(new LatLng(36.712355,-6.033887))
        .add(new LatLng(36.712777,-6.033404))
        .add(new LatLng(36.712742,-6.03128)); 
		// Esta �ltima coordenada coincide con la primera para cerrar el pol�gono

		// 	Definimos el color de las l�neas del pol�gono
		poligonoOptions.strokeColor(Color.MAGENTA);
		poligonoOptions.fillColor(Color.TRANSPARENT);
		poligonoOptions.strokeWidth(15);

		// A�adir el pol�gono al mapa
		mMap.addPolygon(poligonoOptions);
		
		List<List<LatLng>> puntos = poligonoOptions.getHoles();
		Log.i("Puntos",puntos.toString());
		
		CameraPosition camPos = mMap.getCameraPosition();
		
		Toast t = Toast.makeText(MainActivity.this, camPos.zoom+"",Toast.LENGTH_SHORT);
		t.show();
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}