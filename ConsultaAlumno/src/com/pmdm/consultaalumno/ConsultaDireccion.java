package com.pmdm.consultaalumno;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class ConsultaDireccion extends Activity {

	private ArrayList<String> direcciones;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Rellenar el array con direcciones
		direcciones = new ArrayList<String>();
		direcciones.add("C/ Salado, 3");
		direcciones.add("C/ Condes de Bustillo, 17");
		
		// Rescatar el n� de alumno por el que se
		// ha consultado
		Bundle datos =  getIntent().getExtras();
		String numeroAlumno = datos.getString("numAlumno");
		
		if(numeroAlumno.equals("")) {
			Intent resultado = new Intent(ConsultaDireccion.this,MainActivity.class);
			setResult(RESULT_CANCELED, resultado);
		} else  {
			int posicion = Integer.parseInt(numeroAlumno);
			String nombreApAlumno = direcciones.get(posicion);
			
			// Generar un resultado (recordamos que este
			// Activity fue lanzado con startActivityForResult,
			// es decir, que el MainActivity se qued� esperando
			// un resultado
			
			Intent resultado = new Intent(ConsultaDireccion.this,MainActivity.class);
			resultado.putExtra("resultado", nombreApAlumno);
			setResult(RESULT_OK, resultado);
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consulta_direccion, menu);
		return true;
	}

}
