package com.pmdm.consultaalumno;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class ConsultaAlumno extends Activity {
	
	private ArrayList<String> alumnos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Rellenar el array con alumnos
		alumnos = new ArrayList<String>();
		alumnos.add("Pepe P�rez");
		alumnos.add("Mar�a Garc�a");
		
		// Rescatar el n� de alumno por el que se
		// ha consultado
		Bundle datos =  getIntent().getExtras();
		String numeroAlumno = datos.getString("numAlumno");
		
		if(numeroAlumno.equals("")) {
			Intent resultado = new Intent(ConsultaAlumno.this,MainActivity.class);
			setResult(RESULT_CANCELED, resultado);
		} else  {
			int posicion = Integer.parseInt(numeroAlumno);
			String nombreApAlumno = alumnos.get(posicion);
			
			// Generar un resultado (recordamos que este
			// Activity fue lanzado con startActivityForResult,
			// es decir, que el MainActivity se qued� esperando
			// un resultado
			
			Intent resultado = new Intent(ConsultaAlumno.this,MainActivity.class);
			resultado.putExtra("resultado", nombreApAlumno);
			setResult(RESULT_OK, resultado);
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consulta_alumno, menu);
		return true;
	}

}
