package com.dam.pmdm.infoalumno;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	//1� Declarar las variables del layout: bot�n, edittext,...
	// Y adem�s deber� definir las acciones que pueden enviar una
	//respuesta a este activity.
	
	Button btnConsultaNombre, btnConsultaDir;
	EditText numeroAlumno;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        
        
    }

    
    //El m�todo onActivityResult recibe:
    //requestCode: el testigo que yo pas�
    //resultCode: resultado correcto (RESULT_OK) o err�neo (RESULT_CANCELED)
    //data: contiene el Intent que lanz� el resultado con todos los par�metros
 
    
}
