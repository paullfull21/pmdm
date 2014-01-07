package com.dam.cameralistdevice;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class ListCameraActivity extends Activity {

	TextView info;
	int numCameras;
	Camera cam;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_camera);
        
        info = (TextView)findViewById(R.id.infoCamara);
        info.setText("");
        
        // Con el siguiente m�todo obtengo el n� de c�maras que tiene el dispositivo
        numCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo camInfo = new Camera.CameraInfo(); 
        
        for(int idCam=0; idCam<numCameras; idCam++) {
        	// Obtengo la informacion de la camara actual
        	Camera.getCameraInfo(idCam, camInfo);
        	
        	info.append("Camara "+idCam+": ");
        	if(camInfo.facing==Camera.CameraInfo.CAMERA_FACING_FRONT) {
        		//Si quisiera abrir la camara frontal lo haria pasandole el id de esa camara.
        		//cam = Camera.open(idCam);
        		
        		info.append(" camara frontal\n");
        	} else {
        		//Si quisiera abrir la camara trasera lo haria pasandole el id de esa camara.
        		//cam = Camera.open(idCam);
        		
        		info.append(" camara trasera\n");
        	}
        	
        }
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_camera, menu);
        return true;
    }
}
