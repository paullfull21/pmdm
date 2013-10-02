package com.dam.pmdm.realizarllamada;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class LlamadaActivity extends Activity {

	ImageButton btnDial;
	EditText telefonoUsuario;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamada);

        //Rescato el objeto asociado al bot�n de dial, mediante el "id" R.id.buttonDial
        btnDial = (ImageButton) findViewById(R.id.buttonDial);
        
        // Idem para el tel�fono introducido por el usuario.
        telefonoUsuario = (EditText) findViewById(R.id.phoneNumber);

        
        // Gestiono el evento click del bot�n de dial
        btnDial.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Defino un Intent que realice la llamada sobre el n�mero de tel�fono escrito
				// por el usuario
				
				// El par�metro "tel:" de la acci�n ACTION_DIAL, es para indicar el n�mero de tel�fono.
				// Si quisi�ramos llamar a un tel�fono predefinido, p.e. 112 de emergencia:
				// Intent intentLlamada = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:112"));
				
				String telefonoLlamada = telefonoUsuario.getText().toString();
				Intent intentLlamada = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+telefonoLlamada));
				startActivity(intentLlamada);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.llamada, menu);
        return true;
    }
    
}
