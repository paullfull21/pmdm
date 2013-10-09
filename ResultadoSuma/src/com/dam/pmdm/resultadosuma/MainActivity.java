package com.dam.pmdm.resultadosuma;

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
	
	//Declaro las referencias a los botones de suma y resta.
	private Button botonSuma, botonResta;
	//Declaraci�nd de los EditText: operando 1, operando 2 y resultado
	private EditText opUno, opDos;
	private TextView resultOp;
	
	//Declaro 2 variables est�ticas que utilizar� como testigos
	//en el lanzamiento de los Activities.
	private static final int SUBACTIVITY_SUMADOR = 1;
	private static final int SUBACTIVITY_RESTADOR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        botonSuma = (Button) findViewById(R.id.buttonSuma);
        botonResta = (Button) findViewById(R.id.buttonResta);
        opUno = (EditText)findViewById(R.id.operandoUno);
        opDos = (EditText)findViewById(R.id.operandoDos);
        resultOp = (TextView)findViewById(R.id.resultadoOperacion);
        
        // Controlo el evento click del bot�n de suma
        botonSuma.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MainActivity.this, SumadorActivity.class); 
				//Al intent que lanza la actividad, le pasamos como par�metros
				// los valores del operando 1 y del operando 2.
				intent.putExtra("opUno", opUno.getText().toString());
				intent.putExtra("opDos", opDos.getText().toString());
				
				//En este m�todo a diferencia del startActivity, adem�s del
				//intent que define la actividad que ser� lanzada, le pasamos
				//como par�metro el testigo que me permita reconocer a la 
				//actividad cuando est� devuelva un resultado.
				startActivityForResult(intent, SUBACTIVITY_SUMADOR);
			}
		});
        
        // Controlo el evento click del bot�n de resta
        botonResta.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				opUno = (EditText)findViewById(R.id.operandoUno);
				opDos = (EditText)findViewById(R.id.operandoDos);
				
				Intent intent = new Intent(MainActivity.this, RestadorActivity.class); 
				//Al intent que lanza la actividad, le pasamos como par�metros
				// los valores del operando 1 y del operando 2.
				intent.putExtra("opUno", opUno.getText());
				intent.putExtra("opDos", opDos.getText());
				
				//En este m�todo a diferencia del startActivity, adem�s del
				//intent que define la actividad que ser� lanzada, le pasamos
				//como par�metro el testigo que me permita reconocer a la 
				//actividad cuando est� devuelva un resultado.
				startActivityForResult(intent, SUBACTIVITY_RESTADOR);
			}
		});
    }

    
    //El m�todo onActivityResult recibe:
    //requestCode: el testigo que yo pas�
    //resultCode: resultado correcto (RESULT_OK) o err�neo (RESULT_CANCELED)
    //data: contiene el Intent que lanz� el resultado con todos los par�metros
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    		// �De qu� actividad viene?
    		if (requestCode == SUBACTIVITY_SUMADOR) { 
    			// Si la respuesta es correcta...
    			if (resultCode == RESULT_OK) {
    				int suma = data.getIntExtra("resultadoSuma",0);
    				resultOp.setText(suma+"");
    			} else  {
    				Log.e("ERROR","Resultado err�neo");
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
