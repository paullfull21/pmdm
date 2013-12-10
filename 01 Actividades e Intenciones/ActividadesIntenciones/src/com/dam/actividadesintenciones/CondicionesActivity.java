package com.dam.actividadesintenciones;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CondicionesActivity extends Activity {

	private TextView texto;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	   setContentView(R.layout.condiciones);
	   
	   Bundle datos = getIntent().getExtras();
	   String nombreUsuario = datos.getString("nombre");
	   
	   texto = (TextView)findViewById(R.id.textoPersonalizado);
	   texto.setText(nombreUsuario);
	   
	}

}
