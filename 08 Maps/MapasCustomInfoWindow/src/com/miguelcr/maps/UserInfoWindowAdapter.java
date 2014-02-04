package com.miguelcr.maps;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.miguelcr.maps.R;

public class UserInfoWindowAdapter implements InfoWindowAdapter {
	LayoutInflater inflater=null;
	
	UserInfoWindowAdapter(LayoutInflater inflater) {
	    this.inflater=inflater;
	  }	
	
	@Override
	public View getInfoWindow(Marker arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getInfoContents(Marker marker) {
		
	    View infoWindows=inflater.inflate(R.layout.user_info_windows, null);

	    TextView title = (TextView)infoWindows.findViewById(R.id.title);
	    TextView description = (TextView)infoWindows.findViewById(R.id.snippet);

	    title.setText(marker.getTitle());
	    description.setText(marker.getSnippet());

	    return(infoWindows);
	}

}
