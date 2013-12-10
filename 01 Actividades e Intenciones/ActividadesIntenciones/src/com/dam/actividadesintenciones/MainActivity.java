package com.dam.actividadesintenciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button btnLanzarCondiciones;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnLanzarCondiciones = (Button) findViewById(R.id.btnLanzar);
        
        btnLanzarCondiciones.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intentCondicionesActivity = new Intent(MainActivity.this,CondicionesActivity.class);
				intentCondicionesActivity.putExtra("nombre", "Pepito");
				startActivity(intentCondicionesActivity);
				
			}
		});
        
    }


}
