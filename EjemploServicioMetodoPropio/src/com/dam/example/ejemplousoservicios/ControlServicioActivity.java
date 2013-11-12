package com.dam.example.ejemplousoservicios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ControlServicioActivity extends Activity {
	Button btnStart, btnStop;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.control_servicio);

	    
	    btnStart = (Button) findViewById(R.id.buttonStart);
	    btnStop = (Button) findViewById(R.id.buttonStop);
	
	    btnStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(new Intent(ControlServicioActivity.this, ServicioMusica.class));
			}
		});
	    
	    btnStop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stopService(new Intent(ControlServicioActivity.this, ServicioMusica.class));
			}
		});
	    
	    
	}

}