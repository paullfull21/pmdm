package com.dam.pmdm.tomarfotosimple;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	int TESTIGO_TOMAR_FOTO = 0;
	public static int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// here,we are making a folder named picFolder to store pics taken by
		// the camera using this application
		final String dir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
				+ "/fotosDAM/";
		File newdir = new File(dir);
		newdir.mkdirs();

		Button capture = (Button) findViewById(R.id.buttonPhoto);
		capture.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// incrementamos el contador para que el nœmero de foto no sea
				// el mismo
				count++;
				String file = dir + count + ".jpg";
				File newfile = new File(file);
				try {
					newfile.createNewFile();
				} catch (IOException e) {
				}

				Uri outputFileUri = Uri.fromFile(newfile);

				Intent cameraIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

				startActivityForResult(cameraIntent, TESTIGO_TOMAR_FOTO);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == TESTIGO_TOMAR_FOTO && resultCode == RESULT_OK) {
			File imgFile = new File(
					Environment
							.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
							+ "/fotoDAM/" + count + ".jpg");
			if (imgFile.exists()) {

				Bitmap myBitmap = BitmapFactory.decodeFile(imgFile
						.getAbsolutePath());

				ImageView myImage = (ImageView) findViewById(R.id.imagenTomada);
				myImage.setImageBitmap(myBitmap);

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
