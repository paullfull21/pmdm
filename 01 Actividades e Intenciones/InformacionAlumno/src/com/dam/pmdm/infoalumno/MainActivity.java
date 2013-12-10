package com.dam.pmdm.infoalumno;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	//1º Declarar las variables del layout: botón, edittext,...
	// Y además deberé definir las acciones que pueden enviar una
	//respuesta a este activity.
	
	Button btnConsultaNombre, btnConsultaDir;
	EditText numeroAlumno;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        
        
    }

    
    //El método onActivityResult recibe:
    //requestCode: el testigo que yo pasé
    //resultCode: resultado correcto (RESULT_OK) o erróneo (RESULT_CANCELED)
    //data: contiene el Intent que lanzó el resultado con todos los parámetros
 
    
}
