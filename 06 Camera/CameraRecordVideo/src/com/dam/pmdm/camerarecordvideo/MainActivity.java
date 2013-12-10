package com.dam.pmdm.camerarecordvideo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btnRecord;
	private Uri fileUri;
	public static final int MEDIA_TYPE_VIDEO = 2;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	public static TextView output;
	public static Context contexto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		contexto = MainActivity.this;

		btnRecord = (Button) findViewById(R.id.buttonRecord);

		btnRecord.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Copiado de la API.
				// http://developer.android.com/guide/topics/media/camera.html#custom-camera

				// create Intent to take a picture and return control to the
				// calling application
				Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

				fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
				//intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
				intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

				// start the image capture Intent
				startActivityForResult(intent,
						CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);

			}

			
		});
	}
	
	private Uri getOutputMediaFileUri(int mediaTypeVideo) {
		return Uri.fromFile(getOutputMediaFile(mediaTypeVideo));
	}
	
	private static File getOutputMediaFile(int type){
        
        // Comprobamos si en la SD Card existe el directorio MyCameraVideo
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                  Environment.DIRECTORY_MOVIES), "MyCameraVideo");
         
         
        // Si no existe el directorio MyCameraVideo, lo creamos.
        if (! mediaStorageDir.exists()){
             
            if (! mediaStorageDir.mkdirs()){
                // Si la creaci—n del fichero ha dado algœn error lo comunicamos.
                output.setText("Failed to create directory MyCameraVideo.");
                 
                Toast.makeText(contexto, "Failed to create directory MyCameraVideo.",
                        Toast.LENGTH_LONG).show();
                 
                Log.d("MyCameraVideo", "Failed to create directory MyCameraVideo.");
                return null;
            } 
        }
        
        mediaStorageDir.setWritable(true);
 
         
        // SI SE HA PODIDO CREAR EL DIRECTORIO
        // Ahora creamos el fichero de video.
         
        // Para crear un nombre de fichero œnico, se va a concatenar al final del 
        // fichero, la fecha y la hora a la que ha sido generado.
        java.util.Date date= new java.util.Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date.getTime());
         
        File mediaFile;
         
        if(type == MEDIA_TYPE_VIDEO) {
             
            // Generamos un nombre de fichero œnico VID_ + fechaHoraActual + .mp4
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
            "VID_"+ timeStamp + ".mp4");
             
        } else {
            return null;
        }
 
        return mediaFile;
    }
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				 AssetFileDescriptor videoAsset = null;
				try {
					videoAsset = getContentResolver().openAssetFileDescriptor(data.getData(), "r");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         FileInputStream fis = null;
				try {
					fis = videoAsset.createInputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         File videoFile = new File(Environment.getExternalStorageDirectory(),"<VideoFileName>.mp4"); 
		         FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(videoFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		         byte[] buffer = new byte[1024];
		         int length;
		         try {
					while ((length = fis.read(buffer)) > 0) {
					       fos.write(buffer, 0, length);
					  }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}       
		         try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (resultCode == RESULT_CANCELED) {
				// El usuario ha rechazado el video grabado.
			} else {
				// Video capture failed, advise user
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
