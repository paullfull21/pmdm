package com.dam.pmdm.resultadosuma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class SumadorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Rescatar el intent que lanz� este Activity, para obtener
		//los par�metros (operando1 y operando2) que se a�adieron 
		//al intent
		Bundle datos = getIntent().getExtras();
		String operandoUno = datos.getString("opUno");
		String operandoDos = datos.getString("opDos");
		
		if(operandoUno==null || operandoDos==null) {
			Intent resultado = new Intent(); 
			resultado.putExtra("resultadoSuma", 0 );
			
			//con el m�todo setResult devuelvo una respuesta al activity
			//MainActivity que lanz� este activity. La respuesta ser� recibida
			//en el m�todo onActivityResult del MainActivity.
			setResult(RESULT_CANCELED, resultado);
			
			//Invoco al m�todo finish para destruir esta actividad.
			finish();	
		} else {
			int resultadoSuma = Integer.parseInt(operandoUno)+Integer.parseInt(operandoDos);
		
			Intent resultado = new Intent(); 
			resultado.putExtra("resultadoSuma", resultadoSuma );
			setResult(RESULT_OK, resultado);
			finish();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sumador, menu);
		return true;
	}

}