package com.dam.pmdm.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ActivitySecundario extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.layout_secundario);
	    
	    Bundle extras = getIntent().getExtras();
	    String nombre = extras.getString("nombre");
	    String apellidos = extras.getString("apellidos");
	    
	    Log.i("NOMBRE:",""+nombre);
	    Log.i("APELLIDOS:",""+apellidos);
	    
	   // TextView texto = (TextView) findViewById(R.id.textViewNombreApellidos);
	    //texto.setText(nombre+", "+apellidos);
	    
	}

}
