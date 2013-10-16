package com.pmdm.consultaalumno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	// 1. Declaraci�n de variables
	// y definici�n de las constantes que definir� cada
	// acci�n que el usuario puede realizar
	
	private Button btnConsultaNombre, btnConsultaDir;
	private EditText numeroAlumno;
	private TextView textoResultado;
	
	public static int CONSULTA_NOMBRE = 1;
	public static int CONSULTA_DIRECCION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnConsultaNombre = (Button) findViewById(R.id.buttonConsultarNombre);
        btnConsultaDir = (Button) findViewById(R.id.buttonConsultarDir);
        numeroAlumno = (EditText) findViewById(R.id.numeroMatricula);
        
        btnConsultaNombre.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,ConsultaAlumno.class);
				i.putExtra("numAlumno", numeroAlumno.getText().toString());
				startActivityForResult(i, CONSULTA_NOMBRE);
				
			}
		});
        
        	btnConsultaDir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,ConsultaDireccion.class);
				i.putExtra("numAlumno", numeroAlumno.getText().toString());
				startActivityForResult(i, CONSULTA_DIRECCION);
				
			}
		});
       
    }
    

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==RESULT_CANCELED) {
			Log.e("ERROR","El n�mero consultado es incorrecto");
		} else {
			if(requestCode==CONSULTA_NOMBRE) {
				String nombreAp = data.getStringExtra("resultado");
				
				textoResultado = (TextView)findViewById(R.id.textView2);
				textoResultado.setText(nombreAp);
			} else if(requestCode==CONSULTA_DIRECCION) { 
				String direccion = data.getStringExtra("resultado");
				
				textoResultado = (TextView)findViewById(R.id.textView3);
				textoResultado.setText(direccion);
			}
		}
	}




	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
